package github.sachin2dehury.catsfactcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.catsfactcompose.ui.theme.CatsFactComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CatsFactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsFactComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    PaginatedList(
                        pagingData = viewModel.getPaginationFlow().collectAsLazyPagingItems(),
                    )
                }
            }
        }
    }
}

@Composable
fun PaginatedList(
    modifier: Modifier = Modifier,
    pagingData: LazyPagingItems<CatsFactResponse.Data>,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
            .padding(16.dp)
            .background(Color.Gray),
    ) {
        items(pagingData.itemCount) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp)
                    .background(Color.Yellow)
                    .padding(8.dp),
            ) {
                val item = pagingData[it]
                Text(text = item?.fact.orEmpty())
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(8.dp),
                )
            }
        }

        if (pagingData.loadState.append is LoadState.Loading) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(40.dp))
                }
            }
        }
    }
}
