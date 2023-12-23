package gigcreator.controllers

import gigcreator.constants.Constants
import gigcreator.constants.ContentStream
import gigcreator.entities.GuitarAmplifierData
import gigcreator.entities.Result
import gigcreator.repositories.GuitarAmplifierRepository
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.util.*

@RestController
class GuitarAmplifierController(private val amplifierRepository: GuitarAmplifierRepository) {

    @GetMapping("/amplifier/search/image")
    fun getAmplifierById(@RequestParam id: String): ResponseEntity<StreamingResponseBody> {

        val resource = File("images/$id.png")
        val length = resource.length()
        val input = resource.inputStream()

        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_PNG)
            .contentLength(length)
            .body(ContentStream(input, length))
    }
    //
    @GetMapping("/amplifier/search/name")
    fun searchAmplifierByName(@RequestParam name: String): List<GuitarAmplifierData>{
        return amplifierRepository.searchByName(name)
    }

    @GetMapping("/amplifier/search/brand")
    fun searchAmplifierByBrand(@RequestParam name: String): List<GuitarAmplifierData>{
        return amplifierRepository.searchByBrand(name)
    }

    @GetMapping("/amplifier/search/price")
    fun searchAmplifierByPrice(@RequestParam name: String): List<GuitarAmplifierData>{
        return amplifierRepository.searchByPrice(name)
    }

    @GetMapping("/amplifier")
    fun readAmplifier(): List<GuitarAmplifierData>{
        return amplifierRepository.findAll()
    }
    @GetMapping("/amplifier/save")
    fun saveAmplifier(
        @RequestParam("image") image: String,
        @RequestParam("brand") brand: String,
        @RequestParam("price") price: Int,
        @RequestParam("characteristics") characteristics: String,
        @RequestParam("name") name: String,
        @RequestParam("description") description: String,
        @RequestParam("technical_description") technical_description: String,
        @RequestParam("key") key: String,
    ): Result {
        val id = UUID.randomUUID()
        saveByUrl(image, id)
        val amplifier = GuitarAmplifierData()
        amplifier.brand = brand
        amplifier.price = price
        amplifier.characteristics = characteristics
        amplifier.name = name
        amplifier.description = description
        amplifier.technical_description = technical_description
        amplifier.image = id.toString()
        return if (key == Constants.KEY){
            amplifierRepository.save(amplifier)
            Result("saved")
        } else {
            Result("invalid key")
        }
    }
    private fun saveByUrl(image: String, id: UUID){
        val website = URL(image)
        val rbc: ReadableByteChannel = Channels.newChannel(website.openStream())
        val fos = FileOutputStream("images/${id}.png")
        fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
        fos.channel.close()
    }
}