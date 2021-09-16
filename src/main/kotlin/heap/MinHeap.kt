package heap

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
@author "julkar nain"
@since 9/16/21 12:00 PM
*/
class MinHeap(n: Int) {

    private var heap: IntArray = IntArray(n) { Int.MIN_VALUE }
    var heapSize = 0
        private set

    fun insert(data: Int) {
        heap[heapSize] = data
        insertHeapify(heapSize)
        heapSize++
    }

    fun delete(): Int {
        if (heapSize == 0) {
            throw HeapEmpty()
        }
        val deletedItem = heap[0]
        swap(0, --heapSize)
        deleteHeapify(0)

        return deletedItem
    }

    private fun insertHeapify(i: Int) {
        val parent = i.parent()

        if (heap[i] < heap[parent]) {
            swap(i, parent)
            insertHeapify(parent)
        }
    }

    private fun deleteHeapify(i: Int) {
        var smallest = i
        val left = i.left()
        val right = i.right()

        if (left < heapSize && heap[left] < heap[smallest]) {
            smallest = left
        }

        if (right < heapSize && heap[right] < heap[smallest]) {
            smallest = right
        }

        if (smallest != i) {
            swap(i, smallest)
            deleteHeapify(smallest)
        }
    }

    fun sort(): List<Int> {
        for (i in 0 until heapSize) {
            delete()
        }

        return heap.toList()
    }

    inner class HeapEmpty : Exception()

    private fun swap(i: Int, j: Int) {
        val temp = heap[i]
        heap[i] = heap[j]
        heap[j] = temp
    }

    fun getHeapData() = heap.toList()

    private fun Int.parent() = (this - 1) / 2

    private fun Int.left() = 2 * this + 1

    private fun Int.right() = 2 * this + 2
}

class MinHeapTest {

    @Test
    fun test() {
        val heap = MinHeap(6)
        heap.insert(3)
        heap.insert(2)
        heap.insert(15)
        heap.insert(4)
        heap.insert(14)
        heap.insert(45)
        assertEquals(listOf(2, 3, 15, 4, 14, 45), heap.getHeapData())
        heap.delete()
        heap.delete()
        assertEquals(listOf(4, 14, 15, 45, 3, 2), heap.getHeapData())
        assertEquals(4, heap.heapSize)
        assertEquals(listOf(45, 15, 14, 4, 3, 2), heap.sort())
    }
}