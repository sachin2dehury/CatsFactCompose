package github.sachin2dehury.catsfactcompose

class CatsFactRepository(private val service: CatsFactService) {

    suspend fun getCatsFactPaginated(page: Int, perPage: Int) =
        service.getCatsFactPaginated(page, perPage)
}
