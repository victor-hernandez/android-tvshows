package dev.victorhernandez.tvshows.presentation.ui.shows

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import dev.victorhernandez.tvshows.presentation.R
import dev.victorhernandez.tvshows.presentation.model.TvShowListItemUiModel
import dev.victorhernandez.tvshows.presentation.theme.*
import dev.victorhernandez.tvshows.presentation.ui.example.TvShows

internal const val TestTagTvShowListItemCard = "TestTagTvShowListItemCard"

@Composable
fun TvShowListItem(
    show: TvShowListItemUiModel,
    onClick: (TvShowListItemUiModel) -> Unit
) {
    Card(
        modifier = Modifier
            .height(TvShowListItemHeight)
            .padding(SpacingSmall)
            .clickable { onClick.invoke(show) }
            .testTag(TestTagTvShowListItemCard)
    ) {
        Box {
            if (!show.image.isNullOrBlank()) {
                Image(
                    painter = rememberImagePainter(show.image),
                    contentDescription = "${show.name} backdrop picture",
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface.copy(alpha = 0.6f))
                    .padding(
                        top = SpacingMedium,
                        bottom = SpacingMedium,
                        start = SpacingLarge,
                        end = SpacingLarge
                    )
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    style = MaterialTheme.typography.body1,
                    text = show.name
                )
                Row(
                    modifier = Modifier
                        .padding(top = SpacingSmall)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.caption,
                        text = stringResource(R.string.vote_average_over_total, show.voteAverage)
                    )
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = SpacingMedium)
                    )
                }
            }
        }
    }
}

@Composable
private fun TvShowListItemPreview(darkTheme: Boolean) {
    TVShowsTheme(darkTheme) {
        Surface {
            TvShowListItem(
                TvShows.first()
            ) { }
        }
    }
}

@Preview(
    name = "TV show list item preview light",
    widthDp = 180
)
@Composable
private fun TvShowListItemPreviewLight() {
    TvShowListItemPreview(false)
}

@Preview(
    name = "TV show list item preview dark",
    widthDp = 180
)
@Composable
private fun TvShowListItemPreviewDark() {
    Surface {
        TvShowListItemPreview(true)
    }
}