package com.gangaown.kotlinflowexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gangaown.kotlinflowexample.ui.HomeViewModel
import com.gangaown.kotlinflowexample.ui.theme.KotlinFlowExampleTutorialTheme
import kotlinx.coroutines.flow.collect


class HomeActivity : ComponentActivity() {

    private lateinit var homeViewModelFactory:HomeViewModelFactory
    private lateinit var dispatchers: DefaultDispatchers


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dispatchers = DefaultDispatchers()
        homeViewModelFactory = HomeViewModelFactory(dispatchers)
        setContent {
                KotlinFlowExampleTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(" .....")
                }
                val homeViewModel: HomeViewModel = viewModel(factory = homeViewModelFactory)
                homeViewModel.squareFlow(4)
                LaunchedEffect(key1 = true){
                    homeViewModel.sharedFlow.collect { number ->
                        setContent { DisplayNumber(number = number) }
                    }
                }

            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Welcome $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KotlinFlowExampleTutorialTheme {
        Greeting("Android")
    }
}


@Composable
fun DisplayNumber(number:Int) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = number.toString(),
            fontSize = 50.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}