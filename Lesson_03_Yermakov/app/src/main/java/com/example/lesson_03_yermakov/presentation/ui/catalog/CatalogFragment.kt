package com.example.lesson_03_yermakov.presentation.ui.catalog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_03_yermakov.R
import com.example.lesson_03_yermakov.core.OnRecyclerItemClickListener
import com.example.lesson_03_yermakov.core.fitContentViewToInsets
import com.example.lesson_03_yermakov.core.getError
import com.example.lesson_03_yermakov.data.responsemodel.ResponseStates
import com.example.lesson_03_yermakov.databinding.FragmentCatalogBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class CatalogFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by createViewModelLazy(
        CatalogViewModel::class,
        { this.viewModelStore },
        factoryProducer = { viewModelFactory })

    @Inject
    lateinit var catalogAdapter: CatalogAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.fitContentViewToInsets(true)
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    RecyclerView.VERTICAL
                )
            )
            toolbar.inflateMenu(R.menu.catalog_menu)
            catalogAdapter.setOnClickListener(object :
                OnRecyclerItemClickListener<UIModelCatalogProduct> {
                override fun onClick(item: UIModelCatalogProduct) {
                    navigateWithCurrentData(item)
                }
            })
            recyclerView.adapter = catalogAdapter
            viewModel.fetchCatalog()
            viewModel.catalogLiveData.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ResponseStates.Success -> {
                        flipper.displayedChild = UIStates.SUCCESS_VIEW.ordinal
                        catalogAdapter.submitList(result.data)
                    }

                    is ResponseStates.Failure -> {
                        flipper.displayedChild = UIStates.FAILURE_VIEW.ordinal
                        errorLayout.errorText.text = result.e.getError()
                    }

                    is ResponseStates.Loading -> {
                        flipper.displayedChild = UIStates.LOADING_VIEW.ordinal
                    }
                }
            }
            errorLayout.refreshButton.setOnClickListener {
                viewModel.fetchCatalog()
            }
        }
    }

    private fun navigateWithCurrentData(catData: UIModelCatalogProduct) {
        val id = catData.id
        val action = CatalogFragmentDirections.actionCatalogFragmentToProductFragment(id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private enum class UIStates {
        FAILURE_VIEW,
        SUCCESS_VIEW,
        LOADING_VIEW,
    }
}