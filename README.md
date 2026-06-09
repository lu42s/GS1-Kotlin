
# SpaceFarm

Monitoramento inteligente de lavouras em tempo real utilizando dados orbitais (NDVI) para otimizar a produtividade e prevenir perdas agrícolas.

Protótipo Android desenvolvido para a Global Solution FIAP — Indústria Espacial (1º semestre de 2026), na trilha Android Kotlin Developer.

## Sobre o projeto

O SpaceFarm conecta a economia espacial a um problema concreto do agronegócio: acompanhar a saúde das lavouras à distância, com base em dados de satélite. A partir do NDVI (Índice de Vegetação por Diferença Normalizada) e de indicadores como a umidade do solo, o app permite visualizar rapidamente o estado de cada área monitorada e identificar talhões que precisam de atenção antes que a perda aconteça.

A solução se alinha aos Objetivos de Desenvolvimento Sustentável (ODS) da ONU, em especial os relacionados a produção sustentável e uso eficiente de recursos naturais, usando dados orbitais para tornar a agricultura mais inteligente e resiliente. O monitoramento agrícola com dados de satélite é uma das aplicações sugeridas no próprio briefing da Global Solution.

## Funcionalidades

- Tela de apresentação com a identidade visual da solução e acesso direto ao monitoramento.
- Listagem de áreas monitoradas (fazendas) com nome, localização, NDVI e indicador visual de status por cor.
- Filtro por status (Todos / Normal / Atenção / Crítico) para encontrar rapidamente as áreas que exigem ação.
- Tela de detalhes de cada área, com última atualização, NDVI (com barra de progresso), umidade do solo e status.
- Navegação entre telas com passagem de parâmetro (o id da fazenda) usando Navigation Compose.

## Fluxo do aplicativo

O app possui três telas, com o seguinte fluxo de navegação:

```
InicialScreen ("home")
      |  botão "Acessar Monitoramento"
      v
MonitoramentoScreen ("monitoramento")
      |  toque em um card de fazenda
      v
DetalhesScreen ("detalhes/{id}")
```

1. Tela Inicial (`InicialScreen`) — Apresenta o nome da solução, um ícone, a descrição do objetivo do app e o botão "Acessar Monitoramento", que leva à tela principal. É a rota inicial (`startDestination = "home"`).

2. Monitoramento (`MonitoramentoScreen`) — Exibe a lista de áreas em uma `LazyColumn` de `Card`s. No topo, uma linha de `FilterChip`s permite filtrar as fazendas por status. Cada card mostra nome, localização, NDVI e um círculo colorido que representa o status (verde = Normal, amarelo = Atenção, vermelho = Crítico). Tocar em um card abre os detalhes, passando o id da fazenda.

3. Detalhes da Área (`DetalhesScreen`) — Recebe o id pela rota, busca a fazenda correspondente e mostra cards com a última atualização, o NDVI (acompanhado de um `LinearProgressIndicator`), a umidade do solo em porcentagem e o status do monitoramento.

## Tecnologias e bibliotecas

- Linguagem: Kotlin 2.0.21
- UI: Jetpack Compose com Material 3 (Compose BOM 2024.06.00)
- Navegação: Navigation Compose 2.7.7
- Estado: ViewModel (`lifecycle-viewmodel-compose`) com `mutableStateOf`
- Outras dependências: `core-ktx`, `lifecycle-runtime-ktx`, `activity-compose`
- Build: Android Gradle Plugin 8.10.1, Gradle Kotlin DSL e catálogo de versões (`libs.versions.toml`)

## Estrutura do projeto

```
KotlinGSv2/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/br/fiap/spacefarm/
│       │   ├── MainActivity.kt            # Activity única; carrega a navegação
│       │   ├── AppNavigaton.kt            # NavHost e definição das rotas
│       │   ├── models/
│       │   │   ├── Fazenda.kt             # Modelo de dados + enum Status + dados mockados
│       │   │   └── AgroViewModel.kt       # Estado do filtro e acesso aos dados
│       │   ├── screens/
│       │   │   ├── InicialScreen.kt       # Tela de apresentação
│       │   │   ├── MonitoramentoScreen.kt # Lista + filtros
│       │   │   └── DetalhesScreen.kt      # Detalhes de uma área
│       │   └── ui/theme/                  # Cores, tipografia e tema
│       └── res/                           # Ícones, strings e temas
├── build.gradle.kts
├── settings.gradle.kts
└── gradle/libs.versions.toml
```

### Modelo de dados

Cada área monitorada é representada pela `data class Fazenda`, com os campos: `id`, `nome`, `localizacao`, `ndvi` (0.0 a 1.0), `status` (NORMAL, ATENCAO ou CRITICO), `ultimaAtualizacao` e `umidadeSolo` (em %).

Os dados são mockados em `listaFazendasMock` (quatro fazendas de exemplo), o que atende ao requisito de exibição de dados sem necessidade de backend. A estrutura está pronta para, futuramente, ser alimentada por uma API real de dados orbitais (ex.: NASA ou ESA).

## Como executar

Pré-requisitos:

- Android Studio (versão estável recente)
- JDK 17 (já incluído nas versões recentes do Android Studio)
- Emulador ou dispositivo físico com Android 7.0 (API 24) ou superior

Passos:

1. Clone o repositório:
   ```
   git clone [URL_DO_REPOSITORIO_NO_GITHUB]
   ```
2. No Android Studio, selecione "Open" e abra a pasta `KotlinGSv2` (a raiz do projeto Gradle).
3. Aguarde o Gradle Sync baixar as dependências.
4. Selecione um emulador ou conecte um dispositivo.
5. Clique em Run para instalar e executar o app.

## Atendimento aos requisitos da entrega

- Tela inicial (nome, descrição, identidade visual): `InicialScreen.kt`
- Navegação com Navigation Compose, mínimo de 3 telas: `AppNavigaton.kt` (três rotas com `NavHost`)
- Componentes Compose (Column, Row, Card, LazyColumn, Scaffold): em todas as telas
- Exibição de dados relacionados ao tema: `MonitoramentoScreen.kt` e `Fazenda.kt` (lista mockada)
- Interação com o usuário: filtros (`FilterChip`), cards clicáveis e botões de navegação
- Organização e boas práticas: pacotes `models` / `screens` / `ui`, uso de ViewModel e nomes coerentes
