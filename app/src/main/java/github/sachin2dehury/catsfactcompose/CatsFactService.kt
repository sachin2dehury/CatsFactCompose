package github.sachin2dehury.catsfactcompose

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsFactService {

    @GET("facts")
    suspend fun getCatsFactPaginated(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<CatsFactResponse>

    companion object {
        const val BASE_URL = "https://catfact.ninja/"
    }
}
