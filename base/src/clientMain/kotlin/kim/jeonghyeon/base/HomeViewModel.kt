package kim.jeonghyeon.base

import kim.jeonghyeon.base.repository.WordRepository
import kim.jeonghyeon.client.BaseViewModel

class HomeViewModel(val wordRepository: WordRepository = di.wordRepository) : BaseViewModel() {

    val wordBooks = wordRepository.wordBooks.toData(initStatus)

    override fun onInitialized() {

    }

    fun addWordBook(name: String, lang: String) {
        status.load {
            wordRepository.insertWordBook(name, lang)
        }
    }
}