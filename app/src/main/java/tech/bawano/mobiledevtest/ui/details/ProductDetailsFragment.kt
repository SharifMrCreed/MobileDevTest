package tech.bawano.mobiledevtest.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tech.bawano.mobiledevtest.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment() {

    private lateinit var b: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val productDetailsViewModel =
            ViewModelProvider(this)[ProductDetailsViewModel::class.java]

        b = FragmentProductDetailsBinding.inflate(inflater, container, false)
        val root: View = b.root

        val textView: TextView = b.textGallery
        productDetailsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

}