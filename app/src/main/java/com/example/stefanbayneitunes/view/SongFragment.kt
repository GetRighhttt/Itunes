package com.example.stefanbayneitunes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stefanbayneitunes.api.ApiServiceITunes
import com.example.stefanbayneitunes.model.DataForSongs
import com.example.stefanbayneitunes.R
import com.example.stefanbayneitunes.databinding.FragmentItunesSongLayoutBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongForFragment : Fragment() {

    private var _binding: FragmentItunesSongLayoutBinding? = null
    val binding get() = _binding!!

    private var artist: Int = 0

    companion object {
        const val MUSIC_KEY = "MUSIC_TYPE"
        const val WAYNE = 0
        const val KENDRICK = 1
        const val COLE = 2
        const val BURNA_BOY = 3

        private val retrofit= ApiServiceITunes.retrofit

        fun newInstance(musicType: Int): SongForFragment {
            val fragment = SongForFragment()
            val bundle = Bundle()

            bundle.putInt(MUSIC_KEY, musicType)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItunesSongLayoutBinding.inflate(inflater, container, false)

        getSongs(inflater)
        binding.apply {
            setUpSearchView(searchViewOne, inflater)
        }

        return binding.root
    }

    private fun searchSongs(inflater: LayoutInflater, term: String) =
        startRetrofit(inflater, retrofit.getArtistsSongs(term))

    private fun getSongs(inflater: LayoutInflater) {
        artist = requireArguments().getInt(MUSIC_KEY)

        when (artist) {
            WAYNE -> {
                searchSongs(inflater, "lil+wayne")
            }

            KENDRICK -> {
                searchSongs(inflater, "kendrick+lamar")
            }

            COLE -> {
                searchSongs(inflater, "jcole")
            }

            BURNA_BOY -> {
                searchSongs(inflater, "burna+boy")
            }
        }
    }

    private fun setUpSearchView(searchView: SearchView, inflater: LayoutInflater): SearchView =
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    binding.apply {

                        rvSongs.smoothScrollToPosition(0)

                        if (query != null) {
                            artist = requireArguments().getInt(MUSIC_KEY)

                            when (artist) {
                                WAYNE -> {
                                    searchSongs(inflater, query)
                                }

                                KENDRICK -> {
                                    searchSongs(inflater, query)
                                }

                                COLE -> {
                                    searchSongs(inflater, query)
                                }

                                BURNA_BOY -> {
                                    searchSongs(inflater, query)
                                }
                            }
                            clearFocus()
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

    private fun startRetrofit(inflater: LayoutInflater, call: Call<DataForSongs>) {
        call.enqueue(object : Callback<DataForSongs> {
            override fun onResponse(
                call: Call<DataForSongs>,
                response: Response<DataForSongs>
            ) {
                binding.apply {
                    if (response.isSuccessful) {
                        val songAdapter = ItunesSongAdapter(response.body()!!.results)
                        rvSongs.adapter = songAdapter
                    }
                }
            }

            override fun onFailure(call: Call<DataForSongs>, t: Throwable) {
                Toast.makeText(inflater.context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}