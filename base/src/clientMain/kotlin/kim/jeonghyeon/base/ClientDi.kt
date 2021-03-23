package kim.jeonghyeon.base

import base.generated.SimpleConfig
import base.generated.db.db
import kim.jeonghyeon.api.PreferenceApi
import kim.jeonghyeon.auth.SignOAuthClient
import kim.jeonghyeon.base.repository.WordRepository
import kim.jeonghyeon.base.repository.WordRepositoryImpl
import kim.jeonghyeon.pergist.Preference
import kim.jeonghyeon.review.DB
import kim.jeonghyeon.type.weak

var di: ClientDI = ClientDIImpl()

interface ClientDI {
    val preferenceApi: PreferenceApi
    val preference: Preference
    val oauthClient: SignOAuthClient
    val wordRepository: WordRepository
}

/**
 * 1. testable by injecting mock service
 * 2. caller doesn't take care of lifecycle of each service (singleton, factory, weak, etc)
 */
class ClientDIImpl : ClientDI {
    override val oauthClient: SignOAuthClient get() = SignOAuthClient(SimpleConfig.buildTimeLocalIpAddress)
    override val preferenceApi: PreferenceApi get() = api()
    override val preference: Preference = Preference()

    //wordQueries notify to listeners when data is changed.
    //in order that A page change data and B page refresh when data changed, you have to use single instance of Queries.
    //but also if it's not used. need to be cleared.
    val wordQueries: WordQueries by weak { db<DB>().wordQueries }
    val wordBookQueries: WordBookQueries by weak { db<DB>().wordBookQueries }
    val wordReviewQueries: WordReviewQueries by weak { db<DB>().wordReviewQueries }
    val wordTagQueries: WordTagQueries by weak { db<DB>().wordTagQueries }
    override val wordRepository: WordRepository get() = WordRepositoryImpl(wordQueries, wordBookQueries, wordReviewQueries, wordTagQueries)
}