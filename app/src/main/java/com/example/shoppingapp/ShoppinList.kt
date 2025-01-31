package com.example.shoppingapp

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoppingapp.screen.ShoppingItem
import com.example.shoppingapp.screen.shoppingEditItem

data class shoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    val isEditiing: Boolean = false
)

fun deleteItem(item: shoppingItem, exitItem: shoppingItem) {


}

@Preview
@Composable
fun shopingItem(){
    ShoppingListApp()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp() {
    var sItems by remember { mutableStateOf(listOf<shoppingItem>()) }
    var sDeleteItems by remember { mutableStateOf<shoppingItem?> (null) }
    var showDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    var itemTotalItem by remember { mutableStateOf(0) }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Enjoy Shopping")},
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = Color.Blue,
            ),)
    }, bottomBar = {},
        floatingActionButton = {}) {
            innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { showDialog = true }) {
                Text(text = "Add Item")
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(sItems) {

                        item ->
                    if (item.isEditiing) {
                        shoppingEditItem(item = item, onEditCompleted =
                        { editedName, editedQuantity ->
                            sItems = sItems.map {
                                it.copy(
                                    isEditiing = false
                                )
                            }
                            val editItem = sItems.find { it.id == item.id }
                            editItem?.let {
                                it.name = editedName
                                it.quantity = editedQuantity
                            }
                        })


                    } else {
                        ShoppingItem(item = item,
                            onEditClick = {
                                sItems = sItems.map { it.copy(isEditiing = it.id == item.id) }
                            }, onDeleteClick = {
//                        sItems = sItems - item
                                sDeleteItems = item
                                showDeleteDialog = true

                            })
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "Total Item: ${sItems.size} Quanity: $itemTotalItem")

                Button(onClick = {
                    itemTotalItem = sItems.sumOf { it.quantity }
                }) {
                    Text(text = "Order")
                }
            }

        }
    }

    if(showDeleteDialog){
        AlertDialog(onDismissRequest = { showDeleteDialog = false }, confirmButton = { Row (modifier = Modifier
            .fillMaxWidth()
            .padding(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { showDeleteDialog = false }) {
                Text(text = "Cancel")
            }
            Button(onClick = {
                showDeleteDialog = false
                sDeleteItems?.let {
                    sItems = sItems - it
                }
            }) {
                Text(text = "Delete")

            }
        } },
            title = { Text(
            text = "Are you sure to delete?"
        )})
    }

    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false }, confirmButton = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { showDialog = false }) {
                    Text(text = "Cancel")
                }
                Button(onClick = {
                    if (itemName.isNotBlank() && itemQuantity.isNotBlank()) {
                        val newItem = shoppingItem(
                            id = sItems.size + 1,
                            name = itemName,
                            quantity = itemQuantity.toInt()
                        )
                        sItems = sItems + newItem
                        showDialog = false
                        itemName = ""
                        itemQuantity = ""
                    } else {

                    }
                }) {
                    Text(text = "Add")
                }
            }
        },
            title = { Text(text = "Add Shopping Item") },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName, onValueChange = {
                            itemName = it
                        }, singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = {
                            itemQuantity = it
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                    )
                }
            }
        )
    }
}




