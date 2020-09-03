package com.cmm.wdflowers.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.cmm.wdflowers.R
import com.cmm.wdflowers.databinding.MainFragmentBinding
import com.cmm.wdflowers.ui.base.BaseBindingFragment
import org.koin.android.viewmodel.ext.android.viewModel

private const val TAG = "MainFragment"

class MainFragment : BaseBindingFragment<MainFragmentBinding>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun getLayoutId() = R.layout.main_fragment

    private val mainViewModel: MainViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel.getOrder()
        mainViewModel.order().observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "listSize=${it.size}", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "onActivityCreated: $it")
            viewBinding.message.text = it.toString()
        })
    }
}
