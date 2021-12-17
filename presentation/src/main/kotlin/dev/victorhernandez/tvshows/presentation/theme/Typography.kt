package dev.victorhernandez.tvshows.presentation.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import dev.victorhernandez.tvshows.presentation.R

val FontFamilyMontserrat = FontFamily(
    Font(R.font.montserrat_light, weight = FontWeight.Light),
    Font(R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium)
)

val FontFamilyOpenSans = FontFamily(
    Font(R.font.opensans_regular, weight = FontWeight.Normal),
    Font(R.font.opensans_medium, weight = FontWeight.Medium)
)

@OptIn(ExperimentalUnitApi::class)
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamilyMontserrat,
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        letterSpacing = TextUnit(-1.5f, TextUnitType.Sp)
    ),
    h2 = TextStyle(
        fontFamily = FontFamilyMontserrat,
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = TextUnit(-.5f, TextUnitType.Sp)
    ),
    h3 = TextStyle(
        fontFamily = FontFamilyMontserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = FontFamilyMontserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        letterSpacing = TextUnit(.25f, TextUnitType.Sp)
    ),
    h5 = TextStyle(
        fontFamily = FontFamilyMontserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = FontFamilyMontserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        letterSpacing = TextUnit(.15f, TextUnitType.Sp)
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamilyMontserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = TextUnit(.15f, TextUnitType.Sp)
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamilyMontserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = TextUnit(.1f, TextUnitType.Sp)
    ),
    body1 = TextStyle(
        fontFamily = FontFamilyOpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = TextUnit(.5f, TextUnitType.Sp)
    ),
    body2 = TextStyle(
        fontFamily = FontFamilyOpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = TextUnit(.25f, TextUnitType.Sp)
    ),
    button = TextStyle(
        fontFamily = FontFamilyOpenSans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = TextUnit(1.25f, TextUnitType.Sp)
    ),
    caption = TextStyle(
        fontFamily = FontFamilyOpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = TextUnit(.4f, TextUnitType.Sp)
    ),
    overline = TextStyle(
        fontFamily = FontFamilyOpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = TextUnit(1.5f, TextUnitType.Sp)
    )
)

@Composable
private fun TypographyPreview(darkTheme: Boolean) {
    TVShowsTheme(darkTheme) {
        Surface {
            Column {
                Text(
                    text = "H1",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "H2",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "H3",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "H4",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "H5",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "H6",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "Subtitle 1",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "Subtitle 2",
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "Body 1",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "Body 2",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "Button".uppercase(),
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "Caption",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = "Overline",
                    style = MaterialTheme.typography.overline,
                    modifier = Modifier.padding(start = SpacingLarge)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
            }
        }
    }
}

@Preview(
    name = "Typography light",
    widthDp = 640
)
@Composable
private fun TypographyPreviewLight() {
    TypographyPreview(false)
}

@Preview(
    name = "Typography dark",
    widthDp = 640
)
@Composable
private fun TypographyPreviewDark() {
    TypographyPreview(true)
}