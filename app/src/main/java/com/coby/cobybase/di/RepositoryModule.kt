package com.coby.cobybase.di

import com.coby.cobybase.repository.HomeRepository
import com.coby.cobybase.repository.LoginRepository
import com.coby.cobybase.repository.impl.HomeRepositoryImpl
import com.coby.cobybase.repository.impl.LoginRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
}