package hearsilent.universalcollapsingtoolbarlayouttablayoutexample.base;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(this);
	}

	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config =
				new ImageLoaderConfiguration.Builder(context).threadPoolSize(5).build();

		ImageLoader.getInstance().init(config);
	}

	public BaseApplication() {
		super();
	}
}