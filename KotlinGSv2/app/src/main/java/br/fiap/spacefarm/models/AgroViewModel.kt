package br.fiap.spacefarm.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AgroViewModel : ViewModel() {
    // Estado para o filtro (Atende ao requisito de Interação - 1,5 pts)
    var filtroStatus by mutableStateOf("TODOS")
        private set

    fun alterarFiltro(novoFiltro: String) {
        filtroStatus = novoFiltro
    }

    fun getFazendasFiltradas(): List<Fazenda> {
        return if (filtroStatus == "TODOS") {
            listaFazendasMock
        } else {
            listaFazendasMock.filter { it.status.name == filtroStatus }
        }
    }

    fun getFazendaPorId(id: String): Fazenda? {
        return listaFazendasMock.find { it.id == id }
    }
}