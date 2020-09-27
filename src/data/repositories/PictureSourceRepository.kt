package picfinder.data.repositories

import org.jetbrains.exposed.sql.selectAll
import picfinder.data.storage.database.dbQuery
import picfinder.data.storage.table.PictureSources
import picfinder.data.storage.table.asPictureSourceInfo

class PictureSourceRepository {

    suspend fun getPictureSources() = dbQuery {
        PictureSources.selectAll()
            .map { it.asPictureSourceInfo }
    }
}