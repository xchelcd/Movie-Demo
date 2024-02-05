package com.idaxmx.moviedemo.ui.movies

import android.content.Context
import android.util.AttributeSet
import android.util.Log
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
    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 5
    var firstVisibleItem = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0

    var onEndScroll: (() -> Unit)? = null

    init {
        this.adapter = Adapter()
        this.layoutManager = LinearLayoutManager(context)
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = this@MovieList.childCount;
                totalItemCount = (this@MovieList.layoutManager as LinearLayoutManager).itemCount
                firstVisibleItem =
                    (this@MovieList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition();
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                Log.d("MovieList", "1-loading:$loading - visibleItemCount:$visibleItemCount - totalItemCount:$totalItemCount - firstVisibleItem:$firstVisibleItem - (totalItemCount-visibleItemCount)${(totalItemCount - visibleItemCount)} - (firstVisibleItem + visibleThreshold):${(firstVisibleItem + visibleThreshold)} - previousTotal:$previousTotal")
                if (!loading && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)
                ) {
                    Log.d("MovieList", "*-loading:$loading - visibleItemCount:$visibleItemCount - totalItemCount:$totalItemCount - firstVisibleItem:$firstVisibleItem - (totalItemCount-visibleItemCount)${(totalItemCount - visibleItemCount)} - (firstVisibleItem + visibleThreshold):${(firstVisibleItem + visibleThreshold)} - previousTotal:$previousTotal")
                    onEndScroll?.let {
                        it()
                    }
                    loading = true
                } else {
                    Log.d("MovieList", "--loading:$loading - visibleItemCount:$visibleItemCount - totalItemCount:$totalItemCount - firstVisibleItem:$firstVisibleItem - (totalItemCount-visibleItemCount)${(totalItemCount - visibleItemCount)} - (firstVisibleItem + visibleThreshold):${(firstVisibleItem + visibleThreshold)} - previousTotal:$previousTotal")
                }
            }
        })
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