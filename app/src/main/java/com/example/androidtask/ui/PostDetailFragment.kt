package com.example.androidtask.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.androidtask.R
import com.example.androidtask.databinding.FragmentPostDetailBinding
import com.example.androidtask.pojo.PostItem


class PostDetailFragment : Fragment() {


    private lateinit var binding: FragmentPostDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_post_detail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data  = arguments?.getDataObjectExtra(DATA, PostItem::class.java)

        Log.d("PostDetailFragment","${data?.title}")


        binding.title.text = "${data?.id}   ${data?.title}"
        binding.body.text = data?.body
    }

    companion object {
        private const val DATA = "data"
        fun newInstance(data:PostItem ): PostDetailFragment {
            return PostDetailFragment().apply {
                arguments = Bundle().apply {
                    putDataObject(DATA, data)
                }
            }
        }
    }
}
