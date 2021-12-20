package dev.victorhernandez.tvshows.presentation.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry

private val context: Context
    get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext

fun stringResource(@StringRes resId: Int, vararg formatArgs: Any): String =
    context.getString(resId, *formatArgs)