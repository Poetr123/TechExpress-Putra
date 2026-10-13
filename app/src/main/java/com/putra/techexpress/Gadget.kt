package com.putra.techexpress

import java.text.NumberFormat
import java.util.Locale

data class Gadget(
    val id: Int,
    val nama: String,
    val brand: String,
    val harga: Long,
    val deskripsi: String,
    val stok: Int
)

val daftarGadget: List<Gadget> = listOf(
    Gadget(
        id = 1,
        nama = "Galaxy A55 5G",
        brand = "Samsung",
        harga = 5_499_000L,
        deskripsi = "Smartphone dengan layar Super AMOLED, kamera tajam, dan konektivitas 5G.",
        stok = 12
    ),
    Gadget(
        id = 2,
        nama = "Redmi Note 13 Pro",
        brand = "Xiaomi",
        harga = 4_299_000L,
        deskripsi = "Smartphone dengan layar tajam, kamera beresolusi tinggi, dan pengisian daya cepat.",
        stok = 9
    ),
    Gadget(
        id = 3,
        nama = "IdeaPad Slim 3",
        brand = "Lenovo",
        harga = 7_299_000L,
        deskripsi = "Laptop untuk belajar, produktivitas, dan pekerjaan sehari-hari.",
        stok = 6
    ),
    Gadget(
        id = 4,
        nama = "JBL Tune 520BT",
        brand = "JBL",
        harga = 699_000L,
        deskripsi = "Headphone nirkabel dengan desain ringan dan daya tahan baterai panjang.",
        stok = 18
    )
)

fun formatRupiah(amount: Long): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"))
    formatter.maximumFractionDigits = 0
    formatter.minimumFractionDigits = 0
    return formatter.format(amount)
}
