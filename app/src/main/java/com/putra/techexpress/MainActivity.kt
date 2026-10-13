package com.putra.techexpress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TechExpressRoot()
        }
    }
}

@Composable
fun TechExpressRoot() {
    val navController = rememberNavController()

    var diskonTerpasang by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.Login
    ) {

        // LOGIN
        composable(AppRoutes.Login) {
            LoginPage(
                onLogin = {
                    navController.navigate(AppRoutes.Katalog) {
                        popUpTo(AppRoutes.Login) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        // KATALOG
        composable(AppRoutes.Katalog) {
            KatalogPage(
                gadgets = daftarGadget,
                onGadgetClick = { gadget ->
                    navController.navigate(
                        AppRoutes.detail(gadget.id)
                    )
                },
                onKatalogClick = {
                    // Sudah berada di katalog.
                },
                onProfilClick = {
                    navController.navigate(AppRoutes.Profil) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // PROFIL
        composable(AppRoutes.Profil) {
            ProfilPage(
                kodeMember = "TE-2026-001",
                onLogout = {
                    diskonTerpasang = 0

                    navController.navigate(AppRoutes.Login) {
                        popUpTo(AppRoutes.Profil) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onKatalogClick = {
                    val kembaliKeKatalog =
                        navController.popBackStack(
                            AppRoutes.Katalog,
                            inclusive = false
                        )

                    if (!kembaliKeKatalog) {
                        navController.navigate(AppRoutes.Katalog) {
                            launchSingleTop = true
                        }
                    }
                },
                onProfilClick = {
                    // Sudah berada di profil.
                }
            )
        }

        // DETAIL GADGET
        composable(
            route = AppRoutes.Detail,
            arguments = listOf(
                navArgument("gadgetId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val gadgetId = backStackEntry.arguments
                ?.getInt("gadgetId")

            val gadget = daftarGadget.firstOrNull {
                it.id == gadgetId
            }

            if (gadget != null) {
                DetailPage(
                    gadget = gadget,
                    diskonPersen = diskonTerpasang,
                    onBack = {
                        navController.popBackStack()
                    },
                    onPilihDiskon = {
                        navController.navigate(AppRoutes.Diskon)
                    }
                )
            } else {
                // ID tidak ditemukan: kembali ke halaman sebelumnya.
                navController.popBackStack()
            }
        }

        // VOUCHER DISKON
        composable(AppRoutes.Diskon) {
            DiskonPage(
                diskonTerpasang = diskonTerpasang,
                onBack = {
                    navController.popBackStack()
                },
                onPilihDiskon = { persen ->
                    diskonTerpasang = persen
                    navController.popBackStack()
                }
            )
        }
    }
}