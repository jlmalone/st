package vision.salient.trisolaran.ux.directory

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import vision.salient.trisolaran.model.db.main.directoryitem.DirectoryItemEntityView
import vision.salient.trisolaran.model.domain.inline.IndividualId

data class DirectoryUiState(
    // Data
        val directoryListFlow: StateFlow<List<DirectoryItemEntityView>> = MutableStateFlow(emptyList()),

    // Events
        val onNewClicked: () -> Unit = {},
        val onIndividualClicked: (individualId: IndividualId) -> Unit = {},
        val onSettingsClicked: () -> Unit = {}
)