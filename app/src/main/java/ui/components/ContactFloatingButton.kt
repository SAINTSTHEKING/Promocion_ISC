package com.example.promocion_isc.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ContactFloatingButton() {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Contactar a Admisiones") },
            text = {
                Column {
                    Text("Elige el medio de contacto:")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/2722833260")) // Cambia por número real
                            context.startActivity(intent)
                            showDialog = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.QuestionAnswer, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("WhatsApp")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            try {
                                // 1. Es vital agregar "mailto:" antes del correo
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:difusion@zongolica.tecnm.mx")
                                    // Opcional: puedes agregar un asunto predeterminado
                                    putExtra(Intent.EXTRA_SUBJECT, "Informes sobre ISC")
                                }
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                // 2. Manejar el error si no hay app de correo instalada
                                // Aquí podrías mostrar un Toast o un mensaje al usuario
                                println("No se encontró una aplicación de correo: ${e.message}")
                            }
                            showDialog = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Email, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Correo electrónico")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    FloatingActionButton(
        onClick = { showDialog = true },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(Icons.Default.Phone, contentDescription = "Contactar")
    }
}
