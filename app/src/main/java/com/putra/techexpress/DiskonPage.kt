package com.putra.techexpress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class Voucher(
    val persen: Int,
    val judul: String,
    val keterangan: String
)

private val daftarVoucher = listOf(
    Voucher(0, "Tanpa Diskon", "Harga produk tetap normal."),
    Voucher(10, "Hemat 10%", "Potongan harga sebesar 10% untuk produk."),
    Voucher(15, "Hemat 15%", "Potongan harga sebesar 15% untuk produk."),
    Voucher(20, "Hemat 20%", "Potongan harga sebesar 20% untuk produk.")
)

@Composable
fun DiskonPage(
    diskonTerpasang: Int,
    onBack: () -> Unit,
    onPilihDiskon: (Int) -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 12.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                OutlinedButton(onClick = onBack) {
                    Text("← Kembali")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Voucher Diskon",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text("Pilih satu voucher untuk diterapkan.")
                Spacer(modifier = Modifier.height(8.dp))
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items = daftarVoucher, key = { it.persen }) { voucher ->
                    val sedangDipakai = voucher.persen == diskonTerpasang
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (sedangDipakai) {
                                MaterialTheme.colorScheme.secondaryContainer
                            } else {
                                MaterialTheme.colorScheme.surface
                            }
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = voucher.judul,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(voucher.keterangan)
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = { onPilihDiskon(voucher.persen) },
                                modifier = Modifier.fillMaxWidth(),
                                enabled = !sedangDipakai
                            ) {
                                Text(if (sedangDipakai) "Sedang Digunakan" else "Gunakan Voucher")
                            }
                        }
                    }
                }
            }
        }
    }
}
