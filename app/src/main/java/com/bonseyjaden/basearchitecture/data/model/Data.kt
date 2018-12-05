package com.bonseyjaden.basearchitecture.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "Data")
data class Data(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    val key: String
)