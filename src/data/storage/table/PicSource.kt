package picfinder.data.storage.table

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

data class PicSource(
    val id: Int,
    val name: String,
    val url: String
)

object PicSources : IntIdTable() {
    val name = varchar("name", 255)
    val url = varchar("url", 255)
}

val ResultRow.asPicSource: PicSource
    get() = PicSource(
        id = this[PicSources.id].toString().toInt(),
        name = this[PicSources.name],
        url = this[PicSources.url]
    )