package com.bonseyjaden.basearchitecture.helper.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.*
import com.bonseyjaden.basearchitecture.R
import javax.inject.Inject

class Navigator @Inject constructor() {

    //region Activity

    fun startActivity(activity: Activity, clazz: Class<out Activity>) {
        startActivity(activity, clazz, null)
    }

    fun startActivityAndFinish(activity: Activity, clazz: Class<out Activity>) {
        startActivity(activity, clazz, null)
        activity.finish()
    }

    fun startActivityForResult(activity: Activity, intent: Intent, requestCode: Int) {
        activity.startActivityForResult(intent, requestCode)
    }

    fun startActivity(activity: Activity, clazz: Class<out Activity>, bundle: Bundle? = null) {
        val intent = Intent(activity, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        activity.startActivity(intent)
        overridePendingTransition(activity)
    }

    fun startActivityForResult(
        context: Context, clazz: Class<out Activity>, bundle: Bundle? = null,
        requestCode: Int
    ) {
        val intent = Intent(context, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        when (context) {
            is Activity -> context.startActivityForResult(intent, requestCode)
            is Fragment -> context.startActivityForResult(intent, requestCode)
            else -> throw IllegalArgumentException("Context must be activity or fragment")
        }
    }

    fun startActivityAtRoot(activity: Activity, clazz: Class<out Activity>) {
        startActivityAtRoot(activity, clazz, null)
    }

    fun startActivityAtRoot(
        activity: Activity, clazz: Class<out Activity>,
        bundle: Bundle?
    ) {
        val intent = Intent(activity, clazz)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        activity.startActivity(intent)
        overridePendingTransition(activity)
    }

    fun bringActivityToTop(
        context: Context, clazz: Class<out Activity>,
        bundle: Bundle?
    ) {
        val intent = Intent(context, clazz)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        context.startActivity(intent)
    }

    fun startNewActivity(activity: Activity, clazz: Class<out Activity>) {
        startActivity(activity, clazz)
        activity.finish()
        overridePendingTransition(activity)
    }

    fun finishActivityWithResult(activity: Activity, intent: Intent, resultCode: Int) {
        activity.setResult(resultCode, intent)
        activity.finish()
        overridePendingTransition(activity)
    }

    fun finishActivity(activity: Activity) {
        activity.finish()
        overridePendingTransition(activity)
    }

    private fun overridePendingTransition(activity: Activity) {
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

//endregion

//region Fragment

    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int, addToBackStack: Boolean = false, tag: String? = null,
        bundle: Bundle? = null
    ) {
        val transaction = fragmentManager.beginTransaction()
        fragment.arguments = bundle
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        transaction.add(frameId, fragment, tag)
        commitTransaction(transaction, addToBackStack)
    }

    fun showFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment, addToBackStack: Boolean = false
    ) {
        val transaction = fragmentManager.beginTransaction()
        // fixme current we have a issue when using custom animation for show/hide fragment, so now we don't apply any animation here
        transaction.show(fragment)
        commitTransaction(transaction, addToBackStack)
    }

    fun hideFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment, addToBackStack: Boolean = false
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.hide(fragment)
        commitTransaction(transaction, addToBackStack)
    }

    fun getCurrentFragment(activity: Activity, @IdRes containerViewId: Int): Fragment? {
        return (activity as FragmentActivity).supportFragmentManager.findFragmentById(
            containerViewId
        )
    }

    fun findFragment(activity: Activity, TAG: String): Fragment? {
        return (activity as FragmentActivity).supportFragmentManager.findFragmentByTag(TAG)
    }

    fun findChildFragment(parentFragment: Fragment, TAG: String): Fragment? {
        return parentFragment.childFragmentManager.findFragmentByTag(TAG)
    }

    fun replaceFragment(
        activity: Activity, containerViewId: Int, fragment: Fragment, TAG: String?,
        addToBackStack: Boolean = false
    ) {
        val transaction = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        transaction.replace(containerViewId, fragment, TAG)
        commitTransaction(transaction, addToBackStack)
    }

    fun replaceChildFragment(
        parentFragment: Fragment, containerViewId: Int,
        fragment: Fragment, TAG: String?, addToBackStack: Boolean = false
    ) {
        val transaction = parentFragment.childFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        transaction.replace(containerViewId, fragment, TAG)
        commitTransaction(transaction, addToBackStack)
    }

    fun addChildFragment(
        parentFragment: Fragment, containerViewId: Int,
        targetFragment: Fragment, TAG: String?, addToBackStack: Boolean = false
    ) {
        val transaction = parentFragment.childFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        transaction.add(containerViewId, targetFragment, TAG)
        commitTransaction(transaction, addToBackStack)
    }

    fun addChildFragmentWithoutAnimation(
        parentFragment: Fragment, containerViewId: Int,
        targetFragment: Fragment, TAG: String?, addToBackStack: Boolean = false
    ) {
        val transaction = parentFragment.childFragmentManager.beginTransaction()
        transaction.add(containerViewId, targetFragment, TAG)
        commitTransaction(transaction, addToBackStack)
    }

    fun popFragment(activity: Activity) {
        (activity as FragmentActivity).supportFragmentManager.popBackStack()
    }

    fun popSpecificFragment(activity: Activity, tag: String) {
        (activity as FragmentActivity).supportFragmentManager.popBackStack(
            tag,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    fun popFragmentImmediate(activity: Activity) {
        (activity as FragmentActivity).supportFragmentManager.popBackStackImmediate()
    }

    // fixme remember update later
    fun popFragments(activity: Activity, number: Int) {
        for (i in 0..number) {
            (activity as FragmentActivity).supportFragmentManager.popBackStackImmediate()
        }
    }

    fun popChildFragment(parentFragment: Fragment) {
        parentFragment.childFragmentManager.popBackStack()
    }

    fun showDialogFragment(
        dialogFragment: DialogFragment, fragmentManager: FragmentManager,
        tag: String
    ) {
        dialogFragment.show(fragmentManager, tag)
    }

    fun startActivityFromFragment(
        activity: FragmentActivity, clazz: Class<out Activity>,
        finishActivity: Boolean = true
    ) {
        startActivity(activity, clazz)
        if (finishActivity)
            activity.finish()
        overridePendingTransition(activity)
    }

    private fun commitTransaction(
        transaction: FragmentTransaction,
        addToBackStack: Boolean = false
    ) {
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showLastFragmentInBackStack(fragment: Fragment?) {
        fragment?.childFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
//endregion
}