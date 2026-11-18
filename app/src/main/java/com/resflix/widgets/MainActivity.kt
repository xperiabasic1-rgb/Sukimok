
package com.resflix.widgets

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.resflix.widgets.widgets.AnalogClockWidget
import com.resflix.widgets.widgets.BatteryWidget
import com.resflix.widgets.widgets.CalendarWidget
import com.resflix.widgets.widgets.Clock2x4Widget
import com.resflix.widgets.widgets.DigitalClockWidget
import com.resflix.widgets.widgets.DotMatrixClockWidget
import com.resflix.widgets.widgets.MusicWidget
import com.resflix.widgets.widgets.WeatherWidget

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.openWidgetsButton).setOnClickListener {
            Toast.makeText(
                this,
                "Buka menu Widgets di launcher, lalu pilih Resflix.",
                Toast.LENGTH_LONG
            ).show()
        }

        findViewById<Button>(R.id.refreshButton).setOnClickListener {
            refreshAllWidgets()
            Toast.makeText(
                this,
                "Widget Resflix diperbarui.",
                Toast.LENGTH_SHORT
            ).show()
        }

        findViewById<TextView>(R.id.versionText).text =
            "RESFLIX • VERSION 2.1.0"
    }

    private fun refreshAllWidgets() {
        val manager = AppWidgetManager.getInstance(this)

        val providers = listOf(
            DigitalClockWidget::class.java,
            DotMatrixClockWidget::class.java,
            AnalogClockWidget::class.java,
            Clock2x4Widget::class.java,
            CalendarWidget::class.java,
            BatteryWidget::class.java,
            MusicWidget::class.java,
            WeatherWidget::class.java
        )

        providers.forEach { provider ->
            val component = ComponentName(this, provider)
            val ids = manager.getAppWidgetIds(component)

            if (ids.isNotEmpty()) {
                val intent = Intent(this, provider).apply {
                    action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                    putExtra(
                        AppWidgetManager.EXTRA_APPWIDGET_IDS,
                        ids
                    )
                }

                sendBroadcast(intent)
            }
        }
    }
}
