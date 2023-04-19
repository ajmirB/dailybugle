package com.ajmir.news

import com.ajmir.news.mapper.NewsRepositoryMapper
import com.ajmir.news.remote.provideNewsApi
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val newsModule = module {
    factory { provideNewsApi(get()) }
    factoryOf(::NewsRepositoryMapper)
    factoryOf(::NewsRepositoryMapper)
    singleOf(::NewsRepositoryImpl) { bind<NewsRepository>() }
}
