package com.coby.cobybase.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coby.cobybase.MainApplication
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseActivity
import com.coby.cobybase.databinding.ActivityAuthBinding
import com.coby.cobybase.model.TestModel
import com.coby.cobybase.ui.main.MainActivity

class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_auth

    private val myAdapter = GifTabAdapter()
    private var myList = arrayListOf<TestModel>(
        TestModel("1", "Tile 1"),
        TestModel("2", "Tile 2"),
        TestModel("3", "Tile 3")
//        TestModel("4", "Tile 4"),
//        TestModel("5", "Tile 5"),
//        TestModel("6", "Tile 6"),
//        TestModel("7", "Tile 7"),
//        TestModel("8", "Tile 8"),
//        TestModel("9", "Tile 9"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.srlTest.setOnRefreshListener {
            myAdapter.notifyDataSetChanged()
            binding.srlTest.isRefreshing = false
        }

        binding.rcvTest.adapter = myAdapter
        myAdapter.submitList(myList)

        binding.btnAddLast.setOnClickListener {
            myList.add(TestModel("${myList.size + 1}", "Title ${myList.size + 1}"))
            myAdapter.submitList(myList.toMutableList())
        }
        binding.btnEditLast.setOnClickListener {
            val new = TestModel(id = "3", title = "Edited")
            myList[myList.size - 1] = new
            myAdapter.submitList(myList.toMutableList())
        }
        binding.btnRemoveLast.setOnClickListener {
            myList.removeLast()
            myAdapter.submitList(myList.toMutableList())
        }
    }

}