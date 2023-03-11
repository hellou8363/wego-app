package com.yellowh.wego

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yellowh.wego.databinding.ActivityMainBinding
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MountainInfoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callJson()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    } // onCreate

    fun callJson() = runOnUiThread { // 코루틴 실행
        println("코루틴 실행")
        println("백그라운드 작업 수행")
        val url = "http://192.168.35.119:8080/MountainInfo" // 요청할 서블릿 URL
        val client = OkHttpClient() // OkHttp 클라이언트 객체 생성

        // 요청 객체 생성
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        // 요청 보내기
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("JSON 데이터 요청 실패!!")
//                println("e.stackTrace: " + e.stackTrace.toString())
                for (stack in e.stackTrace) {
                    println(stack)
                }
            } // onFailure

            override fun onResponse(call: Call, response: Response) {
                println("데이터 요청 성공!!")
                val responseData = response.body?.string()
                println(responseData)

                val gson = Gson()

                // JSON 문자열을 객체로 변환
                val mountainInfo: Array<MountainInfo>? =
                    gson.fromJson(responseData, Array<MountainInfo>::class.java)

                if (mountainInfo != null) {
//                        for (data in mountainInfo) {
//                            println("data: ${data}")
//
//                        } // for

                    adapter = MountainInfoRecyclerViewAdapter(mountainInfo)
                    binding.recyclerview.adapter = adapter
                    binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)

                } // if
            } // onResponse

        }) // client.newCall.enqueue

    } // callJson
} // end class