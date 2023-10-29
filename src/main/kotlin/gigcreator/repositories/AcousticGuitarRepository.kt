package gigcreator.repositories

import gigcreator.entities.AcousticGuitarData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AcousticGuitarRepository: JpaRepository<AcousticGuitarData, String> {
    @Query("SELECT u FROM AcousticGuitarData u WHERE (u.name = :name)")
    fun searchByName(@Param("name") name: String): List<AcousticGuitarData>

    @Query("SELECT u FROM AcousticGuitarData u WHERE (u.brand = :brand)")
    fun searchByBrand(@Param("brand") brand: String): List<AcousticGuitarData>

    @Query("SELECT u FROM AcousticGuitarData u WHERE (u.price = :price)")
    fun searchByPrice(@Param("price") price: String): List<AcousticGuitarData>

    @Query("SELECT u FROM AcousticGuitarData u WHERE (u.strings = :strings)")
    fun searchByStrings(@Param("strings") strings: Int): List<AcousticGuitarData>
}