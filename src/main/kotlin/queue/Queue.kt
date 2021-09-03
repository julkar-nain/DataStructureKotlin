package queue

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
@author "julkar nain"
@since 9/3/21 11:18 AM
*/

/*
* Queue works on FIFO
* */
class Queue<T> {

    private var head: Node<T>? = null // dequeue from head
    private var tail: Node<T>? = null // enqueue to tail

    /*
    * Add an item to the tail of the queue
    *
    * @param data: T
    * */
    fun enqueue(data: T) {
        val node = Node(data)

        tail?.let {
            it.next = node
            tail = node
        } ?: run {
            tail = node
            head = node
        }
    }

    /*
    * Remove an item from the head
    *
    * @return head: T
    * */
    fun dequeue(): T {
        val data = head?.data

        if (tail == head) {
            tail = null
            head = null
        } else {
            head = head?.next
        }

        return data ?: throw QueueEmptyException()
    }

    /*
    * Returns true if and only if the queue is empty
    *
    * @return Boolean
    * */
    fun isEmpty(): Boolean {
        return head == null
    }

    class QueueEmptyException : Exception()

    data class Node<T>(var data: T, var next: Node<T>? = null)

    @Test
    fun testQueue() {
        val queue = Queue<Int>()
        queue.enqueue(100)
        queue.enqueue(200)

        assertEquals(100, queue.dequeue())
    }
}