package com.gangaown.kotlinflowexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    val countDownFlow = flow {
        val startingVal = 10
        var curValue = startingVal
        emit(startingVal)
        while (curValue>0){
            delay(1000L)
            curValue--
            emit(curValue)
        }
    }
    init {
        collectFlow()
    }

    private fun collectFlow(){

        viewModelScope.launch {
            val count = countDownFlow
                .filter { time ->
                    time % 2 == 0
                }
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    println(time)
                }
                .count { time->
                    time % 2 == 0
                }
            println("The count is $count")
            }

        }
    }
