package lampapos.org.lifegame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BoardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

  private val paint = Paint()

  var board: Board = Board(20)
    set(value) {
      field = value
      invalidate()
    }

  init {
    if (isInEditMode) {
      board = boardFromString(20, "X.XXXX.X\n....X.X.X....")
    }
  }


  private fun drawBoard(board: Board, canvas: Canvas) {
    paint.color = Color.BLUE

    val xStep = (measuredWidth / board.size).toFloat()
    val yStep = (measuredHeight / board.size).toFloat()

    for (row in 0 until board.size) {
      for (col in 0 until board.size) {
        if (board.getCell(row, col)) {
          paint.style = Paint.Style.FILL
        } else {
          paint.style = Paint.Style.STROKE
        }

        canvas.drawRect(col * xStep, row * yStep, (col + 1) * xStep, (row + 1) * yStep, paint)
      }
    }
  }

  override fun onDraw(canvas: Canvas) {
    drawBoard(board, canvas)
  }

}
