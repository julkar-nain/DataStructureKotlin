package stack

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/*
@author "julkar nain"
@since 9/3/21 10:48 AM
*/

/*Stack work on FILO*/
class Stack<T> {

    private var top: Node<T>? = null

    /*
    * Add an item to the top of the stack
    *
    * @Param data: T
    * */
    fun push(data: T) {
        val node = Node(data)
        node.next = top
        top = node
    }

    /*
    * Remove the top item from the stack
    *
    * @return top
    * */
    fun pop(): T {
        val data = top?.data
        top = top?.next

        return data ?: throw StackEmptyException()
    }

    /*
    * Provide top item of the stack without removing anything
    *
    * @return top
    * */
    fun peek(): T {
        return top?.data ?: throw StackEmptyException()
    }

    /*
    * Returns true if and only if the stack is empty
    *
    * @return Boolean
    * */
    fun isEmpty(): Boolean {
        return top == null
    }

    class StackEmptyException : Exception()

    data class Node<T>(var data: T, var next: Node<T>? = null)


    @Test
    fun testPush() {
        val stack = Stack<Int>()
        stack.push(100)
        stack.push(200)

        assertEquals(200, stack.peek())
    }

    @Test
    fun testPop() {
        val stack = Stack<Int>()
        stack.push(100)

        assertEquals(100, stack.pop())
        assertTrue(stack.isEmpty())
    }

    @Test
    fun testPeek() {
        val stack = Stack<Int>()
        stack.push(100)

        assertEquals(100, stack.peek())
        assertFalse(stack.isEmpty())
    }

    @Test
    fun testIsEmpty() {
        val stack = Stack<Int>()

        assertTrue(stack.isEmpty(), "isEmpty test1")

        stack.push(100)

        assertFalse(stack.isEmpty(), "isEmpty test2")
    }
}