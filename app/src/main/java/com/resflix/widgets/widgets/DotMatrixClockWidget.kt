package com.resflix.widgets.widgets
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.resflix.widgets.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
class DotMatrixClockWidget : AppWidgetProvider() {
 override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
  val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
  val date = SimpleDateFormat("EEE, dd MMM", Locale.getDefault()).format(Date())
  ids.forEach { id ->
   val v = RemoteViews(context.packageName, R.layout.widget_dot_clock)
   v.setTextViewText(R.id.dotTime, time)
   v.setTextViewText(R.id.dotDate, date)
   manager.updateAppWidget(id, v)
  }
 }
}
