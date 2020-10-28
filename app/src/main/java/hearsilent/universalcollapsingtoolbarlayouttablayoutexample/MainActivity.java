package hearsilent.universalcollapsingtoolbarlayouttablayoutexample;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.nostra13.universalimageloader.core.ImageLoader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.fragment.DemoFragment;
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.libs.AppBarStateChangeListener;
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.libs.Utils;

public class MainActivity extends AppCompatActivity {

	Toolbar mToolbar;
	CollapsingToolbarLayout mCollapsingToolbar;
	AppBarLayout mAppBarLayout;
	TabLayout mTabLayout;
	TextView mToolbarTextView;
	KenBurnsView mHeaderImageView;
	View mContainerView;

	ViewPager mViewPager;

	ViewPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViews();
		setUpViews();
	}

	private void findViews() {
		mToolbar = findViewById(R.id.toolbar);
		mToolbarTextView = findViewById(R.id.toolbar_title);
		mCollapsingToolbar = findViewById(R.id.collapsing_toolbar);
		mAppBarLayout = findViewById(R.id.app_bar);
		mTabLayout = findViewById(R.id.tabLayout);
		mHeaderImageView = findViewById(R.id.imageView_header);
		mContainerView = findViewById(R.id.view_container);

		mViewPager = findViewById(R.id.viewPager);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mHeaderImageView != null) {
			mHeaderImageView.resume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mHeaderImageView != null) {
			mHeaderImageView.pause();
		}
	}

	private void setUpViews() {
		setSupportActionBar(mToolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
		}
		mCollapsingToolbar.setTitleEnabled(false);
		mCollapsingToolbar.getLayoutParams().height =
				(Utils.isLand(this) ? Utils.getDisplayDimen(this).y :
						Utils.getDisplayDimen(this).x) * 9 / 16 +
						Utils.getStatusBarHeightPixel(this);
		mCollapsingToolbar.requestLayout();

		// TODO : Hack for CollapsingToolbarLayout
		mToolbarTextView.setText("Demo");
		actionBarResponsive();
		mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {

			@Override
			public void onStateChanged(AppBarLayout appBarLayout, State state) {
				if (mToolbarTextView != null) {
					if (state == State.COLLAPSED) {
						mToolbarTextView.setAlpha(1);
						mCollapsingToolbar.setContentScrimColor(
								ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
					} else if (state == State.EXPANDED) {
						mToolbarTextView.setAlpha(0);
						mCollapsingToolbar.setContentScrimColor(ContextCompat
								.getColor(MainActivity.this, android.R.color.transparent));
					}
				}
			}

			@Override
			public void onOffsetChanged(State state, float offset) {
				if (mToolbarTextView != null) {
					if (state == State.IDLE) {
						mToolbarTextView.setAlpha(offset);
						mCollapsingToolbar.setContentScrimColor((int) new ArgbEvaluator()
								.evaluate(offset, ContextCompat
												.getColor(MainActivity.this, android.R.color.transparent),
										ContextCompat.getColor(MainActivity.this,
												R.color.colorPrimary)));
					}
				}
			}
		});

		mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		mViewPager.setOffscreenPageLimit(mAdapter.getCount());
		mViewPager.setAdapter(mAdapter);
		mTabLayout.setupWithViewPager(mViewPager);
		ImageLoader.getInstance().displayImage("https://unsplash.it/1024/768", mHeaderImageView,
				Utils.getDisplayImageBuilder().build());

		// If your CollapsingToolbarLayout too complex (e.g. ImageView into FrameLayout), then
		// your status bar may looks so buggy.
		// You can hotfix by this code when you need to use some 24.2.0's features,
		// or you can wait for Google fix this (24.2.1), or downgrade to 24.1.1.
		// The issue: http://goo.gl/FMWs37
		ViewCompat
				.setOnApplyWindowInsetsListener(mContainerView, new OnApplyWindowInsetsListener() {

					@Override
					public WindowInsetsCompat onApplyWindowInsets(View v,
					                                              WindowInsetsCompat insets) {
						return insets.consumeSystemWindowInsets();
					}
				});
	}

	private void actionBarResponsive() {
		int actionBarHeight = Utils.getActionBarHeightPixel(this);
		int tabHeight = Utils.getTabHeight(this);
		if (actionBarHeight > 0) {
			mToolbar.getLayoutParams().height = actionBarHeight + tabHeight;
			mToolbar.requestLayout();
		}
	}

	private static class ViewPagerAdapter extends FragmentPagerAdapter {

		public ViewPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		}

		@NonNull
		@Override
		public Fragment getItem(int position) {
			return new DemoFragment();
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "Demo " + position;
		}
	}
}
