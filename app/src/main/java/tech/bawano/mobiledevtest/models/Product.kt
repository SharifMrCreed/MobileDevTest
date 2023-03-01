package tech.bawano.mobiledevtest.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var id: Int,
    var url: String,
    var name: String,
    var description: String,
    var terms: String,
    @SerializedName("current_value")
    var currentValue: String,
) : Parcelable {
    fun isSameAs(other: Product): Boolean {
        return id == other.id || url == other.url || name == other.name
    }

}


var callback: DiffUtil.ItemCallback<Product> = object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem.isSameAs(newItem)
}