package vision.salient.trisolaran.model.db.main.household

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import vision.salient.trisolaran.model.domain.inline.HouseholdId
import vision.salient.trisolaran.model.domain.inline.LastName

@Entity("Household")
data class HouseholdEntity(
        @PrimaryKey
    val id: HouseholdId,
        val name: LastName,

        val created: Instant,
        val lastModified: Instant
)
