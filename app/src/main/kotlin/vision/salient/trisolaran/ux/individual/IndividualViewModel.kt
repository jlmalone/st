package vision.salient.trisolaran.ux.individual

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import vision.salient.trisolaran.ui.navigation.ViewModelNav
import vision.salient.trisolaran.ui.navigation.ViewModelNavImpl
import vision.salient.trisolaran.ux.individual.GetIndividualUiStateUseCase
import vision.salient.trisolaran.ux.individual.IndividualArgs
import vision.salient.trisolaran.ux.individual.IndividualUiState
import javax.inject.Inject

@HiltViewModel
class IndividualViewModel
@Inject constructor(
        getIndividualUiStateUseCase: GetIndividualUiStateUseCase,
        savedStateHandle: SavedStateHandle
) : ViewModel(), ViewModelNav by ViewModelNavImpl() {
    private val args = IndividualArgs(savedStateHandle)
    val uiState: IndividualUiState = getIndividualUiStateUseCase.invoke(args.individualId, viewModelScope) { navigate(it) }
}
