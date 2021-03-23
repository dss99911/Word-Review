package kim.jeonghyeon.base.repository

import kim.jeonghyeon.base.*
import kim.jeonghyeon.client.shareFlow
import kim.jeonghyeon.client.toShare
import kim.jeonghyeon.pergist.asListFlow
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    suspend fun insertWordBook(name: String, lang: String)
    val wordBooks: Flow<List<WordBook>>
    suspend fun insertWord(
        wordBook: WordBook,
        word: String,
        meaning: String,
        sentence: String?,
        sentenceMeaning: String?,
        description: String?
    )
    suspend fun getWords(wordBook: WordBook): Flow<List<Word>>
}

class WordRepositoryImpl(
    val wordQuery: WordQueries,
    val wordBookQuery: WordBookQueries,
    val wordReviewQueries: WordReviewQueries,
    val wordTagQueries: WordTagQueries
) : WordRepository {
    val scope = MainScope()

    override suspend fun insertWordBook(name: String, lang: String) {
        wordBookQuery.insert(name, lang)
    }

    override val wordBooks: Flow<List<WordBook>>
    = wordBookQuery.selectAll().asListFlow().toShare(scope)

    override suspend fun getWords(wordBook: WordBook): Flow<List<Word>> =
        wordQuery.selectAllInWordBook(wordBook.id).asListFlow()

    override suspend fun insertWord(
        wordBook: WordBook,
        word: String,
        meaning: String,
        sentence: String?,
        sentenceMeaning: String?,
        description: String?
    ) {
        wordQuery.insert(wordBook.id, word, meaning, sentence, sentenceMeaning, description)
    }
}