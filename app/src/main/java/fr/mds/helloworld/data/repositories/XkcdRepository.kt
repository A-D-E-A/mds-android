package fr.mds.helloworld.data.repositories

import fr.mds.helloworld.data.models.XkcdComic
import fr.mds.helloworld.data.network.XkcdApiService

interface XkcdRepository {
    suspend fun getComic(id: Int): XkcdComic
}

class NetworkXkcdRepository(private val apiService: XkcdApiService): XkcdRepository {
    override suspend fun getComic(id: Int): XkcdComic = apiService.getComic(id)
}
