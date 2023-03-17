package com.yellowh.wego

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yellowh.wego.databinding.ActivityHomeBinding
import com.yellowh.wego.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class HomeFragment : Fragment() {

    private lateinit var mbinding: FragmentHomeBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var adapter: MountainInfoRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    } // onAttach

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mbinding = FragmentHomeBinding.inflate(inflater, container, false)

        callJson()

        return mbinding.root

//        return inflater.inflate(R.layout.fragment_home, container, false);
    } // onCreateView

    fun callJson() = GlobalScope.launch { // 코루틴 실행
        println("코루틴 실행")
        println("백그라운드 작업 수행")
        val url = "http://192.168.110.24:8080/wego/MountainInfo" // 요청할 서블릿 URL(학원 WIFI)
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

                    mainActivity?.runOnUiThread {
                        println("MainActivity UI 스레드 수행")

                        adapter = MountainInfoRecyclerViewAdapter(mountainInfo)
                        mbinding.recyclerview.adapter = adapter
                        mbinding.recyclerview.layoutManager = LinearLayoutManager(mainActivity)
                    }
                } // if
            } // onResponse

        }) // client.newCall.enqueue

    } // callJson

} // end class