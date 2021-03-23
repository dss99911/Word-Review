package kim.jeonghyeon.review

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kim.jeonghyeon.androidlibrary.compose.Screen
import kim.jeonghyeon.androidlibrary.compose.unaryPlus
import kim.jeonghyeon.androidlibrary.compose.widget.Button
import kim.jeonghyeon.androidlibrary.compose.widget.ScrollableColumn
import kim.jeonghyeon.base.helloText
import kim.jeonghyeon.base.HomeViewModel
import kim.jeonghyeon.review.components.ReviewScreen
import kim.jeonghyeon.review.components.ReviewTextField

@Composable
fun HomeScreen(model: HomeViewModel) {
    ReviewScreen(
        viewModel = model,
        title = "Wordbook",
        actions = {
            Icon(
                imageVector = Icons.Outlined.Add,
                modifier = Modifier.clickable {
                    model.isShownAddDialog.value = true
                }
            )
        }
    ) {
        Column {
            ScrollableColumn(list = +model.wordBooks) {
                Text("${it.name}, ${it.lang}")
            }
        }

        if (+model.isShownAddDialog == true) {
            WordBookAddDialog(
                model,
                onDismiss = { model.isShownAddDialog.value = false },
                onAdd = { model.onClickAddWordBook()
            })
        }

    }
}

@Composable
fun WordBookAddDialog(
    model: HomeViewModel,
    onDismiss: () -> Unit,
    onAdd: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Add WordBook") },
        text = {
            Column {
                ReviewTextField(label = "Name", text = model.dialogNameToAdd)
                ReviewTextField(label = "Language", text = model.dialogLangToAdd)
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onAdd() },
                enabled = +model.dialogIsAddButtonActive ?: false
            ) {
                Text("ADD")
            }
        }
    )
}