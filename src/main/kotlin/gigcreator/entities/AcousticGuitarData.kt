package gigcreator.entities

import jakarta.persistence.*

@Entity
@Table(name = "acoustic_guitar_storage")
class AcousticGuitarData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = ""
    var brand: String = ""
    var price: Int = 0
    var strings: Int = 0
    var name: String = ""
    var description: String = ""
    var technical_description: String = ""
    var image: String = ""
}