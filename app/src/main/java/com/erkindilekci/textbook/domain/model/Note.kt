package com.erkindilekci.textbook.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erkindilekci.textbook.presentation.ui.theme.LightBlue
import com.erkindilekci.textbook.presentation.ui.theme.LightGreen
import com.erkindilekci.textbook.presentation.ui.theme.LightOrange
import com.erkindilekci.textbook.presentation.ui.theme.LightPurple
import com.erkindilekci.textbook.presentation.ui.theme.LightYellow

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) {
    companion object {
        val noteColorList = listOf(LightOrange, LightYellow, LightPurple, LightBlue, LightGreen)
    }
}

class InvalidNoteException(message: String) : Exception(message)