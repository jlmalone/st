package vision.salient.trisolaran.ux.about

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.FileSystem
import okio.Path.Companion.toOkioPath
import vision.salient.trisolaran.individual.CreateIndividualLargeTestDataUseCase
import vision.salient.trisolaran.individual.CreateIndividualTestDataUseCase
import vision.salient.trisolaran.model.config.RemoteConfig
import vision.salient.trisolaran.model.domain.inline.FirstName
import org.jdc.template.model.repository.IndividualRepository
import vision.salient.trisolaran.model.webservice.colors.ColorService
import vision.salient.trisolaran.model.webservice.colors.dto.ColorsDto
import vision.salient.trisolaran.ui.navigation.ViewModelNav
import vision.salient.trisolaran.ui.navigation.ViewModelNavImpl
import vision.salient.trisolaran.util.ext.ApiResponse
import vision.salient.trisolaran.util.ext.message
import vision.salient.trisolaran.util.ext.onError
import vision.salient.trisolaran.util.ext.onException
import vision.salient.trisolaran.util.ext.onFailure
import vision.salient.trisolaran.util.ext.onSuccess
import vision.salient.trisolaran.util.ext.readText
import vision.salient.trisolaran.ux.about.typography.TypographyRoute
import vision.salient.trisolaran.ux.acknowledgement.AcknowledgmentsRoute
import vision.salient.trisolaran.work.WorkScheduler
import javax.inject.Inject

@HiltViewModel
class AboutViewModel
@Inject constructor(
        private val application: Application,
        private val individualRepository: IndividualRepository,
        private val colorService: ColorService,
        private val workScheduler: WorkScheduler,
        private val remoteConfig: RemoteConfig,
        private val createIndividualTestDataUseCase: CreateIndividualTestDataUseCase,
        private val createIndividualLargeTestDataUseCase: CreateIndividualLargeTestDataUseCase,
        private val fileSystem: FileSystem
) : ViewModel(), ViewModelNav by ViewModelNavImpl() {

    private val resetServiceEnabledFlow: StateFlow<Boolean> = MutableStateFlow(remoteConfig.isColorServiceEnabled()).asStateFlow()

    val uiState: AboutUiState = AboutUiState(
        resetServiceEnabledFlow = resetServiceEnabledFlow,
        testQueryWebServiceCall = { testQueryWebServiceCall() },
        testFullUrlQueryWebServiceCall = { testFullUrlQueryWebServiceCall() },
        testSaveQueryWebServiceCall = { testSaveQueryWebServiceCall() },
        workManagerSimpleTest = { workManagerSimpleTest() },
        workManagerSyncTest = { workManagerSyncTest() },
        testTableChange = { testTableChange() },
        licensesClicked = { navigate(AcknowledgmentsRoute.createRoute()) },
        createSampleData = { createSampleData() },
        createLargeSampleData = { createLargeSampleData() },
        m3TypographyClicked = { navigate(TypographyRoute.createRoute()) }
    )

    private fun testQueryWebServiceCall() = viewModelScope.launch {
        Logger.i { "TypeSafe Call..." }
        if (!remoteConfig.isColorServiceEnabled()) {
            Logger.e { "Color Service is NOT enabled... skipping" }
            return@launch
        }

        val response = colorService.getColorsBySafeArgs()
        processWebServiceResponse(response)

        Logger.i { "========================================================" }

        processWebServiceResponse2(colorService.getColorsBySafeArgs())
    }

    private fun testFullUrlQueryWebServiceCall() = viewModelScope.launch {
        Logger.i { "Full URL Call..." }
        val response = colorService.getColorsByFullUrl()
        processWebServiceResponse(response)
    }

    private fun testSaveQueryWebServiceCall() = viewModelScope.launch {
        val outputFile = application.filesDir.toOkioPath() / "colors.json"
        colorService.getColorsToFile(fileSystem, outputFile)

        Logger.i { "Downloaded file contents:\n ${vision.salient.trisolaran.util.ext.readText(outputFile)}" }
    }

    private fun processWebServiceResponse(response: ApiResponse<ColorsDto, Unit>) {
        vision.salient.trisolaran.util.ext.onSuccess {
            data.colors.forEach {
                Logger.i { "Result: ${it.colorName}" }
            }
        }.onFailure {
            Logger.e { "Web Service FAILURE ${message()}" }
        }.onError {

        }.onException {

        }
    }

    private fun processWebServiceResponse2(response: ApiResponse<ColorsDto, Unit>) {
        vision.salient.trisolaran.util.ext.onSuccess {
            data.colors.forEach {
                Logger.i { "Result: ${it.colorName}" }
            }
        }.onFailure {
            Logger.e { "Web Service FAILURE (message: [${message()}]" }
        }
    }

    private fun workManagerSimpleTest() = viewModelScope.launch {
        workScheduler.scheduleSimpleWork("test1")
        workScheduler.scheduleSimpleWork("test2")

        delay(3000)

        workScheduler.scheduleSimpleWork("test3")
    }

    private fun workManagerSyncTest() = viewModelScope.launch {
        workScheduler.scheduleSync()
        workScheduler.scheduleSync(true)

        delay(3000)

        workScheduler.scheduleSync()
    }

    private fun testTableChange() = viewModelScope.launch {
        // Sample tests
        if (individualRepository.getIndividualCount() == 0) {
            Logger.e { "No data.. cannot perform test" }
            return@launch
        }

        // Make some changes
        val originalName: FirstName?

        val individualList = individualRepository.getAllIndividuals()
        if (individualList.isNotEmpty()) {
            val individual = individualList[0]
            originalName = individual.firstName
            Logger.i { "ORIGINAL NAME = $originalName" }

            // change name
            individualRepository.saveIndividual(individual.copy(firstName = FirstName("Bobby")))

            // restore name
            individualRepository.saveIndividual(individual.copy(firstName = originalName))
        } else {
            Logger.e { "Cannot find individual" }
        }
    }

    private fun createSampleData() = viewModelScope.launch {
        createIndividualTestDataUseCase()
    }

    private fun createLargeSampleData() = viewModelScope.launch {
        createIndividualLargeTestDataUseCase()
    }
}