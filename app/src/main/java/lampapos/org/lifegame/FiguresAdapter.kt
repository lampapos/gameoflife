package lampapos.org.lifegame

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.intellij.lang.annotations.Language

class FiguresAdapter(private val clickListener: OnItemClickListener)
  : RecyclerView.Adapter<FiguresAdapter.FigureViewHolder>() {

  class FigureViewHolder(
      itemView: View,
      val image: ImageView = itemView.findViewById(R.id.icon),
      val label: TextView = itemView.findViewById(R.id.label)
  ) : RecyclerView.ViewHolder(itemView)

  data class Figure(@DrawableRes val image: Int, @StringRes val label: Int, val boardStr: String)

  interface OnItemClickListener {
    fun onClick(boardStr: String)
  }

  @Language("TEXT")
  private val figures = listOf(
      Figure(R.drawable.beehive, R.string.beehive, "......\n..XX..\n.X..X.\n..XX.."),
      Figure(R.drawable.block, R.string.block, "....\n.XX.\n.XX.\n...."),
      Figure(R.drawable.glider, R.string.glider, ".X.\n..X\nXXX"),
      Figure(R.drawable.flower, R.string.flower, ".....\n..X..\n.X.X.\n..X..\n.....")
  )

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FigureViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    return FigureViewHolder(itemView)
  }

  override fun getItemCount(): Int = figures.size

  override fun onBindViewHolder(viewHolder: FigureViewHolder, position: Int) {
    val figure = figures[position]
    viewHolder.image.setImageResource(figure.image)
    viewHolder.label.setText(figure.label)
    viewHolder.itemView.setOnClickListener { clickListener.onClick(figure.boardStr) }
  }

}
