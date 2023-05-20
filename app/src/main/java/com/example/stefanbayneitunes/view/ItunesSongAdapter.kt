package com.example.stefanbayneitunes.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.stefanbayneitunes.model.AllDataOfTheSongs
import com.example.stefanbayneitunes.R
import com.example.stefanbayneitunes.databinding.ImageForItunesSongBinding


class ItunesSongAdapter(private val list: List<AllDataOfTheSongs>) :
    RecyclerView.Adapter<ItunesSongAdapter.SongViewHolder>() {

    // Inner class to begin the adaptation of our data source to our UI
    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun onBind(iTunesSong: AllDataOfTheSongs) {
            val binding = ImageForItunesSongBinding.bind(itemView)

            binding.apply {
                artistName.text = iTunesSong.artistName
                collectionName.text = iTunesSong.collectionName
                trackName.text = iTunesSong.trackName
                primaryGenre.text = iTunesSong.primaryGenreName

                if(iTunesSong.trackPrice.isNullOrBlank()) {
                    songPrice.text = "**FREE**"
                } else {
                    songPrice.text = "$${iTunesSong.trackPrice} / $${iTunesSong.collectionPrice}"
                }

                Glide.with(itemView.context)
                    .load(iTunesSong.artworkUrl60)
                    .placeholder(R.drawable.baseline_music_note_24)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(songImage)

                itemView.setOnClickListener {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.setDataAndType(Uri.parse(iTunesSong.previewUrl), "audio/*")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val listItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_for_itunes_song, parent, false)
        return SongViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

}