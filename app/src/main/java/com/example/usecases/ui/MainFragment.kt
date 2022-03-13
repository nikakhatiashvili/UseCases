package com.example.usecases.ui

import android.R.attr.delay
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usecases.databinding.FragmentMainBinding
import com.example.usecases.model.CryptoItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import java.util.*


@AndroidEntryPoint
class MainFragment : Fragment() {

    private val homeViewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: MainAdapter
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        bind()
        return root
    }
    private fun bind(){

        adapter = MainAdapter()
        binding.apply {
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
        }
//        val mainHandler = Handler(Looper.getMainLooper())
//        mainHandler.post(object : Runnable {
//            override fun run() {
//                viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//                    homeViewModel.getMarket().flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect{
//                        Log.d("sadasda", it.toString())
//                        adapter.data = it
//
//                    }
//                }
//                mainHandler.postDelayed(this, 60000)
//            }
//        })
//        val job = CoroutineScope(Dispatchers.Main).launch {
//            tickFlow(10000L).collect {
//                adapter.data = it
//            }
//        }
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.tickFlow(15000).collectLatest {
                adapter.data = it
            }
        }
//        viewLifecycleOwner.lifecycleScope.launch {
//            homeViewModel.triggerFlow().collectLatest{
//                adapter.data = it
//            }
//        }
    }


//    fun tickFlow(millis: Long) = callbackFlow<List<CryptoItem>> {
//        val timer = Timer()
//        var time = 0
//        timer.scheduleAtFixedRate(
//            object : TimerTask() {
//                override fun run() {
//                    try {
//                        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//                            homeViewModel.getMarket().flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect{
//                                Log.d("sadasda", it.toString())
//                                adapter.data = it
//                            }
//                        }
//                    } catch (e: Exception) {}
//                    time += 1
//                }
//            },
//            0,
//            millis)
//        awaitClose {
//            timer.cancel()
//        }
//    }


}