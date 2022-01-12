package lsvapp.kitsu.application.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import lsvapp.kitsu.data.api.AnimeApi
import lsvapp.kitsu.data.api.PostApi
import lsvapp.kitsu.data.api.UserApi
import lsvapp.kitsu.data.repository.AnimeRepository
import lsvapp.kitsu.data.repository.PostRepository
import lsvapp.kitsu.data.repository.UserRepository
import lsvapp.kitsu.domain.entity.utils.DtoConverter
import lsvapp.kitsu.domain.interactor.AnimeInteractor
import lsvapp.kitsu.domain.interactor.PostInteractor
import lsvapp.kitsu.domain.interactor.UserInteractor
import lsvapp.kitsu.presentation.feed.postcreate.PostCreateViewModel
import lsvapp.kitsu.presentation.feed.postdetails.PostDetailsViewModel
import lsvapp.kitsu.presentation.feed.tab.FeedViewTabModel
import lsvapp.kitsu.presentation.movie.animeDetails.AnimeDetailsViewModel
import lsvapp.kitsu.presentation.movie.animelist.AnimeListViewModel
import lsvapp.kitsu.presentation.movie.episcodeDetails.AnimeEpisodeViewModel
import lsvapp.kitsu.presentation.movie.movietab.MovieTabViewModel
import lsvapp.kitsu.presentation.profile.CurrentProfileViewModel
import lsvapp.kitsu.presentation.profile.details.ProfileDetailsViewModel
import lsvapp.kitsu.presentation.utils.navigation.MainRouter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val dataModules
    get() = networkModule + repositoryModule + interactorModule + viewModelModules + utils


private val networkModule = module {

    factory<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    factory<Converter.Factory> { MoshiConverterFactory.create() }

    factory<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    val BASE_URL = "https://kitsu.io/api/edge/"
    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
    }

    single<AnimeApi> { get<Retrofit>().create() }
    single<PostApi> { get<Retrofit>().create() }
    single<UserApi> { get<Retrofit>().create() }

}

private val repositoryModule = module {
    single { AnimeRepository(get()) }
    single { PostRepository(get()) }
    single { UserRepository(get()) }
}

private val interactorModule = module {
    single { AnimeInteractor(get(), get()) }
    single { PostInteractor(get()) }
    single { UserInteractor(get(), get()) }
}

private val viewModelModules = module {
    viewModel { FeedViewTabModel(get(), get()) }
    viewModel { MovieTabViewModel(get()) }
    viewModel { (profileId: Long) -> ProfileDetailsViewModel(profileId, get()) }
    viewModel { (postId: Long) -> PostDetailsViewModel(postId, get(), get()) }
    viewModel { AnimeListViewModel(get()) }
    viewModel { (animeId: Long) -> AnimeDetailsViewModel(animeId, get()) }
    viewModel { PostCreateViewModel(get()) }
    viewModel { CurrentProfileViewModel(get()) }
    viewModel { (animeId: Long, episodeId: Long) ->
        AnimeEpisodeViewModel(
            animeId = animeId,
            episodeId = episodeId,
            get()
        )
    }
}

private val utils = module {
    single { MainRouter() }
    factory { DtoConverter() }
}