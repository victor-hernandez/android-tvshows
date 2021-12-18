package dev.victorhernandez.tvshows.presentation.ui.show

import app.cash.turbine.test
import dev.victorhernandez.tvshows.domain.usecase.GetSimilarTvShowsUseCase
import dev.victorhernandez.tvshows.presentation.mapper.toDetailUiModel
import dev.victorhernandez.tvshows.presentation.utils.TvShowPageDomainModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.random.Random
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvShowDetailsViewModelTest {

    private lateinit var sut: TvShowDetailsViewModel

    @Mock
    private lateinit var getSimilarTvShowsUseCase: GetSimilarTvShowsUseCase

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        sut = TvShowDetailsViewModel(
            getSimilarTvShowsUseCase,
            testDispatcher
        )
    }

    @Test
    fun `verify load similar tv shows happy path`() = testDispatcher.runBlockingTest {
        // given:
        val showId = Random.nextInt()
        val response = TvShowPageDomainModelFactory.createOne()
        whenever(getSimilarTvShowsUseCase.execute(any())).thenAnswer { response }

        // when:
        sut.loadNextSimilarTvShows(showId)

        // then:
        verify(getSimilarTvShowsUseCase).execute(GetSimilarTvShowsUseCase.Params(showId, 1))
        verifyNoMoreInteractions(getSimilarTvShowsUseCase)
    }

    @Test
    fun `verify load similar tv shows on error interactions`() = testDispatcher.runBlockingTest {
        // given:
        val showId = Random.nextInt()
        val error = Throwable()
        whenever(getSimilarTvShowsUseCase.execute(any())).thenAnswer { throw error }

        // when:
        sut.loadNextSimilarTvShows(showId)

        // then:
        verify(getSimilarTvShowsUseCase).execute(GetSimilarTvShowsUseCase.Params(showId, 1))
        verifyNoMoreInteractions(getSimilarTvShowsUseCase)
    }

    @Test
    fun `verify ui state content successful load`() = testDispatcher.runBlockingTest {
        val showId = Random.nextInt()
        val response = TvShowPageDomainModelFactory.createOne()
        whenever(getSimilarTvShowsUseCase.execute(any())).thenAnswer { response }
        sut.uiState.test {
            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(emptyList(), shows)
            }

            sut.loadNextSimilarTvShows(showId)

            awaitItem().apply {
                assertEquals(true, loading)
                assertEquals(emptyList(), shows)
            }

            awaitItem().apply {
                assertEquals(true, loading)
                assertEquals(response.toDetailUiModel(), shows)
            }

            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(response.toDetailUiModel(), shows)
            }
        }
    }

    @Test
    fun `verify ui state content on error load`() = testDispatcher.runBlockingTest {
        val showId = Random.nextInt()
        val error = Throwable()
        whenever(getSimilarTvShowsUseCase.execute(any())).thenAnswer { throw error }
        sut.uiState.test {
            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(emptyList(), shows)
            }

            sut.loadNextSimilarTvShows(showId)

            awaitItem().apply {
                assertEquals(true, loading)
                assertEquals(emptyList(), shows)
            }

            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(emptyList(), shows)
            }
        }
    }
}