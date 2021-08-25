package com.example.a91roomdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Dao
import com.example.a91roomdbdemo.data.Product
import com.example.a91roomdbdemo.data.ProductDB
import com.example.a91roomdbdemo.data.ProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var dao :ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = ProductDB.getInstance(application).productDao

        val btnInsert :Button = findViewById(R.id.btnInsert)
        btnInsert.setOnClickListener(){

            val name :String  = findViewById<TextView>(R.id.tfName).text.toString()
            val price:Double =  findViewById<TextView>(R.id.tfPrice).text.toString().toDouble()

            val p = Product(0, name, price)

            CoroutineScope(IO).launch {
                dao.insertProduct(p)
            }

        }

        val btnGet :Button = findViewById(R.id.btnGet)
        btnGet.setOnClickListener(){

            CoroutineScope(IO).launch {
                var productName = ""
                //val products /*:List<Product>*/ = dao.getAll()
                val products :List<Product> = dao.getPriceLessThan(5.00)

                for (product: Product in products){
                    productName += product.name + "\n"
                }

                CoroutineScope(Main).launch {
                    val tvResult: TextView = findViewById(R.id.tvResult)
                    tvResult.text = productName
                }
            }

        }
    }
}