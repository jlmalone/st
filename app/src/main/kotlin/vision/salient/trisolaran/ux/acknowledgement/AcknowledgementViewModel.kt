package vision.salient.trisolaran.ux.acknowledgement

import android.app.Application
import androidx.lifecycle.ViewModel
import co.touchlab.kermit.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import vision.salient.trisolaran.ui.navigation.ViewModelNav
import vision.salient.trisolaran.ui.navigation.ViewModelNavImpl
import javax.inject.Inject

@HiltViewModel
class AcknowledgementViewModel
@Inject constructor(
    private val application: Application
) : ViewModel(), ViewModelNav by ViewModelNavImpl() {

    private val acknowledgementHtmlStateFlow = MutableStateFlow<String?>(null)

    val uiState = AcknowledgementUiState(
        acknowledgementHtmlFlow = acknowledgementHtmlStateFlow
    )

    init {
        loadLicenses()
    }

    private fun loadLicenses() {
        try {
            val htmlFilename = "licenses.html"

            // read the file
            acknowledgementHtmlStateFlow.value = application.assets.open(htmlFilename).bufferedReader().use { it.readText() }
        } catch (expected: Exception) {
            Logger.e(expected) { "Failed to render Acknowledgments html" }
            acknowledgementHtmlStateFlow.value = "Failed to load licenses:\n [${expected.message}]"
        }
    }
}