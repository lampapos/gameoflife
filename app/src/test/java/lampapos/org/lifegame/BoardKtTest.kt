package lampapos.org.lifegame

import org.junit.Test

/**   */
class BoardKtTest {

  @Test
  fun `test board string parsing`() {
    val board = boardFromString(5, ".X.\nX.X\nXXX")

    assert(!board.getCell(0, 0))
    assert(board.getCell(0, 1))
    assert(!board.getCell(0, 2))

    assert(board.getCell(1, 0))
    assert(!board.getCell(1, 1))
    assert(board.getCell(1, 2))

    assert(board.getCell(2, 0))
    assert(board.getCell(2, 1))
    assert(board.getCell(2, 2))
  }
}
