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
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme

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

// Start building your app here!
@Composable
fun MyApp() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            start = Offset( x = canvasWidth, y = 0f ),
            end = Offset( x = 0f, y = canvasHeight ),
            color = Color.Yellow,
            strokeWidth = 5F
        )

        drawCircle(
            color = Color.Cyan,
            center = Offset( x = canvasWidth / 2, y = canvasHeight / 2 ),
            radius = size.minDimension / 4
        )

        drawRect(
            color = Color.Red,
            topLeft = Offset(x = 0f, y = 0f),
            size = Size(width = canvasWidth, height = canvasWidth / 3)
        )

        val rectHeight = canvasHeight / 3 + canvasHeight / 6

        drawRect(
            color = Color.Yellow,
            topLeft = Offset(x = 0f, y = rectHeight),
            size = Size(width = canvasWidth, height = canvasWidth / 6)
        )

        drawRect(
            color = Color.Green,
            topLeft = Offset(x = 0f, y = rectHeight + canvasHeight / 6),
            size = Size(width = canvasWidth, height = canvasWidth / 6)
        )

        drawRect(
            color = Color.Blue,
            topLeft = Offset(x = 0f, y = rectHeight + canvasHeight / 3),
            size = Size(width = canvasWidth, height = canvasWidth / 6)
        )

        drawRect(
            color = Color.Magenta,
            topLeft = Offset(x = 0f, y = rectHeight * 2),
            size = Size(width = canvasWidth, height = canvasWidth / 6)
        )
    }

    // MaterialTheme.colors.background
    Surface(color = Color.White) {
        Text(
            text = "THURSDAY 10:45 PM",
            style = MaterialTheme.typography.subtitle1
        )
    }
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
