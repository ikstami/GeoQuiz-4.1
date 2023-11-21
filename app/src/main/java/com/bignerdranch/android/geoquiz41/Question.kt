package com.bignerdranch.android.geoquiz41

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean, var usedCheat:Boolean=false) {

}
