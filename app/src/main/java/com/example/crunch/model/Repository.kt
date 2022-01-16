package com.example.crunch.model

import com.example.crunch.db.LocalDataSource
import com.example.crunch.network.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataSource : RemoteDataSource, localDataSource: LocalDataSource) {

    val remote = remoteDataSource
    val local  = localDataSource

}

