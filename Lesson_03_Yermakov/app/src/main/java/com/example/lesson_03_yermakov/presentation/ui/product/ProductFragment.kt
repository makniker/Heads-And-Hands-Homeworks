package com.example.lesson_03_yermakov.presentation.ui.product

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lesson_03_yermakov.R
import com.example.lesson_03_yermakov.data.responsemodel.ResponseStates
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseProduct
import com.example.lesson_03_yermakov.databinding.FragmentProductBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

private const val DESCRIPTION_PREFIX = "description: "
private const val MAX_IMAGE_COUNT = 3

class ProductFragment : Fragment() {
    private val args: ProductFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by createViewModelLazy(
        ProductViewModel::class,
        { this.viewModelStore },
        factoryProducer = { viewModelFactory })

    lateinit var imageAdapter: ImageAdapter
    private lateinit var dialog: BottomSheetDialog
    private lateinit var sizeRecycler: RecyclerView

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private var imageList = mutableListOf<ShopImage>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            toolbar.setNavigationIcon(R.drawable.ic_up)
            toolbar.setNavigationOnClickListener { parentFragmentManager.popBackStack() }
            imageAdapter = ImageAdapter { item: ShopImage ->
                imageList.forEach { it.isSelected = false }
                refreshGallery(item)
                imageAdapter.submitList(imageList)
                imageAdapter.notifyItemRangeChanged(0, MAX_IMAGE_COUNT)
            }
            productLayout.smallImages.adapter = imageAdapter
            viewModel.fetchProduct(args.idArg)
            viewModel.productLiveData.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ResponseStates.Success -> {
                        flipper.displayedChild = UIStates.SUCCESS_VIEW.ordinal
                        bind(result.data)
                    }

                    is ResponseStates.Failure -> {
                        flipper.displayedChild = UIStates.FAILURE_VIEW.ordinal
                    }

                    is ResponseStates.Loading -> {
                        flipper.displayedChild = UIStates.LOADING_VIEW.ordinal
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bind(result: ResponseProduct) {
        with(binding.productLayout) {
            title.text = result.title
            badge.text = result.badge[0].value
            badge.chipBackgroundColor =
                ColorStateList.valueOf(Color.parseColor(result.badge[0].color))
            price.text = context?.getString(R.string.price_format, result.price.toString()) ?: ""
            department.text = result.department
            description.text = result.description.substring(DESCRIPTION_PREFIX.length).trimStart()
            details.text = result.details.joinToString("\n") {
                context?.getString(R.string.details_format, it) ?: ""
            }
            setUpGallery(result)

            val availableList = result.sizes.filter { it.isAvailable }.map { it.value }

            textSize.setText(availableList[0])

            sizes.setEndIconOnClickListener {
                showBottomDialog(availableList)
            }
        }
    }

    private fun setUpGallery(result: ResponseProduct) {
        imageList = result.images.map { ShopImage(false, it) }.toMutableList()
        if (imageList.size < MAX_IMAGE_COUNT) {
            for (i in 0..(MAX_IMAGE_COUNT - imageList.size)) {
                imageList.add(ShopImage(false, ""))
            }
        }
        val item = imageList[0]
        refreshGallery(item)
        imageAdapter.submitList(imageList)
    }

    private fun refreshGallery(item: ShopImage) {
        with(binding.productLayout) {
            item.isSelected = true
            if (item.imageUrl.isNotEmpty()) {
                Glide.with(bigImage).load(item.imageUrl).into(bigImage)
            } else {
                bigImage.setImageResource(R.drawable.ic_error_logo)
            }
        }
    }

    private fun showBottomDialog(sizes: List<String>) {
        val dialogView = layoutInflater.inflate(R.layout.fragment_size_dialog, null)
        dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(dialogView)
        sizeRecycler = dialogView.findViewById(R.id.sizes)
        val adapter = SizeAdapter { size: String ->
            binding.productLayout.textSize.setText(size)
            dialog.dismiss()
        }
        adapter.submitList(sizes)
        sizeRecycler.adapter = adapter
        dialog.show()
    }

    private enum class UIStates {
        FAILURE_VIEW,
        SUCCESS_VIEW,
        LOADING_VIEW,
    }
}