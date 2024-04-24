package vision.salient.trisolaran.ux.individualedit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import vision.salient.trisolaran.ui.navigation.ViewModelNav
import vision.salient.trisolaran.ui.navigation.ViewModelNavImpl
import vision.salient.trisolaran.ux.individualedit.GetIndividualEditUiStateUseCase
import vision.salient.trisolaran.ux.individualedit.IndividualEditArgs
import vision.salient.trisolaran.ux.individualedit.IndividualEditUiState
import javax.inject.Inject

@HiltViewModel
class IndividualEditViewModel
@Inject constructor(
        getIndividualEditUiStateUseCase: GetIndividualEditUiStateUseCase,
        savedStateHandle: SavedStateHandle
) : ViewModel(), ViewModelNav by ViewModelNavImpl() {
    private val args = IndividualEditArgs(savedStateHandle)
    val uiState: IndividualEditUiState = getIndividualEditUiStateUseCase(args.individualId, viewModelScope) { navigate(it) }
}
