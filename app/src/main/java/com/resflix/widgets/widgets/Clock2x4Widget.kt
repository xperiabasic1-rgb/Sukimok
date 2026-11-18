
package com.resflix.widgets.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.RectF
import android.widget.RemoteViews
import com.resflix.widgets.R
import java.util.Calendar

/**
 * Resflix 2x4 Analog Clock Widget
 * Adapted from clock_2x4 theme assets.
 * Weather features are excluded.
 */
class Clock2x4Widget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        manager: AppWidgetManager,
        ids: IntArray
    ) {
        ids.forEach { update(context, manager, it) }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        when (intent.action) {
            ACTION_TIME_SET,
            ACTION_TIMEZONE_CHANGED,
            ACTION_TIME_TICK -> {
                val manager = AppWidgetManager.getInstance(context)

                val component = ComponentName(
                    context,
                    Clock2x4Widget::class.java
                )

                val ids = manager.getAppWidgetIds(component)

                ids.forEach { update(context, manager, it) }
            }
        }
    }

    private fun update(
        context: Context,
        manager: AppWidgetManager,
        id: Int
    ) {
        val views = RemoteViews(
            context.packageName,
            R.layout.widget_clock_2x4
        )

        views.setImageViewBitmap(
            R.id.clock_face,
            renderClock(context)
        )

        manager.updateAppWidget(id, views)
    }

    private fun renderClock(context: Context): Bitmap {
        val size = 720

        val output = Bitmap.createBitmap(
            size,
            size,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(output)

        val bg = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.resflix_clock_bg
        )

        val dial = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.resflix_clock_dial
        )

        val hour = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.resflix_clock_hour
        )

        val minute = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.resflix_clock_minute
        )

        val second = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.resflix_clock_second
        )

        val paint = Paint(
            Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG
        )

        val destination = RectF(
            0f,
            0f,
            size.toFloat(),
            size.toFloat()
        )

        canvas.drawBitmap(bg, null, destination, paint)

        paint.colorFilter = PorterDuffColorFilter(
            Color.WHITE,
            PorterDuff.Mode.SRC_IN
        )

        canvas.drawBitmap(dial, null, destination, paint)

        paint.colorFilter = null

        val now = Calendar.getInstance()

        val hourAngle =
            now.get(Calendar.HOUR) * 30f +
            now.get(Calendar.MINUTE) * 0.5f

        val minuteAngle =
            now.get(Calendar.MINUTE) * 6f +
            now.get(Calendar.SECOND) * 0.1f

        val secondAngle =
            now.get(Calendar.SECOND) * 6f

        drawHand(canvas, hour, size, hourAngle, Color.WHITE)
        drawHand(canvas, minute, size, minuteAngle, Color.WHITE)
        drawHand(canvas, second, size, secondAngle, null)

        bg.recycle()
        dial.recycle()
        hour.recycle()
        minute.recycle()
        second.recycle()

        return output
    }

    private fun drawHand(
        canvas: Canvas,
        bitmap: Bitmap,
        size: Int,
        angle: Float,
        tint: Int?
    ) {
        val paint = Paint(
            Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG
        )

        if (tint != null) {
            paint.colorFilter = PorterDuffColorFilter(
                tint,
                PorterDuff.Mode.SRC_IN
            )
        }

        val scale = size / 480f

        val matrix = Matrix().apply {
            postScale(scale, scale)
            postRotate(
                angle,
                size / 2f,
                size / 2f
            )
        }

        canvas.drawBitmap(bitmap, matrix, paint)
    }

    companion object {
        private const val ACTION_TIME_SET =
            "android.intent.action.TIME_SET"

        private const val ACTION_TIMEZONE_CHANGED =
            "android.intent.action.TIMEZONE_CHANGED"

        private const val ACTION_TIME_TICK =
            "android.intent.action.TIME_TICK"
    }
}
