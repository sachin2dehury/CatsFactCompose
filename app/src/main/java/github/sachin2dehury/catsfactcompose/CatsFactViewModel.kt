package github.sachin2dehury.catsfactcompose

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsFactViewModel @Inject constructor(private val repository: CatsFactRepository) :
    ViewModel() {

    private val pagingConfig = PagingConfig(10)

    fun getPaginationFlow() = Pager(pagingConfig, 1) {
        CatsFactPagingSource(repository)
    }.flow
}
