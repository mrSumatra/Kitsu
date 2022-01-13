package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.UserRepository
import lsvapp.kitsu.domain.entity.Image
import lsvapp.kitsu.domain.entity.User
import lsvapp.kitsu.domain.entity.dto.Data
import lsvapp.kitsu.domain.entity.dto.UserDto
import lsvapp.kitsu.domain.entity.utils.DtoConverter
import kotlin.random.Random

class UserInteractor(
    private val userRepository: UserRepository,
    private val dtoConverter: DtoConverter
) {
    suspend fun getUsers(page: Int? = null, size: Int? = null) =
        userRepository.getUsers(page = page, size = size).data.map {
            dtoConverter.dataToUser(it)
        }

    suspend fun getUserById(id: Long): User {
        val user = userRepository.getUserById(id)
        return dtoConverter.dataToUser(user.data)
    }

    fun getCurrentUser() = dtoConverter.dataToUser(currentUser)

    companion object {
        private val currentUser = Data<UserDto>(
            id = Random.nextLong(),
            attributes = UserDto(
                name = "Ivan",
                avatar = Image(
                    tiny = "https://adindex.ru/files2/news/2019_07/273997_inkognito.jpg",
                    small = "https://adindex.ru/files2/news/2019_07/273997_inkognito.jpg",
                    medium = "https://adindex.ru/files2/news/2019_07/273997_inkognito.jpg",
                    large = "https://adindex.ru/files2/news/2019_07/273997_inkognito.jpg",
                    original = "https://adindex.ru/files2/news/2019_07/273997_inkognito.jpg"
                ),
                coverImage = Image(
                    tiny = "https://loveopium.ru/content/2015/01/detroit/00s.jpg",
                    small = "https://loveopium.ru/content/2015/01/detroit/00s.jpg",
                    medium = "https://loveopium.ru/content/2015/01/detroit/00s.jpg",
                    large = "https://loveopium.ru/content/2015/01/detroit/00s.jpg",
                    original = "https://loveopium.ru/content/2015/01/detroit/00s.jpg"
                ),
                birthday = "24-04-1995",
                gender = "MALE",
                about = "text about me",
                location = "Russia, Moskow",
                likesGivenCount = "532",
                followingCount = "134",
                followersCount = "1342"
            ),
            type = "User"
        )
    }
}