package com.merttoptas.bitcointracker.views.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.merttoptas.bitcointracker.R
import com.merttoptas.bitcointracker.databinding.FragmentCoinBinding
import com.merttoptas.bitcointracker.viewmodels.CoinViewModel
import com.merttoptas.bitcointracker.views.adapter.CoinAdapter

class CoinFragment : Fragment() {
    private val coinRankingViewModel: CoinViewModel by lazy {
        ViewModelProviders.of(this).get(CoinViewModel::class.java)
    }
    private lateinit var adapter: CoinAdapter
    var totalCoin = 0
    private val layoutManager = GridLayoutManager(context, 1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCoinBinding>(
            inflater, R.layout.fragment_coin, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = coinRankingViewModel

        adapter = CoinAdapter(context!!.applicationContext)
        binding.recyclerCoinRanking.layoutManager = layoutManager
        binding.recyclerCoinRanking.adapter = adapter

        coinRankingViewModel.totalCoin.observe(viewLifecycleOwner, Observer {
            totalCoin = it
            adapter.totalCoins = totalCoin
            adapter.notifyDataSetChanged()
        })
        coinRankingViewModel.coins.observe(viewLifecycleOwner, Observer {
            adapter.data = it.toList()
        })

        return binding.root
    }


}
