package com.ajmir.ui.home

import com.ajmir.ui.home.mapper.HomeMapper
import com.ajmir.ui.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val homeUiModule = module {
    factoryOf(::HomeMapper)
    viewModelOf(::HomeViewModel)
}
