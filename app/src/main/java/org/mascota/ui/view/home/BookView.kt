package org.mascota.ui.view.home

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import org.mascota.R
import org.mascota.R.styleable.CustomBookView
import org.mascota.databinding.ViewCustomBookBinding

class BookView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var viewCustomBookBinding : ViewCustomBookBinding

    private var bgBookView: View
    private var topBookView: View
    private var lineView: View

    init {
        addView(createCustomView())

        bgBookView = viewCustomBookBinding.vBgBook
        topBookView = viewCustomBookBinding.vTopBook
        lineView = viewCustomBookBinding.vMidLine

        val attrsCustomBookView = context.obtainStyledAttributes(attrs, CustomBookView)
        setAttr(attrsCustomBookView)
    }

    private fun setAttr(attrs: TypedArray) {
        val lineColor = attrs.getColor(R.styleable.CustomBookView_lineColor, 0)
        lineView.setBackgroundColor(lineColor)

        val bookColor = attrs.getColor(R.styleable.CustomBookView_bookStrokeColor, 0)
        (bgBookView.background as GradientDrawable).setStroke(1, bookColor)
        (topBookView.background as GradientDrawable).setStroke(1, bookColor)
    }

    private fun createCustomView(): View {
        val inflater = LayoutInflater.from(context)
        viewCustomBookBinding = DataBindingUtil.inflate(inflater, R.layout.view_custom_book, this, false)

        return viewCustomBookBinding.root

    }
}