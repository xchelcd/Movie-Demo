package com.idaxmx.moviedemo.ui.movies

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idaxmx.moviedemo.data.model.Movie

class MovieList(
    context: Context,
    attrs: AttributeSet?,
): RecyclerView(context, attrs) {

    var data: List<Movie> = emptyList()
        set(value) {
            field = value
            // adapter?.notifyItemRangeChanged(0, value.size)
            adapter?.notifyDataSetChanged()
        }

    init {
        this.adapter = Adapter()
        this.layoutManager = LinearLayoutManager(context)
    }


    inner class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {

        var onMovieSelected: ((Movie) -> Unit)? = null

        inner class ViewHolder(
            private val movieItem: MovieItem
        ): RecyclerView.ViewHolder(movieItem) {

            fun bind(movie: Movie) {
                movieItem.data = movie.also(::addListener)
            }

            private fun addListener(movie: Movie) {
                movieItem.setOnClickListener {
                    onMovieSelected?.let(movie::let)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val movieItem = MovieItem(context, null)
            movieItem.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            return ViewHolder(movieItem)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            data[position].also(holder::bind)
        }

        override fun getItemCount(): Int = data.size
    }
}