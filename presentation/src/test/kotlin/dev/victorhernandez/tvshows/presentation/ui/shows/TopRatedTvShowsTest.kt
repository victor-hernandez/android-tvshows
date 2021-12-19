package dev.victorhernandez.tvshows.presentation.ui.shows

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.victorhernandez.tvshows.presentation.model.TvShowUiModel
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme
import dev.victorhernandez.tvshows.presentation.ui.example.TvShows
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.*

@RunWith(AndroidJUnit4::class)
class TopRatedTvShowsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val shows = TvShows

    private val onClick: ((TvShowUiModel) -> Unit) = mock()

    private val onLoadMore: (() -> Unit) = mock()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TVShowsTheme {
                TopRatedTvShows(
                    shows,
                    onLoadMore,
                    onClick
                )
            }
        }
    }

    @Test
    fun `should display top rated TV shows`() {
        composeTestRule
            .onNodeWithTag(TestTagTopRatedTvShowsLazyVerticalGrid)
            .assertIsDisplayed()
    }

    @Test
    fun `should invoke on click call when a show is clicked`() {
        val show = shows.first()
        composeTestRule
            .onNodeWithText(show.name)
            .performClick()

        verify(onClick).invoke(show)
        verifyNoMoreInteractions(onClick)
    }

    @Test
    fun `should invoke on load more shows callback when reaching the end of the list`() {
        composeTestRule.onNodeWithTag(TestTagTopRatedTvShowsLazyVerticalGrid)
            .performScrollToIndex(shows.size/2-1)

        verify(onLoadMore, atLeastOnce()).invoke()
    }
}