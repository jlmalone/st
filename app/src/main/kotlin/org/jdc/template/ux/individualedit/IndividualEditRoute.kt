package org.jdc.template.ux.individualedit

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import org.jdc.template.model.domain.inline.IndividualId
import org.jdc.template.ui.navigation.NavComposeRoute
import vision.salient.trisolaran.ui.navigation.NavRoute
import vision.salient.trisolaran.ui.navigation.NavRouteDefinition
import vision.salient.trisolaran.ui.navigation.asNavRoute
import vision.salient.trisolaran.ui.navigation.asNavRouteDefinition
import org.jdc.template.util.ext.getIndividualId

object IndividualEditRoute : NavComposeRoute() {
    private const val ROUTE_BASE = "individualEdit"
    override val routeDefinition: NavRouteDefinition = vision.salient.trisolaran.ui.navigation.asNavRouteDefinition()

    fun createRoute(individualId: IndividualId? = null): NavRoute {
        return vision.salient.trisolaran.ui.navigation.asNavRoute()
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
                savedStateHandle.getIndividualId(IndividualEditRoute.Arg.INDIVIDUAL_ID),
            )
}