package picfinder.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.select
import picfinder.data.network.client.RestClient
import picfinder.data.network.client.imgurService
import picfinder.data.network.client.unsplashService
import picfinder.data.network.entity.PictureInfo
import picfinder.data.storage.database.dbQuery
import picfinder.data.storage.table.PictureSource
import picfinder.data.storage.table.PictureSources
import picfinder.data.storage.table.asPictureSourceInfo
import picfinder.server.tryOrNull

class PictureRepository(private val restClient: RestClient) {

    suspend fun getPictureBySourceId(sourceId: Int, query: String): PictureInfo? = withContext(Dispatchers.IO) {
        tryOrNull {
            when (getPictureSourceNameById(sourceId)) {
                PictureSource.UNSPLASH -> getPictureByUnsplash(query)
                PictureSource.IMGUR -> getPictureByImgur(query)
            }
        }
    }

    private suspend fun getPictureByUnsplash(query: String): PictureInfo {
        return restClient.unsplashService.getPictureByQuery(query = query)
    }

    private suspend fun getPictureByImgur(query: String): PictureInfo? {
        val result = restClient.imgurService.getPictureByQuery(query = query)
        return result.data.firstOrNull { it.imageUrl.isNotBlank() && it.query.isNotBlank() }
    }

    private suspend fun getPictureSourceNameById(id: Int) = dbQuery {
        PictureSources.select { PictureSources.id.eq(id) }
            .map { it.asPictureSourceInfo.source }
            .first()
    }
}