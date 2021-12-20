package dev.victorhernandez.tvshows.presentation.ui.shows

import dev.victorhernandez.tvshows.domain.usecase.GetTopRatedShowsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

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
            // when:
            // { on init }

            // then:
            verify(getTopRatedShowsUseCase).execute(GetTopRatedShowsUseCase.Params(1))
            verifyNoMoreInteractions(getTopRatedShowsUseCase)
        }
}