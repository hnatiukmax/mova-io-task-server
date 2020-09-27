package picfinder.data.network.services

import picfinder.data.network.entity.ImgurPictureInfo
import picfinder.data.network.entity.JsonDataWrapper
import picfinder.server.IMGUR_CLIENT_ID
import picfinder.server.extensions.fromEnv
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurPictureService {

    @GET("/3/gallery/search")
    suspend fun getPictureByQuery(
        @Query("q") query: String,
        @Query("client_id") clientId: String = IMGUR_CLIENT_ID.fromEnv(),
        @Query("q_type") qType: String = "jpg|png"
    ): JsonDataWrapper<List<ImgurPictureInfo>>
}