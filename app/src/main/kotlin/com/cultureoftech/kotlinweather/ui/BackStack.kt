package com.cultureoftech.kotlinweather.ui

import java.util.*

/**
 * Created by bgogetap on 2/18/16.
 */
class BackStack(): Iterable<BackStackItem> {

    val backStack: Stack<BackStackItem> = Stack()

    fun size(): Int = backStack.size

    fun push(item: BackStackItem) = backStack.push(item)

    fun pop(): BackStackItem = backStack.pop()

    fun peek(): BackStackItem = backStack.peek()

    fun clear() = backStack.clear()

    override fun iterator(): Iterator<BackStackItem> {
        return backStack.iterator()
    }
}
