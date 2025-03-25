package com.example.mdp.ui.components.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomBarIcon(
    navController: NavController,
    currentRoute: String?,
    route: String,
    outlinedIcon: ImageVector,
    roundedIcon: ImageVector,
    description: String
) {
    val isSelected = currentRoute == route
    IconButton(onClick = { navController.navigate(route) }) {
        Icon(
            imageVector = if (isSelected) roundedIcon else outlinedIcon,
            contentDescription = description,
            modifier = Modifier.size(32.dp),
            tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
        )
    }
}