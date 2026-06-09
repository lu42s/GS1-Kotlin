package br.fiap.spacefarm.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.fiap.spacefarm.models.AgroViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesScreen(navController: NavController, viewModel: AgroViewModel, id: String) {
    val fazenda = viewModel.getFazendaPorId(id)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Área") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        if (fazenda != null) {
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = fazenda.nome, fontSize = 24.sp, fontWeight = FontWeight.Bold)

                Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🛰️ Última Atualização", fontWeight = FontWeight.Medium)
                        Text(fazenda.ultimaAtualizacao, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🌱 Índice de Vegetação (NDVI)", fontWeight = FontWeight.Medium)
                        Text("${fazenda.ndvi} (Escala 0.0 a 1.0)", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        LinearProgressIndicator(
                            progress = { fazenda.ndvi },
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp).height(8.dp)
                        )
                    }
                }

                Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("💧 Umidade do Solo", fontWeight = FontWeight.Medium)
                        Text("${fazenda.umidadeSolo}%", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("⚠️ Status do Monitoramento", fontWeight = FontWeight.Medium)
                        Text(fazenda.status.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Fazenda não encontrada.")
            }
        }
    }
}