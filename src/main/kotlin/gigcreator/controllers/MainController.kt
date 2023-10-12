package gigcreator.controllers

import gigcreator.constants.Constants
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
                         @RequestParam password: String,
                         @RequestParam key: String): String {
        val user = UserData()
        user.email = email
        user.password = password

        return try {
            if (key == Constants.KEY) {
                userDataRepository.save(user)
                "data has been sent"
            }else {
                "invalid key"
            }
        }catch (e: Throwable){
            "data identity error"
        }
    }

    @GetMapping("/read")
    private fun readUser(@RequestParam key: String): List<UserData> {
        return if (key == Constants.KEY) userDataRepository.findAll() else listOf()
    }

    @GetMapping("/search")
    private fun searchUser(@RequestParam email: String,
                           @RequestParam key: String): UserData {
        return try {
            if (key == Constants.KEY){
                userDataRepository.searchByEmail(email)
            } else {
                UserData()
            }
        } catch (e: Throwable){
            UserData()
        }
    }

}