package com.example.f_meme

import android.content.Context
import android.content.Intent
import ru.surfstudio.android.core.ui.navigation.Route
import ru.surfstudio.android.core.ui.navigation.activity.route.ActivityWithParamsRoute
import ru.surfstudio.standard.domain.feed.Meme

/**
 * Роут для экрана мема [MemeActivityView]
 */
class MemeActivityRoute(val meme: Meme) : ActivityWithParamsRoute() {

    constructor(intent: Intent) : this(
            intent.getSerializableExtra(Route.EXTRA_FIRST) as Meme
    )

    override fun prepareIntent(context: Context?) = Intent(context, MemeActivityView::class.java).apply {
        putExtra(Route.EXTRA_FIRST, meme)
    }
}