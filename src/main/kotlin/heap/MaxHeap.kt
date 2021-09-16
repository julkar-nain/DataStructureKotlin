package heap

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
@author "julkar nain"
@since 9/16/21 12:00 PM
*/
class MaxHeap(n: Int) {

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

        if (heap[parent] > 0 && heap[i] > heap[parent]) {
            swap(i, parent)
            insertHeapify(parent)
        }
    }

    private fun deleteHeapify(i: Int) {
        var largest = i
        val left = i.left()
        val right = i.right()

        if (left < heapSize && heap[left] > heap[largest]) {
            largest = left
        }

        if (right < heapSize && heap[right] > heap[largest]) {
            largest = right
        }

        if (largest != i) {
            swap(i, largest)
            deleteHeapify(largest)
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
        val heap = MaxHeap(6)
        heap.insert(3)
        heap.insert(2)
        heap.insert(15)
        heap.insert(4)
        heap.insert(14)
        heap.insert(45)
        assertEquals(listOf(45, 14, 15, 2, 4, 3), heap.getHeapData())
        heap.delete()
        heap.delete()
        assertEquals(listOf(14, 4, 3, 2, 15, 45), heap.getHeapData())
        assertEquals(4, heap.heapSize)
        assertEquals(listOf(2, 3, 4, 14, 15, 45), heap.sort())
    }
}