package dev.victorhernandez.tvshows.presentation.ui.show

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.victorhernandez.tvshows.presentation.R
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme
import dev.victorhernandez.tvshows.presentation.ui.example.TvShowDetails
import dev.victorhernandez.tvshows.presentation.ui.ktx.swipeToNext
import dev.victorhernandez.tvshows.presentation.ui.ktx.swipeToPrevious
import dev.victorhernandez.tvshows.presentation.utils.stringResource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RelatedTvShowsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val shows = TvShowDetails

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TVShowsTheme {
                RelatedTvShows(
                    shows,
                    { }
                ) { }
            }
        }
    }

    @Test
    fun `should display first show info by default`() {
        composeTestRule
            .onNodeWithText(shows[0].name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(stringResource(R.string.vote_average_over_total, shows[0].voteAverage))
            .assertIsDisplayed()
    }

    @Test
    fun `should display second show info when swiping left`() {
        // when:
        composeTestRule.onNodeWithTag(TestTagRelatedTvShowsLazyRow)
            .performTouchInput {
                swipeToNext()
            }

        // then:
        composeTestRule
            .onNodeWithText(shows[1].name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(stringResource(R.string.vote_average_over_total, shows[1].voteAverage))
            .assertIsDisplayed()
    }

    @Test
    fun `should display third show info when swiping left twice`() {
        // when:
        for (i in 1..2) {
            composeTestRule.onNodeWithTag(TestTagRelatedTvShowsLazyRow)
                .performTouchInput {
                    swipeToNext()
                }
        }

        // then:
        composeTestRule
            .onNodeWithText(shows[2].name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(stringResource(R.string.vote_average_over_total, shows[2].voteAverage))
            .assertIsDisplayed()
    }

    @Test
    fun `should display last show info when swiping left after reaching the end item`() {
        // given:
        var swipeCount = 0

        // when:
        for (i in 1..(shows.size + 1)) {
            swipeCount = i
            composeTestRule.onNodeWithTag(TestTagRelatedTvShowsLazyRow)
                .performTouchInput {
                    swipeToNext()
                }
        }

        // then:
        Assert.assertTrue(swipeCount > shows.size)

        composeTestRule
            .onNodeWithText(shows[2].name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(stringResource(R.string.vote_average_over_total, shows[2].voteAverage))
            .assertIsDisplayed()
    }

    @Test
    fun `should display first show info when swiping right`() {
        // when:
        composeTestRule.onNodeWithTag(TestTagRelatedTvShowsLazyRow)
            .performTouchInput {
                swipeToPrevious()
            }

        // then:
        composeTestRule
            .onNodeWithText(shows[0].name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(stringResource(R.string.vote_average_over_total, shows[0].voteAverage))
            .assertIsDisplayed()
    }

    @Test
    fun `should display previous show info when swiping back right`() {
        // given:
        composeTestRule.onNodeWithTag(TestTagRelatedTvShowsLazyRow)
            .performTouchInput {
                swipeToNext()
            }

        // when:
        composeTestRule.onNodeWithTag(TestTagRelatedTvShowsLazyRow)
            .performTouchInput {
                swipeToPrevious()
            }

        // then:
        composeTestRule
            .onNodeWithText(shows[0].name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(stringResource(R.string.vote_average_over_total, shows[0].voteAverage))
            .assertIsDisplayed()
    }
}