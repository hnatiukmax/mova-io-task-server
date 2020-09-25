package picfinder.data.repositories

import org.jetbrains.exposed.sql.selectAll
import picfinder.data.storage.database.dbQuery
import picfinder.data.storage.table.PicSources
import picfinder.data.storage.table.asPicSource

class PicSourceRepository {

    suspend fun getPicSources() = dbQuery {
        PicSources.selectAll()
            .map { it.asPicSource }
    }
}