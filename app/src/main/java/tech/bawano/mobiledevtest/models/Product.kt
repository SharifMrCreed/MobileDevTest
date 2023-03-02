package tech.bawano.mobiledevtest.models

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


const val PRODUCT = "Product"

@Entity(tableName = PRODUCT)
@Parcelize
data class Product(
    @PrimaryKey
    var id: Int = 0,
    var url: String?,
    var name: String = "",
    var description: String = "",
    var terms: String = "",
    @SerializedName("current_value")
    var currentValue: String = "",
    var isSaved: Boolean = false
) : Parcelable


var callback: DiffUtil.ItemCallback<Product> = object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
}