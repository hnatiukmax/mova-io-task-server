package picfinder.data.storage.table

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

enum class PictureSource {
    UNSPLASH,
    IMGUR;
}

data class PictureSourceInfo(
    val id: Int,
    val source: PictureSource,
    val url: String
)

object PictureSources : IntIdTable() {
    val name = enumerationByName("name", 255, PictureSource::class)
    val url = varchar("url", 255)
}

val ResultRow.asPictureSourceInfo: PictureSourceInfo
    get() = PictureSourceInfo(
        id = this[PictureSources.id].toString().toInt(),
        source = this[PictureSources.name],
        url = this[PictureSources.url]
    )