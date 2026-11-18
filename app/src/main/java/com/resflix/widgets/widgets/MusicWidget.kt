package com.resflix.widgets.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.resflix.widgets.MainActivity
import com.resflix.widgets.R

class MusicWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        ids.forEach { id ->
            val views = RemoteViews(context.packageName, R.layout.widget_music)
            val launch = context.packageManager.getLaunchIntentForPackage("com.spotify.music")
                ?: Intent(context, MainActivity::class.java)
            val pending = PendingIntent.getActivity(
                context, id, launch,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.musicOpen, pending)
            manager.updateAppWidget(id, views)
        }
    }
}
