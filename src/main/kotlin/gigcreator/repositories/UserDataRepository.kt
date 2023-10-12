package gigcreator.repositories

import gigcreator.entities.UserData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserDataRepository: JpaRepository<UserData, Int> {
    @Query("SELECT u FROM UserData u WHERE (u.email = :email)")
    fun searchByEmail(@Param("email") email: String): List<UserData>

    /*
    @Modifying
    @Query("UPDATE UserData u SET u.email = ?1 WHERE u.email2 = ?2")
    fun updateUserByEmail(email: String, email2: String)
     */
}
