package hearsilent.universalcollapsingtoolbarlayouttablayoutexample.libs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.R;

public class Utils {

	public static DisplayImageOptions.Builder getDisplayImageBuilder() {
		return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.displayer(new FadeInBitmapDisplayer(500));
	}

	public static int getStatusBarHeightPixel(Context context) {
		if (context instanceof Activity) {
			Rect rectangle = new Rect();
			Window window = ((Activity) context).getWindow();
			window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
			return rectangle.top;
		} else {
			int result = 0;
			@SuppressLint({"InternalInsetResource", "DiscouragedApi"}) int resourceId =
					context.getResources().getIdentifier("status_bar_height", "dimen", "android");
			if (resourceId > 0) {
				result = context.getResources().getDimensionPixelSize(resourceId);
			}
			return result;
		}
	}

	public static int getActionBarHeightPixel(Context context) {
		TypedValue tv = new TypedValue();
		if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
			return TypedValue.complexToDimensionPixelSize(tv.data,
					context.getResources().getDisplayMetrics());
		} else {
			return 0;
		}
	}

	public static int getTabHeight(Context context) {
		return context.getResources().getDimensionPixelSize(R.dimen.tab_height);
	}

	public static Point getDisplayDimen(Context context) {
		Display display = ((WindowManager) context.getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size;
	}

	public static boolean isLand(Context context) {
		return context.getResources().getConfiguration().orientation ==
				Configuration.ORIENTATION_LANDSCAPE;
	}

}
