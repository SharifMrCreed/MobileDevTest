package tech.bawano.mobiledevtest.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import tech.bawano.mobiledevtest.databinding.FragmentProductDetailsBinding
import tech.bawano.mobiledevtest.models.Product
import tech.bawano.mobiledevtest.ui.products.ProductsViewModel

class ProductDetailsFragment : Fragment() {

    private lateinit var b: FragmentProductDetailsBinding
    private var id = 0
    private lateinit var product: Product
    private val vm by lazy {
        ViewModelProvider(this)[ProductsViewModel::class.java]

    }

    // this will contain any arguments set on the fragment. in this case the id of the product.
    private val args:ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        b = FragmentProductDetailsBinding.inflate(inflater, container, false)
        id = args.id

        // we get the product and pass it to the view to be bound
        lifecycleScope.launch {
            vm.getProduct(id).collect{
                product = it
                b.product = product
            }

        }

        //on clicking the bookmark button the product is updated appropriately. in this case its a
        // database update but it could as well be a rest Api call
        b.bookmark.setOnClickListener {
            product.isSaved = !product.isSaved
            vm.update(product)
        }
        return b.root
    }

}