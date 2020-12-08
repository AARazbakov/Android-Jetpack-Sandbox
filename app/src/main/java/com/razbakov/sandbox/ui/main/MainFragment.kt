package com.razbakov.sandbox.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.razbakov.sandbox.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val listAdapter = HealthChecksAdapter()
    private val viewModel: MainViewModel by viewModels()
    private var refreshJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.healthDataList.observe(this, {
            listAdapter.submitList(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MainFragmentBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.healthCheckList.apply {
            adapter = listAdapter
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    setDrawable(
                        resources.getDrawable(
                            android.R.drawable.divider_horizontal_bright,
                            activity?.theme
                        )
                    )
                }
            )
        }
        binding.swipeRefresh.setOnRefreshListener {
            refreshJob?.cancel()
            refreshJob = lifecycleScope.launch {
                viewModel.refreshData()
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
