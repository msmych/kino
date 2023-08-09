package uk.matvey.kino.persisntence

import org.jooq.DSLContext
import org.jooq.SQLDialect.POSTGRES
import org.jooq.impl.DSL
import javax.sql.DataSource

open class Repo(protected val ds: DataSource) {

    fun <T> withDslCtx(block: (DSLContext) -> T): T {
        return ds.connection.use { conn ->
            val ctx = DSL.using(conn, POSTGRES)
            block(ctx)
        }
    }
}
