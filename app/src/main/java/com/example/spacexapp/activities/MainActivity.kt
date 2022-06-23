package com.example.spacexapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexapp.RocketsAdapter
import com.example.spacexapp.databinding.ActivityMainBinding
import com.example.spacexapp.listeners.RocketListeners
import com.example.spacexapp.models.Rocket
import com.example.spacexapp.repository.RocketRepository
import com.example.spacexapp.utils.Resource
import com.example.spacexapp.viewmodels.RocketViewModel
import com.zain.android.internetconnectivitylibrary.ConnectionUtil
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(),RocketListeners {
    @Inject
    lateinit var repository: RocketRepository
    private lateinit var binding:ActivityMainBinding
    var rocketsList:ArrayList<Rocket?>? = null
    private val viewModel:RocketViewModel by viewModels()
    lateinit var rocketsAdapter: RocketsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        loadDataIfOffline()
        setupRecyclerView()

        isNetworkAvailable()

        if(!isNetworkAvailable()){
            viewModel.rockets.observe(this) { response ->
                when (response) {
                    is Resource.Success -> {

                        hideProgressBar()
                        response.data?.let {
                                rocketResponse->
                            rocketsList?.addAll(rocketResponse)
//                        viewModel.saveRocket(rocketsList as List<Rocket>)
                            rocketsAdapter.differ.submitList(rocketResponse)
                        }
                    }
                    is Resource.Error->{
                        hideProgressBar()
                        Log.e("Error while fetching",response.message.toString())
                    }
                    is Resource.Loading->{
                        showProgressBar()
                    }
                    else -> {

                        rocketsAdapter.differ.submitList(rocketsList)
                        Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }else{

            Toast.makeText(this, "No internet Connection ", Toast.LENGTH_SHORT).show()

        }



        val radius = 5f
        val decorView = window.decorView
        val windowBackground = decorView.background;
            binding.toolbar.setupWith(binding.root)
                .setBlurAlgorithm(RenderScriptBlur(this.baseContext))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setFrameClearDrawable(windowBackground) // Optional

    }

    private fun isNetworkAvailable():Boolean{
        var available = false
        val connectionUtil = ConnectionUtil(this)
        connectionUtil.onInternetStateListener { isAvailable ->

//            Toast.makeText(this, "Online? $isAvailable", Toast.LENGTH_SHORT).show();
            available = isAvailable

        }
        return available
    }


    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE

    }
    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE

    }
    private fun setupRecyclerView(){
        rocketsAdapter = RocketsAdapter(this)
        binding.rvBreakingNews.apply {
            val llm = LinearLayoutManager(context)
            llm.orientation = LinearLayoutManager.VERTICAL
            layoutManager = llm
            adapter = rocketsAdapter
        }

    }

    override fun onItemClicked(rockets: Rocket) {
        val intent = Intent(applicationContext, RocketDetailsActivity::class.java)
        intent.putExtra("rocketList",rockets)
        startActivity(intent)
    }


}

