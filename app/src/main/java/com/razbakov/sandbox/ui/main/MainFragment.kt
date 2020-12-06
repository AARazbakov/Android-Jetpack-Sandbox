package com.razbakov.sandbox.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.razbakov.sandbox.R
import com.razbakov.sandbox.database.entity.HealthCheckData
import com.razbakov.sandbox.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

class MainFragment : Fragment(), HealthChecksAdapter.HealthChecksCallback {

    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.healthCheckList.apply {
            adapter = HealthChecksAdapter(
                requireContext(),
                listOf(HealthCheckData(1, "Yandex", "https://yandex.ru/", Date(), true)),
                this@MainFragment
            )
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
    }

    override fun onItemClicked(item: HealthCheckData) {
//        TODO("Not yet implemented")
    }
}
