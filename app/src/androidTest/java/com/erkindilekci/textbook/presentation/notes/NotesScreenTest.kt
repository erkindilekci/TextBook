package com.erkindilekci.textbook.presentation.notes

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.erkindilekci.textbook.R
import com.erkindilekci.textbook.di.AppModule
import com.erkindilekci.textbook.presentation.MainActivity
import com.erkindilekci.textbook.presentation.ui.theme.TextBookTheme
import com.erkindilekci.textbook.presentation.util.Constants
import com.erkindilekci.textbook.presentation.util.Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesScreenTest {

    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            TextBookTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.NotesScreen.route
                ) {
                    composable(Screen.NotesScreen.route) {
                        NotesScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun clickToggleOrderSection_isVisible() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        composeRule.onNodeWithTag(Constants.ORDER_SECTION).assertDoesNotExist()
        composeRule.onNodeWithContentDescription(context.getString(R.string.sort_note))
            .performClick()
        composeRule.onNodeWithTag(Constants.ORDER_SECTION).assertIsDisplayed()
    }
}
