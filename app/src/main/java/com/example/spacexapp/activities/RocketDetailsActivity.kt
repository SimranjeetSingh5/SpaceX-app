package com.example.spacexapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.spacexapp.adapters.ViewPagerAdapter
import com.example.spacexapp.databinding.ActivityRocketDetailsBinding
import com.example.spacexapp.models.Rocket

class RocketDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityRocketDetailsBinding
    lateinit var rocket: Rocket
    private lateinit var imageViewPagerAdapter:ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRocketDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rocket = intent.getSerializableExtra("rocketList") as Rocket
        imageViewPagerAdapter = ViewPagerAdapter(rocket.flickrImages)
        setUpViewPager()
        binding.rocketName.text = rocket.name
        if (rocket.active == true){
            binding.activeStatus.text = "Active"
            binding.activeStatus.setTextColor(Color.GREEN)
        }else{

            binding.activeStatus.text = "InActive"
            binding.activeStatus.setTextColor(Color.RED)
        }
        binding.height.text = "Height :"+rocket.height?.feet+"ft"+"/"+rocket.height?.meters+"m"
        binding.cost.text = "Cost per launch :"+rocket.costPerLaunch
        binding.diameter.text = "Diameter :"+rocket.diameter?.feet+"ft"+"/"+rocket.diameter?.meters+"m"
        binding.wikiLink.text = "Wiki :-"+rocket.wikipedia
        binding.description.text = rocket.description
//        Toast.makeText(this,rocket.toString(),Toast.LENGTH_LONG).show()

    }

    private fun setUpViewPager() {
        binding.viewPager.adapter = imageViewPagerAdapter

        //set the orientation of the viewpager using ViewPager2.orientation
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //select any page you want as your starting page
        val currentPageIndex = 0
        binding.viewPager.currentItem = currentPageIndex

        // registering for page change callback
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    //update the image number textview
                    binding.imageNumberTV.text = "${position + 1} / ${rocket.flickrImages.size}"
                }
            }
        )
    }

}