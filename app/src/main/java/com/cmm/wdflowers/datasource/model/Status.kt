package com.cmm.wdflowers.datasource.model

sealed class Status {
    object Success : Status()
    object Error : Status()
}
