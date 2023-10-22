package gigcreator.repositories

import gigcreator.entities.AcousticGuitarData
import org.springframework.data.jpa.repository.JpaRepository

interface AcousticGuitarRepository: JpaRepository<AcousticGuitarData, String>