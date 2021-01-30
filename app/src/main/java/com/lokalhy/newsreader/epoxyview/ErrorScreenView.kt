package com.lokalhy.newsreader.epoxyview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.lokalhy.newsreader.databinding.ErrorScreenViewBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class ErrorScreenView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?= null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ErrorScreenViewBinding.inflate(
        LayoutInflater.from(context), this, false).also { addView(it.root) }

    init {
        binding.apply {
            btnTryAgain.setOnClickListener {
                onTryAgainClick?.invoke()
            }
        }
    }

    var onTryAgainClick: (() -> Unit)? = null
        @CallbackProp set

}
