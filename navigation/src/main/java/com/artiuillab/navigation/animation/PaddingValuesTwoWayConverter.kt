package com.artiuillab.navigation.animation

import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset

@Stable
class PaddingValuesTwoWayConverter(
    private val layoutDirection: LayoutDirection
) : TwoWayConverter<PaddingValues, AnimationVector4D> {

    override val convertFromVector: (AnimationVector4D) -> PaddingValues = { vector ->
        PaddingValues.Absolute(
            top = vector.v1.dp,
            bottom = vector.v2.dp,
            left = vector.v3.dp,
            right = vector.v4.dp,
        )
    }

    override val convertToVector: (PaddingValues) -> AnimationVector4D = { paddingValues ->
        AnimationVector4D(
            v1 = paddingValues.calculateTopPadding().value,
            v2 = paddingValues.calculateBottomPadding().value,
            v3 = paddingValues.calculateLeftPadding(layoutDirection).value,
            v4 = paddingValues.calculateRightPadding(layoutDirection).value,
        )
    }
}

fun Modifier.padding(
    paddingValuesProvider: () -> PaddingValues,
): Modifier {
    return this.layout { measurable, constraints ->
        val paddingValues = paddingValuesProvider()

        val leftPadding = paddingValues.calculateLeftPadding(layoutDirection)
        val topPadding = paddingValues.calculateTopPadding()
        val rightPadding = paddingValues.calculateRightPadding(layoutDirection)
        val bottomPadding = paddingValues.calculateBottomPadding()

        val roundedLeftPadding = leftPadding.roundToPx()
        val horizontal = roundedLeftPadding + rightPadding.roundToPx()

        val roundedTopPadding = topPadding.roundToPx()
        val vertical = roundedTopPadding + bottomPadding.roundToPx()

        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))

        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainHeight(placeable.height + vertical)
        layout(width, height) { placeable.place(roundedLeftPadding, roundedTopPadding) }
    }
}