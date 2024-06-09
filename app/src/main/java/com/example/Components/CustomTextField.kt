package com.example.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboard_Type: KeyboardType,
    placeHolder: String ="",
    prefixIcon: @Composable () -> Unit,
    imeAction: ImeAction,
    onActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it },
        singleLine = isSingleLine,
        label = { Text(text = labelId) },
        leadingIcon = prefixIcon,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboard_Type,
            imeAction = imeAction
        ), // Set keyboardType within keyboardOptions
        keyboardActions = onActions,
        placeholder = { Text(text = placeHolder) }, // Set placeholder correctly
        enabled = enabled, // Corrected enabled parameter
        modifier = modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp).fillMaxWidth(),
        textStyle = TextStyle(fontSize = 18.sp, color = Color.DarkGray),
        )

}