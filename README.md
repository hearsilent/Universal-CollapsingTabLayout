# Universal CollapsingTabLayout
[![GitHub release](https://img.shields.io/github/release/hearsilent/Universal-CollapsingToolbarLayout-TabLayout-Example.svg?maxAge=2592000)](https://github.com/hearsilent/Universal-CollapsingToolbarLayout-TabLayout-Example)
[![license](https://img.shields.io/github/license/hearsilent/Universal-CollapsingToolbarLayout-TabLayout-Example.svg?maxAge=2592000)](https://github.com/hearsilent/Universal-CollapsingToolbarLayout-TabLayout-Example/blob/master/LICENSE)

CollapsingToolbarLayout with TabLayout.

## Screenshot
<img src="https://raw.githubusercontent.com/hearsilent/Universal-CollapsingToolbarLayout-TabLayout-Example/master/screenshots/screenrecord.gif" height="500">

### Extend
<img src="https://raw.githubusercontent.com/hearsilent/Universal-CollapsingToolbarLayout-TabLayout-Example/master/screenshots/device-2016-08-28-230914_framed.png" height="500">
### Collapsed
<img src="https://raw.githubusercontent.com/hearsilent/Universal-CollapsingToolbarLayout-TabLayout-Example/master/screenshots/device-2016-08-28-230929_framed.png" height="500">

## Usage

*For a working implementation, please have a look at the Sample Project - sample*

<a href='https://play.google.com/store/apps/details?id=hearsilent.universalcollapsingtoolbarlayouttablayoutexample&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png'  width="155" height="60"/></a>

Just Clone and Build.

## Customization

You can change your view into container view.
```java
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

Android GINGERBREAD 2.3+

## Known issues
- [x] ~~Android Support Library 24.2.0 doesn't work successful ( You can downgrade to 24.1.1 ) : http://goo.gl/FMWs37~~

## Let me know!

I'd be really happy if you sent me links to your projects where you use my component. Just send an email to hear.silent1995@gmail.com And do let me know if you have any questions or suggestion regarding the example. 

## License

    Copyright 2016, HearSilent

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
