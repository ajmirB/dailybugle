package com.ajmir.dailybugle

import android.app.Application
import com.ajmir.common.commonModule
import com.ajmir.news.newsModule
import com.ajmir.retrofit.networkingModule
import com.ajmir.ui.article.articleUiModule
import com.ajmir.ui.home.homeUiModule
import org.koin.core.context.GlobalContext

class DailyBugleApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            modules(
                commonModule,
                networkingModule,
                newsModule,
                homeUiModule,
                articleUiModule
            )
        }
    }
}
