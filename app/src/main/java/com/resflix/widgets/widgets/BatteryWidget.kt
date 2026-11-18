package com.resflix.widgets.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.RemoteViews
import com.resflix.widgets.R

class BatteryWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        val battery = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = battery?.getIntExtra("level", -1) ?: -1
        val scale = battery?.getIntExtra("scale", 100) ?: 100
        val percent = if (level >= 0 && scale > 0) (level * 100 / scale) else 0
        val status = battery?.getIntExtra("status", -1) ?: -1
        val charging = status == 2 || status == 5

        ids.forEach { id ->
            val views = RemoteViews(context.packageName, R.layout.widget_battery)
            views.setTextViewText(R.id.batteryPercent, "$percent%")
            views.setTextViewText(
                R.id.batteryStatus,
                if (charging) "CHARGING" else "BATTERY"
            )
            manager.updateAppWidget(id, views)
        }
    }
}
