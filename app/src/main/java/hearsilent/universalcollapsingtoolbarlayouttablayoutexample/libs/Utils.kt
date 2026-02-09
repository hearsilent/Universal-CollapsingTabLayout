package hearsilent.universalcollapsingtoolbarlayouttablayoutexample.libs

import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.util.TypedValue
import android.view.WindowManager
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.R
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.extensions.currentWindowMetricsPointCompat

object Utils {
    fun getActionBarHeightPixel(context: Context): Int {
        val tv = TypedValue()
        return if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        } else {
            0
        }
    }

    fun getTabHeight(context: Context): Int {
        return context.resources.getDimensionPixelSize(R.dimen.tab_height)
    }

    fun getDisplayDimen(context: Context): Point {
        val manager =
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?) ?: return Point()
        return manager.currentWindowMetricsPointCompat()
    }

    fun isLand(context: Context): Boolean {
        return context.resources.configuration.orientation ==
                Configuration.ORIENTATION_LANDSCAPE
    }
}
