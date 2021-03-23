package kim.jeonghyeon.review

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kim.jeonghyeon.androidlibrary.compose.Screen
import kim.jeonghyeon.androidlibrary.compose.unaryPlus
import kim.jeonghyeon.androidlibrary.compose.widget.Button
import kim.jeonghyeon.androidlibrary.compose.widget.ScrollableColumn
import kim.jeonghyeon.base.helloText
import kim.jeonghyeon.base.HomeViewModel

//todo Screen에 Scaffold 추가하고, screen을 상속해서, screen을 만들게 하기.
// scaffoldState를 각 스크린에서 참조할 수 있어야하므로, state를 screen에넣어주기.
// 그런데, 직관적이지 않으므로, screen의 content의 파라미터로 전달해주거나, viewModel로 처리하기
// TopAppBar는 각 스크린에서 별도로 정의하게하고, 공용으로 쓰고 싶다면, 직접 컴포저블 함수를 만들어서 사용하면 됨.
// TopAppBar의 메뉴가 클릭됐을 때, scaffold의 drawer나, 특정 액션이 취해질 수 있게 하기.
@Composable
fun HomeScreen(model: HomeViewModel) {
    val a: (b:() -> Unit) -> Unit = {
        it()
    }

    Screen(model) {
        Column {
            ScrollableColumn(list = +model.wordBooks) {
                Text("${it.name}, ${it.lang}")
            }
            Button("Add") {
                model.addWordBook("a", "b")
            }
        }


    }
}