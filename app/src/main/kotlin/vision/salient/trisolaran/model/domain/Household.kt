package vision.salient.trisolaran.model.domain

import kotlinx.datetime.Clock
import vision.salient.trisolaran.model.domain.inline.CreatedTime
import vision.salient.trisolaran.model.domain.inline.HouseholdId
import vision.salient.trisolaran.model.domain.inline.LastModifiedTime
import vision.salient.trisolaran.model.domain.inline.LastName
import java.util.UUID

data class Household(
        val id: HouseholdId = HouseholdId(UUID.randomUUID().toString()),
        val name: LastName,

        val created: CreatedTime = CreatedTime(Clock.System.now()),
        val lastModified: LastModifiedTime = LastModifiedTime(Clock.System.now())
)