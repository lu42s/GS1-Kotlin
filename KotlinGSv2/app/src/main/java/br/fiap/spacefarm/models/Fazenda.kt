package br.fiap.spacefarm.models

data class Fazenda (
    val id: String,
    val nome: String,
    val localizacao: String,
    val ndvi: Float, // Índice de Vegetação (0.0 a 1.0)
    val status: Status,
    val ultimaAtualizacao: String,
    val umidadeSolo: Int // Porcentagem
)

enum class Status {
    NORMAL, ATENCAO, CRITICO
}

// Dados Mockados (Atende ao requisito de exibição de dados)
val listaFazendasMock = listOf(
    Fazenda("1", "Fazenda Esperança", "Mato Grosso, BR", 0.85f, Status.NORMAL, "Hoje, 08:00", 65),
    Fazenda("2", "Agro Vale do Sol", "Goiás, BR", 0.45f, Status.ATENCAO, "Ontem, 14:30", 30),
    Fazenda("3", "Plantio Horizonte", "Bahia, BR", 0.20f, Status.CRITICO, "Hoje, 09:15", 15),
    Fazenda("4", "Fazenda Santa Fé", "Paraná, BR", 0.90f, Status.NORMAL, "Hoje, 07:45", 70)
)