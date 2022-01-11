package lsvapp.kitsu.presentation.utils.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.view.isVisible
import lsvapp.kitsu.R

class StubView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val titleTextView: TextView
    private val descTextView: TextView

    init {
        inflate(context, R.layout.view_stub, this)

        titleTextView = findViewById(R.id.title)
        descTextView = findViewById(R.id.desc)

        context.obtainStyledAttributes(attrs, R.styleable.StubView).use {
            titleTextView.text = it.getString(R.styleable.StubView_title)
            descTextView.text = it.getString(R.styleable.StubView_desc)
        }
    }

    fun setStubView(title: String, desc: String?) {
        titleTextView.text = title
        desc?.let {
            descTextView.isVisible = true
            descTextView.text = it
        }
    }
}