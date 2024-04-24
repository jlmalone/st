package vision.salient.trisolaran.ux.individual

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import vision.salient.trisolaran.model.domain.Individual
import vision.salient.trisolaran.ui.compose.dialog.DialogUiState

data class IndividualUiState(
        val dialogUiStateFlow: StateFlow<DialogUiState<*>?> = MutableStateFlow(null),

    // Data
        val individualFlow: StateFlow<Individual?> = MutableStateFlow(null),

    // Events
        val onEditClick: () -> Unit = {},
        val onDeleteClick: () -> Unit = {},
        val deleteIndividual: () -> Unit = {},
)