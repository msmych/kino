package uk.matvey.kino.movies

import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table
import uk.matvey.kino.persisntence.Repo
import java.util.UUID
import javax.sql.DataSource

class MovieRepo(ds: DataSource) : Repo(ds) {

    fun add(movie: Movie): Movie {
        return withDslCtx { ctx ->
            ctx.insertInto(MOVIES)
                .set(ID, movie.id)
                .set(TITLE, movie.title)
                .set(YEAR, movie.year)
                .execute()
            requireNotNull(find(movie.id))
        }
    }

    fun find(id: UUID): Movie? {
        return withDslCtx { ctx ->
            ctx.selectFrom(MOVIES)
                .where(ID.eq(id))
                .fetchOne()
                ?.let { record ->
                    Movie(
                        record.get(ID),
                        record.get(TITLE),
                        record.get(YEAR)
                    )
                }
        }
    }

    companion object {
        private val MOVIES = table("kino.movies")

        private val ID = field("id", UUID::class.java)
        private val TITLE = field("title", String::class.java)
        private val YEAR = field("year", Int::class.java)
    }
}
