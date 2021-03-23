package kim.jeonghyeon.review.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ReviewAppBar(
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit = { },
    title: @Composable RowScope.() -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Column {
        TopAppBar(
            modifier = modifier,
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = MaterialTheme.colors.onSurface,
            actions = actions,
            title = { Row { title() } },
            navigationIcon = {
                Image(

                )
            }
        )
        Divider()
    }
}