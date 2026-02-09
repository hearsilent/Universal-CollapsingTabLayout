package hearsilent.universalcollapsingtoolbarlayouttablayoutexample

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import coil3.imageLoader
import coil3.load
import coil3.memory.MemoryCache
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.databinding.ActivityMainBinding
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.fragment.DemoFragment
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.libs.AppBarStateChangeListener
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.libs.Utils

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpViews()
    }

    override fun onResume() {
        super.onResume()
        mBinding.imageViewHeader.resume()
    }

    override fun onPause() {
        mBinding.imageViewHeader.pause()
        super.onPause()
    }

    private fun setUpViews() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
        mBinding.collapsingToolbar.isTitleEnabled = false

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, insets ->
            val displayDimen = Utils.getDisplayDimen(this)
            mBinding.collapsingToolbar.layoutParams.height =
                (if (Utils.isLand(this)) displayDimen.y else displayDimen.x) * 9 / 16
            mBinding.collapsingToolbar.requestLayout()

            insets
        }

        // TODO : Hack for CollapsingToolbarLayout
        mBinding.toolbarTitle.setText(R.string.demo)
        actionBarResponsive()
        mBinding.appBar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                if (state == State.COLLAPSED) {
                    mBinding.toolbarTitle.alpha = 1f
                    mBinding.collapsingToolbar.setContentScrimColor(
                        ContextCompat.getColor(this@MainActivity, R.color.colorPrimary)
                    )
                } else if (state == State.EXPANDED) {
                    mBinding.toolbarTitle.alpha = 0f
                    mBinding.collapsingToolbar.setContentScrimColor(
                        ContextCompat
                            .getColor(this@MainActivity, android.R.color.transparent)
                    )
                }
            }

            override fun onOffsetChanged(state: State?, offset: Float) {
                if (state == State.IDLE) {
                    mBinding.toolbarTitle.alpha = offset
                    mBinding.collapsingToolbar.setContentScrimColor(
                        ArgbEvaluator()
                            .evaluate(
                                offset, ContextCompat
                                    .getColor(this@MainActivity, android.R.color.transparent),
                                ContextCompat.getColor(
                                    this@MainActivity,
                                    R.color.colorPrimary
                                )
                            ) as Int
                    )
                }
            }
        })

        mAdapter = ViewPagerAdapter(this)
        mBinding.viewPager.adapter = mAdapter
        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager) { tab, position ->
            tab.text = "Demo $position"
        }.attach()

        val imageLoader = imageLoader
        val url = "https://unsplash.it/1024/768"
        imageLoader.diskCache?.remove(url)
        imageLoader.memoryCache?.remove(MemoryCache.Key(url))
        mBinding.imageViewHeader.load(url)
    }

    private fun actionBarResponsive() {
        val actionBarHeight = Utils.getActionBarHeightPixel(this)
        val tabHeight = Utils.getTabHeight(this)
        if (actionBarHeight > 0) {
            mBinding.toolbar.layoutParams.height = actionBarHeight + tabHeight
            mBinding.toolbar.requestLayout()
        }
    }

    class ViewPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {

        override fun createFragment(position: Int): Fragment {
            return DemoFragment()
        }

        override fun getItemCount(): Int {
            return 4
        }
    }
}
