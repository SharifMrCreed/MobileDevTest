package tech.bawano.mobiledevtest.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import tech.bawano.mobiledevtest.R
import tech.bawano.mobiledevtest.databinding.FragmentProductsBinding
import tech.bawano.mobiledevtest.models.Product
import tech.bawano.mobiledevtest.models.ProductAdapter

class ProductsFragment : Fragment(), ProductAdapter.OnProductClick {

    private lateinit var b: FragmentProductsBinding
    private val vm by lazy {
        ViewModelProvider(this)[ProductsViewModel::class.java]

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentProductsBinding.inflate(inflater, container, false)

        val adapter = ProductAdapter(this)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        b.recyclerView.apply {
            setAdapter(adapter)
            layoutManager = gridLayoutManager
        }
        lifecycleScope.launch {
            vm.getAllProducts().collect { productList ->
                if (productList.isEmpty()){
                    vm.insertProducts()
                }else{
                    b.progressBar.visibility = View.GONE
                    adapter.submitList(productList)
                }
            }
        }


        return b.root
    }

    override fun onClick(id:Int) {
        findNavController().navigate(
            R.id.nav_product_details,
            bundleOf("id" to id)
        )

    }

}