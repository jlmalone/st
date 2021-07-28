package org.jdc.template.model.db.main.individual

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jdc.template.model.db.main.type.IndividualType
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime
import java.util.UUID

@Entity
data class Individual(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var householdId: String? = null,
    var individualType: IndividualType = IndividualType.HEAD,
    var firstName: String? = null,
    var lastName: String? = null,
    var birthDate: LocalDate? = null,
    var alarmTime: LocalTime = LocalTime.now(),
    var lastModified: OffsetDateTime = OffsetDateTime.now(),
    var phone: String? = null,
    var email: String? = null,
    var available: Boolean = false
) {
    fun getFullName(): String = "${firstName ?: ""} ${lastName ?: ""}"
}
