package com.example.movieapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import com.example.android.devbyteviewer.domain.Movie
import com.example.movieapp.MainCoroutineRule
import com.example.movieapp.data.source.FakeLocalDataSource
import com.example.movieapp.data.source.FakeRemoteDataSource
import com.example.movieapp.data.source.local_data.LocalDataSource
import com.example.movieapp.data.source.remote_data.NetworkMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.mock


@ExperimentalCoroutinesApi
class RepositoryTest {
    var localData = mutableListOf(Movie(1), Movie(2))
    var remoteData = mutableListOf(NetworkMovie(3), NetworkMovie(4))
    private lateinit var fakeLocalDataSource: FakeLocalDataSource
    private lateinit var fakeRemoteDataSource: FakeRemoteDataSource
    private lateinit var repository: MovieGetway
   private lateinit var  boundaryCallback:MovieBoundaryCallback
    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
     var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createRepository() {
         fakeLocalDataSource = FakeLocalDataSource(localData)
        fakeRemoteDataSource = FakeRemoteDataSource(remoteData)
         boundaryCallback = MovieBoundaryCallback(fakeRemoteDataSource,fakeLocalDataSource,Dispatchers.Main)
        val fakeData = mockPagedList(localData)
        repository = Repository(MutableLiveData(fakeData),boundaryCallback)
    }

    @Test
    fun getMovies_fromLocalData_notNull() = mainCoroutineRule.runBlockingTest{
    val movieResult =  repository.getUpComingMovies()
        val data = movieResult.data.getOrAwaitValue()
       // repository data size  equal to local data size
        assertEquals(data.size,2)
    }
    @Test
    fun getMovies_onZeroLoad_localDataSizeIncrease() = mainCoroutineRule.runBlockingTest{
        // on zero load insert network list which size equals to 2
        boundaryCallback.onZeroItemsLoaded()
        // local  data size  equal to local data size + network data size
        assertEquals(fakeLocalDataSource.movieList.size,4)
    }
    @Test
    fun getMovies_onItemEnd_localDataSizeIncrease() = mainCoroutineRule.runBlockingTest{
        // on item end  load insert network list which size equals to 2
        boundaryCallback.onItemAtEndLoaded(Movie(2))
        // local  data size  equal to local data size + network data size
        assertEquals(fakeLocalDataSource.movieList.size,4)
    }
    @Test
    fun getMovies_null_showError() = mainCoroutineRule.runBlockingTest{
        // set remote data to null
        fakeRemoteDataSource.movieList = null
        boundaryCallback.onZeroItemsLoaded()
        // local  data size  equal to local data size + network data size
        assertThat(boundaryCallback.networkErrors.getOrAwaitValue(), CoreMatchers.`is`(true))

    }
}
fun <T> mockPagedList(list: List<T>): PagedList<T> {
    val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
    Mockito.`when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
        val index = invocation.arguments.first() as Int
        list[index]
    }
    Mockito.`when`(pagedList.size).thenReturn(list.size)
    return pagedList
}