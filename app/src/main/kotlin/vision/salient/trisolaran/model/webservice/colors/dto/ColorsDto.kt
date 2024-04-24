package vision.salient.trisolaran.model.webservice.colors.dto

import kotlinx.serialization.Serializable
import vision.salient.trisolaran.model.webservice.colors.dto.ColorDto

@Serializable
data class ColorsDto(val colors: List<ColorDto>)
