package lsvapp.kitsu.presentation.utils.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lsvapp.kitsu.R
import lsvapp.kitsu.presentation.utils.widget.adapter.ContentViewerAdapter
import lsvapp.kitsu.presentation.utils.widget.adapter.ContentViewerItem

class ContentViewer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val contentViewerAdapter = ContentViewerAdapter()
    private val titleTextView: TextView
    private val contentRecycler: RecyclerView

    init {
        inflate(context, R.layout.view_content_viewer, this)

        titleTextView = findViewById(R.id.content_title)
        contentRecycler = findViewById(R.id.content_recycler)

        val linear = LinearLayoutManager(context).apply {
            orientation = RecyclerView.HORIZONTAL
        }
        contentRecycler.apply {
            adapter = contentViewerAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }

        context.obtainStyledAttributes(attrs, R.styleable.ContentViewer).use {
            titleTextView.text = it.getString(R.styleable.ContentViewer_content_view_title)
        }
    }

    fun setContent(items: List<ContentViewerItem>) {
        contentViewerAdapter.items = items
    }

    fun setTitle(@StringRes title: String) {
        titleTextView.text = title
    }
}