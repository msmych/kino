package uk.matvey.kino

import uk.matvey.kino.movies.MovieService

class Services(repos: Repos) {

    val movieService = MovieService(repos.movieRepo)
}
