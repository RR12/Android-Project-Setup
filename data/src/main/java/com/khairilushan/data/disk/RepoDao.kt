package com.khairilushan.data.disk

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.khairilushan.data.disk.BaseDao
import com.khairilushan.data.entity.RepoEntity
import io.reactivex.Single

/**
 * Created by khairil on 11/7/17.
 */
@Dao
abstract class RepoDao : BaseDao<RepoEntity> {

    @Query("SELECT * FROM repo where name LIKE :name")
    abstract fun findRepoByName(name: String): Single<List<RepoEntity>>

    @Query("SELECT * FROM repo")
    abstract fun getAllRepo(): Single<List<RepoEntity>>

}