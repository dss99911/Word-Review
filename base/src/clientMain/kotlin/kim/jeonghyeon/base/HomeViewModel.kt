package kim.jeonghyeon.base

import kim.jeonghyeon.base.repository.WordRepository
import kim.jeonghyeon.client.BaseViewModel
import kim.jeonghyeon.extension.isNotEmpty
import kotlinx.coroutines.flow.map

class HomeViewModel(val wordRepository: WordRepository = di.wordRepository) : BaseViewModel() {

    val wordBooks = wordRepository.wordBooks.toData(initStatus)
    val isShownAddDialog = viewModelFlow<Boolean>()

    val dialogNameToAdd = viewModelFlow<String>()
        .withSource(isShownAddDialog) { if (it) emit("") }
    val dialogLangToAdd = viewModelFlow<String>()
        .withSource(isShownAddDialog) { if (it) emit("") }
    val dialogIsAddButtonActive = viewModelFlow<Boolean>()
            //todo add to library : withSource(vararg)
        .withSource(dialogNameToAdd) { emit(dialogNameToAdd.valueOrNull.isNotEmpty() && dialogLangToAdd.valueOrNull.isNotEmpty()) }
        .withSource(dialogLangToAdd) { emit(dialogNameToAdd.valueOrNull.isNotEmpty() && dialogLangToAdd.valueOrNull.isNotEmpty()) }

    override fun onInitialized() {
    }

    fun onClickAddWordBook() {

        status.load {
            wordRepository.insertWordBook(dialogNameToAdd.value, dialogLangToAdd.value)
            isShownAddDialog.value = false
        }
    }
}