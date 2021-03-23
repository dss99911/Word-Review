package kim.jeonghyeon.review.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

@Composable
fun ReviewTopBar(title: String, scaffoldState: ScaffoldState, actions: @Composable RowScope.() -> Unit = {}) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = {
                scaffoldState.drawerState.open()
            }) {
                Icon(Icons.Filled.Menu)
            }
        },
        actions = actions
    )
}