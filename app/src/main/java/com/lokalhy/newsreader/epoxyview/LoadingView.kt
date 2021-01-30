package com.lokalhy.newsreader.epoxyview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.lokalhy.newsreader.R
import com.lokalhy.newsreader.base.bindView

@ModelView(defaultLayout = R.layout.config_loading_view)
class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        gravity = Gravity.CENTER
        LayoutInflater.from(context).inflate(R.layout.view_loading, this, true)
    }

    private val tvMessage by bindView<TextView>(R.id.tv_message)

    @ModelProp
    fun setMessage(value: String) {
        tvMessage.text = value
    }
}