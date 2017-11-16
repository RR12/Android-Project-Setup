package com.khairilushan.data.disk

import com.khairilushan.data.entity.RepoEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by khairil on 11/7/17.
 */
class AppDatabase @Inject constructor(githubDatabase: GithubDatabase) {

    private val mRepoDao: RepoDao by lazy { githubDatabase.getRepoDao() }

    fun getAllRepo(): Single<List<RepoEntity>> = mRepoDao.getAllRepo()

    fun findRepoByName(name: String): Single<List<RepoEntity>> = mRepoDao.findRepoByName(name)

    fun insertRepo(repoEntity: RepoEntity) {
        mRepoDao.insert(repoEntity)
    }

}