# Universal CollapsingTabLayout
[![GitHub release](https://img.shields.io/github/release/hearsilent/Universal-CollapsingTabLayout.svg?maxAge=2592000)](https://github.com/hearsilent/Universal-CollapsingTabLayout)
[![license](https://img.shields.io/github/license/hearsilent/Universal-CollapsingTabLayout.svg?maxAge=2592000)](https://github.com/hearsilent/Universal-CollapsingTabLayout/blob/master/LICENSE)

CollapsingToolbarLayout with TabLayout.

## Screenshot
<img src="https://raw.githubusercontent.com/hearsilent/Universal-CollapsingTabLayout/master/screenshots/screenrecord.gif" height="500">

### Expanded
<img src="https://raw.githubusercontent.com/hearsilent/Universal-CollapsingTabLayout/master/screenshots/device-2016-08-28-230914_framed.png" height="500">

### Collapsed
<img src="https://raw.githubusercontent.com/hearsilent/Universal-CollapsingTabLayout/master/screenshots/device-2016-08-28-230929_framed.png" height="500">

## Usage

*For a working implementation, please have a look at the Sample Project - sample*

<a href='https://play.google.com/store/apps/details?id=hearsilent.universalcollapsingtoolbarlayouttablayoutexample&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png'  width="155" height="60"/></a>

Just Clone and Build.

## Customization

You can change your view into container view.
```xml
<FrameLayout
	android:id="@+id/view_container"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	android:fitsSystemWindows="true"
	app:layout_collapseMode="parallax">

	...
</FrameLayout>
```

## Compatibility

Android LOLLIPOP 5.0+

## Known issues
- [x] ~~Android Support Library 24.2.0 doesn't work successful ( You can downgrade to 24.1.1 ) : http://goo.gl/FMWs37~~

## Let me know!

I'd be really happy if you sent me links to your projects where you use my component. Just send an email to hear.silent1995+github@gmail.com And do let me know if you have any questions or suggestion regarding the example. 

## License

	   MIT License
	
	   Copyright (c) 2026 HearSilent
	
	   Permission is hereby granted, free of charge, to any person obtaining a copy
	   of this software and associated documentation files (the "Software"), to deal
	   in the Software without restriction, including without limitation the rights
	   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	   copies of the Software, and to permit persons to whom the Software is
	   furnished to do so, subject to the following conditions:
	
	   The above copyright notice and this permission notice shall be included in all
	   copies or substantial portions of the Software.
	
	   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	   SOFTWARE.
