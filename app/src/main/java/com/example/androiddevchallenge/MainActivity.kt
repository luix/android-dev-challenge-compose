/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.palettePink
import com.example.androiddevchallenge.ui.theme.palettePurple
import com.example.androiddevchallenge.ui.theme.paletteRed

//import androidx.compose.animation.core.transitionDefinition
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.transition
//import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}


private enum class ThemeState {
    IDLE, SHRINKING
}


// Start building your app here!
@Composable
fun MyApp() {
    //val themeState = remember { mutableStateOf(ThemeState.IDLE) }

    val offsetY: Float by animateFloatAsState(targetValue = -150f)

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = palettePurple[400] ?: Color.Red,
        targetValue = palettePurple[500] ?: Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    //val deltaXAnim = rememberInfiniteTransition()
    /*val dx by deltaXAnim.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing)
        )
    )*/

    val screenWidthPx = with(LocalDensity.current) {
        (LocalConfiguration.current.screenHeightDp * density)
    }
    val animTranslate by animateFloatAsState(
        targetValue = screenWidthPx,
        animationSpec = TweenSpec(10000, easing = LinearEasing)
    )

    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            translate(top = animTranslate) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                val rowHeight = 150f
                val headerHeight = rowHeight * 4

                drawLine(
                    start = Offset(x = canvasWidth, y = 0f),
                    end = Offset(x = 0f, y = canvasHeight),
                    color = Color.Yellow,
                    strokeWidth = 5F
                )

                // draw header with time, weather temperature and city
                drawRect(
                    color = getNextColor(),
                    topLeft = Offset(x = 0f, y = 0f),
                    size = Size(width = canvasWidth, height = headerHeight)
                )

                var heightOffset = headerHeight
                while (heightOffset < canvasHeight) {
                    drawRect(
                        color = getNextColor(),
                        topLeft = Offset(x = 0f, y = heightOffset + offsetY),
                        size = Size(width = canvasWidth, height = rowHeight)
                    )
                    heightOffset += rowHeight
                }

                drawCircle(
                    color = paletteRed[100] ?: Color.Yellow,
                    center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                    radius = size.minDimension / 4
                )
            }
        }
    )

    // MaterialTheme.colors.background
    Surface(color = color) {
        Text(
            text = "THURSDAY 10:45 PM",
            style = MaterialTheme.typography.subtitle1
        )
    }
}




private var idx = 3
private val colorArray = listOf(100, 200, 300, 400, 500, 600, 700, 800, 900)
private var up = false
private fun getNextColor() : Color {
    if (up) {
        idx += 1
    } else {
        idx -= 1
    }
    val key = colorArray[idx]
    up = when (idx) {
        colorArray.size - 1 -> false
        0 -> true
        else -> up
    }
    return palettePink[key] ?: Color.Magenta
    // Log.d("getNextColor", "color: ${color.value} , idx: $idx , up: $up, key> $key")
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}


/*

        val running = true
        val animating = false

        val rotationY = animatedProgress.value


        while(running) {

            if (animating) {

            } else {

            }
        }


 */