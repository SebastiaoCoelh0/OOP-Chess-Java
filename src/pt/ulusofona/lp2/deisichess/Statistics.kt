package pt.ulusofona.lp2.deisichess

fun getStatsCalculator(type: StatType): (GameManager) -> (List<String>) {

    val gameManager = GameManager()

    return { gameManager ->
        when (type) {
            StatType.TOP_5_CAPTURAS -> test(gameManager)
            StatType.TOP_5_PONTOS -> test(gameManager)
            StatType.PECAS_MAIS_5_CAPTURAS -> test(gameManager)
            StatType.PECAS_MAIS_BARALHADAS -> test(gameManager)
            StatType.TIPOS_CAPTURADOS -> test(gameManager)
        }
    }
}

fun test(gameManager: GameManager): List<String> {
    return emptyList<String>()
}