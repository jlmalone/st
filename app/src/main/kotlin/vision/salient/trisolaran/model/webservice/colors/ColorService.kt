package vision.salient.trisolaran.model.webservice.colors

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.plugins.resources.prepareGet
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.resources.Resource
import okio.FileSystem
import okio.Path
import vision.salient.trisolaran.model.webservice.colors.dto.ColorDto
import vision.salient.trisolaran.model.webservice.colors.dto.ColorsDto
import vision.salient.trisolaran.model.webservice.colors.dto.ErrorDto
import vision.salient.trisolaran.util.ext.ApiResponse
import vision.salient.trisolaran.util.ext.CacheApiResponse
import vision.salient.trisolaran.util.ext.cacheHeaders
import vision.salient.trisolaran.util.ext.executeSafely
import vision.salient.trisolaran.util.ext.executeSafelyCached
import vision.salient.trisolaran.util.ext.saveBodyToFile
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ColorService
@Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getColorsBySafeArgs(): ApiResponse<ColorsDto, Unit> {
        return vision.salient.trisolaran.util.ext.executeSafely({ get(ColorsResource.All()) }) { it.body() }
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun getColorsToFile(filesystem: FileSystem, file: Path): ApiResponse<Boolean, Boolean> {
        return try {
            httpClient.prepareGet(ColorsResource.All()).execute { httpResponse ->
                if (httpResponse.status.isSuccess() && vision.salient.trisolaran.util.ext.saveBodyToFile(filesystem, file)) {
                    ApiResponse.Success(true) as ApiResponse<Boolean, Boolean> // This is unnecessary, but the compiler doesn't know that.
                } else {
                    Logger.e { "Failed to save colors json (${httpResponse.status}" }
                    ApiResponse.Failure.Error(false) as ApiResponse<Boolean, Boolean> // This is unnecessary, but the compiler doesn't know that.
                }
            }
        } catch (expected: Throwable) {
            return ApiResponse.Failure.Exception(expected) as ApiResponse<Boolean, Boolean> // This is unnecessary, but the compiler doesn't know that.
        }
    }

    suspend fun getColorsByFullUrl(): ApiResponse<ColorsDto, Unit> {
        return vision.salient.trisolaran.util.ext.executeSafely(
                { get("https://raw.githubusercontent.com/jeffdcamp/android-template/33017aa38f59b3ff728a26c1ee350e58c8bb9647/src/test/json/rest-test.json") }
        ) {
            it.body()
        }
    }

    suspend fun postColors(colorsDto: ColorsDto): ApiResponse<Unit, ErrorDto> {
        return vision.salient.trisolaran.util.ext.executeSafely(
                {
                    post(ColorsResource.All()) {
                        setBody(colorsDto)
                        contentType(ContentType.Application.Json)
                    }
                },
                mapError = {
                    ApiResponse.Failure.Error(ErrorDto(it.status.value, it.status.description))
                },
                mapSuccess = { }
        )
    }

    suspend fun getSearch(query: String): ApiResponse<ColorDto, ErrorDto> {
        return vision.salient.trisolaran.util.ext.executeSafely(
                { get(ColorsResource.Search(query = query)) },
                mapError = {
                    ApiResponse.Failure.Error(ErrorDto(it.status.value, it.status.description))
                },
                mapSuccess = { it.body() }
        )
    }

    suspend fun getColor(id: Long): ApiResponse<ColorDto, ErrorDto> {
        return vision.salient.trisolaran.util.ext.executeSafely(
                { get(ColorsResource.Color(id = id)) }, // json/{id}
                mapError = {
                    ApiResponse.Failure.Error(ErrorDto(it.status.value, it.status.description))
                },
                mapSuccess = { it.body() }
        )
    }

    suspend fun getColorHsl(id: Long): ApiResponse<ColorDto, ErrorDto> {
        return vision.salient.trisolaran.util.ext.executeSafely(
                { get(ColorsResource.Color(id = id, format = "hsl")) }, // json/{id}?format=hsl
                mapError = {
                    ApiResponse.Failure.Error(ErrorDto(it.status.value, it.status.description))
                },
                mapSuccess = { it.body() }
        )
    }

    suspend fun getColors(etag: String?, lastModified: String? = null): CacheApiResponse<ColorDto, Unit> {
        return vision.salient.trisolaran.util.ext.executeSafelyCached(
                {
                    get(ColorsResource.All()) {
                        vision.salient.trisolaran.util.ext.cacheHeaders(etag = etag, lastModified = lastModified)
                    }
                },
                mapSuccess = { it.body() }
        )
    }
}

@Resource("json")
private object ColorsResource {
    @Resource("rest-test.json") // json/rest-test.json
    class All(val parent: ColorsResource = org.jdc.template.model.webservice.colors.ColorsResource)
    @Resource("rest-test.json") // json/rest-test.json?id=<colorId>
    class Search(val parent: ColorsResource = org.jdc.template.model.webservice.colors.ColorsResource, val query: String)

    @Resource("{id}") // json/{id}?format=<format>
    class Color(val parent: ColorsResource = org.jdc.template.model.webservice.colors.ColorsResource, val id: Long, val format: String? = null)
}
