package vision.salient.trisolaran.ux.individual

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import vision.salient.trisolaran.model.domain.inline.IndividualId
import vision.salient.trisolaran.ui.navigation.NavComposeRoute
import org.jdc.template.ui.navigation.NavRoute
import org.jdc.template.ui.navigation.NavRouteDefinition
import vision.salient.trisolaran.ui.navigation.RouteUtil
import org.jdc.template.ui.navigation.asNavRoute
import org.jdc.template.ui.navigation.asNavRouteDefinition
import org.jdc.template.ux.individual.IndividualRoute
import vision.salient.trisolaran.util.ext.requireIndividualId
import vision.salient.trisolaran.ux.NavIntentFilterPart

object IndividualRoute : NavComposeRoute() {
    private const val ROUTE_BASE = "individual"
    override val routeDefinition: NavRouteDefinition = "$ROUTE_BASE/${RouteUtil.defineArg(Arg.INDIVIDUAL_ID)}".asNavRouteDefinition() // individual/{individualId}

    fun createRoute(individualId: IndividualId): NavRoute {
        return "$ROUTE_BASE/${individualId.value}".asNavRoute() // individual/123456
    }

    override fun getArguments(): List<NamedNavArgument> {
        return listOf(
            navArgument(Arg.INDIVIDUAL_ID) {
                type = NavType.StringType
            }
        )
    }

    // adb shell am start -W -a android.intent.action.VIEW -d "android-template://individual/xxxxxx"
    override fun getDeepLinks(): List<NavDeepLink> {
        return listOf(
            navDeepLink { uriPattern = "${NavIntentFilterPart.DEFAULT_APP_SCHEME}://$ROUTE_BASE/${RouteUtil.defineArg(Arg.INDIVIDUAL_ID)}" }, // android-template://individual/{individualId},
            // navDeepLink { uriPattern = "${NavIntentFilterPart.DEFAULT_WEB_SCHEME_HOST}/$ROUTE_BASE/${RouteUtil.defineArg(Arg.INDIVIDUAL_ID)}" } // http://www.mysite.com/individual/{individualId}
        )
    }

    object Arg {
        const val INDIVIDUAL_ID = "individualId"
    }
}

data class IndividualArgs(val individualId: IndividualId) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                    vision.salient.trisolaran.util.ext.requireIndividualId(IndividualRoute.Arg.INDIVIDUAL_ID),
            )
}