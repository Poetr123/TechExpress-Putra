package com.putra.techexpress

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(
    currentPage: String,
    onKatalogClick: () -> Unit,
    onProfilClick: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentPage == AppRoutes.Katalog,
            onClick = onKatalogClick,
            icon = { Text("▦") },
            label = { Text("Katalog") }
        )
        NavigationBarItem(
            selected = currentPage == AppRoutes.Profil,
            onClick = onProfilClick,
            icon = { Text("●") },
            label = { Text("Profil") }
        )
    }
}
