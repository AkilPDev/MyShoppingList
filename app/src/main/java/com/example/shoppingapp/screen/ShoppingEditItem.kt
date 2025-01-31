package com.example.shoppingapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppingapp.shoppingItem

@Composable
fun shoppingEditItem(
    item: shoppingItem,
    onEditCompleted: (String, Int) -> Unit
) {
    var editName by remember {
        mutableStateOf(item.name)
    }
    var editQuantity by remember {
        mutableStateOf(item.quantity.toString())
    }
    var isEdit by remember {
        mutableStateOf(item.isEditiing)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            BasicTextField(value = editName, onValueChange = {
                editName = it
            }, singleLine = true, modifier = Modifier
                .wrapContentSize()
                .padding(8.dp))

            BasicTextField(value = editQuantity, onValueChange = {
                editQuantity = it
            }, singleLine = true, modifier = Modifier
                .wrapContentSize()
                .padding(8.dp))
        }
        Button(onClick = {
            isEdit = false
            onEditCompleted(editName, editQuantity.toIntOrNull() ?: 1)
        }) {
            Text(text = "Save")
        }
    }
}