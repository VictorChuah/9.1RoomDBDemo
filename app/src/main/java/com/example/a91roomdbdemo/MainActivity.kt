package com.example.a91roomdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.a91roomdbdemo.data.Product
import com.example.a91roomdbdemo.data.ProductDB
import com.example.a91roomdbdemo.data.ProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var dao :ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = ProductDB.getInstance(application).productDao

        val btn :Button = findViewById(R.id.btnInsert)

        btn.setOnClickListener(){
            val p = Product(0, "Kang", 1.10)

            CoroutineScope(IO).launch {
                dao.insertProduct(p)
            }


        }
    }
}