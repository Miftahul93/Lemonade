package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    textResource = R.string.lemon_select,
                    imageResource = R.drawable.lemon_tree,
                    contentDesc = R.string.lemon_three,
                    modifier = Modifier
                        .clickable {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                )
            }

            2 -> {
                LemonTextAndImage(
                    textResource = R.string.lemon_squeeze,
                    imageResource = R.drawable.lemon_squeeze,
                    contentDesc = R.string.lemon,
                    modifier = Modifier
                        .clickable {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }
                        }
                )
            }

            3 -> {
                LemonTextAndImage(
                    textResource = R.string.lemon_drink,
                    imageResource = R.drawable.lemon_drink,
                    contentDesc = R.string.glass_of_lemonade,
                    modifier = Modifier
                        .clickable { currentStep = 4 }
                )
            }

            4 -> {
                LemonTextAndImage(
                    textResource = R.string.lemon_restart,
                    imageResource = R.drawable.lemon_restart,
                    contentDesc = R.string.empty_glass,
                    modifier = Modifier
                        .clickable { currentStep = 1 }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textResource: Int,
    imageResource: Int,
    contentDesc: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(contentDesc),
            modifier = Modifier
                .wrapContentSize()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(textResource),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonAppPreview() {
    LemonadeTheme {
        LemonApp()
    }
}