package com.erkindilekci.textbook.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erkindilekci.textbook.ui.theme.LightBlue
import com.erkindilekci.textbook.ui.theme.LightGreen
import com.erkindilekci.textbook.ui.theme.LightOrange
import com.erkindilekci.textbook.ui.theme.LightPurple
import com.erkindilekci.textbook.ui.theme.LightYellow

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