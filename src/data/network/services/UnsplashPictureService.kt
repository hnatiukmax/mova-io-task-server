package picfinder.data.network.services

import picfinder.data.network.entity.UnsplashPictureInfo
import picfinder.server.UNSPLASH_CLIENT_ID
import picfinder.server.extensions.fromEnv
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashPictureService {

    @GET("/photos/random")
    suspend fun getPictureByQuery(
        @Query("client_id") clientId: String = UNSPLASH_CLIENT_ID.fromEnv(),
        @Query("query") query: String
    ): UnsplashPictureInfo
}