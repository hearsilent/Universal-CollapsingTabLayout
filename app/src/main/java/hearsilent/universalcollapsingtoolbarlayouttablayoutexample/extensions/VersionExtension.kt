package hearsilent.universalcollapsingtoolbarlayouttablayoutexample.extensions

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(parameter = 0)
fun atLeast(api: Int) = Build.VERSION.SDK_INT >= api