package tech.bawano.mobiledevtest.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import tech.bawano.mobiledevtest.R


@BindingAdapter("setImage")
fun setImage(view: ImageView, uri: String?) =
    Glide.with(view).load(uri).centerInside().into(view)


@BindingAdapter("setBookmarked")
fun setBookmarked(view: ImageView, isBookmarked: Boolean) =
    Glide.with(view).load(
        if (isBookmarked) R.drawable.ic_bookmarked
        else R.drawable.ic_unbookmarked
    ).centerInside().into(view)

