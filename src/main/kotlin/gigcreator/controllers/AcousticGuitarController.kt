package gigcreator.controllers

import gigcreator.constants.Constants
import gigcreator.constants.ContentStream
import gigcreator.entities.AcousticGuitarData
import gigcreator.entities.Result
import gigcreator.repositories.AcousticGuitarRepository
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
class AcousticGuitarController(private val acousticGuitarRepository: AcousticGuitarRepository) {

    @GetMapping("/guitar/acoustic/image")
    fun getGuitarById(@RequestParam id: String): ResponseEntity<StreamingResponseBody>{

        val resource = File("images/$id.png")
        val length = resource.length()
        val input = resource.inputStream()

        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_PNG)
            .contentLength(length)
            .body(ContentStream(input, length))
    }
    @GetMapping("/guitar/acoustic")
    fun readAcousticGuitar(): List<AcousticGuitarData>{
        return acousticGuitarRepository.findAll()
    }
    @GetMapping("/guitar/acoustic/save")
    fun saveGuitar(
        @RequestParam("image") image: String,
        @RequestParam("brand") brand: String,
        @RequestParam("price") price: Int,
        @RequestParam("strings") strings: Int,
        @RequestParam("name") name: String,
        @RequestParam("description") description: String,
        @RequestParam("technical_description") technical_description: String,
        @RequestParam("key") key: String,
    ): Result {
        val id = UUID.randomUUID()
        saveByUrl(image, id)
        val guitar = AcousticGuitarData()
        guitar.brand = brand
        guitar.price = price
        guitar.strings = strings
        guitar.name = name
        guitar.description = description
        guitar.technical_description = technical_description
        guitar.image = id.toString()
        return if (key == Constants.KEY){
            acousticGuitarRepository.save(guitar)
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