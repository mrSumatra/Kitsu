package lsvapp.kitsu.application.di

import lsvapp.kitsu.utils.BASE_URL
import lsvapp.kitsu.data.network.AnimeAPI
import lsvapp.kitsu.data.repository.KitsuRepository
import lsvapp.kitsu.domain.interactor.KitsuInteractor
import lsvapp.kitsu.presentation.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val dataModules
    get() = networkModule + repositoryModule + interactorModule + viewModelModules


private val networkModule = module {

    factory<Converter.Factory> { MoshiConverterFactory.create() }

    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }

    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get())
            .build()
    }

    single<AnimeAPI> { get<Retrofit>().create() }

}

private val repositoryModule = module {
    single{KitsuRepository(get())}
}

private val interactorModule = module {
    single{ KitsuInteractor(get()) }
}

private val viewModelModules = module {
    viewModel { MainViewModel(get()) }
}