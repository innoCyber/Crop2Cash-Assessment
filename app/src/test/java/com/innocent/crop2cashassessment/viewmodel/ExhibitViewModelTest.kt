package com.innocent.crop2cashassessment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.innocent.crop2cashassessment.model.Exhibit
import com.innocent.crop2cashassessment.model.ExhibitsLoader
import com.innocent.crop2cashassessment.ui.ExhibitViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ExhibitViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var exhibitListViewModel: ExhibitViewModel
    private val mockExhibitListRepository: ExhibitsLoader = mock()


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `Test that getExhibitList fetch data from endpoint`() {
        val expectedExhibits = getExhibits()
        runTest {
            whenever(mockExhibitListRepository.getExhibitList()).thenReturn(
                expectedExhibits
            )
            exhibitListViewModel = ExhibitViewModel(mockExhibitListRepository)

            val exhibits =   exhibitListViewModel.exhibits

            assertThat(exhibits).isNotNull()
            assertThat(expectedExhibits).isNotNull()
        }
    }

    private fun getExhibits(): List<Exhibit> {
        val element = Exhibit(
            title = "title",
            images = listOf()
        )
        return listOf(element)
    }
}