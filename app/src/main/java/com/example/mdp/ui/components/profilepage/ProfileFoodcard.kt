package com.example.mdp.ui.components.profilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mdp.data.model.Meal

@Composable
fun ProfileFoodCard(meal: Meal) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp)
    ) {
        Column {
            Text(
                text = meal.name,
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = meal.timestamp.toString(), // Convert timestamp to a readable date format if needed
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleSmall
            )
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Replace with actual food image
                contentDescription = "Food Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}