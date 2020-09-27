package picfinder.data.network.services

import picfinder.data.network.entity.UnsplashPictureInfo
import picfinder.server.UNSPLASH_CLIENT_ID
import picfinder.server.extensions.fromEnv
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashPictureService {

    companion object {
        private const val RESPONSE_COUNT = 10
    }

    @GET("/photos/random")
    suspend fun getPictureByQuery(
        @Query("client_id") clientId: String = UNSPLASH_CLIENT_ID.fromEnv(),
        @Query("count") count: Int = RESPONSE_COUNT,
        @Query("query") query: String
    ): List<UnsplashPictureInfo>
}