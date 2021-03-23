package kim.jeonghyeon.review.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import kim.jeonghyeon.androidlibrary.compose.Screen
import kim.jeonghyeon.client.BaseViewModel

@Composable
fun ReviewScreen(
    viewModel: BaseViewModel,
    title: String = "",
    actions: @Composable RowScope.() -> Unit = {},
    children: @Composable (BaseViewModel) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            HomeDrawer(closeDrawer = { scaffoldState.drawerState.close() })
        },
        topBar = {
            ReviewTopBar(
                title = title,
                scaffoldState = scaffoldState,
                actions = actions
            )
        },
        bodyContent = {
            Screen(viewModel = viewModel, children = children)
        }
    )
}