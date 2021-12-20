package dev.victorhernandez.tvshows.data.source

import dev.victorhernandez.tvshows.data.model.mapper.toData
import dev.victorhernandez.tvshows.data.remote.TvShowApiService
import dev.victorhernandez.tvshows.data.utils.TvShowPageApiModelFactory
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
class TvShowRemoteDataSourceImplTest {

    private lateinit var sut: TvShowRemoteDataSourceImpl

    @Mock
    private lateinit var tvShowApiService: TvShowApiService

    @Before
    fun setUp() {
        sut = TvShowRemoteDataSourceImpl(tvShowApiService)
    }

    @Test
    fun `verify get top rated shows happy path`() = runBlockingTest {
        // given:
        val page = Random.nextInt()
        val response = TvShowPageApiModelFactory.createOne()
        whenever(tvShowApiService.getTopRatedShows(any())).doAnswer { response }

        // when:
        val result = sut.getTopRatedShows(page)

        // then:
        Assert.assertEquals(response.toData(), result)
        verify(tvShowApiService).getTopRatedShows(page)
        verifyNoMoreInteractions(tvShowApiService)
    }

    @Test
    fun `verify get top rated shows on error interactions`() = runBlockingTest {
        // given:
        val page = Random.nextInt()
        val error = Throwable()
        whenever(tvShowApiService.getTopRatedShows(any())).doAnswer { throw error }

        // when:
        var result: Throwable? = null
        try {
            sut.getTopRatedShows(page)
        } catch (t: Throwable) {
            result = t
        }

        // then:
        Assert.assertEquals(error, result)
        verify(tvShowApiService).getTopRatedShows(page)
        verifyNoMoreInteractions(tvShowApiService)
    }

    @Test
    fun `verify get similar shows happy path`() = runBlockingTest {
        // given:
        val showId = Random.nextInt()
        val page = Random.nextInt()
        val response = TvShowPageApiModelFactory.createOne()
        whenever(tvShowApiService.getSimilarShows(any(), any())).doAnswer { response }

        // when:
        val result = sut.getSimilarShows(showId, page)

        // then:
        Assert.assertEquals(response.toData(), result)
        verify(tvShowApiService).getSimilarShows(showId, page)
        verifyNoMoreInteractions(tvShowApiService)
    }

    @Test
    fun `verify get similar shows on error interactions`() = runBlockingTest {
        // given:
        val showId = Random.nextInt()
        val page = Random.nextInt()
        val error = Throwable()
        whenever(tvShowApiService.getSimilarShows(any(), any())).doAnswer { throw error }

        // when:
        var result: Throwable? = null
        try {
            sut.getSimilarShows(showId, page)
        } catch (t: Throwable) {
            result = t
        }

        // then:
        Assert.assertEquals(error, result)
        verify(tvShowApiService).getSimilarShows(showId, page)
        verifyNoMoreInteractions(tvShowApiService)
    }
}