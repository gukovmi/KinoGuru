package com.shellwoo.kinoguru.feature.movie.detail.ui

import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.shellwoo.kinoguru.core.ktx.fromHtml
import com.shellwoo.kinoguru.core.ui.ext.inflate
import com.shellwoo.kinoguru.core.ui.ext.onPause
import com.shellwoo.kinoguru.feature.movie.detail.R
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieVideoItem
import kotlinx.android.synthetic.main.movie_video_item_you_tube.view.*
import javax.inject.Inject

class MovieVideoItemYouTubeViewHolder @Inject constructor(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.movie_video_item_you_tube, false)) {

    private var youTubePlayerListener: AbstractYouTubePlayerListener? = null
    private var youTubePlayer: YouTubePlayer? = null

    init {
        parent.onPause { pause() }
    }

    fun bind(movieVideoItemYouTube: MovieVideoItem.YouTube, itemNumber: Int, itemCount: Int) {
        renderDescription(movieVideoItemYouTube.name, itemNumber, itemCount)
        renderPlayer(movieVideoItemYouTube.key)
    }

    private fun renderDescription(itemName: String, itemNumber: Int, itemCount: Int) {
        val description = itemView.context.getString(R.string.movie_details_video_description, itemNumber + 1, itemCount, itemName)
            .fromHtml()
        itemView.description.text = description
    }

    private fun renderPlayer(itemKey: String) {
        renderVideoLoading()
        resetYouTubePlayer(itemKey)
        youTubePlayerListener?.let(itemView.player::addYouTubePlayerListener)
    }

    private fun resetYouTubePlayer(videoKey: String) {
        youTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@MovieVideoItemYouTubeViewHolder.youTubePlayer = youTubePlayer
                youTubePlayer.cueVideo(videoKey, 0f)
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                renderVideoLoaded()
                youTubePlayer.removeListener(this)
            }

            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                if (state == PlayerConstants.PlayerState.VIDEO_CUED) {
                    renderVideoLoaded()
                }
            }
        }
    }

    private fun renderVideoLoading() {
        with(itemView) {
            playerSkeleton.isVisible = true
            player.isInvisible = true
        }
    }

    private fun renderVideoLoaded() {
        with(itemView) {
            playerSkeleton.isVisible = false
            player.isInvisible = false
        }
    }

    fun clear() {
        pause()
        youTubePlayerListener?.let { youTubePlayer?.removeListener(it) }
        youTubePlayerListener = null
        youTubePlayer = null
    }

    private fun pause() {
        youTubePlayer?.pause()
    }
}