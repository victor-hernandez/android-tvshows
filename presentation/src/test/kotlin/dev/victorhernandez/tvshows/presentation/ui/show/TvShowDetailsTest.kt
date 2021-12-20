package dev.victorhernandez.tvshows.presentation.ui.show

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.victorhernandez.tvshows.presentation.R
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme
import dev.victorhernandez.tvshows.presentation.ui.example.TvShowDetails
import dev.victorhernandez.tvshows.presentation.utils.stringResource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TvShowDetailsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val show = TvShowDetails.first()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TVShowsTheme {
                TvShowDetails(
                    show
                )
            }
        }
    }

    @Test
    fun `should display at least the TV show name and vote average`() {
        composeTestRule
            .onNodeWithText(show.name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(stringResource(R.string.vote_average_over_total, show.voteAverage))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(show.overview)
            .assertExists()
    }

    @Test
    fun `should display TV show info`() {
        // when:
        composeTestRule.onNodeWithTag(TestTagTvShowDetailBottomSheet)
            .performTouchInput {
                swipeUp()
            }

        // then:
        composeTestRule
            .onNodeWithText(show.name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(stringResource(R.string.vote_average_over_total, show.voteAverage))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(show.overview)
            .assertIsDisplayed()
    }

    @Test
    fun `should display at least the TV show name and vote average when collapsing the bottom sheet`() {
        // given:
        composeTestRule.onNodeWithTag(TestTagTvShowDetailBottomSheet).performTouchInput {
            swipeUp()
        }

        // when:
        composeTestRule.onNodeWithTag(TestTagTvShowDetailBottomSheet).performTouchInput {
            swipeDown()
        }

        // then:
        composeTestRule
            .onNodeWithText(show.name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(stringResource(R.string.vote_average_over_total, show.voteAverage))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(show.overview)
            .assertExists()
    }
}