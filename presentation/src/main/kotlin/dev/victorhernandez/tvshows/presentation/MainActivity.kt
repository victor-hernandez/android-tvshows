package dev.victorhernandez.tvshows.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme
import dev.victorhernandez.tvshows.presentation.ui.example.TvShows
import dev.victorhernandez.tvshows.presentation.ui.shows.TopRatedTvShows

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVShowsTheme {
                TopRatedTvShows(
                    shows = TvShows
                ) { }
            }
        }
    }

}