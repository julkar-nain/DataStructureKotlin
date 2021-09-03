package graph

/*
@author "julkar nain"
@since 9/3/21 12:12 PM
*/
class DFS {

    fun explore(graph: List<List<Int>>, sourceNode: Int = 0) {
        if (graph.isEmpty()) {
            return
        }

        val colors = Array(graph.size) { 0 }

        explore(sourceNode, graph, colors)
    }

    private fun explore(sourceNode: Int, graph: List<List<Int>>, colors: Array<Int>) {
        colors[sourceNode] = 1

        for (child in graph[sourceNode]) {
            if (child == 0) {
                explore(child, graph, colors)
            }
        }
    }

}