package sort

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
@author "julkar nain"
@since 9/14/21 12:03 PM
*/
class QuickSort {

    fun sort(a: IntArray): IntArray {
        quickSort(a, 0, a.size - 1)

        return a
    }

    private fun quickSort(a: IntArray, start: Int, end: Int) {
        if (start < end) {
            val partitionPoint = partition(a, start, end)
            quickSort(a, start, partitionPoint - 1)
            quickSort(a, partitionPoint + 1, end)
        }
    }

    private fun partition(a: IntArray, start: Int, end: Int): Int {
        val pivot = a[end]
        var leftIndex = start - 1

        for (rightIndex in start until end) {
            if (a[rightIndex] < pivot) {
                a.swap(++leftIndex, rightIndex)
            }
        }

        a.swap(leftIndex + 1, end)
        return leftIndex + 1
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    @Test
    fun test() {
        Assertions.assertArrayEquals(intArrayOf(1, 2, 3, 4, 5), sort(intArrayOf(4, 5, 3, 1, 2)))
    }
}