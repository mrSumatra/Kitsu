package lsvapp.kitsu.application.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import lsvapp.kitsu.utils.BASE_URL
import lsvapp.kitsu.data.network.AnimeApi
import lsvapp.kitsu.data.repository.AnimeRepository
import lsvapp.kitsu.domain.interactor.AnimeInteractor
import lsvapp.kitsu.presentation.feed.FeedViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val dataModules
    get() = networkModule + repositoryModule + interactorModule + viewModelModules


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

    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
    }

    single<AnimeApi> { get<Retrofit>().create() }

}

private val repositoryModule = module {
    single { AnimeRepository(get()) }
}

private val interactorModule = module {
    single { AnimeInteractor(get()) }
}

private val viewModelModules = module {
    viewModel { FeedViewModel(get()) }
}