package vision.salient.trisolaran.ux.directory

import kotlinx.coroutines.CoroutineScope
import org.jdc.template.model.repository.IndividualRepository
import org.jdc.template.ui.navigation.NavigationAction
import vision.salient.trisolaran.util.ext.stateInDefault
import vision.salient.trisolaran.ux.individual.IndividualRoute
import vision.salient.trisolaran.ux.individualedit.IndividualEditRoute
import vision.salient.trisolaran.ux.settings.SettingsRoute
import javax.inject.Inject

class GetDirectoryUiStateUseCase
@Inject constructor(
    private val individualRepository: IndividualRepository,
) {
    operator fun invoke(
        coroutineScope: CoroutineScope,
        navigate: (NavigationAction) -> Unit,
    ): DirectoryUiState {
        return DirectoryUiState(
            directoryListFlow = vision.salient.trisolaran.util.ext.stateInDefault(coroutineScope, emptyList()),
            onNewClicked = { navigate(NavigationAction.Navigate(IndividualEditRoute.createRoute())) },
            onIndividualClicked = { individualId -> navigate(NavigationAction.Navigate(IndividualRoute.createRoute(individualId))) },
            onSettingsClicked = { navigate(NavigationAction.Navigate(SettingsRoute.createRoute())) }
        )
    }
}