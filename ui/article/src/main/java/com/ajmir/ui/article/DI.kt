package com.ajmir.ui.article

import com.ajmir.ui.article.mapper.ArticleMapper
import com.ajmir.ui.article.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val articleUiModule = module {
    factoryOf(::ArticleMapper)
    viewModelOf(::ArticleViewModel)
}
