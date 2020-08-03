package com.grevi.msx

import android.app.Application
import com.grevi.msx.network.ApiServices
import com.grevi.msx.network.NetworkInterceptor
import com.grevi.msx.repos.Repository
import com.grevi.msx.ui.PostViewModel
import com.grevi.msx.utils.PostViewModelFactory
import com.grevi.msx.utils.ViewModelFactory
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

class ApplicationModule :Application(), DIAware {
    override val di by DI.lazy {

        import(androidXModule(this@ApplicationModule))

        bind() from singleton { NetworkInterceptor(instance()) }
        bind() from singleton { ApiServices(instance()) }
        bind() from singleton { Repository(instance()) }

        bind() from provider { ViewModelFactory(instance()) }
        bind() from provider { PostViewModelFactory(instance()) }
    }
}