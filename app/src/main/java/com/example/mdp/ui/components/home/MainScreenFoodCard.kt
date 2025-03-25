package com.example.mdp.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

@Composable
fun MainScreenFoodCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Text Column (Left Side)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp) // Space between text and image
            ) {
                Text(
                    text = "Cheesy Margherita Pizza",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Calories: 300 kcal", fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Carbs: 45g", fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Fat: 12g", fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Protein: 10g", fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
            }

            // Image Column (Right Side)
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

//data class can be implement later in separate file
data class MealInfo(
   val food_name: String,
    val calories: Int,
    val carbs: Int,
    val fat: Int,
    val protein: Int
)