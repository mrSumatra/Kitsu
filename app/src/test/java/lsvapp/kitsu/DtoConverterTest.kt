package lsvapp.kitsu

import lsvapp.kitsu.domain.entity.*
import lsvapp.kitsu.domain.entity.dto.*
import lsvapp.kitsu.domain.entity.enums.AgeRating
import lsvapp.kitsu.domain.entity.utils.DtoConverter
import org.junit.Assert
import org.junit.Test

class DtoConverterTest {

    @Test
    fun `data to user`() {
        val dtoConverter = DtoConverter()
        val expected = User(
            id = 12421,
            name = "name",
            about = "about",
            gender = "gender",
            coverImage = null,
            avatar = null,
            birthday = "birthday",
            followersCount = "followersCount",
            followingCount = "followingCount",
            likesGivenCount = "likesGivenCount",
            location = "localtion"
        )
        Assert.assertEquals(expected, dtoConverter.dataToUser(dataUser))
    }

    @Test
    fun `data to anime episode`() {
        val dtoConverter = DtoConverter()
        val expected = AnimeEpisode(
            id = 12421,
            canonicalTitle = "canonicalTitle",
            description = "description",
            seasonNumber = "seasonNumber",
            number = "number",
            length = "length"
        )
        Assert.assertEquals(expected, dtoConverter.dataToAnimeEpisode(dataAnimeEpisode))
    }

    @Test
    fun `data to groupe`() {
        val dtoConverter = DtoConverter()
        val expected = Groupe(
            id = 12421,
            about = "about",
            locale = "locale",
            membersCount = "membersCount",
            name = "name",
            avatar = null
        )
        Assert.assertEquals(expected, dtoConverter.dataToGroupe(dataGropeDto))
    }

    @Test
    fun `data to anime`() {
        val dtoConverter = DtoConverter()
        val expected = Anime(
            id = 12421,
            canonicalTitle = "canonicalTitle",
            startDate = "startDate",
            description = "description",
            youtubeVideoId = "youtubeVideoId",
            posterImage = Image(
                null, null, null, null, null
            ),
            coverImage = null,
            ageRating = AgeRating.G,
            averageRating = "averageRating",
            popularityRank = 10,
            userCount = null,
            favoritesCount = 0,
            episodeCount = 10,
            episodeLength = 1,
            endDate = "1",
            status = "status"
        )
        Assert.assertEquals(expected, dtoConverter.dataToAnime(dataAnime))
    }

    companion object {

        private val dataAnime = Data<AnimeDto>(
            id = 12421,
            type = "",
            attributes = AnimeDto(
                canonicalTitle = "canonicalTitle",
                startDate = "startDate",
                description = "description",
                youtubeVideoId = "youtubeVideoId",
                posterImage = Image(
                    null, null, null, null, null
                ),
                coverImage = null,
                ageRating = AgeRating.G,
                averageRating = "averageRating",
                popularityRank = 10,
                userCount = null,
                favoritesCount = 0,
                episodeCount = 10,
                episodeLength = 1,
                endDate = "1",
                status = "status"
            )
        )

        private val dataUser = Data<UserDto>(
            id = 12421,
            type = "",
            attributes = UserDto(
                name = "name",
                about = "about",
                gender = "gender",
                coverImage = null,
                avatar = null,
                birthday = "birthday",
                followersCount = "followersCount",
                followingCount = "followingCount",
                likesGivenCount = "likesGivenCount",
                location = "localtion"
            )
        )

        private val dataAnimeEpisode = Data<AnimeEpisodeDto>(
            id = 12421,
            type = "",
            attributes = AnimeEpisodeDto(
                canonicalTitle = "canonicalTitle",
                description = "description",
                seasonNumber = "seasonNumber",
                number = "number",
                length = "length"
            )
        )

        private val dataGropeDto = Data<GroupeDto>(
            id = 12421,
            type = "",
            attributes = GroupeDto(
                about = "about",
                locale = "locale",
                membersCount = "membersCount",
                name = "name",
                avatar = null
            )
        )
    }
}