package lampapos.org.lifegame

fun boardFromString(size: Int, boardString: String): Board {
  val iterator = boardString.iterator()
  var row = 0
  var col = 0
  val board: Array<Array<Boolean>> = Array(size) { _ -> Array(size) { _ -> false} }

  while (iterator.hasNext()) {
    val currentChar = iterator.next()

    if (currentChar == 'X') {
      board[row][col] = true
    }

    if (currentChar == '\n') {
      row++
      col = 0
    } else {
      col++
    }
  }

  return Board(size, board)
}

class Board(
    val size: Int,
    private val board: Array<Array<Boolean>> = Array(size) { _ -> Array(size) { _ -> false} }
) {
  fun getCell(row: Int, column: Int) = board[row][column]

  fun makeStep(): Board {
    val newBoard: Array<Array<Boolean>> = Array(size) { _ -> Array(size) { _ -> false} }

    for (row in 0 until size) {
      for (column in 0 until size) {
        val neighboursCount = countNeighbours(row, column)

        when {
          neighboursCount < 2 -> newBoard[row][column] = false
          neighboursCount == 2 -> newBoard[row][column] = board[row][column]
          neighboursCount == 3 -> newBoard[row][column] = true
          neighboursCount > 3 -> newBoard[row][column] = false
        }
      }
    }

    return Board(size, newBoard)
  }

  private fun countNeighbours(row: Int, column: Int): Int {
    //            pC c nC
    //        pR   . . .
    //         r   . x .
    //        nR   . . .

    val pR = (size + row - 1) % size
    val nR = (row + 1) % size

    val pC = (size + column - 1) % size
    val nC = (column + 1) % size

    var count = 0
    if (board[pR][pC]) { count++ }
    if (board[pR][column]) { count++ }
    if (board[pR][nC]) { count++ }

    if (board[row][pC]) { count++ }
    if (board[row][nC]) { count++ }

    if (board[nR][pC]) { count++ }
    if (board[nR][column]) { count++ }
    if (board[nR][nC]) { count++ }

    return count
  }

}
