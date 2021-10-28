package lsvapp.kitsu.presentation.maintab

import androidx.fragment.app.Fragment
import lsvapp.kitsu.R

enum class MainTabs(
    val tag: TabTag,
    val menuId: Int? = null,
    val newInstanceAction: () -> Fragment,
) {
    FEED(
        tag = TabTag.FEED,
        menuId = R.id.tab_feed,
        newInstanceAction = { lsvapp.kitsu.presentation.feed.FeedTabFragment() }
    ),
    MOVIE(
        tag = TabTag.MOVIE,
        menuId = R.id.tab_movie,
        newInstanceAction = { lsvapp.kitsu.presentation.movie.MovieTabFragment() }
    ),
    GROPE(
        tag = TabTag.GROPE,
        menuId = R.id.tab_group,
        newInstanceAction = { lsvapp.kitsu.presentation.group.GroupTabFragment() }
    ),
    PROFILE(
        tag = TabTag.PROFILE,
        menuId = R.id.tab_profile,
        newInstanceAction = { lsvapp.kitsu.presentation.profile.ProfileTabFragment() }
    )
}