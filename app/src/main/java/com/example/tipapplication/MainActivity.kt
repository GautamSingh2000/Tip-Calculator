package com.example.tipapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Components.CustomTextField
import com.example.Utils.TotalPerPersionTipCalculation
import com.example.Utils.TotalTipCalculation
import com.example.Wigits.RoundedIconButton
import com.example.buttonType
import com.example.tipapplication.ui.theme.Purple80
import com.example.tipapplication.ui.theme.TIPApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Column {
                    MainContent()
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    TIPApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Composable
fun TopHeader(perPersionValue: Double = 0.0) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        color = Purple80,
        shape = CircleShape.copy(CornerSize(30.dp)),
    ) {
        var total = "%.2f".format(perPersionValue)
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Total Per Person", fontWeight = FontWeight.W500,
                fontSize = 25.sp, textAlign = TextAlign.Center
            )
            Text(
                text = "$ $total", fontWeight = FontWeight.W800,
                fontSize = 35.sp, textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun MainContent() {
    BillForm(
        modifier = Modifier
    ) {
        Log.e("Bill", it)
    }
}

@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = {},
) {

    var numberOfPeron by remember {
        mutableStateOf(1)
    }

    var billState = remember {
        mutableStateOf("")
    }

    var actionState by remember(billState.value) {
        mutableStateOf(
            billState.value.trim().isNotEmpty()
        )
    }

    var tipAmount by remember {
        mutableStateOf(0.0)
    }

    var sliderState by remember {
        mutableStateOf(0f)
    }

    var tipPercentage = (sliderState * 100).toInt()

    var totalTipPerPersonAmount by remember {
        mutableStateOf(0.0)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    TopHeader(totalTipPerPersonAmount)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(2.dp, Color.LightGray),
        shadowElevation = 2.dp,
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            CustomTextField(
                valueState = billState,
                labelId = "Enter bill",
                enabled = true,
                isSingleLine = true,
                keyboard_Type = KeyboardType.Number,
                prefixIcon = {
                    Icon(
                        imageVector = Icons.Rounded.AttachMoney,
                        contentDescription = "Money icon"
                    )
                },
                imeAction = ImeAction.Next,
                onActions = KeyboardActions {
                    if (!actionState) return@KeyboardActions
                    onValChange(billState.value)
                    keyboardController?.hide()
                })
            if (actionState) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Split",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(120.dp))
                    Row(modifier = Modifier, horizontalArrangement = Arrangement.End) {
                        RoundedIconButton(
                            modifier = Modifier,
                            buttonType.Sub,
                            numberOfPeron
                        ) {
                            numberOfPeron = it
                            Log.e("Button", "vale change by sub $it")

                            totalTipPerPersonAmount = TotalPerPersionTipCalculation(
                                totalBill = (billState.value).toDouble(),
                                tipPercentage = tipPercentage,
                                spliInPersion = numberOfPeron
                            )
                        }
                        Text(
                            text = "$numberOfPeron",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically)
                                .padding(horizontal = 10.dp),
                            textAlign = TextAlign.Center
                        )
                        RoundedIconButton(
                            modifier = Modifier,
                            buttonType.Add, numberOfPeron
                        ) {
                            numberOfPeron = it
                            Log.e("Button", "vale change by add $it")

                            totalTipPerPersonAmount = TotalPerPersionTipCalculation(
                                totalBill = (billState.value).toDouble(),
                                tipPercentage = tipPercentage,
                                spliInPersion = numberOfPeron
                            )
                        }
                    }

                }

                Row(modifier = Modifier.padding(vertical = 12.dp)) {
                    Text(text = "Tip")
                    Spacer(modifier = Modifier.width(170.dp))
                    Text(text = "$ $tipAmount")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "$tipPercentage %",
                    modifier.align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Slider(modifier = Modifier.padding(horizontal = 15.dp),
//                steps = 6,
                    value = sliderState,
                    onValueChange = {
                        sliderState = it
                        tipAmount = TotalTipCalculation(
                            totalBill = (billState.value).toDouble(),
                            tipPercentage = tipPercentage
                        )

                        totalTipPerPersonAmount = TotalPerPersionTipCalculation(
                            totalBill = (billState.value).toDouble(),
                            tipPercentage = tipPercentage,
                            spliInPersion = numberOfPeron
                        )
                    }
                )
            } else {
//                Box {
//
//                }

            }
        }

    }
}