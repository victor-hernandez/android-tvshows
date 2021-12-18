package dev.victorhernandez.tvshows.presentation.ui.shows

import app.cash.turbine.test
import dev.victorhernandez.tvshows.domain.usecase.GetTopRatedShowsUseCase
import dev.victorhernandez.tvshows.presentation.mapper.toListUiModel
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
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopRatedTvShowsViewModelTest {

    private lateinit var sut: TopRatedTvShowsViewModel

    @Mock
    private lateinit var getTopRatedShowsUseCase: GetTopRatedShowsUseCase

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        sut = TopRatedTvShowsViewModel(
            getTopRatedShowsUseCase,
            testDispatcher
        )
    }

    @Test
    fun `verify load top rated tv shows happy path interactions`() =
        testDispatcher.runBlockingTest {
            // given:
            val response = TvShowPageDomainModelFactory.createOne()
            whenever(getTopRatedShowsUseCase.execute(any())).thenAnswer { response }

            // when:
            sut.loadTopRatedTvShows()

            // then:
            verify(getTopRatedShowsUseCase).execute(GetTopRatedShowsUseCase.Params(1))
            verifyNoMoreInteractions(getTopRatedShowsUseCase)
        }

    @Test
    fun `verify load top rated tv shows on error interactions`() = testDispatcher.runBlockingTest {
        // given:
        val error = Throwable()
        whenever(getTopRatedShowsUseCase.execute(any())).thenAnswer { throw error }

        // when:
        sut.loadTopRatedTvShows()

        // then:
        verify(getTopRatedShowsUseCase).execute(GetTopRatedShowsUseCase.Params(1))
        verifyNoMoreInteractions(getTopRatedShowsUseCase)
    }

    @Test
    fun `verify ui state content successful load`() = testDispatcher.runBlockingTest {
        val response = TvShowPageDomainModelFactory.createOne()
        whenever(getTopRatedShowsUseCase.execute(any())).thenAnswer { response }
        sut.uiState.test {
            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(emptyList(), shows)
            }

            sut.loadTopRatedTvShows()

            awaitItem().apply {
                assertEquals(true, loading)
                assertEquals(emptyList(), shows)
            }

            awaitItem().apply {
                assertEquals(true, loading)
                assertEquals(response.toListUiModel(), shows)
            }

            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(response.toListUiModel(), shows)
            }
        }
    }

    @Test
    fun `verify ui state content on error load`() = testDispatcher.runBlockingTest {
        val error = Throwable()
        whenever(getTopRatedShowsUseCase.execute(any())).thenAnswer { throw error }
        sut.uiState.test {
            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(emptyList(), shows)
            }

            sut.loadTopRatedTvShows()

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