package com.khairilushan.data.disk

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

/**
 * Created by khairil on 11/7/17.
 */
interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: T)

    @Delete
    fun delete(data: T)

    @Update
    fun update(data: T)

}