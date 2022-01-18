package com.gangaown.kotlinflowexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gangaown.kotlinflowexample.DispatcherProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val dispatchers: DispatcherProvider): ViewModel() {

    private val _sharedFlow = MutableSharedFlow<Int>(replay = 0)
    val sharedFlow = _sharedFlow.asSharedFlow()
    init {

        viewModelScope.launch (dispatchers.main) {
            sharedFlow.collect{
                delay(2000L)
                println("The received number is $it")
            }
        }

        viewModelScope.launch (dispatchers.main){
            sharedFlow.collect{
                delay(3000L)
                println("The received number is $it")
            }
        }
        squareFlow(6)
    }

   fun squareFlow(number:Int){
        viewModelScope.launch (dispatchers.main) {
                _sharedFlow.emit(number * number)
        }
    }
}
