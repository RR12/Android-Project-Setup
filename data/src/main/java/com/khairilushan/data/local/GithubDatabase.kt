package com.khairilushan.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.khairilushan.data.entity.RepoEntity

/**
 * Created by khairil on 11/7/17.
 */
@Database(entities = arrayOf(RepoEntity::class), version = 1)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun getRepoDao(): RepoDao

}
