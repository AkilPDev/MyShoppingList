package com.example.shoppingapp.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppingapp.shoppingItem

@Composable
fun ShoppingItem(
    item: shoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, color = Color.LightGray),
                shape = RoundedCornerShape(20)
            ), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = "Qty ${item.quantity}", modifier = Modifier.padding(8.dp))

        IconButton(onClick = { onEditClick() }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
        IconButton(onClick = { onDeleteClick() }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}