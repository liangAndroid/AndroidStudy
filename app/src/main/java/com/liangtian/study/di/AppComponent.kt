package com.liangtian.study.di

import android.app.Application
import com.liangtian.study.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by zhuliangtian on 2018/5/10.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,AppModule::class,MainAcitivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}