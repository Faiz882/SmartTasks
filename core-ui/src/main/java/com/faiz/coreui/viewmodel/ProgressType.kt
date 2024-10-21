package com.faiz.coreui.viewmodel

sealed class ProgressType {
    object NoProgress : ProgressType()
    object ProgressDialog : ProgressType()
    object SwipeRefresh : ProgressType()
    data class TransitionButton(val buttonId: Int) : ProgressType()
    data class ShimmerView(val viewId: Int) : ProgressType()
}