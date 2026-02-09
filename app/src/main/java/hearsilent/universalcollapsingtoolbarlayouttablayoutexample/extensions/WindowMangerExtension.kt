package hearsilent.universalcollapsingtoolbarlayouttablayoutexample.extensions

import android.graphics.Insets
import android.graphics.Point
import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager

fun WindowManager.currentWindowMetricsPointCompat(): Point {
    return if (atLeast(Build.VERSION_CODES.R)) {
        val windowInsets = currentWindowMetrics.windowInsets
        var insets: Insets = windowInsets.getInsets(WindowInsets.Type.navigationBars())
        windowInsets.displayCutout?.run {
            insets = Insets.max(
                insets,
                Insets.of(safeInsetLeft, safeInsetTop, safeInsetRight, safeInsetBottom)
            )
        }
        val insetsWidth = insets.right + insets.left
        val insetsHeight = insets.top + insets.bottom
        Point(
            currentWindowMetrics.bounds.width() - insetsWidth,
            currentWindowMetrics.bounds.height() - insetsHeight
        )
    } else {
        Point().apply {
            defaultDisplay.getSize(this)
        }
    }
}