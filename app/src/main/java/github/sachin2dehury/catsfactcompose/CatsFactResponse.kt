package github.sachin2dehury.catsfactcompose

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatsFactResponse(
    @Json(name = "current_page")
    val currentPage: Int? = null,
    val data: List<Data?>? = null,
    @Json(name = "first_page_url")
    val firstPageUrl: String? = null,
    val from: Int? = null,
    @Json(name = "last_page")
    val lastPage: Int? = null,
    @Json(name = "last_page_url")
    val lastPageUrl: String? = null,
    val links: List<Link?>? = null,
    @Json(name = "next_page_url")
    val nextPageUrl: String? = null,
    val path: String? = null,
    @Json(name = "per_page")
    val perPage: Int? = null,
    @Json(name = "prev_page_url")
    val prevPageUrl: String? = null,
    val to: Int? = null,
    val total: Int? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        val fact: String? = null,
        val length: Int? = null,
    )

    @JsonClass(generateAdapter = true)
    data class Link(
        val active: Boolean? = null,
        val label: String? = null,
        val url: String? = null,
    )
}
