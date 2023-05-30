package com.erkindilekci.textbook.presentation

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import com.erkindilekci.textbook.R
import com.erkindilekci.textbook.di.AppModule
import com.erkindilekci.textbook.presentation.ui.theme.TextBookTheme
import com.erkindilekci.textbook.presentation.util.Constants
import com.erkindilekci.textbook.presentation.util.Navigation
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesEndToEndTest {

    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        composeRule.activity.setContent {
            TextBookTheme {
                Navigation()
            }
        }
    }

    @Test
    fun saveNewNote_EditAfterwards() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        composeRule.onNodeWithContentDescription(context.getString(R.string.add_note))
            .performClick()

        composeRule.onNodeWithTag(Constants.TITLE_TEXT_FIELD).performTextInput("title test")
        composeRule.onNodeWithTag(Constants.CONTENT_TEXT_FIELD).performTextInput("content test")
        composeRule.onNodeWithContentDescription(context.getString(R.string.save_note))
            .performClick()

        composeRule.onNodeWithText("title test").assertIsDisplayed()
        composeRule.onNodeWithText("content test").performClick()

        composeRule.onNodeWithTag(Constants.TITLE_TEXT_FIELD).assertTextEquals("title test")
        composeRule.onNodeWithTag(Constants.CONTENT_TEXT_FIELD).assertTextEquals("content test")
        composeRule.onNodeWithTag(Constants.TITLE_TEXT_FIELD).performTextInput(" changed")
        composeRule.onNodeWithContentDescription(context.getString(R.string.save_note))
            .performClick()

        composeRule.onNodeWithText("title test changed").assertIsDisplayed()
    }

    @Test
    fun saveNewNotes_orderByTitleDescending() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        for (i in 1..3) {
            composeRule.onNodeWithContentDescription(context.getString(R.string.add_note))
                .performClick()

            composeRule.onNodeWithTag(Constants.TITLE_TEXT_FIELD).performTextInput("title test: $i")
            composeRule.onNodeWithTag(Constants.CONTENT_TEXT_FIELD)
                .performTextInput("content test: $i")
            composeRule.onNodeWithContentDescription(context.getString(R.string.save_note))
                .performClick()
        }

        composeRule.onNodeWithText("title test: 1").assertIsDisplayed()
        composeRule.onNodeWithText("title test: 2").assertIsDisplayed()
        composeRule.onNodeWithText("title test: 3").assertIsDisplayed()

        composeRule.onNodeWithContentDescription(context.getString(R.string.sort_note))
            .performClick()
        composeRule.onNodeWithContentDescription(context.getString(R.string.title)).performClick()
        composeRule.onNodeWithContentDescription(context.getString(R.string.descending))
            .performClick()

        composeRule.onAllNodesWithTag(Constants.NOTE_ITEM)[0].assertTextContains("title test: 3")
        composeRule.onAllNodesWithTag(Constants.NOTE_ITEM)[1].assertTextContains("title test: 2")
        composeRule.onAllNodesWithTag(Constants.NOTE_ITEM)[2].assertTextContains("title test: 1")
    }
}
