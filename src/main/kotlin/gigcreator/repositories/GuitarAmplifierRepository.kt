package gigcreator.repositories

import gigcreator.entities.GuitarAmplifierData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface GuitarAmplifierRepository: JpaRepository<GuitarAmplifierData, String> {
    @Query("SELECT u FROM GuitarAmplifierData u WHERE (u.name = :name)")
    fun searchByName(@Param("name") name: String): List<GuitarAmplifierData>

    @Query("SELECT u FROM GuitarAmplifierData u WHERE (u.brand = :brand)")
    fun searchByBrand(@Param("brand") brand: String): List<GuitarAmplifierData>

    @Query("SELECT u FROM GuitarAmplifierData u WHERE (u.price = :price)")
    fun searchByPrice(@Param("price") price: String): List<GuitarAmplifierData>
}