package lampapos.org.lifegame

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    boardView.board = boardFromString(20, "XXXX...XXXX.X.X.X.X\nX.X.X.X.X")

     boardView.setOnClickListener { boardView.board = boardView.board.makeStep() }

    val figuresAdapter = FiguresAdapter(clickListener = object : FiguresAdapter.OnItemClickListener {

      override fun onClick(boardStr: String) {
        boardView.board = boardFromString(20, boardStr)
      }

    })

    figuresList.adapter = figuresAdapter
  }

}
