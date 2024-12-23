package com.ashish.shgardi.data.model


import com.google.gson.annotations.SerializedName

data class PeopleList(
    @SerializedName("page")
    val page: Int? = 1,
    @SerializedName("results")
    val results: List<Result?>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)

data class Result(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("known_for")
    val knownFor: List<KnownFor?>? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
)
data class KnownFor(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: List<String?>? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
)