package github.sachin2dehury.catsfactcompose

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatsFactPagingSource(private val repository: CatsFactRepository) :
    PagingSource<Int, CatsFactResponse.Data>() {
    override fun getRefreshKey(state: PagingState<Int, CatsFactResponse.Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>) = withContext(Dispatchers.IO) {
        val page = params.key ?: 1
        val perPage = params.loadSize
        val response = repository.getCatsFactPaginated(page, perPage)
        if (response.isSuccessful && response.code() == 200) {
            val nextPage = if (response.body()?.nextPageUrl.isNullOrEmpty()) null else page + 1
            val list = response.body()?.data?.filterNotNull().orEmpty()
            LoadResult.Page(data = list, nextKey = nextPage, prevKey = null)
        } else {
            LoadResult.Error(Throwable())
        }
    }
}
