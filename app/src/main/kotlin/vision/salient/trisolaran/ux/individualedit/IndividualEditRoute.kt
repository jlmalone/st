package vision.salient.trisolaran.ux.individualedit

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import vision.salient.trisolaran.model.domain.inline.IndividualId
import vision.salient.trisolaran.ui.navigation.NavComposeRoute
import org.jdc.template.ui.navigation.NavRoute
import org.jdc.template.ui.navigation.NavRouteDefinition
import vision.salient.trisolaran.ui.navigation.RouteUtil
import org.jdc.template.ui.navigation.asNavRoute
import org.jdc.template.ui.navigation.asNavRouteDefinition
import org.jdc.template.ux.individualedit.IndividualEditRoute
import vision.salient.trisolaran.util.ext.getIndividualId

object IndividualEditRoute : NavComposeRoute() {
    private const val ROUTE_BASE = "individualEdit"
    override val routeDefinition: NavRouteDefinition = "$ROUTE_BASE?${RouteUtil.defineOptionalArgs(Arg.INDIVIDUAL_ID)}".asNavRouteDefinition()

    fun createRoute(individualId: IndividualId? = null): NavRoute {
        return "$ROUTE_BASE?${RouteUtil.optionalArgs(mapOf(Arg.INDIVIDUAL_ID to individualId?.value))}".asNavRoute()
    }

    override fun getArguments(): List<NamedNavArgument> {
        return listOf(
            navArgument(Arg.INDIVIDUAL_ID) {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )
    }

    object Arg {
        const val INDIVIDUAL_ID = "individualId"
    }
}

data class IndividualEditArgs(val individualId: IndividualId?) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                    vision.salient.trisolaran.util.ext.getIndividualId(IndividualEditRoute.Arg.INDIVIDUAL_ID),
            )
}