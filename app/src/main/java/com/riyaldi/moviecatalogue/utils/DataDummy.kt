package com.riyaldi.moviecatalogue.utils

import com.riyaldi.moviecatalogue.data.source.local.entity.DetailEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.*
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.Genre
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.ProductionCompany
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.ProductionCountry
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.SpokenLanguage
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.*

typealias TvShowGenre = com.riyaldi.moviecatalogue.data.source.remote.response.tv.Genre
typealias TvShowProductionCompany = com.riyaldi.moviecatalogue.data.source.remote.response.tv.ProductionCompany
typealias TvShowProductionCountry = com.riyaldi.moviecatalogue.data.source.remote.response.tv.ProductionCountry
typealias TvShowSpokenLanguage = com.riyaldi.moviecatalogue.data.source.remote.response.tv.SpokenLanguage

object DataDummy {
    fun getMovies(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                464052,
                "Wonder Woman 1984",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                7.2
            ),
            MovieEntity(
                508442,
                "Soul",
                "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                8.4
            ),
            MovieEntity(
                517096,
                "Cosmoball",
                "/eDJYDXRoWoUzxjd52gtz5ODTSU1.jpg",
                5.3
            )
        )
    }

    fun getDetailMovie(): DetailEntity {
        return DetailEntity(
                "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                listOf("Fantasy", "Action", "Adventure"),
                468552,
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "2020-12-16",
                151,
                "Wonder Woman 1984",
                7.2
        )
    }

    fun getTvShows(): List<TvShowEntity> {
        return listOf(
            TvShowEntity(
                77169,
                "Cobra Kai",
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                8.1
            ),
            TvShowEntity(
                44217,
                "Vikings",
                "/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg",
                8.0
            ),
            TvShowEntity(
                82856,
                "The Mandalorian",
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                8.5
            )
        )
    }

    fun getDetailTvShow(): DetailEntity {
        return DetailEntity(
                "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                listOf("Drama", "Action", "Adventure"),
                77169,
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                "2018-05-02",
                30,
                "Cobra Kai",
                8.1
        )
    }

    // for remote
    fun getRemoteMovies(): List<Movie> {
        return listOf(
                Movie(
                        adult = false,
                        backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                        genreIds =  listOf(14, 28, 12),
                        id = 464052,
                        originalLanguage = "en",
                        originalTitle = "Wonder Woman 1984",
                        overview = "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                        popularity = 3342.686,
                        posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                        releaseDate = "2020-12-16",
                        title = "Wonder Woman 1984",
                        video = false,
                        voteAverage = 7.2,
                        voteCount = 2641
                ),
                Movie(
                        adult = false,
                        backdropPath = "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                        genreIds =  listOf(16, 35, 18, 10402, 14),
                        id = 508442,
                        originalLanguage = "en",
                        originalTitle = "Soul",
                        overview = "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                        popularity = 2849.972,
                        posterPath = "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                        releaseDate = "2020-12-25",
                        title = "Soul",
                        video = false,
                        voteAverage = 8.4,
                        voteCount = 3477
                ),
                Movie(
                        adult = false,
                        backdropPath = "/ibwOX4xUndc6E90MYfglghWvO5Z.jpg",
                        genreIds =  listOf(878, 12),
                        id = 517096,
                        originalLanguage = "ru",
                        originalTitle = "Вратарь Галактики",
                        overview = "Cosmoball is a mesmerizing intergalactic game of future played between humans and aliens at the giant extraterrestrial ship hovering in the sky over Earth. A young man with enormous power of an unknown nature joins the team of hot-headed superheroes in exchange for a cure for his mother’s deadly illness. The Four from Earth will fight not only to defend the honor of their home planet in the spectacular game, but to face the unprecedented threat to the Galaxy and embrace their own destiny.",
                        popularity = 1893.591,
                        posterPath = "/eDJYDXRoWoUzxjd52gtz5ODTSU1.jpg",
                        releaseDate = "2020-08-27",
                        title = "Cosmoball",
                        video = false,
                        voteAverage = 5.5,
                        voteCount = 59
                )
        )
    }

    fun getRemoteDetailMovie(): MovieDetailResponse {
        return MovieDetailResponse(
                adult = false,
                backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                belongsToCollection = BelongsToCollection(
                        id = 468552,
                        name = "Wonder Woman Collection",
                        posterPath = "/8AQRfTuTHeFTddZN4IUAqprN8Od.jpg",
                        backdropPath = "/n9KlvCOBFDmSyw3BgNrkUkxMFva.jpg"
                ),
                budget = 200000000,
                genres = listOf(
                        Genre(
                                id = 14,
                                name = "Fantasy"
                        ),
                        Genre(
                                id = 28,
                                name = "Action"
                        ),
                        Genre(
                                id = 12,
                                name = "Adventure"
                        )
                ),
                homepage = "https://www.warnerbros.com/movies/wonder-woman-1984",
                id = 464052,
                imdbId = "tt7126948",
                originalLanguage = "en",
                originalTitle = "Wonder Woman 1984",
                overview = "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                popularity = 3342.686,
                posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                productionCompanies = listOf(
                        ProductionCompany(
                                id = 9993,
                                logoPath = "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
                                name = "DC Entertainment",
                                originCountry = "US"
                        ),
                        ProductionCompany(
                                id = 174,
                                logoPath = "/ky0xOc5OrhzkZ1N6KyUxacfQsCk.png",
                                name = "Warner Bros. Pictures",
                                originCountry = "US"
                        ),
                        ProductionCompany(
                                id = 114152,
                                logoPath = null,
                                name = "The Stone Quarry",
                                originCountry = "US"
                        ),
                        ProductionCompany(
                                id = 128064,
                                logoPath = "/13F3Jf7EFAcREU0xzZqJnVnyGXu.png",
                                name = "DC Films",
                                originCountry = "US"
                        ),
                        ProductionCompany(
                                id = 507,
                                logoPath = "/z7H707qUWigbjHnJDMfj6QITEpb.png",
                                name = "Atlas Entertainment",
                                originCountry = "US"
                        ),
                        ProductionCompany(
                                id = 429,
                                logoPath = "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
                                name = "DC Comics",
                                originCountry = "US"
                        )
                ),
                productionCountries = listOf(
                        ProductionCountry(
                                iso31661 = "US",
                                name = "United States of America"
                        )
                ),
                releaseDate = "2020-12-16",
                revenue = 131400000,
                runtime = 151,
                spokenLanguages = listOf(
                        SpokenLanguage(
                                englishName = "English",
                                iso6391 = "en",
                                name = "English"
                        )
                ),
                status = "Released",
                tagline = "A new era of wonder begins.",
                title = "Wonder Woman 1984",
                video = false,
                voteAverage = 7.2,
                voteCount = 2654

        )
    }

    fun getRemoteTvShows(): List<TvShow> {
        return listOf(
                TvShow(
                        backdropPath = "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                        firstAirDate = "2018-05-02",
                        genreIds = listOf(10759, 18),
                        id = 77169,
                        name = "Cobra Kai",
                        originCountry = listOf("US"),
                        originalLanguage = "en",
                        originalName = "Cobra Kai",
                        overview = "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                        popularity = 1132.227,
                        posterPath = "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                        voteAverage = 8.1,
                        voteCount = 1717
                ),
                TvShow(
                        backdropPath = "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                        firstAirDate = "2019-11-12",
                        genreIds = listOf(10765, 10759),
                        id = 82856,
                        name = "The Mandalorian",
                        originCountry = listOf("US"),
                        originalLanguage = "en",
                        originalName = "The Mandalorian",
                        overview = "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                        popularity = 1001.909,
                        posterPath = "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                        voteAverage = 8.5,
                        voteCount = 5135
                ),
                TvShow(
                        backdropPath = "/aq2yEMgRQBPfRkrO0Repo2qhUAT.jpg",
                        firstAirDate = "2013-03-03",
                        genreIds = listOf(10759, 18),
                        id = 44217,
                        name = "Vikings",
                        originCountry = listOf("CA"),
                        originalLanguage = "en",
                        originalName = "Vikings",
                        overview = "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                        popularity = 976.685,
                        posterPath = "/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg",
                        voteAverage = 8.0,
                        voteCount = 3794
                )
        )
    }

    fun getRemoteDetailTvShow(): TvShowDetailResponse {
        return TvShowDetailResponse(
                backdropPath = "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                createdBy = listOf(
                        CreatedBy(
                                id = 68844,
                                creditId = "5ab47d649251417b020149ed",
                                name = "Hayden Schlossberg",
                                gender = 2,
                                profilePath = null
                        ),
                        CreatedBy(
                                id = 347335,
                                creditId = "5ab36c76c3a368615b004c6e",
                                name = "Josh Heald",
                                gender = 2,
                                profilePath = null
                        ),
                        CreatedBy(
                                id = 1801553,
                                creditId = "5ab47d690e0a265f24017c48",
                                name = "John Hurwitz",
                                gender = 2,
                                profilePath = null
                        )
                ),
                episodeRunTime = listOf(30),
                firstAirDate = "2018-05-02",
                genres = listOf(
                        TvShowGenre(
                                id = 10759,
                                name = "Action & Adventure"
                        ),
                        TvShowGenre(
                                id = 18,
                                name = "Drama"
                        )
                ),
                homepage = "https://www.netflix.com/title/81002370",
                id = 77169,
                inProduction = true,
                languages = listOf("en"),
                lastAirDate = "2021-01-01",
                lastEpisodeToAir = LastEpisodeToAir(
                        airDate = "2021-01-01",
                        episodeNumber = 10,
                        id = 2529899,
                        name = "December 19",
                        overview = "Old wounds begin to heal at a country club holiday party, but a brutal assault by Kreese's students leads to new betrayals and alliances.",
                        productionCode = "",
                        seasonNumber = 3,
                        stillPath = "/AvnkMnigxqapasWQCFpyXLPdxmG.jpg",
                        voteAverage = 7.5,
                        voteCount = 2
                ),
                name = "Cobra Kai",
                nextEpisodeToAir = null,
                networks = listOf(
                        Network(
                                name = "Netflix",
                                id = 213,
                                logoPath = "/wwemzKWzjKYJFfCeiB57q3r4Bcm.png",
                                originCountry = ""
                        ),
                        Network(
                                name = "YouTube Premium",
                                id = 1436,
                                logoPath = "/3p05CgodUb9gPayuliuhawNj1Wo.png",
                                originCountry = "US"
                        )
                ),
                numberOfEpisodes = 30,
                numberOfSeasons = 3,
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "Cobra Kai",
                overview = "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                popularity = 1132.227,
                posterPath = "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                productionCompanies = listOf(
                        TvShowProductionCompany(
                                id = 101024,
                                logoPath = null,
                                name = "Hurwitz & Schlossberg Productions",
                                originCountry = "US"
                        ),
                        TvShowProductionCompany(
                                id = 11073,
                                logoPath = "/wHs44fktdoj6c378ZbSWfzKsM2Z.png",
                                name = "Sony Pictures Television",
                                originCountry = "US"
                        ),
                        TvShowProductionCompany(
                                id = 907,
                                logoPath = "/ca5SWI5uvU985f8Kbb4xc8AmVWH.png",
                                name = "Overbrook Entertainment",
                                originCountry = "US"
                        ),
                ),
                productionCountries = listOf(
                        TvShowProductionCountry(
                                iso31661 = "US",
                                name = "United States of America"
                        )
                ),
                seasons = listOf(
                        Season(
                                airDate = "2018-05-02",
                                episodeCount = 10,
                                id = 99459,
                                name = "Season 1",
                                overview = "Decades after the tournament that changed their lives, the rivalry between Johnny and Daniel reignites.",
                                posterPath = "/lLrKXnM3WPEtrPCd1aTHT6x3hn.jpg",
                                seasonNumber = 1
                        ),
                        Season(
                                airDate = "2019-04-24",
                                episodeCount = 10,
                                id = 120052,
                                name = "Season 2",
                                overview = "Johnny continues building a new life, but a face from his past could disrupt his future. Meanwhile, Daniel opens a Miyagi-Do studio to rival Cobra Kai.",
                                posterPath = "/77kyNXN6yCRjDt9eBMj96vLMx8W.jpg",
                                seasonNumber = 2
                        ),
                        Season(
                                airDate = "2021-01-01",
                                episodeCount = 10,
                                id = 160283,
                                name = "Season 3",
                                overview = "With a new sensei at the helm of the Cobra Kai dojo, a three-way feud takes center stage. Old grudges — like Cobra Kai — never die.",
                                posterPath = "/5DfWqh360sjMxqj3Th3DZdnFk3I.jpg",
                                seasonNumber = 3
                        )
                ),
                spokenLanguages = listOf(
                        TvShowSpokenLanguage(
                                englishName = "English",
                                iso6391 = "en",
                                name = "English"
                        )
                ),
                status = "Returning Series",
                tagline = "Cobra Kai never dies.",
                type = "Scripted",
                voteAverage = 8.1,
                voteCount = 1724
        )
    }
}