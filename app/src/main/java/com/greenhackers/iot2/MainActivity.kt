package com.greenhackers.iot2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenhackers.iot2.RetrofitBuilder.Companion.retrofitCli
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    val ha = Handler()
    lateinit var adapter: DataAdapter
    lateinit var list: ArrayList<Response>
    lateinit var manager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = ArrayList()
        manager = LinearLayoutManager(this)
        adapter = DataAdapter(list) { vh, position ->
            list.remove(list[position])
            adapter.notifyDataSetChanged()
        }

        rv?.layoutManager = manager
        rv?.adapter = adapter
        ha.postDelayed(object:Runnable{
            override fun run() {
                getData()
                ha.postDelayed(this, 10000)
            }

        },10000)

        ivReload?.setOnClickListener{getData()}
        getData()
    }

    private fun getData() {
        progressBar?.visibility = VISIBLE
        tvNoInternet?.visibility = GONE
        ivReload?.visibility = GONE
        doAsync {
            retrofitCli<Service>()
                .getData()
                .enqueue(object : Callback<List<Response>> {
                    override fun onFailure(call: Call<List<Response>>, t: Throwable) {
                        runOnUiThread {
                            progressBar?.visibility = GONE
                            tvNoInternet?.visibility = VISIBLE
                            ivReload?.visibility = VISIBLE
                            //Log.e("data", t.message.toString())
                        }

                    }

                    override fun onResponse(
                        call: Call<List<Response>>,
                        response: retrofit2.Response<List<Response>>
                    ) {
                        runOnUiThread {
                            progressBar?.visibility = GONE
                            tvNoInternet?.visibility = GONE
                            ivReload?.visibility = GONE
                        }
                        try {
                            list.clear()
                            val l = (response.body() as ArrayList<Response>).reversed()
                            list.addAll(l)
                            adapter.notifyDataSetChanged()
                        }catch (e:Exception){}

                        //Log.e("data", response.body().toString())
                    }

                })
        }

    }
}
