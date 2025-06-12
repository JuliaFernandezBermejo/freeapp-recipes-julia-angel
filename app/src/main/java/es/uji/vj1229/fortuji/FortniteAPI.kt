package es.uji.vj1229.fortuji

import android.graphics.drawable.Icon
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FortniteAPI {

    // News
    class NewsResponse (val data: DataResponse)

    class DataResponse (
        val image: String,
        val motds: List<MotdResponse>
    )

    class MotdResponse (
        val title: String,
        val tabTitle: String,
        val tileImage: String,
        val body: String
    )

    @Headers("Accept: application/json")
    @GET("news/br")
    suspend fun getNews(): NewsResponse

    //Cosmetics
    class CosmeticSearchResponse(val data: List<CosmeticResponse>)

    class CosmeticResponse (
        val id: String,
        val name: String,
        val description: String,
        val type: TypeResponse,
        val rarity: RarityResponse,
        val images: ImagesResponse,
        val showcaseVideo: String?,
        val added: String
    )

    class TypeResponse (
        val value: String,
        val displayValue: String
    )

    class RarityResponse (
        val value: String,
        val displayValue: String
    )

    class ImagesResponse (
        val bean : ImageSizeResponse?,
        val featured: String?,
        val icon: String?,
        val lego: ImageSizeResponse?,
        val other: OtherResponse?,
        val smallIcon: String?
    )

    class ImageSizeResponse(
        val large: String?,
        val small: String?,
        val wide: String?
    )

    class OtherResponse (
        val background: String?,
        val coverart: String?,
        val decal: String?
    )

    @Headers("Accept: application/json")
    @GET("cosmetics/br/search/all")
    suspend fun getCosmeticsByName(
        @Query("name") name: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ) : CosmeticSearchResponse

    @Headers("Accept: application/json")
    @GET("cosmetics/br/search/all")
    suspend fun getCosmeticsByID(
        @Query("id") id : String
    ) : CosmeticSearchResponse

    @Headers("Accept: application/json")
    @GET("cosmetics/br/search/all")
    suspend fun getCosmeticsByDescription(
        @Query("description") description : String,
        @Query("matchMethod") matchMethod: String = "contains"
    ) : CosmeticSearchResponse
}