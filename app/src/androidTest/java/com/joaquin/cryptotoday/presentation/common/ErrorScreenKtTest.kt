package com.joaquin.cryptotoday.presentation.common

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorScreen_isDisplayed() {
        // Arrange
        val errorMessage = "404 Error Message"
        var retryClicked = false
        val onRetryClick: () -> Unit = { retryClicked = true }

        // Act
        composeTestRule.setContent {
            ErrorScreen(errorMessage = errorMessage, onRetryClick = onRetryClick)
        }

        // Assert
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Retry")
            .assertIsDisplayed()
            .performClick()

        // Assert that Retfy button is clicked
        assertTrue(retryClicked)
    }

}