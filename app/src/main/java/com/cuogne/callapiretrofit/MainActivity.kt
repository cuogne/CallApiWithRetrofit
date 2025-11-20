package com.cuogne.callapiretrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*
Tóm lại các bước để call api là:

B1: thêm thư viện retrofit, gson và coroutine

B2: add quyền internet vào

B3: Tạo 1 class chứa dữ liệu giống với api sắp lấy (hoặc là các biến cần lấy
nếu muốn đổi tên biến dưới local thì hãy dùng @SerializedName("tên biến json cần đổi"))

B4: tạo 1 api service, api này sẽ quản lí các endpoint được gọi (GET) + 1 hàm getPost() tự đặt tên

B5: tạo 1 instance của retrofit để tránh gọi lại quá nhiều, trong này sẽ chứa base url của api cần call và khởi tạo retrofit

B6: vào main, gọi val response = RetrofitClient.instance.getPost() -> getPost() là cái hàm tự đặt tên
hồi nãy để biết là cần call api với endpoint này, check isSuccessful và
lấy dữ liệu api từ response.body(), sau đó là các bước xử lí dữ liệu.
 */

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.textView)

        getData()
    }

    private fun getData() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // call api lan 1 de lay 5 pokemon dau tien
                val response1 = RetrofitClient.instance.getPokemon(limit = 5, offset = 0)

                if (response1.isSuccessful) {
                    val pokemonResults = response1.body()?.results // lay mang chua cac pokemon

                    if (!pokemonResults.isNullOrEmpty()) {
                        // loop qua tung pokemon
                        for (pokemonResult in pokemonResults) {
                            val detailUrl = pokemonResult.url

                            // goi api thu 2 de lay pokemon detail
                            val response2 = RetrofitClient.instance.getDetailPokemon(detailUrl)

                            if (response2.isSuccessful) {
                                response2.body()?.let {
                                    Log.d("API_DATA", "Name: ${it.name}, ID: ${it.id}, Img: ${it.sprites.img}")
                                }
                            }
                        }
                    } else {
                        Log.d("API_CALL", "Danh sách Pokemon trả về rỗng hoặc null.")
                    }
                } else {
                    Log.e("API_CALL", "Lỗi gọi getPokemon: ${response1.code()} - ${response1.message()}")
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Exception trong coroutine: ${e.message}", e)
            }
        }
    }
}