@file:Suppress("UnusedPrivateProperty") // use when contrast themes are supported

package org.jdc.template.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

object AppTheme {
    val extendedColors: ExtendedColorScheme
        @Composable get() = LocalAppColors.current
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicTheme && Build.VERSION.SDK_INT >= 31 -> if (darkTheme) dynamicDarkColorScheme(LocalContext.current) else dynamicLightColorScheme(LocalContext.current)
        else -> if (darkTheme) darkScheme else lightScheme
    }

    val extendedAppColors = if (darkTheme) extendedDark else extendedLight

    CompositionLocalProvider(
        LocalAppColors provides extendedAppColors,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}

private val LocalAppColors = staticCompositionLocalOf<ExtendedColorScheme> {
    error("No AppColorPalette provided")
}

@Immutable
data class ExtendedColorScheme(
    val customColorA: ColorFamily,
    val customColorB: ColorFamily,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecifiedScheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

private val lightScheme = lightColorScheme(
    primary = vision.salient.trisolaran.ui.theme.primaryLight,
    onPrimary = vision.salient.trisolaran.ui.theme.onPrimaryLight,
    primaryContainer = vision.salient.trisolaran.ui.theme.primaryContainerLight,
    onPrimaryContainer = vision.salient.trisolaran.ui.theme.onPrimaryContainerLight,
    secondary = vision.salient.trisolaran.ui.theme.secondaryLight,
    onSecondary = vision.salient.trisolaran.ui.theme.onSecondaryLight,
    secondaryContainer = vision.salient.trisolaran.ui.theme.secondaryContainerLight,
    onSecondaryContainer = vision.salient.trisolaran.ui.theme.onSecondaryContainerLight,
    tertiary = vision.salient.trisolaran.ui.theme.tertiaryLight,
    onTertiary = vision.salient.trisolaran.ui.theme.onTertiaryLight,
    tertiaryContainer = vision.salient.trisolaran.ui.theme.tertiaryContainerLight,
    onTertiaryContainer = vision.salient.trisolaran.ui.theme.onTertiaryContainerLight,
    error = vision.salient.trisolaran.ui.theme.errorLight,
    onError = vision.salient.trisolaran.ui.theme.onErrorLight,
    errorContainer = vision.salient.trisolaran.ui.theme.errorContainerLight,
    onErrorContainer = vision.salient.trisolaran.ui.theme.onErrorContainerLight,
    background = vision.salient.trisolaran.ui.theme.backgroundLight,
    onBackground = vision.salient.trisolaran.ui.theme.onBackgroundLight,
    surface = vision.salient.trisolaran.ui.theme.surfaceLight,
    onSurface = vision.salient.trisolaran.ui.theme.onSurfaceLight,
    surfaceVariant = vision.salient.trisolaran.ui.theme.surfaceVariantLight,
    onSurfaceVariant = vision.salient.trisolaran.ui.theme.onSurfaceVariantLight,
    outline = vision.salient.trisolaran.ui.theme.outlineLight,
    outlineVariant = vision.salient.trisolaran.ui.theme.outlineVariantLight,
    scrim = vision.salient.trisolaran.ui.theme.scrimLight,
    inverseSurface = vision.salient.trisolaran.ui.theme.inverseSurfaceLight,
    inverseOnSurface = vision.salient.trisolaran.ui.theme.inverseOnSurfaceLight,
    inversePrimary = vision.salient.trisolaran.ui.theme.inversePrimaryLight,
    surfaceDim = vision.salient.trisolaran.ui.theme.surfaceDimLight,
    surfaceBright = vision.salient.trisolaran.ui.theme.surfaceBrightLight,
    surfaceContainerLowest = vision.salient.trisolaran.ui.theme.surfaceContainerLowestLight,
    surfaceContainerLow = vision.salient.trisolaran.ui.theme.surfaceContainerLowLight,
    surfaceContainer = vision.salient.trisolaran.ui.theme.surfaceContainerLight,
    surfaceContainerHigh = vision.salient.trisolaran.ui.theme.surfaceContainerHighLight,
    surfaceContainerHighest = vision.salient.trisolaran.ui.theme.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = vision.salient.trisolaran.ui.theme.primaryDark,
    onPrimary = vision.salient.trisolaran.ui.theme.onPrimaryDark,
    primaryContainer = vision.salient.trisolaran.ui.theme.primaryContainerDark,
    onPrimaryContainer = vision.salient.trisolaran.ui.theme.onPrimaryContainerDark,
    secondary = vision.salient.trisolaran.ui.theme.secondaryDark,
    onSecondary = vision.salient.trisolaran.ui.theme.onSecondaryDark,
    secondaryContainer = vision.salient.trisolaran.ui.theme.secondaryContainerDark,
    onSecondaryContainer = vision.salient.trisolaran.ui.theme.onSecondaryContainerDark,
    tertiary = vision.salient.trisolaran.ui.theme.tertiaryDark,
    onTertiary = vision.salient.trisolaran.ui.theme.onTertiaryDark,
    tertiaryContainer = vision.salient.trisolaran.ui.theme.tertiaryContainerDark,
    onTertiaryContainer = vision.salient.trisolaran.ui.theme.onTertiaryContainerDark,
    error = vision.salient.trisolaran.ui.theme.errorDark,
    onError = vision.salient.trisolaran.ui.theme.onErrorDark,
    errorContainer = vision.salient.trisolaran.ui.theme.errorContainerDark,
    onErrorContainer = vision.salient.trisolaran.ui.theme.onErrorContainerDark,
    background = vision.salient.trisolaran.ui.theme.backgroundDark,
    onBackground = vision.salient.trisolaran.ui.theme.onBackgroundDark,
    surface = vision.salient.trisolaran.ui.theme.surfaceDark,
    onSurface = vision.salient.trisolaran.ui.theme.onSurfaceDark,
    surfaceVariant = vision.salient.trisolaran.ui.theme.surfaceVariantDark,
    onSurfaceVariant = vision.salient.trisolaran.ui.theme.onSurfaceVariantDark,
    outline = vision.salient.trisolaran.ui.theme.outlineDark,
    outlineVariant = vision.salient.trisolaran.ui.theme.outlineVariantDark,
    scrim = vision.salient.trisolaran.ui.theme.scrimDark,
    inverseSurface = vision.salient.trisolaran.ui.theme.inverseSurfaceDark,
    inverseOnSurface = vision.salient.trisolaran.ui.theme.inverseOnSurfaceDark,
    inversePrimary = vision.salient.trisolaran.ui.theme.inversePrimaryDark,
    surfaceDim = vision.salient.trisolaran.ui.theme.surfaceDimDark,
    surfaceBright = vision.salient.trisolaran.ui.theme.surfaceBrightDark,
    surfaceContainerLowest = vision.salient.trisolaran.ui.theme.surfaceContainerLowestDark,
    surfaceContainerLow = vision.salient.trisolaran.ui.theme.surfaceContainerLowDark,
    surfaceContainer = vision.salient.trisolaran.ui.theme.surfaceContainerDark,
    surfaceContainerHigh = vision.salient.trisolaran.ui.theme.surfaceContainerHighDark,
    surfaceContainerHighest = vision.salient.trisolaran.ui.theme.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = vision.salient.trisolaran.ui.theme.primaryLightMediumContrast,
    onPrimary = vision.salient.trisolaran.ui.theme.onPrimaryLightMediumContrast,
    primaryContainer = vision.salient.trisolaran.ui.theme.primaryContainerLightMediumContrast,
    onPrimaryContainer = vision.salient.trisolaran.ui.theme.onPrimaryContainerLightMediumContrast,
    secondary = vision.salient.trisolaran.ui.theme.secondaryLightMediumContrast,
    onSecondary = vision.salient.trisolaran.ui.theme.onSecondaryLightMediumContrast,
    secondaryContainer = vision.salient.trisolaran.ui.theme.secondaryContainerLightMediumContrast,
    onSecondaryContainer = vision.salient.trisolaran.ui.theme.onSecondaryContainerLightMediumContrast,
    tertiary = vision.salient.trisolaran.ui.theme.tertiaryLightMediumContrast,
    onTertiary = vision.salient.trisolaran.ui.theme.onTertiaryLightMediumContrast,
    tertiaryContainer = vision.salient.trisolaran.ui.theme.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = vision.salient.trisolaran.ui.theme.onTertiaryContainerLightMediumContrast,
    error = vision.salient.trisolaran.ui.theme.errorLightMediumContrast,
    onError = vision.salient.trisolaran.ui.theme.onErrorLightMediumContrast,
    errorContainer = vision.salient.trisolaran.ui.theme.errorContainerLightMediumContrast,
    onErrorContainer = vision.salient.trisolaran.ui.theme.onErrorContainerLightMediumContrast,
    background = vision.salient.trisolaran.ui.theme.backgroundLightMediumContrast,
    onBackground = vision.salient.trisolaran.ui.theme.onBackgroundLightMediumContrast,
    surface = vision.salient.trisolaran.ui.theme.surfaceLightMediumContrast,
    onSurface = vision.salient.trisolaran.ui.theme.onSurfaceLightMediumContrast,
    surfaceVariant = vision.salient.trisolaran.ui.theme.surfaceVariantLightMediumContrast,
    onSurfaceVariant = vision.salient.trisolaran.ui.theme.onSurfaceVariantLightMediumContrast,
    outline = vision.salient.trisolaran.ui.theme.outlineLightMediumContrast,
    outlineVariant = vision.salient.trisolaran.ui.theme.outlineVariantLightMediumContrast,
    scrim = vision.salient.trisolaran.ui.theme.scrimLightMediumContrast,
    inverseSurface = vision.salient.trisolaran.ui.theme.inverseSurfaceLightMediumContrast,
    inverseOnSurface = vision.salient.trisolaran.ui.theme.inverseOnSurfaceLightMediumContrast,
    inversePrimary = vision.salient.trisolaran.ui.theme.inversePrimaryLightMediumContrast,
    surfaceDim = vision.salient.trisolaran.ui.theme.surfaceDimLightMediumContrast,
    surfaceBright = vision.salient.trisolaran.ui.theme.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = vision.salient.trisolaran.ui.theme.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = vision.salient.trisolaran.ui.theme.surfaceContainerLowLightMediumContrast,
    surfaceContainer = vision.salient.trisolaran.ui.theme.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = vision.salient.trisolaran.ui.theme.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = vision.salient.trisolaran.ui.theme.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = vision.salient.trisolaran.ui.theme.primaryLightHighContrast,
    onPrimary = vision.salient.trisolaran.ui.theme.onPrimaryLightHighContrast,
    primaryContainer = vision.salient.trisolaran.ui.theme.primaryContainerLightHighContrast,
    onPrimaryContainer = vision.salient.trisolaran.ui.theme.onPrimaryContainerLightHighContrast,
    secondary = vision.salient.trisolaran.ui.theme.secondaryLightHighContrast,
    onSecondary = vision.salient.trisolaran.ui.theme.onSecondaryLightHighContrast,
    secondaryContainer = vision.salient.trisolaran.ui.theme.secondaryContainerLightHighContrast,
    onSecondaryContainer = vision.salient.trisolaran.ui.theme.onSecondaryContainerLightHighContrast,
    tertiary = vision.salient.trisolaran.ui.theme.tertiaryLightHighContrast,
    onTertiary = vision.salient.trisolaran.ui.theme.onTertiaryLightHighContrast,
    tertiaryContainer = vision.salient.trisolaran.ui.theme.tertiaryContainerLightHighContrast,
    onTertiaryContainer = vision.salient.trisolaran.ui.theme.onTertiaryContainerLightHighContrast,
    error = vision.salient.trisolaran.ui.theme.errorLightHighContrast,
    onError = vision.salient.trisolaran.ui.theme.onErrorLightHighContrast,
    errorContainer = vision.salient.trisolaran.ui.theme.errorContainerLightHighContrast,
    onErrorContainer = vision.salient.trisolaran.ui.theme.onErrorContainerLightHighContrast,
    background = vision.salient.trisolaran.ui.theme.backgroundLightHighContrast,
    onBackground = vision.salient.trisolaran.ui.theme.onBackgroundLightHighContrast,
    surface = vision.salient.trisolaran.ui.theme.surfaceLightHighContrast,
    onSurface = vision.salient.trisolaran.ui.theme.onSurfaceLightHighContrast,
    surfaceVariant = vision.salient.trisolaran.ui.theme.surfaceVariantLightHighContrast,
    onSurfaceVariant = vision.salient.trisolaran.ui.theme.onSurfaceVariantLightHighContrast,
    outline = vision.salient.trisolaran.ui.theme.outlineLightHighContrast,
    outlineVariant = vision.salient.trisolaran.ui.theme.outlineVariantLightHighContrast,
    scrim = vision.salient.trisolaran.ui.theme.scrimLightHighContrast,
    inverseSurface = vision.salient.trisolaran.ui.theme.inverseSurfaceLightHighContrast,
    inverseOnSurface = vision.salient.trisolaran.ui.theme.inverseOnSurfaceLightHighContrast,
    inversePrimary = vision.salient.trisolaran.ui.theme.inversePrimaryLightHighContrast,
    surfaceDim = vision.salient.trisolaran.ui.theme.surfaceDimLightHighContrast,
    surfaceBright = vision.salient.trisolaran.ui.theme.surfaceBrightLightHighContrast,
    surfaceContainerLowest = vision.salient.trisolaran.ui.theme.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = vision.salient.trisolaran.ui.theme.surfaceContainerLowLightHighContrast,
    surfaceContainer = vision.salient.trisolaran.ui.theme.surfaceContainerLightHighContrast,
    surfaceContainerHigh = vision.salient.trisolaran.ui.theme.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = vision.salient.trisolaran.ui.theme.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = vision.salient.trisolaran.ui.theme.primaryDarkMediumContrast,
    onPrimary = vision.salient.trisolaran.ui.theme.onPrimaryDarkMediumContrast,
    primaryContainer = vision.salient.trisolaran.ui.theme.primaryContainerDarkMediumContrast,
    onPrimaryContainer = vision.salient.trisolaran.ui.theme.onPrimaryContainerDarkMediumContrast,
    secondary = vision.salient.trisolaran.ui.theme.secondaryDarkMediumContrast,
    onSecondary = vision.salient.trisolaran.ui.theme.onSecondaryDarkMediumContrast,
    secondaryContainer = vision.salient.trisolaran.ui.theme.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = vision.salient.trisolaran.ui.theme.onSecondaryContainerDarkMediumContrast,
    tertiary = vision.salient.trisolaran.ui.theme.tertiaryDarkMediumContrast,
    onTertiary = vision.salient.trisolaran.ui.theme.onTertiaryDarkMediumContrast,
    tertiaryContainer = vision.salient.trisolaran.ui.theme.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = vision.salient.trisolaran.ui.theme.onTertiaryContainerDarkMediumContrast,
    error = vision.salient.trisolaran.ui.theme.errorDarkMediumContrast,
    onError = vision.salient.trisolaran.ui.theme.onErrorDarkMediumContrast,
    errorContainer = vision.salient.trisolaran.ui.theme.errorContainerDarkMediumContrast,
    onErrorContainer = vision.salient.trisolaran.ui.theme.onErrorContainerDarkMediumContrast,
    background = vision.salient.trisolaran.ui.theme.backgroundDarkMediumContrast,
    onBackground = vision.salient.trisolaran.ui.theme.onBackgroundDarkMediumContrast,
    surface = vision.salient.trisolaran.ui.theme.surfaceDarkMediumContrast,
    onSurface = vision.salient.trisolaran.ui.theme.onSurfaceDarkMediumContrast,
    surfaceVariant = vision.salient.trisolaran.ui.theme.surfaceVariantDarkMediumContrast,
    onSurfaceVariant = vision.salient.trisolaran.ui.theme.onSurfaceVariantDarkMediumContrast,
    outline = vision.salient.trisolaran.ui.theme.outlineDarkMediumContrast,
    outlineVariant = vision.salient.trisolaran.ui.theme.outlineVariantDarkMediumContrast,
    scrim = vision.salient.trisolaran.ui.theme.scrimDarkMediumContrast,
    inverseSurface = vision.salient.trisolaran.ui.theme.inverseSurfaceDarkMediumContrast,
    inverseOnSurface = vision.salient.trisolaran.ui.theme.inverseOnSurfaceDarkMediumContrast,
    inversePrimary = vision.salient.trisolaran.ui.theme.inversePrimaryDarkMediumContrast,
    surfaceDim = vision.salient.trisolaran.ui.theme.surfaceDimDarkMediumContrast,
    surfaceBright = vision.salient.trisolaran.ui.theme.surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = vision.salient.trisolaran.ui.theme.surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = vision.salient.trisolaran.ui.theme.surfaceContainerLowDarkMediumContrast,
    surfaceContainer = vision.salient.trisolaran.ui.theme.surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = vision.salient.trisolaran.ui.theme.surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = vision.salient.trisolaran.ui.theme.surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = vision.salient.trisolaran.ui.theme.primaryDarkHighContrast,
    onPrimary = vision.salient.trisolaran.ui.theme.onPrimaryDarkHighContrast,
    primaryContainer = vision.salient.trisolaran.ui.theme.primaryContainerDarkHighContrast,
    onPrimaryContainer = vision.salient.trisolaran.ui.theme.onPrimaryContainerDarkHighContrast,
    secondary = vision.salient.trisolaran.ui.theme.secondaryDarkHighContrast,
    onSecondary = vision.salient.trisolaran.ui.theme.onSecondaryDarkHighContrast,
    secondaryContainer = vision.salient.trisolaran.ui.theme.secondaryContainerDarkHighContrast,
    onSecondaryContainer = vision.salient.trisolaran.ui.theme.onSecondaryContainerDarkHighContrast,
    tertiary = vision.salient.trisolaran.ui.theme.tertiaryDarkHighContrast,
    onTertiary = vision.salient.trisolaran.ui.theme.onTertiaryDarkHighContrast,
    tertiaryContainer = vision.salient.trisolaran.ui.theme.tertiaryContainerDarkHighContrast,
    onTertiaryContainer = vision.salient.trisolaran.ui.theme.onTertiaryContainerDarkHighContrast,
    error = vision.salient.trisolaran.ui.theme.errorDarkHighContrast,
    onError = vision.salient.trisolaran.ui.theme.onErrorDarkHighContrast,
    errorContainer = vision.salient.trisolaran.ui.theme.errorContainerDarkHighContrast,
    onErrorContainer = vision.salient.trisolaran.ui.theme.onErrorContainerDarkHighContrast,
    background = vision.salient.trisolaran.ui.theme.backgroundDarkHighContrast,
    onBackground = vision.salient.trisolaran.ui.theme.onBackgroundDarkHighContrast,
    surface = vision.salient.trisolaran.ui.theme.surfaceDarkHighContrast,
    onSurface = vision.salient.trisolaran.ui.theme.onSurfaceDarkHighContrast,
    surfaceVariant = vision.salient.trisolaran.ui.theme.surfaceVariantDarkHighContrast,
    onSurfaceVariant = vision.salient.trisolaran.ui.theme.onSurfaceVariantDarkHighContrast,
    outline = vision.salient.trisolaran.ui.theme.outlineDarkHighContrast,
    outlineVariant = vision.salient.trisolaran.ui.theme.outlineVariantDarkHighContrast,
    scrim = vision.salient.trisolaran.ui.theme.scrimDarkHighContrast,
    inverseSurface = vision.salient.trisolaran.ui.theme.inverseSurfaceDarkHighContrast,
    inverseOnSurface = vision.salient.trisolaran.ui.theme.inverseOnSurfaceDarkHighContrast,
    inversePrimary = vision.salient.trisolaran.ui.theme.inversePrimaryDarkHighContrast,
    surfaceDim = vision.salient.trisolaran.ui.theme.surfaceDimDarkHighContrast,
    surfaceBright = vision.salient.trisolaran.ui.theme.surfaceBrightDarkHighContrast,
    surfaceContainerLowest = vision.salient.trisolaran.ui.theme.surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = vision.salient.trisolaran.ui.theme.surfaceContainerLowDarkHighContrast,
    surfaceContainer = vision.salient.trisolaran.ui.theme.surfaceContainerDarkHighContrast,
    surfaceContainerHigh = vision.salient.trisolaran.ui.theme.surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = vision.salient.trisolaran.ui.theme.surfaceContainerHighestDarkHighContrast,
)

val extendedLight = ExtendedColorScheme(
    customColorA = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorALight,
            vision.salient.trisolaran.ui.theme.onCustomColorALight,
            vision.salient.trisolaran.ui.theme.customColorAContainerLight,
            vision.salient.trisolaran.ui.theme.onCustomColorAContainerLight,
    ),
    customColorB = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorBLight,
            vision.salient.trisolaran.ui.theme.onCustomColorBLight,
            vision.salient.trisolaran.ui.theme.customColorBContainerLight,
            vision.salient.trisolaran.ui.theme.onCustomColorBContainerLight,
    ),
)

