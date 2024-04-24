package vision.salient.trisolaran.ux

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.dbtools.android.work.ux.monitor.WorkManagerStatusScreen
import vision.salient.trisolaran.ui.navigation.NavUriLogger
import vision.salient.trisolaran.ui.navigation.WorkManagerStatusRoute
import vision.salient.trisolaran.ux.about.AboutRoute
import vision.salient.trisolaran.ux.about.AboutScreen
import vision.salient.trisolaran.ux.about.typography.TypographyRoute
import vision.salient.trisolaran.ux.about.typography.TypographyScreen
import vision.salient.trisolaran.ux.acknowledgement.AcknowledgementScreen
import vision.salient.trisolaran.ux.acknowledgement.AcknowledgmentsRoute
import vision.salient.trisolaran.ux.directory.DirectoryRoute
import vision.salient.trisolaran.ux.directory.DirectoryScreen
import vision.salient.trisolaran.ux.individual.IndividualRoute
import vision.salient.trisolaran.ux.individual.IndividualScreen
import vision.salient.trisolaran.ux.individualedit.IndividualEditRoute
import vision.salient.trisolaran.ux.individualedit.IndividualEditScreen
import vision.salient.trisolaran.ux.settings.SettingsRoute
import vision.salient.trisolaran.ux.settings.SettingsScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    // Debug navigation
    navController.addOnDestinationChangedListener(NavUriLogger())

    NavHost(
        navController = navController,
        startDestination = DirectoryRoute.routeDefinition.value
    ) {
        DirectoryRoute.addNavigationRoute(this) { DirectoryScreen(navController) }
        IndividualRoute.addNavigationRoute(this) { IndividualScreen(navController) }
        IndividualEditRoute.addNavigationRoute(this) { IndividualEditScreen(navController) }

        SettingsRoute.addNavigationRoute(this) { SettingsScreen(navController) }
        AboutRoute.addNavigationRoute(this) { AboutScreen(navController) }

        TypographyRoute.addNavigationRoute(this) { TypographyScreen(navController) }

        AcknowledgmentsRoute.addNavigationRoute(this) { AcknowledgementScreen(navController) }

        WorkManagerStatusRoute.addNavigationRoute(this) { WorkManagerStatusScreen { navController.popBackStack() } }
    }
}

/**
 * Used for building Compose Navigation deeplinks.
 *
 * Provides common values to build navDeepLink uriPattern (used in AndroidManifest intent-filter)
 */
object NavIntentFilterPart {
    const val DEFAULT_APP_SCHEME = "android-template"
    const val DEFAULT_WEB_SCHEME_HOST = "http://www.mysite.com"
}
