package com.example.mdp.ui.components.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mdp.R

@Composable
fun AppLogo(iconSize: Int) {
    Column (modifier = Modifier.padding(vertical = 20.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_fithall),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(iconSize.dp)
                .clip(CircleShape)
        )
    }
}