package gigcreator.controllers

import gigcreator.entities.UserData
import gigcreator.repositories.UserDataRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
open class MainController(private val userDataRepository: UserDataRepository) {


    @GetMapping("/")
    private fun getMain() = "main"

    @GetMapping("/save")
    private fun saveUser(@RequestParam email: String,
                         @RequestParam password: String): String {
        val user = UserData()
        user.email = email
        user.password = password

        return userDataRepository.save(user).email
    }

    @GetMapping("/read")
    private fun readUser(): List<UserData> {
        return userDataRepository.findAll()
    }

    @GetMapping("/search")
    private fun searchUser(@RequestParam email: String): List<UserData> {
        return userDataRepository.searchByEmail(email)
    }

}