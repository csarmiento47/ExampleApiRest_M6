package com.inforcap.exampleapirest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.inforcap.exampleapirest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var postList: ArrayList<PostEntity> = arrayListOf()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initRecyclerView()
        loadApiData()

    }

    private fun initRecyclerView() {
        adapter = PostAdapter(postList)
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.postsRecyclerView.adapter = adapter
    }

    private fun loadApiData() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPost()
        call.enqueue(object: Callback<ArrayList<PostEntity>> {
            override fun onResponse(
                call: Call<ArrayList<PostEntity>>,
                response: Response<ArrayList<PostEntity>>
            ) {
                response.body()?.map {
                    Log.d("MAIN_API","${it.id} ${it.title}")
                    postList.add(it)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<PostEntity>>, t: Throwable) {
                Toast.makeText(applicationContext,"Error: No logramos establecer conexión con los datos", Toast.LENGTH_SHORT).show()
            }
        })
    }

}