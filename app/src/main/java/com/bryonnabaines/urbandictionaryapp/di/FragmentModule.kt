package com.bryonnabaines.urbandictionaryapp.di

import com.bryonnabaines.urbandictionaryapp.FragmentSearch
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * created by bryonnabaines on 2020-01-08
 */

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentSearch(): FragmentSearch
}