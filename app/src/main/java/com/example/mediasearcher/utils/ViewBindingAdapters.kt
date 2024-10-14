package com.example.mediasearcher.utils

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mediasearcher.R
import timber.log.Timber


@BindingAdapter("android:visibility")
fun visibility(view: View, visible: Boolean?) = when (visible) {
    true -> view.visibility = View.VISIBLE
    else -> view.visibility = View.GONE
}

@BindingAdapter(
    value = ["imageUrl"]
)
fun imageUrl(
    view: ImageView,
    imageUrl: String?
) {
    try {
        val drawable = ContextCompat.getDrawable(
            view.context,
            R.drawable.no_image
        )?.apply {
            setTint(ContextCompat.getColor(view.context, R.color.md_theme_inversePrimary))
        }

        Glide.with(view).load(imageUrl).placeholder(drawable).into(view)
    } catch (e: Exception) {
        Timber.d("ViewBindingAdapters_TAG: imageUrl: exception: $e")
    }
}

@BindingAdapter(
    value = ["textHtml"]
)fun textHtml(view: TextView, text: String?) = try {
    view.text =
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
} catch (e: Exception) {
    Timber.d("ViewBindingAdapters_TAG: text: ERROR: ${e.message} ")
    view.text = text
}

