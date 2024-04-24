package org.jdc.template.model.db.main.individual

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import vision.salient.trisolaran.model.domain.inline.Email
import vision.salient.trisolaran.model.domain.inline.FirstName
import vision.salient.trisolaran.model.domain.inline.HouseholdId
import vision.salient.trisolaran.model.domain.inline.IndividualId
import vision.salient.trisolaran.model.domain.inline.LastName
import vision.salient.trisolaran.model.domain.inline.Phone
import org.jdc.template.model.domain.type.IndividualType

@Entity("Individual")
data class IndividualEntity(
        @PrimaryKey
    val id: IndividualId,
        val householdId: HouseholdId?,
        val individualType: IndividualType,
        val firstName: FirstName?,
        val lastName: LastName?,
        val birthDate: LocalDate?,
        val alarmTime: LocalTime?,
        val phone: Phone?,
        val email: Email?,
        val available: Boolean,

        val created: Instant,
        val lastModified: Instant
)
