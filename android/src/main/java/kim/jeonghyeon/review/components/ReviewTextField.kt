package kim.jeonghyeon.review.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kim.jeonghyeon.androidlibrary.compose.unaryPlus
import kim.jeonghyeon.client.ViewModelFlow

@Composable
fun ReviewTextField(
    label: String,
    text: ViewModelFlow<String>,
    modifier: Modifier = Modifier
) {
    androidx.compose.material.OutlinedTextField(
        +text ?: "",
        { text.value = it },
        modifier,
        label = { Text(label) }
    )
}