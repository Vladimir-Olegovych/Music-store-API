package gigcreator.repositories

import gigcreator.entities.UserData
import org.springframework.data.jpa.repository.JpaRepository

interface UserDataRepository: JpaRepository<UserData, Int>