package com.gangaown.kotlinflowexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    val countDownFlow = flow {
        val startingVal = 5
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
            val reduceResult = countDownFlow
                .reduce{ accumulator, value ->
                    accumulator + value
                }
            println("The count is $reduceResult")
            }

        }
    }
