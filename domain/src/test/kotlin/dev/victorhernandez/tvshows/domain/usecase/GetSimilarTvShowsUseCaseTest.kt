package dev.victorhernandez.tvshows.domain.usecase

import dev.victorhernandez.tvshows.domain.repository.TvShowRepository
import dev.victorhernandez.tvshows.domain.utils.TvShowPageDomainModelFactory
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class GetSimilarTvShowsUseCaseTest {

    private lateinit var sut: GetSimilarTvShowsUseCase

    @Mock
    private lateinit var repository: TvShowRepository

    @Before
    fun setUp() {
        sut = GetSimilarTvShowsUseCase(repository)
    }

    @Test
    fun `verify get top rated shows happy path`() = runBlockingTest {
        // given:
        val showId = Random.nextInt()
        val page = Random.nextInt()
        val response = TvShowPageDomainModelFactory.createOne()
        whenever(repository.getSimilarShows(any(), any())).doAnswer { response }

        // when:
        val result = sut.execute(GetSimilarTvShowsUseCase.Params(showId, page))

        // then:
        Assert.assertEquals(response, result)
        verify(repository).getSimilarShows(showId, page)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `verify get top rated shows on error interactions`() = runBlockingTest {
        // given:
        val showId = Random.nextInt()
        val page = Random.nextInt()
        val error = Throwable()
        whenever(repository.getSimilarShows(any(), any())).doAnswer { throw error }

        // when:
        var result: Throwable? = null
        try {
            sut.execute(GetSimilarTvShowsUseCase.Params(showId, page))
        } catch (t: Throwable) {
            result = t
        }

        // then:
        Assert.assertEquals(error, result)
        verify(repository).getSimilarShows(showId, page)
        verifyNoMoreInteractions(repository)
    }

}