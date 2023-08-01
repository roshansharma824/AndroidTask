package com.example.androidtask.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtask.PostViewModel
import com.example.androidtask.PostsApplication
import com.example.androidtask.R
import com.example.androidtask.PostViewModelFactory
import com.example.androidtask.adapter.PostListAdapter
import com.example.androidtask.databinding.FragmentHomeBinding
import com.google.gson.Gson


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(
            requireActivity(),
            PostViewModelFactory(
                (requireActivity().application as PostsApplication).repository
            )
        )[PostViewModel::class.java]

        val adapter = PostListAdapter()

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allPosts.observe(viewLifecycleOwner){ posts->
            posts.let {
                adapter.submitList(it)
            }
        }

        adapter.setOnItemClickListener {
            requireActivity().replaceFragment(R.id.fragment_container_view, PostDetailFragment.newInstance(it), true)
        }

        binding.permissionButton.setOnClickListener {
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }



    }

}


fun FragmentActivity.replaceFragment(
    container: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.beginTransaction()
        .replace(container, fragment, fragment.javaClass.simpleName)
        .apply {
            if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
        }
        .commit()
}

fun <T> Bundle.putDataObject(key: String, t: T) {
    putString(key, Gson().toJson(t))
}

fun <T> Bundle.getDataObjectExtra(key: String, type: Class<T>): T {
    return Gson().fromJson(getString(key), type)
}