package com.resflix.widgets.widgets
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.resflix.widgets.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
class CalendarWidget : AppWidgetProvider() {
 override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
  val now=Date(); val locale=Locale.getDefault()
  val day=SimpleDateFormat("EEEE",locale).format(now).uppercase(locale)
  val date=SimpleDateFormat("dd",locale).format(now)
  val month=SimpleDateFormat("MMMM yyyy",locale).format(now).uppercase(locale)
  ids.forEach { id ->
   val v=RemoteViews(context.packageName,R.layout.widget_calendar)
   v.setTextViewText(R.id.calendarDay,day); v.setTextViewText(R.id.calendarDate,date)
   v.setTextViewText(R.id.calendarMonth,month); manager.updateAppWidget(id,v)
  }
 }
}
