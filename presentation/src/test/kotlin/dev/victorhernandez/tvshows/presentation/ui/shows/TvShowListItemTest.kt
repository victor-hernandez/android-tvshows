package dev.victorhernandez.tvshows.presentation.ui.shows

import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.victorhernandez.tvshows.presentation.R
import dev.victorhernandez.tvshows.presentation.model.TvShowUiModel
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme
import dev.victorhernandez.tvshows.presentation.ui.example.TvShows
import dev.victorhernandez.tvshows.presentation.utils.stringResource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

@RunWith(AndroidJUnit4::class)
class TvShowListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val show = TvShows.first()

    private val onClick: ((TvShowUiModel) -> Unit) = mock()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TVShowsTheme {
                Surface {
                    TvShowListItem(
                        show,
                        onClick
                    )
                }
            }
        }
    }

    @Test
    fun `should display required TV show info`() {
        composeTestRule
            .onNodeWithText(show.name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(stringResource(R.string.vote_average_over_total, show.voteAverage))
            .assertIsDisplayed()
    }

    @Test
    fun `should invoke on click call when clicked`() {
        composeTestRule
            .onNodeWithTag(TestTagTvShowListItemCard)
            .performClick()

        verify(onClick).invoke(show)
        verifyNoMoreInteractions(onClick)
    }
}

