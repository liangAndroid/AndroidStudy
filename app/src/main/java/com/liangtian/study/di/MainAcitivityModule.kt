package com.liangtian.study.di

import com.liangtian.study.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by zhuliangtian on 2018/5/10.
 */
@Module
abstract class MainAcitivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}