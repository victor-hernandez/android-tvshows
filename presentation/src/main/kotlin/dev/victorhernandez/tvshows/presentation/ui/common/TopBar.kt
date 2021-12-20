package dev.victorhernandez.tvshows.presentation.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.victorhernandez.tvshows.presentation.R
import dev.victorhernandez.tvshows.presentation.theme.SpacingMassive
import dev.victorhernandez.tvshows.presentation.theme.SpacingMedium
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme

@Composable
fun TvShowsTopBar(
    canNavigateUp: Boolean = false,
    onNavigateUp: () -> Unit
) {
    TopAppBar(
        elevation = 0.dp
    ) {
        if (canNavigateUp) {
            IconButton(onClick = onNavigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.navigate_up_button)
                )
            }
        } else {
            Spacer(modifier = Modifier.size(SpacingMassive))
        }

        Text(
            modifier = Modifier.padding(start = SpacingMedium),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h6,
        )
    }
}

@Composable
private fun TvShowsTopBarPreview(
    canNavigateUp: Boolean,
    darkTheme: Boolean
) {
    TVShowsTheme(darkTheme) {
        TvShowsTopBar(canNavigateUp) { }
    }
}

@Preview(name = "TV shows top bar preview light")
@Composable
private fun TvShowsTopBarPreviewLight() {
    TvShowsTopBarPreview(canNavigateUp = false, darkTheme = false)
}

@Preview(name = "TV shows top bar preview dark")
@Composable
private fun TvShowsTopBarPreviewDark() {
    TvShowsTopBarPreview(canNavigateUp = false, darkTheme = true)
}

@Preview(name = "TV shows top bar with up button preview light")
@Composable
private fun TvShowsTopBarWithUpButtonPreviewLight() {
    TvShowsTopBarPreview(canNavigateUp = true, darkTheme = false)
}

@Preview(name = "TV shows top bar with up button preview dark")
@Composable
private fun TvShowsTopBarWithUpButtonPreviewDark() {
    TvShowsTopBarPreview(canNavigateUp = true, darkTheme = true)
}