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
import com.example.stefanbayneitunes.view.SongForFragment.Companion.MUSIC_KEY
import com.example.stefanbayneitunes.view.SongForFragment.Companion.WAYNE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongForFragment : Fragment() {

    lateinit var rvSongList: RecyclerView
    lateinit var songAdapter: ItunesSongAdapter
    lateinit var searchView: SearchView
    private var musicType: Int = WAYNE

    private val retrofit: ApiServiceITunes =
        ApiServiceITunes.createRetrofit().create(ApiServiceITunes::class.java)

    companion object {
        const val MUSIC_KEY = "MUSIC_TYPE"
        const val WAYNE = 0
        const val KENDRICK = 1
        const val COLE = 2
        const val BURNA_BOY = 3

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
        val view = inflater.inflate(R.layout.fragment_itunes_song_layout, container, false)

        rvSongList = view.findViewById(R.id.rv_songs)
        searchView = view.findViewById(R.id.search_view_one)
        getSongs(inflater)
        setUpSearchView(searchView, inflater)

        return view
    }

    private fun searchSongs(inflater: LayoutInflater, term: String) =
        startRetrofit(inflater, retrofit.getArtistsSongs(term))

    private fun getSongs(inflater: LayoutInflater) {
        musicType = requireArguments().getInt(MUSIC_KEY)

        when (musicType) {
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

                    rvSongList.smoothScrollToPosition(0)

                    if (query != null) {
                        musicType = requireArguments().getInt(MUSIC_KEY)

                        when (musicType) {
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
                if (response.isSuccessful) {
                    songAdapter = ItunesSongAdapter(response.body()!!.results)
                    rvSongList.adapter = songAdapter
                }
            }

            override fun onFailure(call: Call<DataForSongs>, t: Throwable) {
                Toast.makeText(inflater.context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}