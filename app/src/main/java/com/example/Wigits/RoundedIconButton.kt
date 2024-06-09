package com.example.Wigits

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buttonType

@Preview
@Composable
private fun buttonpreview() {
    RoundedIconButton(  modifier=Modifier) {
        Log.e("Button", "vale change by add $it")
    }
}


    val modifiedIcomSize = Modifier.size(40.dp)
    @Composable
    fun RoundedIconButton(
        modifier: Modifier = Modifier,
        Type: buttonType = buttonType.Add,
        personCount: Int = 1,
        onValChange: (Int) -> Unit = {},
    ) {
        var buttonType: ImageVector
        var Count: Int
        when (Type) {
            com.example.buttonType.Sub -> buttonType = Icons.Default.Remove
            com.example.buttonType.Add -> buttonType = Icons.Default.Add
        }
        Card(modifier = modifier
            .size(45.dp)
            .padding(5.dp)
            .clickable(
            ) {

                when (Type) {
                    com.example.buttonType.Sub -> if (personCount > 1) {
                        Count = (personCount - 1)
                    } else {
                        Count = 1
                    }

                    com.example.buttonType.Add -> Count = (personCount + 1)
                }
                onValChange(Count)
            }.then(modifiedIcomSize),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(Color.White),
            shape = CircleShape
        ) {
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment =  Alignment.Center) {
                Icon(
                    imageVector = buttonType,
                    contentDescription = "Icon Image",
                    tint = Color.Black,
//                    modifier = modifier.align(alignment = Alignment.CenterHorizontally)
                )
            }
        }

    }