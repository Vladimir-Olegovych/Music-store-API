package gigcreator.repositories

import gigcreator.entities.ElectricGuitarData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ElectricGuitarRepository: JpaRepository<ElectricGuitarData, String> {
    @Query("SELECT u FROM ElectricGuitarData u WHERE (u.name = :name)")
    fun searchByName(@Param("name") name: String): List<ElectricGuitarData>

    @Query("SELECT u FROM ElectricGuitarData u WHERE (u.brand = :brand)")
    fun searchByBrand(@Param("brand") brand: String): List<ElectricGuitarData>

    @Query("SELECT u FROM ElectricGuitarData u WHERE (u.price = :price)")
    fun searchByPrice(@Param("price") price: String): List<ElectricGuitarData>

    @Query("SELECT u FROM ElectricGuitarData u WHERE (u.strings = :strings)")
    fun searchByStrings(@Param("strings") strings: Int): List<ElectricGuitarData>
}