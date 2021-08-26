package lsvapp.kitsu.presentation.maintab

import androidx.fragment.app.Fragment
import lsvapp.kitsu.R

enum class MainTabs(
    val newInstanceAction: () -> Fragment,
    val menuId: Int? = null,
    val tag: TabTag
) {
    FEED(
        { lsvapp.kitsu.presentation.feed.FeedTabFragment() },
        tag = TabTag.FEED,
        menuId = R.id.tab_feed
    ),
    MOVIE(
        { lsvapp.kitsu.presentation.movie.MovieTabFragment() },
        tag = TabTag.MOVIE,
        menuId = R.id.tab_movie
    ),
    GROPE(
        { lsvapp.kitsu.presentation.group.GroupTabFragment() },
        tag = TabTag.GROPE,
        menuId = R.id.tab_group
    ),
    PROFILE(
        { lsvapp.kitsu.presentation.profile.ProfileTabFragment() },
        tag = TabTag.PROFILE,
        menuId = R.id.tab_profile
    )
}

enum class TabTag {
    FEED,
    MOVIE,
    GROPE,
    PROFILE
}