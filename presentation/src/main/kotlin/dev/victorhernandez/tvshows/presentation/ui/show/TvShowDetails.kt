package dev.victorhernandez.tvshows.presentation.ui.show

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import dev.victorhernandez.tvshows.presentation.R
import dev.victorhernandez.tvshows.presentation.model.TvShowUiModel
import dev.victorhernandez.tvshows.presentation.theme.*
import dev.victorhernandez.tvshows.presentation.ui.common.TvShowsTopBar

internal const val TestTagTvShowDetailBottomSheet = "TestTagTvShowDetailBottomSheet"

@Composable
fun TvShowDetailsScreen(
    show: TvShowUiModel,
    onBackPressed: () -> Unit,
    viewModel: TvShowDetailsViewModel = hiltViewModel()
) {

    viewModel.init(show)
    val state = viewModel.uiState.collectAsState()

    RelatedTvShows(
        shows = state.value.shows,
        onListEndReached = { viewModel.loadNextSimilarTvShows() },
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvShowDetails(
    show: TvShowUiModel
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = TvShowDetailsBottomSheetPeekHeight,
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetBackgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.8f),
        sheetElevation = 0.dp,
        modifier = Modifier.testTag(TestTagTvShowDetailBottomSheet),
        sheetContent = {
            Text(
                style = MaterialTheme.typography.h5,
                text = show.name,
                modifier = Modifier
                    .padding(
                        top = SpacingXLarge,
                        start = SpacingLarge,
                        end = SpacingLarge
                    )
            )
            Row(
                modifier = Modifier
                    .padding(
                        top = SpacingMedium,
                        start = SpacingLarge,
                        end = SpacingLarge
                    )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.body2,
                    text = stringResource(
                        R.string.vote_average_over_total,
                        show.voteAverage
                    )
                )
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = SpacingMedium)
                )
            }
            Text(
                style = MaterialTheme.typography.body1,
                text = show.overview,
                modifier = Modifier
                    .padding(
                        top = SpacingXLarge,
                        bottom = SpacingMassive,
                        start = SpacingLarge,
                        end = SpacingLarge
                    )
            )
        }
    ) {
        if (!show.image.isNullOrBlank()) {
            Image(
                painter = rememberImagePainter(show.imageBig),
                contentDescription = "${show.name} poster picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun TvShowDetailsPreview(darkTheme: Boolean) {
    TVShowsTheme(darkTheme) {
        Scaffold(
            topBar = { TvShowsTopBar(false) { } }
        ) {
            TvShowDetails(
                show = dev.victorhernandez.tvshows.presentation.ui.example.TvShowDetails.first()
            )
        }
    }
}

@Preview(name = "TV show details preview light")
@Composable
private fun TvShowDetailsPreviewLight() {
    TvShowDetailsPreview(false)
}

@Preview(name = "TV show details preview dark")
@Composable
private fun TvShowDetailsPreviewDark() {
    TvShowDetailsPreview(true)
}

