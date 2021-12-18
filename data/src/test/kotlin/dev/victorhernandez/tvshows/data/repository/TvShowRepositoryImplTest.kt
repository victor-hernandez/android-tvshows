package dev.victorhernandez.tvshows.data.repository

import dev.victorhernandez.tvshows.data.model.mapper.toDomain
import dev.victorhernandez.tvshows.data.source.TvShowRemoteDataSource
import dev.victorhernandez.tvshows.data.utils.TvShowPageDataModelFactory
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
class TvShowRepositoryImplTest {

    private lateinit var sut: TvShowRepositoryImpl

    @Mock
    private lateinit var remoteDataSource: TvShowRemoteDataSource

    @Before
    fun setUp() {
        sut = TvShowRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `verify get top rated shows happy path`() = runBlockingTest {
        // given:
        val page = Random.nextInt()
        val response = TvShowPageDataModelFactory.createOne()
        whenever(remoteDataSource.getTopRatedShows(any())).doAnswer { response }

        // when:
        val result = sut.getTopRatedShows(page)

        // then:
        Assert.assertEquals(response.toDomain(), result)
        verify(remoteDataSource).getTopRatedShows(page)
        verifyNoMoreInteractions(remoteDataSource)
    }

    @Test
    fun `verify get top rated shows on error interactions`() = runBlockingTest {
        // given:
        val page = Random.nextInt()
        val error = Throwable()
        whenever(remoteDataSource.getTopRatedShows(any())).doAnswer { throw error }

        // when:
        var result: Throwable? = null
        try {
            sut.getTopRatedShows(page)
        } catch (t: Throwable) {
            result = t
        }

        // then:
        Assert.assertEquals(error, result)
        verify(remoteDataSource).getTopRatedShows(page)
        verifyNoMoreInteractions(remoteDataSource)
    }

    @Test
    fun `verify similar TV shows happy path`() = runBlockingTest {
        // given:
        val showId = Random.nextInt()
        val page = Random.nextInt()
        val response = TvShowPageDataModelFactory.createOne()
        whenever(remoteDataSource.getSimilarShows(any(), any())).doAnswer { response }

        // when:
        val result = sut.getSimilarShows(showId, page)

        // then:
        Assert.assertEquals(response.toDomain(), result)
        verify(remoteDataSource).getSimilarShows(showId, page)
        verifyNoMoreInteractions(remoteDataSource)
    }

    @Test
    fun `verify get similar TV shows on error interactions`() = runBlockingTest {
        // given:
        val showId = Random.nextInt()
        val page = Random.nextInt()
        val error = Throwable()
        whenever(remoteDataSource.getSimilarShows(any(), any())).doAnswer { throw error }

        // when:
        var result: Throwable? = null
        try {
            sut.getSimilarShows(showId, page)
        } catch (t: Throwable) {
            result = t
        }

        // then:
        Assert.assertEquals(error, result)
        verify(remoteDataSource).getSimilarShows(showId, page)
        verifyNoMoreInteractions(remoteDataSource)
    }
}