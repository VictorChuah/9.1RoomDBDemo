package com.example.a91roomdbdemo.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(p:Product)
}