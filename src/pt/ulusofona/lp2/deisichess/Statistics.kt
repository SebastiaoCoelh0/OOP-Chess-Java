package pt.ulusofona.lp2.deisichess

fun getStatsCalculator(type: StatType): (GameManager) -> (List<String>) {

    //SEM CICLOS!! LISTA.FILTER().SORTED().DISTINCT()...

    val gameManager = GameManager()

    return { gameManager ->
        when (type) {
            StatType.TOP_5_CAPTURAS -> top5capturas(gameManager)
            StatType.TOP_5_PONTOS -> top5Pontos(gameManager)
            StatType.PECAS_MAIS_5_CAPTURAS -> pecasMais5Capturas(gameManager)
            StatType.PECAS_MAIS_BARALHADAS -> pecasMaisBaralhadas(gameManager)
            StatType.TIPOS_CAPTURADOS -> tiposCapturados(gameManager)
        }
    }
}

fun top5capturas(gameManager: GameManager): List<String> {

    return gameManager.getBoard().listPieces.sortedByDescending { it.getPiecesCaptured() }.take(5)
        .map { it.getName() + " (" + it.teamText + ") fez " + it.getPiecesCaptured() + " capturas" }
}

fun top5Pontos(gameManager: GameManager): List<String> {

    return gameManager.getBoard().listPieces.sortedWith(compareByDescending<Piece> { it.getPointsCaptured() }
        .thenBy { it.getName() }).take(5).filter { it.getPointsCaptured() > 0 }
        .map { it.getName() + " (" + it.teamText + ") tem " + it.getPointsCaptured() + " pontos" }
}

fun pecasMais5Capturas(gameManager: GameManager): List<String> {

    return gameManager.getBoard().listPieces.filter { it.getPiecesCaptured() > 5 }
        .sortedBy { it.getInvalidMoves() / it.getValidMoves() }
        .map { it.teamText + ":" + it.getName() + ":" + it.getPiecesCaptured() }
}

fun pecasMaisBaralhadas(gameManager: GameManager): List<String> {

    return gameManager.getBoard().listPieces.filter { it.getInvalidMoves() > 0 }
        .sortedBy { it.getInvalidMoves() / it.getValidMoves() }
        .map { "" + it.getTeam() + ":" + it.getName() + ":" + it.getInvalidMoves() + ":" + it.getValidMoves() }
}

fun tiposCapturados(gameManager: GameManager): List<String> {

    return gameManager.getBoard().capturedPiecesTypesArrayString.distinct().filter { it.isNotEmpty() }.sorted()
}