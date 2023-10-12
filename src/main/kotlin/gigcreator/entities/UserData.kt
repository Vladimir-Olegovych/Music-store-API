package gigcreator.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "user_data")
class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = ""
    var email: String = ""
    var password: String = ""
}