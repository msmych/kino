package uk.matvey.kino

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.Log
import org.jooq.tools.JooqLogger
import uk.matvey.kino.movies.MovieRepo

class Repos(dataSourceConfigFile: String) {

    private val hikariConfig = HikariConfig(dataSourceConfigFile)

    private val dataSource = HikariDataSource(hikariConfig)

    init {
        JooqLogger.globalThreshold(Log.Level.INFO)
    }

    val movieRepo = MovieRepo(dataSource)
}
