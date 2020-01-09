package com.bryonnabaines.urbandictionaryapp.di

import com.bryonnabaines.urbandictionaryapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector





/**
 * created by bryonnabaines on 2020-01-08
 */

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}