package lsvapp.kitsu.presentation.utils.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.view.isVisible
import com.google.android.material.textview.MaterialTextView
import lsvapp.kitsu.R

class CenterToolbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val titleTextView: MaterialTextView
    private val leftStartIcon: ImageView
    private val rightEndIcon: ImageView

    init {
        inflate(context, R.layout.view_center_toolbar, this)

        titleTextView = findViewById(R.id.title_text_view)
        leftStartIcon = findViewById(R.id.left_start_image)
        rightEndIcon = findViewById(R.id.right_end_image)

        context.obtainStyledAttributes(attrs, R.styleable.CenterToolbarView).use {
            val attrTitle = it.getString(R.styleable.CenterToolbarView_view_toolbar_title)
            title = attrTitle
        }
    }

    var title: CharSequence?
        get() = titleTextView.text
        set(value) {
            titleTextView.text = value
        }

    fun setBackNavigation(backAction: () -> Unit) = editLeftStartIcon {
        setImageResource(R.drawable.ic_arrow_back)
        setOnClickListener { backAction.invoke() }
    }

    fun editLeftStartIcon(action: ImageView.() -> Unit) {
        leftStartIcon.isVisible = true
        action.invoke(leftStartIcon)
    }

    fun editRightEndIcon(action: ImageView.() -> Unit) {
        rightEndIcon.isVisible = true
        action.invoke(rightEndIcon)
    }
}