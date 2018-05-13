package com.liangtian.study.di

import com.liangtian.study.MainActivity
import com.liangtian.study.ui.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by zhuliangtian on 2018/5/10.
 */
@Module
abstract class MainAcitivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeSearchActivity(): SearchActivity
}