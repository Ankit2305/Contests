package com.example.programmingcontest.ui.listcontest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class ListContestTypes: Parcelable {
    SITE,
    CATEGORY
}