package lsvapp.kitsu.presentation.maintab

import androidx.fragment.app.Fragment
import lsvapp.kitsu.R
import lsvapp.kitsu.presentation.feed.tab.FeedTabFragment
import lsvapp.kitsu.presentation.group.GroupTabFragment
import lsvapp.kitsu.presentation.movie.MovieTabFragment
import lsvapp.kitsu.presentation.profile.ProfileTabFragment

enum class MainTabs(
    val tag: TabTag,
    val menuId: Int? = null,
    val newInstanceAction: () -> Fragment,
) {
    FEED(
        tag = TabTag.FEED,
        menuId = R.id.tab_feed,
        newInstanceAction = { FeedTabFragment() }
    ),
    MOVIE(
        tag = TabTag.MOVIE,
        menuId = R.id.tab_movie,
        newInstanceAction = { MovieTabFragment() }
    ),
    GROPE(
        tag = TabTag.GROPE,
        menuId = R.id.tab_group,
        newInstanceAction = { GroupTabFragment() }
    ),
    PROFILE(
        tag = TabTag.PROFILE,
        menuId = R.id.tab_profile,
        newInstanceAction = { ProfileTabFragment() }
    )
}