package br.fiap.spacefarm.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.fiap.spacefarm.models.AgroViewModel
import br.fiap.spacefarm.models.Fazenda
import br.fiap.spacefarm.models.Status

@OptIn (ExperimentalMaterial3Api::class)
@Composable
fun MonitoramentoScreen(navController: NavController, viewModel: AgroViewModel) {
    val fazendas = viewModel.getFazendasFiltradas()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Áreas Monitoradas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            // Interação: Filtros
            Text("Filtrar por Status:", style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(bottom = 8.dp))
            ScrollableRow {
                listOf("TODOS", "NORMAL", "ATENCAO", "CRITICO").forEach { filtro ->
                    val isSelected = viewModel.filtroStatus == filtro
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.alterarFiltro(filtro) },
                        label = { Text(filtro) },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Lista de Dados (LazyColumn)
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(fazendas) { fazenda ->
                    FazendaCard(fazenda = fazenda, onClick = {
                        navController.navigate("detalhes/${fazenda.id}")
                    })
                }
            }
        }
    }
}

@Composable
fun FazendaCard(fazenda: Fazenda, onClick: () -> Unit) {
    val corStatus = when (fazenda.status) {
        Status.NORMAL -> Color(0xFF4CAF50) // Verde
        Status.ATENCAO -> Color(0xFFFFC107) // Amarelo
        Status.CRITICO -> Color(0xFFF44336) // Vermelho
    }

    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = fazenda.nome, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = fazenda.localizacao, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = "NDVI: ${fazenda.ndvi}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
            }
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .padding(end = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.foundation.Canvas(modifier = Modifier.size(16.dp)) {
                    drawCircle(color = corStatus)
                }
            }
        }
        Text(
            text = "Toque para ver detalhes",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.End).padding(end = 16.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun ScrollableRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(androidx.compose.foundation.rememberScrollState()),
        content = content
    )
}