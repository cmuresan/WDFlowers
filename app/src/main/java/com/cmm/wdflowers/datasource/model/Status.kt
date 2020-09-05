package com.cmm.wdflowers.datasource.model

import androidx.annotation.StringRes

sealed class Status {
    object Success : Status()
    class Error(@StringRes val stringResId: Int = -1) : Status()
}
