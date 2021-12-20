package dev.victorhernandez.tvshows.presentation.ui.show

import app.cash.turbine.test
import dev.victorhernandez.tvshows.domain.usecase.GetSimilarTvShowsUseCase
import dev.victorhernandez.tvshows.presentation.ktx.append
import dev.victorhernandez.tvshows.presentation.mapper.toUi
import dev.victorhernandez.tvshows.presentation.utils.TvShowListItemDomainModelFactory
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
        val show = TvShowListItemDomainModelFactory.createOne().toUi()
        val response = TvShowPageDomainModelFactory.createOne()
        whenever(getSimilarTvShowsUseCase.execute(any())).thenAnswer { response }

        // when:
        sut.init(show)

        // then:
        verify(getSimilarTvShowsUseCase).execute(GetSimilarTvShowsUseCase.Params(show.id, 1))
        verifyNoMoreInteractions(getSimilarTvShowsUseCase)
    }

    @Test
    fun `verify load similar tv shows on error interactions`() = testDispatcher.runBlockingTest {
        // given:
        val show = TvShowListItemDomainModelFactory.createOne().toUi()
        val error = Throwable()
        whenever(getSimilarTvShowsUseCase.execute(any())).thenAnswer { throw error }

        // when:
        sut.init(show)

        // then:
        verify(getSimilarTvShowsUseCase).execute(GetSimilarTvShowsUseCase.Params(show.id, 1))
        verifyNoMoreInteractions(getSimilarTvShowsUseCase)
    }

    @Test
    fun `verify ui state content successful load`() = testDispatcher.runBlockingTest {
        val show = TvShowListItemDomainModelFactory.createOne().toUi()
        val response = TvShowPageDomainModelFactory.createOne()
        whenever(getSimilarTvShowsUseCase.execute(any())).thenAnswer { response }

        sut.uiState.test {
            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(emptyList(), shows)
            }

            sut.init(show)

            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(listOf(show), shows)
            }

            awaitItem().apply {
                assertEquals(true, loading)
                assertEquals(listOf(show), shows)
            }

            awaitItem().apply {
                assertEquals(true, loading)
                assertEquals(listOf(show).append(response.toUi()), shows)
            }

            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(listOf(show).append(response.toUi()), shows)
            }
        }
    }

    @Test
    fun `verify ui state content on error load`() = testDispatcher.runBlockingTest {
        val show = TvShowListItemDomainModelFactory.createOne().toUi()
        val error = Throwable()
        whenever(getSimilarTvShowsUseCase.execute(any())).thenAnswer { throw error }

        sut.uiState.test {
            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(emptyList(), shows)
            }

            sut.init(show)

            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(listOf(show), shows)
            }

            awaitItem().apply {
                assertEquals(true, loading)
                assertEquals(listOf(show), shows)
            }

            awaitItem().apply {
                assertEquals(false, loading)
                assertEquals(listOf(show), shows)
            }
        }
    }
}