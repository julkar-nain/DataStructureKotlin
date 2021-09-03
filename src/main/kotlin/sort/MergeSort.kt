package sort

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

/*
@author "julkar nain"
@since 9/3/21 10:09 AM
*/
class MergeSort {

    fun sort(data: IntArray): IntArray {
        divide(data, 0, data.size - 1)

        return data
    }

    private fun divide(data: IntArray, start: Int, end: Int) {
        if (start < end) {
            val mid = (start + end) / 2
            divide(data, start, mid)
            divide(data, mid + 1, end)
            merge(data, start, mid, end)
        }
    }

    private fun merge(data: IntArray, start: Int, mid: Int, end: Int) {
        val leftData = IntArray(mid - start + 1)
        val rightData = IntArray(end - mid)

        for (i in start..mid) {
            leftData[i - start] = data[i]
        }

        for (j in mid + 1..end) {
            rightData[j - mid - 1] = data[j]
        }

        var leftIndex = 0
        var rightIndex = 0

        for (i in start..end) {
            data[i] = if (leftIndex >= leftData.size) {
                rightData[rightIndex++]
            } else if (rightIndex >= rightData.size || leftData[leftIndex] < rightData[rightIndex]) {
                leftData[leftIndex++]
            } else {
                rightData[rightIndex++]
            }
        }
    }

    @Test
    fun test() {
        assertArrayEquals(intArrayOf(1, 2, 3, 4, 5), sort(intArrayOf(4, 5, 3, 1, 2)))
    }
}