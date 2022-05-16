package com.example.stefanbayneitunes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stefanbayneitunes.API.ApiServiceITunes
import com.example.stefanbayneitunes.DataClass.DataForSongs
import com.example.stefanbayneitunes.Model.ItunesSongAdapter
import com.example.stefanbayneitunes.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongForFragment : Fragment() {

    lateinit var rvSongList: RecyclerView
    lateinit var songAdapter: ItunesSongAdapter

    private var musicType : Int = HIPHOP

    companion object {

        const val MUSIC_KEY = "MUSIC_TYPE"

        const val HIPHOP = 0
        const val JAZZ = 1
        const val GOSPEL = 2

        fun newInstance(musicType: Int) : SongForFragment{
            val fragment = SongForFragment()
            val bundle = Bundle()

            bundle.putInt(MUSIC_KEY, musicType)
            fragment.arguments = bundle

            return fragment
        }
    }

    fun getSongs(inflater: LayoutInflater)
    {
        musicType = requireArguments().getInt(MUSIC_KEY)

        if (musicType == HIPHOP){
            startRetrofit(inflater, ApiServiceITunes.createRetrofit().create(ApiServiceITunes::class.java).getHipHopSongs())
        }else if(musicType == JAZZ){
            startRetrofit(inflater, ApiServiceITunes.createRetrofit().create(ApiServiceITunes::class.java).getJazzSongs())
        }else if(musicType == GOSPEL){
            startRetrofit(inflater, ApiServiceITunes.createRetrofit().create(ApiServiceITunes::class.java).getGospelSongs())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_itunes_song_layout, container, false)

        rvSongList = view.findViewById(R.id.name_Of_Song)

        getSongs(inflater)

        return view
    }

    private fun startRetrofit(inflater: LayoutInflater, call: Call<DataForSongs>){
        call.enqueue(object: Callback<DataForSongs>{
            override fun onResponse(
                call: Call<DataForSongs>,
                response: Response<DataForSongs>
            ) {
                if (response.isSuccessful){
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