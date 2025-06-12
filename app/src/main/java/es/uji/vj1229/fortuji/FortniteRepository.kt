package es.uji.vj1229.fortuji

import android.util.Log
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import es.uji.vj1229.fortuji.common.Cosmetic
import es.uji.vj1229.fortuji.common.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object FortniteRepository {

    private val api: FortniteAPI

    init {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory()).build()
        api = Retrofit.Builder()
            .baseUrl("https://fortnite-api.com/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(FortniteAPI::class.java)
    }

    suspend fun getNews() = try {
        withContext(Dispatchers.IO) {
            val newsResponse = api.getNews()
            val news = ArrayList<News>()
            for (motd in newsResponse.data.motds) {
                news.add(News(motd.title, motd.tabTitle, motd.tileImage, motd.body))
            }
            Result.success(Pair(newsResponse.data.image, news))
        }
    } catch (e: Exception) {
        Log.d("Prueba", e.toString())
        Result.failure(e)
    }

    suspend fun getCosmeticsByName(text: String) = try{
        withContext(Dispatchers.IO) {
            val cosmeticsSearchResponse = api.getCosmeticsByName(text)
            val cosmetics = ArrayList<Cosmetic>()
            for (cosmetic in cosmeticsSearchResponse.data)
            {
                val images = HashMap<String, String>()
                with(cosmetic.images) {
                    bean?.apply {
                        large?.let { images.put("Bean large", it) }
                        small?.let { images.put("Bean small", it) }
                        wide?.let { images.put("Bean wide", it) }
                    }
                    lego?.apply {
                        large?.let { images.put("Lego large", it) }
                        small?.let { images.put("Lego small", it) }
                        wide?.let { images.put("Lego wide", it) }
                    }
                    other?.apply {
                        background?.let { images.put("Background", it) }
                        coverart?.let { images.put("Coverart", it) }
                        decal?.let { images.put("Decal", it) }
                    }
                    featured?.let { images.put("Featured", it) }
                    icon?.let { images.put("Icon", it) }
                    smallIcon?.let { images.put("Small icon", it) }
                }

                cosmetics.add(Cosmetic(
                    cosmetic.id,
                    cosmetic.name,
                    cosmetic.description,
                    if (cosmetic.type.displayValue == "null") cosmetic.type.value else cosmetic.type.displayValue,
                    if (cosmetic.rarity.displayValue == "null") cosmetic.rarity.value else cosmetic.rarity.displayValue,
                    images,
                    cosmetic.showcaseVideo,
                    cosmetic.added
                ))
            }
            Result.success(cosmetics)
        }
    } catch (e:Exception) {
        Log.d("Fallo", e.toString())
        Result.failure(e)
    }

    suspend fun getCosmeticsByID(text: String) = try {
        withContext(Dispatchers.IO) {
            val cosmeticsSearchResponse = api.getCosmeticsByID(text)
            val cosmetics = ArrayList<Cosmetic>()
            for (cosmetic in cosmeticsSearchResponse.data)
            {
                val images = HashMap<String, String>()
                with(cosmetic.images) {
                    bean?.apply {
                        large?.let { images.put("Bean large", it) }
                        small?.let { images.put("Bean small", it) }
                        wide?.let { images.put("Bean wide", it) }
                    }
                    lego?.apply {
                        large?.let { images.put("Lego large", it) }
                        small?.let { images.put("Lego small", it) }
                        wide?.let { images.put("Lego wide", it) }
                    }
                    other?.apply {
                        background?.let { images.put("Background", it) }
                        coverart?.let { images.put("Coverart", it) }
                        decal?.let { images.put("Decal", it) }
                    }
                    featured?.let { images.put("Featured", it) }
                    icon?.let { images.put("Icon", it) }
                    smallIcon?.let { images.put("Small icon", it) }
                }

                cosmetics.add(Cosmetic(
                    cosmetic.id,
                    cosmetic.name,
                    cosmetic.description,
                    if (cosmetic.type.displayValue == "null") cosmetic.type.value else cosmetic.type.displayValue,
                    if (cosmetic.rarity.displayValue == "null") cosmetic.rarity.value else cosmetic.rarity.displayValue,
                    images,
                    null,
                    cosmetic.added
                ))
            }
            Result.success(cosmetics)
        }
    } catch (e:Exception) {
        Result.failure(e)
    }

    suspend fun getCosmeticsByDescription(text: String) = try {
        withContext(Dispatchers.IO) {
            val cosmeticsSearchResponse = api.getCosmeticsByDescription(text)
            val cosmetics = ArrayList<Cosmetic>()
            for (cosmetic in cosmeticsSearchResponse.data)
            {
                val images = HashMap<String, String>()
                with(cosmetic.images) {
                    bean?.apply {
                        large?.let { images.put("Bean large", it) }
                        small?.let { images.put("Bean small", it) }
                        wide?.let { images.put("Bean wide", it) }
                    }
                    lego?.apply {
                        large?.let { images.put("Lego large", it) }
                        small?.let { images.put("Lego small", it) }
                        wide?.let { images.put("Lego wide", it) }
                    }
                    other?.apply {
                        background?.let { images.put("Background", it) }
                        coverart?.let { images.put("Coverart", it) }
                        decal?.let { images.put("Decal", it) }
                    }
                    featured?.let { images.put("Featured", it) }
                    icon?.let { images.put("Icon", it) }
                    smallIcon?.let { images.put("Small icon", it) }
                }

                cosmetics.add(Cosmetic(
                    cosmetic.id,
                    cosmetic.name,
                    cosmetic.description,
                    if (cosmetic.type.displayValue == "null") cosmetic.type.value else cosmetic.type.displayValue,
                    if (cosmetic.rarity.displayValue == "null") cosmetic.rarity.value else cosmetic.rarity.displayValue,
                    images,
                    null,
                    cosmetic.added
                ))
            }
            Result.success(cosmetics)
        }
    } catch (e:Exception) {
        Result.failure(e)
    }
}