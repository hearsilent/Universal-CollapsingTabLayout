package hearsilent.universalcollapsingtoolbarlayouttablayoutexample;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nostra13.universalimageloader.core.ImageLoader;

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
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		mToolbarTextView = (TextView) findViewById(R.id.toolbar_title);
		mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
		mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
		mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
		mHeaderImageView = (KenBurnsView) findViewById(R.id.imageView_header);
		mContainerView = findViewById(R.id.view_container);

		mViewPager = (ViewPager) findViewById(R.id.viewPager);
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
						if (Build.VERSION.SDK_INT >= 11) {
							mToolbarTextView.setAlpha(1);
						} else {
							setAlphaForView(mToolbarTextView, 1);
						}
						mCollapsingToolbar.setContentScrimColor(
								ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
					} else if (state == State.EXPANDED) {
						if (Build.VERSION.SDK_INT >= 11) {
							mToolbarTextView.setAlpha(0);
						} else {
							setAlphaForView(mToolbarTextView, 0);
						}
						mCollapsingToolbar.setContentScrimColor(ContextCompat
								.getColor(MainActivity.this, android.R.color.transparent));
					}
				}
			}

			@Override
			public void onOffsetChanged(State state, float offset) {
				if (mToolbarTextView != null) {
					if (state == State.IDLE) {
						if (Build.VERSION.SDK_INT >= 11) {
							mToolbarTextView.setAlpha(offset);
							mCollapsingToolbar.setContentScrimColor(
									(int) new android.animation.ArgbEvaluator().evaluate(offset,
											ContextCompat.getColor(MainActivity.this,
													android.R.color.transparent), ContextCompat
													.getColor(MainActivity.this,
															R.color.colorPrimary)));
						} else {
							setAlphaForView(mToolbarTextView, offset);
							mCollapsingToolbar.setContentScrimColor((int) new ArgbEvaluator()
									.evaluate(offset, ContextCompat.getColor(MainActivity.this,
											android.R.color.transparent), ContextCompat
											.getColor(MainActivity.this, R.color.colorPrimary)));
						}
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

	private void setAlphaForView(View v, float alpha) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(alpha, alpha);
		alphaAnimation.setDuration(0);
		alphaAnimation.setFillAfter(true);
		v.startAnimation(alphaAnimation);
	}

	private void actionBarResponsive() {
		int actionBarHeight = Utils.getActionBarHeightPixel(this);
		int tabHeight = Utils.getTabHeight(this);
		if (actionBarHeight > 0) {
			mToolbar.getLayoutParams().height = actionBarHeight + tabHeight;
			mToolbar.requestLayout();
		}
	}

	private class ViewPagerAdapter extends FragmentPagerAdapter {

		public ViewPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

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
