package gigcreator.entities

import jakarta.persistence.*

@Entity
@Table(name = "guitar_amplifier_storage")
class GuitarAmplifierData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = ""
    var brand: String = ""
    var price: Int = 0
    var characteristics: String = ""
    var name: String = ""
    var description: String = ""
    var technical_description: String = ""
    var image: String = ""
}