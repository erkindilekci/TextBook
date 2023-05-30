package com.erkindilekci.textbook.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
