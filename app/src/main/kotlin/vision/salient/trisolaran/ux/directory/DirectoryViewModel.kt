package vision.salient.trisolaran.ux.directory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import vision.salient.trisolaran.ui.navigation.ViewModelNav
import vision.salient.trisolaran.ui.navigation.ViewModelNavImpl
import javax.inject.Inject

@HiltViewModel
class DirectoryViewModel
@Inject constructor(
        getDirectoryUiStateUseCase: GetDirectoryUiStateUseCase,
) : ViewModel(), ViewModelNav by ViewModelNavImpl() {
    val uiState: DirectoryUiState = getDirectoryUiStateUseCase(viewModelScope) { navigate(it) }
}