package tech.bawano.mobiledevtest.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.bawano.mobiledevtest.databinding.RvProductItemBinding

class ProductAdapter() : ListAdapter<Product, ProductAdapter.ProductViewHolder>(callback) {

    interface OnProductClick {
        fun onClick(product: Product)
    }

    private lateinit var onProductClick: OnProductClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
         ProductViewHolder(RvProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) =
        holder.bind(position)


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.context.run {
            onProductClick = this as OnProductClick
        }
    }

    inner class ProductViewHolder(private val b: RvProductItemBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(position: Int) {

        }

    }

}