val extendedDark = ExtendedColorScheme(
    customColorA = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorADark,
            vision.salient.trisolaran.ui.theme.onCustomColorADark,
            vision.salient.trisolaran.ui.theme.customColorAContainerDark,
            vision.salient.trisolaran.ui.theme.onCustomColorAContainerDark,
    ),
    customColorB = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorBDark,
            vision.salient.trisolaran.ui.theme.onCustomColorBDark,
            vision.salient.trisolaran.ui.theme.customColorBContainerDark,
            vision.salient.trisolaran.ui.theme.onCustomColorBContainerDark,
    ),
)

val extendedLightMediumContrast = ExtendedColorScheme(
    customColorA = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorALightMediumContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorALightMediumContrast,
            vision.salient.trisolaran.ui.theme.customColorAContainerLightMediumContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorAContainerLightMediumContrast,
    ),
    customColorB = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorBLightMediumContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorBLightMediumContrast,
            vision.salient.trisolaran.ui.theme.customColorBContainerLightMediumContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorBContainerLightMediumContrast,
    ),
)

val extendedLightHighContrast = ExtendedColorScheme(
    customColorA = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorALightHighContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorALightHighContrast,
            vision.salient.trisolaran.ui.theme.customColorAContainerLightHighContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorAContainerLightHighContrast,
    ),
    customColorB = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorBLightHighContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorBLightHighContrast,
            vision.salient.trisolaran.ui.theme.customColorBContainerLightHighContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorBContainerLightHighContrast,
    ),
)

val extendedDarkMediumContrast = ExtendedColorScheme(
    customColorA = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorADarkMediumContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorADarkMediumContrast,
            vision.salient.trisolaran.ui.theme.customColorAContainerDarkMediumContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorAContainerDarkMediumContrast,
    ),
    customColorB = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorBDarkMediumContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorBDarkMediumContrast,
            vision.salient.trisolaran.ui.theme.customColorBContainerDarkMediumContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorBContainerDarkMediumContrast,
    ),
)

val extendedDarkHighContrast = ExtendedColorScheme(
    customColorA = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorADarkHighContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorADarkHighContrast,
            vision.salient.trisolaran.ui.theme.customColorAContainerDarkHighContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorAContainerDarkHighContrast,
    ),
    customColorB = ColorFamily(
            vision.salient.trisolaran.ui.theme.customColorBDarkHighContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorBDarkHighContrast,
            vision.salient.trisolaran.ui.theme.customColorBContainerDarkHighContrast,
            vision.salient.trisolaran.ui.theme.onCustomColorBContainerDarkHighContrast,
    ),
)