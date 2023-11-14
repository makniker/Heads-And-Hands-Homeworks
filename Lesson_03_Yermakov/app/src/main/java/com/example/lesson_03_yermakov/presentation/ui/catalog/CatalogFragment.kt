package com.example.lesson_03_yermakov.presentation.ui.catalog

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
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
    lateinit var adapter: CatalogAdapter

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
            recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            recyclerView.adapter = adapter
            viewModel.fetchCatalog()
            viewModel.catalogLiveData.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ResponseStates.Success -> {
                        flipper.displayedChild = UIStates.SUCCESS_VIEW.ordinal
                        adapter.items = result.data
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private enum class UIStates{
        FAILURE_VIEW,
        SUCCESS_VIEW,
        LOADING_VIEW,
    }
}