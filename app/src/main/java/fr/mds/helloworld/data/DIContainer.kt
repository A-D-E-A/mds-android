package fr.mds.helloworld.data

import fr.mds.helloworld.data.repositories.XkcdRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import fr.mds.helloworld.data.network.XkcdApiService
import fr.mds.helloworld.data.repositories.NetworkXkcdRepository
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

interface DIContainer {
    val xkcdRepository: XkcdRepository
}

class BaseDIContainer: DIContainer {
    private val baseUrl = "https://xkcd.com/"

    private val mediaType = "application/json".toMediaType()
    private val converterFactory = Json.asConverterFactory(mediaType)

    private val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .baseUrl(baseUrl)
            .build()

    private val retrofitService: XkcdApiService by lazy { retrofit.create(XkcdApiService::class.java) }

    override val xkcdRepository: XkcdRepository by lazy { NetworkXkcdRepository(retrofitService) }
}
