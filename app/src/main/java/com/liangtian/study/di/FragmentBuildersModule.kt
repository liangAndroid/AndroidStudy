package com.liangtian.study.di

import com.liangtian.study.ui.SearchBookFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSearchBookFragment(): SearchBookFragment
}