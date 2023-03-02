package tech.bawano.mobiledevtest.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.bawano.mobiledevtest.databinding.RvProductItemBinding

/**
 * This adapter is required by the recyclerview to load data in an efficient way. only what is visible
 * or what is soon to be visible is loaded. that is ~15 items at a time and then the views are recycled
 */
class ProductAdapter(fragment: Fragment) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(callback) {


    private var onProductClick: OnProductClick
    init {
        onProductClick = fragment as OnProductClick
    }
    interface OnProductClick {
        fun onClick(id: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
         ProductViewHolder(RvProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) =
        holder.bind(position)



    inner class ProductViewHolder(private val b: RvProductItemBinding) : RecyclerView.ViewHolder(b.root) {
        // the bind method helps much when you have multiple item views that your adapter might need to load
        // I might call it best practice but that's not written anywhere
        fun bind(position: Int) {
            val product = getItem(position)
            b.product = product
            b.root.setOnClickListener{
                onProductClick.onClick(product.id)
            }
        }
    }

}