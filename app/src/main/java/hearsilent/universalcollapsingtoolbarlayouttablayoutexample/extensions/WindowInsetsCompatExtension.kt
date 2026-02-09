package hearsilent.universalcollapsingtoolbarlayouttablayoutexample.extensions

import androidx.core.view.WindowInsetsCompat

val WindowInsetsCompat.statusHeight
    get() = this.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars()).top
val WindowInsetsCompat.navigationHeight
    get() = this.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.navigationBars())
        .bottom