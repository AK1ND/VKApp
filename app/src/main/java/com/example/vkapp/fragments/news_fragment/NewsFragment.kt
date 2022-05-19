package com.example.vkapp.fragments.news_fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vkapp.R
import com.example.vkapp.const.VERSION
import com.example.vkapp.const.getToken
import com.example.vkapp.databinding.FragmentNewsBinding
import com.example.vkapp.model_package.model.ModelMain
import com.example.vkapp.retrofit.RetrofitCreator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

@DelicateCoroutinesApi
@SuppressLint("NotifyDataSetChanged")
@RequiresApi(Build.VERSION_CODES.O)

class NewsFragment : Fragment() {

    private var _newsBinding: FragmentNewsBinding? = null
    private val newsBinding get() = _newsBinding!!

    var retrofitBuilder = RetrofitCreator().getUserAccessToken()
    lateinit var responseBody: ModelMain
    lateinit var adapter: NewsAdapter
    lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _newsBinding = FragmentNewsBinding.inflate(inflater, container, false)

        return newsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_news_id)

        news()
    }

    private fun news() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = retrofitBuilder.newsJSONResponse(
                    "50",
                    getToken,
                    VERSION
                ).awaitResponse()

                if (response.isSuccessful) {
                    responseBody = response.body()!!
                    adapter = NewsAdapter(responseBody, requireContext().applicationContext)
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter
                    adapter.setOnItemClickListener { test, id ->
                        val action =
                            NewsFragmentDirections.actionNewsFragmentToPostFragment(
                                test,
                                id
                            )
                        findNavController().navigate(action)

                    }
                }
            } catch (e: Exception) {
                Log.d("error", "ERROR")
                news()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _newsBinding = null
    }

}