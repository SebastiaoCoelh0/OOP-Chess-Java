package pt.ulusofona.lp2.deisichess

fun getStatsCalculator(type: StatType) {

    when (type) {
        StatType.TOP_5_CAPTURAS -> println()
        StatType.TOP_5_PONTOS -> println()
        StatType.PECAS_MAIS_5_CAPTURAS -> println()
        StatType.PECAS_MAIS_BARALHADAS -> println()
        StatType.TIPOS_CAPTURADOS -> println()
    }
}