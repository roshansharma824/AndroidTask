package com.example.androidtask

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtask.adapter.PostListAdapter
import com.example.androidtask.databinding.ActivityMainBinding
import com.example.androidtask.ui.HomeFragment

class MainActivity : AppCompatActivity() {

//    private val postViewModel: PostViewModel by viewModels {
//        WordViewModelFactory((application as PostsApplication).repository)
//    }
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomeFragment>(R.id.fragment_container_view)
            }
        }


    }
}