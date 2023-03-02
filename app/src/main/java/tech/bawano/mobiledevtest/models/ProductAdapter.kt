package tech.bawano.mobiledevtest.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.bawano.mobiledevtest.databinding.RvProductItemBinding

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
        fun bind(position: Int) {
            val product = getItem(position)
            b.product = product
            b.root.setOnClickListener{
                onProductClick.onClick(product.id)
            }
        }
    }

}