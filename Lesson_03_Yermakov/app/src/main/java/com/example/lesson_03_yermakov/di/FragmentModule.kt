package com.example.lesson_03_yermakov.di

import com.example.lesson_03_yermakov.presentation.ui.catalog.CatalogFragment
import com.example.lesson_03_yermakov.presentation.ui.signIn.SignInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun signInFragment(): SignInFragment
    @ContributesAndroidInjector
    abstract fun catalogFragment(): CatalogFragment
}