package com.example.anime

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.bumptech.glide.Glide
import com.example.anime.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableEdgeToEdge()
        getAnime()
        binding.btn.setOnClickListener {
            getAnime()
        }
    }



    private fun CreateRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun getAnime() {
        val retrofit = CreateRetrofit()
        val animeService = retrofit.create(ApiInterface::class.java)
        val call = animeService.getAnime()
        call.enqueue(object : Callback<TopAnime> {
            override fun onResponse(call: Call<TopAnime>, response: Response<TopAnime>) {
                if (response.isSuccessful) {
                    val animeList = response.body()?.data ?: emptyList()
                    val randomIndexes = (0 until animeList.size).shuffled().take(10) // Generate 10 random indexes

                    randomIndexes.forEach { index ->
                        val anime = animeList.getOrNull(index) // Retrieve anime at the random index
                        val imageUrl = anime?.images?.jpg?.image_url
                        if (!imageUrl.isNullOrEmpty()) {
                            Glide.with(this@MainActivity)
                                .load(imageUrl)
                                .into(binding.image)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Image URL is empty or null for anime at index $index",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to retrieve data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<TopAnime>, t: Throwable) {
                Log.e("API_ERROR", "Failed to fetch anime", t)
                Toast.makeText(
                    this@MainActivity,
                    "Failed to retrieve data: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}