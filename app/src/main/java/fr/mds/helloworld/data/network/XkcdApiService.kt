package fr.mds.helloworld.data.network

import fr.mds.helloworld.data.models.XkcdComic
import retrofit2.http.GET
import retrofit2.http.Path

interface XkcdApiService {
    @GET("{id}/info.0.json")
    suspend fun getComic(@Path("id") id: Int): XkcdComic
}
