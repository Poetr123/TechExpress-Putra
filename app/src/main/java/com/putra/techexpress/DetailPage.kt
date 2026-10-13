package com.putra.techexpress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailPage(
    gadget: Gadget,
    diskonPersen: Int,
    onBack: () -> Unit,
    onPilihDiskon: () -> Unit
) {
    val diskonAman = diskonPersen.coerceIn(0, 100)
    val hargaAkhir = gadget.harga * (100L - diskonAman) / 100L

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedButton(onClick = onBack) {
                Text("Kembali")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = gadget.nama,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(text = gadget.brand, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("Deskripsi", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(gadget.deskripsi)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Stok tersedia: ${gadget.stok}")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text("Harga asli", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = formatRupiah(gadget.harga),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Diskon terpasang: $diskonAman%", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = formatRupiah(hargaAkhir),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onPilihDiskon, modifier = Modifier.fillMaxWidth()) {
                Text("Pilih Voucher Diskon")
            }
        }
    }
}
