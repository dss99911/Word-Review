package kim.jeonghyeon.review.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import kim.jeonghyeon.androidlibrary.compose.unaryPlus
import kim.jeonghyeon.client.Navigator

@Composable
fun MainScaffold(content: @Composable() (PaddingValues) -> Unit) {

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            when (+Navigator.currentFlow) {
                is HomeViewModel -> HomeDrawer(closeDrawer = { scaffoldState.drawerState.close() })
                is ModelViewModel -> SubDrawer(ModelViewModel.items, closeDrawer = { scaffoldState.drawerState.close() })
                is ViewViewModel -> SubDrawer(ViewViewModel.items, closeDrawer = { scaffoldState.drawerState.close() })
                else -> {
                }
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(text = (+Navigator.currentFlow as? SampleViewModel)?.title?: "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scaffoldState.drawerState.open()
                    }) {
                        Icon(Icons.Filled.Menu)
                    }
                }
            )
        },
        bodyContent = {
            content(it)
        }
    )
}

