package vision.salient.trisolaran.individual

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.jdc.template.model.repository.IndividualRepository
import vision.salient.trisolaran.model.domain.Individual
import vision.salient.trisolaran.model.domain.inline.FirstName
import vision.salient.trisolaran.model.domain.inline.LastName
import vision.salient.trisolaran.model.domain.inline.Phone
import javax.inject.Inject

class CreateIndividualLargeTestDataUseCase
@Inject constructor(
        private val individualRepository: IndividualRepository,
        @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke() = withContext(defaultDispatcher) {
        // clear any existing items
        individualRepository.deleteAllIndividuals()

        val individualList = mutableListOf<Individual>()
        for (i in 1..25) {
            individualList.add(Individual(
                firstName = FirstName("Person"),
                lastName = LastName("$i"),
                phone = Phone("801-555-00$i"),
                individualType = IndividualType.HEAD,
                birthDate = LocalDate(1970, 1, 1),
                alarmTime = LocalTime(7, 0),
            ))
        }

        individualRepository.saveIndividuals(individualList)
    }
}
