package com.vishal2376.bookkeeper.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vishal2376.bookkeeper.R


val fontMontserrat = FontFamily(Font(R.font.montserrat))
val fontRoboto = FontFamily(Font(R.font.roboto))

var headingTextStyle = TextStyle(
    fontSize = 24.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.SemiBold,
)

var titleTextStyle = TextStyle(
    fontSize = 18.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.SemiBold,
)

var bodyTextStyle = TextStyle(
    fontSize = 14.sp,
    fontFamily = fontRoboto,
)