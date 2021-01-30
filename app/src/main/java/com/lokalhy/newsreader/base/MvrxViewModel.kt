package com.lokalhy.newsreader.base

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.BuildConfig
import com.airbnb.mvrx.MvRxState

abstract class MvrxViewModel<S :MvRxState>(state: S) : BaseMvRxViewModel<S>(state, BuildConfig.DEBUG)