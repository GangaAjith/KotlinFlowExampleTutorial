package com.gangaown.kotlinflowexample.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        homeViewModel = HomeViewModel(testDispatchers)
    }

    @Test
    fun `squareFlow, return square of the number`() = runBlocking{

          val job = launch {
               homeViewModel.sharedFlow.test{
                   val emission = awaitItem()
                   assertThat(emission).isEqualTo(36)
                   cancelAndConsumeRemainingEvents()
               }
          }

       homeViewModel.squareFlow(6)
       job.join()
       job.cancel()
    }


}