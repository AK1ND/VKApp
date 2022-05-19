package com.example.vkapp.fragments.post_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.vkapp.R
import com.example.vkapp.const.getToken
import com.example.vkapp.databinding.FragmentPostBinding
import com.example.vkapp.retrofit.RetrofitCreator
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import retrofit2.awaitResponse

@SuppressLint("SetTextI18n")
@DelicateCoroutinesApi
class PostFragment : Fragment() {
    private val args by navArgs<PostFragmentArgs>()

    private var _postBinding: FragmentPostBinding? = null
    private val binding get() = _postBinding!!


    var retrofitBuilder = RetrofitCreator().getUserAccessToken()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _postBinding = FragmentPostBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postDetails()
        attachment()
    }

    private fun attachment() {
        val picasso = Picasso.get()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = retrofitBuilder.getPostInfo(
                    "${args.ownerId}_${args.postID}",
                    getToken
                ).awaitResponse()

                if (response.isSuccessful) {
                    showHideButtonView()
                    var setImgPos = 0
                    val data = response.body()!!.response[0]
                    binding.countOfLikes.text = data.likes.count.toString()

                    if (data.likes.can_like == 0) {
                        binding.imIsLikedId.setImageResource(R.drawable.ic_baseline_favorite_24)
                    }

                    binding.imIsLikedId.setOnClickListener {
                        addLike()
                    }

                    if (data.text != "") {
                        binding.repostTextPageOwner.text = "Author's text: ${data.text}"
                    }

                    val imageSize = data.attachments[setImgPos].photo.sizes.size - 2

                    picasso.load(data.attachments[setImgPos].photo.sizes[imageSize].url)
                        .into(binding.imagePostContent)

                    binding.scrollPreviously.setOnClickListener {
                        showHideButtonView()
                        setImgPos -= 1
                        Log.d("CW", setImgPos.toString())
                        if (setImgPos == -1) {
                            setImgPos = data.attachments.size - 1
                        }
                        try {
                            picasso.load(data.attachments[setImgPos].photo.sizes[imageSize].url)
                                .into(binding.imagePostContent)
                        } catch (e: Exception) {
                            Log.d("CW", setImgPos.toString())
                            // catch exception if type of attachment is not a photo (video or other)
                            // and revive to 0 position
                            setImgPos = 0
                            picasso.load(data.attachments[setImgPos].photo.sizes[imageSize].url)
                                .into(binding.imagePostContent)
                        }
                    }

                    binding.scrollNext.setOnClickListener {
                        showHideButtonView()
                        setImgPos += 1
                        if (setImgPos == data.attachments.size) {
                            setImgPos = 0
                        }
                        try {
                            picasso.load(data.attachments[setImgPos].photo.sizes[imageSize].url)
                                .into(binding.imagePostContent)
                        } catch (e: Exception) {
                            Log.d("adaw", "awd")
                            // catch exception if type of attachment is not a photo (video or other)
                            // and revive to 0 position
                            setImgPos = 0
                            picasso.load(data.attachments[setImgPos].photo.sizes[imageSize].url)
                                .into(binding.imagePostContent)
                        }

                    }

                }
            } catch (e: Exception) {
                Log.d("error", "ERROR")
            }
        }
    }


    private fun postDetails() {
        val picasso = Picasso.get()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = retrofitBuilder.getPostInfo(
                    "${args.ownerId}_${args.postID}",
                    getToken
                ).awaitResponse()

                if (response.isSuccessful) {
                    showHideButtonView()
                    val data = response.body()!!.response[0]
                    val copyHistory = data.copy_history[0]
                    var setImgPos = 0
                    binding.countOfLikes.text = data.likes.count.toString()

                    if (data.likes.can_like == 0) {
                        binding.imIsLikedId.setImageResource(R.drawable.ic_baseline_favorite_24)
                    }

                    binding.imIsLikedId.setOnClickListener {
                        addLike()
                    }

                    if (data.text != "") {
                        binding.repostTextPageOwner.text = "Author's text: ${data.text}"
                    }


                    val imageSize =
                        copyHistory.attachments[0].photo.sizes.size - 2
                    binding.postText.text = copyHistory.text

                    picasso.load(copyHistory.attachments[setImgPos].photo.sizes[imageSize].url)
                        .into(binding.imagePostContent)

                    binding.scrollNext.setOnClickListener {
                        showHideButtonView()
                        setImgPos += 1
                        if (setImgPos == copyHistory.attachments.size) {
                            setImgPos = 0
                        }
                        try {
                            picasso.load(copyHistory.attachments[setImgPos].photo.sizes[imageSize].url)
                                .into(binding.imagePostContent)
                        } catch (e: Exception) {
                            Log.d("adaw", "awd")
                            // catch exception if type of attachment is not a photo (video or other)
                            // and revive to 0 position
                            setImgPos = 0
                            picasso.load(copyHistory.attachments[setImgPos].photo.sizes[imageSize].url)
                                .into(binding.imagePostContent)
                        }

                    }

                    binding.scrollPreviously.setOnClickListener {
                        showHideButtonView()
                        setImgPos += 1
                        if (setImgPos == copyHistory.attachments.size) {
                            setImgPos = 0
                        }
                        try {
                            picasso.load(copyHistory.attachments[setImgPos].photo.sizes[imageSize].url)
                                .into(binding.imagePostContent)
                        } catch (e: Exception) {
                            Log.d("adaw", "awd")
                            // catch exception if type of attachment is not a photo (video or other)
                            // and revive to 0 position
                            setImgPos = 0
                            picasso.load(copyHistory.attachments[setImgPos].photo.sizes[imageSize].url)
                                .into(binding.imagePostContent)
                        }

                    }

                }
            } catch (e: Exception) {
                Log.d("error", "ERROR")
            }
        }
    }

    private fun addLike() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = retrofitBuilder.addLike(
                    args.postID,
                    getToken
                ).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    binding.countOfLikes.text = data.response.likes.toString()
                    binding.imIsLikedId.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
            } catch (e: java.lang.Exception) {
                Log.d("ErrorLike", "Like is already done")
                deleteLike()
            }


        }
    }

    private fun deleteLike() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = retrofitBuilder.deleteLike(
                    args.postID,
                    getToken
                ).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    binding.countOfLikes.text = data.response.likes.toString()
                    binding.imIsLikedId.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            } catch (e: java.lang.Exception) {
                Log.d("ErrorLike", "Like is already done")
            }


        }
    }


    private fun showHideButtonView() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                binding.buttonLeft.visibility = View.VISIBLE
                binding.buttonRight.visibility = View.VISIBLE
                delay(1500)
                binding.buttonLeft.visibility = View.INVISIBLE
                binding.buttonRight.visibility = View.INVISIBLE
            } catch (e: Exception) {
                Log.d("!!!", "error")
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _postBinding = null
    }

}