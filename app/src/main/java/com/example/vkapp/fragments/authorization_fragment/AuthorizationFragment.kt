package com.example.vkapp.fragments.authorization_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import com.example.vkapp.R
import com.example.vkapp.TokenData
import com.example.vkapp.const.URL_AUTH_GET_TOKEN
import com.example.vkapp.const.getToken
import com.example.vkapp.const.s

class AuthorizationFragment : Fragment() {

    private lateinit var webView: WebView
    private var urlLink: String = ""
    private val dm: TokenData by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView = requireView().findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled

        loadAuth()

        dm.token.observe(activity as LifecycleOwner, {
            getToken = it
            Log.d("CW", getToken)
            Log.d("!!!CW", s.length.toString())
            Log.d("!!!CW", getToken.length.toString())
            when (s.length) {
                it.length -> destroyView()
            }
        })
    }

    private fun destroyView() {

        webView.destroy()
        view?.findNavController()?.navigate(R.id.action_authorizationFragment_to_newsFragment)

    }


    private fun loadAuth() {

        webView.loadUrl(URL_AUTH_GET_TOKEN)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                urlLink = webView.url.toString()
                Log.d("!!!CW", urlLink)
                val accessS = urlLink
                val s = accessS.substringBefore("%2526expires_in")
                val token = s.substringAfter("%253D")
                dm.token.value = token

                return super.shouldOverrideUrlLoading(view, request)
            }
        }

    }
}