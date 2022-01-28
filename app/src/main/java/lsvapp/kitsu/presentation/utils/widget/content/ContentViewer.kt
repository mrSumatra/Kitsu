package lsvapp.kitsu.presentation.utils.widget.content

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lsvapp.kitsu.R
import lsvapp.kitsu.presentation.utils.widget.content.adapter.ContentViewerAdapter
import lsvapp.kitsu.presentation.utils.widget.content.adapter.ContentViewerItem

class ContentViewer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val contentViewerAdapter = ContentViewerAdapter()
    private val titleTextView: TextView
    private val descTextView: TextView
    private val contentRecycler: RecyclerView

    init {
        inflate(context, R.layout.view_content_viewer, this)

        titleTextView = findViewById(R.id.title)
        descTextView = findViewById(R.id.desc)
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
            descTextView.text = it.getString(R.styleable.ContentViewer_content_view_desc)
        }
    }

    fun setContent(items: List<ContentViewerItem>) {
        contentViewerAdapter.items = items
    }

    fun setTitle(@StringRes title: String) {
        titleTextView.isVisible = true
        titleTextView.text = title
    }

    fun setDesc(@StringRes desc: String) {
        descTextView.isVisible = true
        descTextView.text = desc
    }
}