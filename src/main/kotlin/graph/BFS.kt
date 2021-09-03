package graph

import queue.Queue

/*
@author "julkar nain"
@since 9/3/21 11:35 AM
*/
class BFS {

    fun explore(graph: List<List<Int>>, sourceNode: Int = 0) {
        if (graph.isEmpty()) {
            return
        }

        val colors = Array(graph.size) { 0 }

        explore(graph, colors, Queue<Int>().apply {
            enqueue(sourceNode)
        })
    }

    private fun explore(graph: List<List<Int>>, colors: Array<Int>, queue: Queue<Int>) {
        while (!queue.isEmpty()) {
            val node = queue.dequeue()
            colors[node] = 1

            for (child in graph[node]) {
                if (child == 0) {
                    queue.enqueue(child)
                    explore(graph, colors, queue)
                }
            }
        }
    }
}