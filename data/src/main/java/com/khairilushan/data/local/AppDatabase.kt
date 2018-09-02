package com.khairilushan.data.local

import com.khairilushan.data.entity.RepoEntity
import javax.inject.Inject

/**
 * Created by khairil on 11/7/17.
 */
class AppDatabase @Inject constructor(githubDatabase: GithubDatabase) {

    private val mRepoDao: RepoDao by lazy { githubDatabase.getRepoDao() }

    fun getAllRepo(): List<RepoEntity> = mRepoDao.getAllRepo()

    fun findRepoByName(name: String): List<RepoEntity> = mRepoDao.findRepoByName(name)

    fun insertRepo(repoEntity: RepoEntity) {
        mRepoDao.insert(repoEntity)
    }

}
