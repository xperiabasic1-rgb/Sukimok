package com.resflix.widgets.widgets
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.resflix.widgets.R
class AnalogClockWidget : AppWidgetProvider() {
 override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
  ids.forEach { manager.updateAppWidget(it, RemoteViews(context.packageName, R.layout.widget_analog_clock)) }
 }
}
