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
class GetTopRatedShowsUseCaseTest {

    private lateinit var sut: GetTopRatedShowsUseCase

    @Mock
    private lateinit var repository: TvShowRepository

    @Before
    fun setUp() {
        sut = GetTopRatedShowsUseCase(repository)
    }

    @Test
    fun `verify get top rated shows happy path`() = runBlockingTest {
        // given:
        val page = Random.nextInt()
        val response = TvShowPageDomainModelFactory.createOne()
        whenever(repository.getTopRatedShows(any())).doAnswer { response }

        // when:
        val result = sut.execute(GetTopRatedShowsUseCase.Params(page))

        // then:
        Assert.assertEquals(response, result)
        verify(repository).getTopRatedShows(page)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `verify get top rated shows on error interactions`() = runBlockingTest {
        // given:
        val page = Random.nextInt()
        val error = Throwable()
        whenever(repository.getTopRatedShows(any())).doAnswer { throw error }

        // when:
        var result: Throwable? = null
        try {
            sut.execute(GetTopRatedShowsUseCase.Params(page))
        } catch (t: Throwable) {
            result = t
        }

        // then:
        Assert.assertEquals(error, result)
        verify(repository).getTopRatedShows(page)
        verifyNoMoreInteractions(repository)
    }

}