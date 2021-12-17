package dev.victorhernandez.tvshows.presentation.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.victorhernandez.tvshows.presentation.R
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme

@Composable
fun TvShowsTopBar() {
    TopAppBar(
        elevation = 0.dp
    ) {
        Text(
            modifier = Modifier.padding(start = 32.dp),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h6,
        )
    }
}

@Composable
private fun TvShowsTopBarPreview(darkTheme: Boolean) {
    TVShowsTheme(darkTheme) {
        TvShowsTopBar()
    }
}

@Preview(name = "TV shows top bar preview light")
@Composable
private fun TvShowsTopBarPreviewLight() {
    TvShowsTopBarPreview(false)
}

@Preview(name = "TV shows top bar preview dark")
@Composable
private fun TvShowsTopBarPreviewDark() {
    TvShowsTopBarPreview(true)
}