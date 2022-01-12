package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.UserRepository
import lsvapp.kitsu.domain.entity.Image
import lsvapp.kitsu.domain.entity.dto.Data
import lsvapp.kitsu.domain.entity.dto.UserDto
import kotlin.random.Random

class UserInteractor(
    private val userRepository: UserRepository
) {
    suspend fun getUsers(page: Int? = null, size: Int? = null) =
        userRepository.getUsers(page = page, size = size)

    suspend fun getUserById(id: Long) = userRepository.getUserById(id)

    fun getCurrentUser() = Data<UserDto>(
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
            location = "Russia, Moskow"
        ),
        type = "User"
    )
}