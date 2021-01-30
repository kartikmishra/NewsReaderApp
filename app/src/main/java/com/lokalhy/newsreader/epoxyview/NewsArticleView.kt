package com.lokalhy.newsreader.epoxyview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.lokalhy.newsreader.databinding.NewsArticleViewLayoutBinding
import com.squareup.picasso.Picasso

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class NewsArticleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?= null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val binding = NewsArticleViewLayoutBinding.inflate(
        LayoutInflater.from(context), this, false
    ).also { addView(it.root) }



    init {
        setOnClickListener {
            onClick?.invoke()
        }
    }

    var onClick: (() -> Unit)? = null
        @CallbackProp set

    @ModelProp
    lateinit var title:String

    @ModelProp
    lateinit var description:String

    @ModelProp
    lateinit var source:String

    @ModelProp
    lateinit var publishedAt:String

    @ModelProp
    lateinit var urlToImage:String

    @AfterPropsSet
    fun setData() {
        binding.apply {
            tvTitle.text = title
            tvDesc.text = description
            Picasso.get().load(urlToImage).fit().into(ivArticle)
            tvSource.text = "By $source"
        }
    }

}