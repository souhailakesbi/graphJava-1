package graph;

class TestGraphImpl{

  public static void main(String[] args) {
    IncidenceGraph graph = new IncidenceGraph(30);
    Vertex v1 = new Vertex(1,5,Color.yellow);
    Vertex v2 = new Vertex(2,5,Color.green);
    Vertex v3 = new Vertex(3,5,Color.blue);
    Vertex[] vertex={v1,v2};
    Edge e1 = new UndirectedEdge(1,8, Color.yellow,vertex,8);
    Edge e2 = new DirectedEdge(2,8, Color.yellow,vertex,8);
    graph.addVertex(v1);
    graph.addVertex(v2);
    graph.addVertex(v3);
    graph.addEgde(e1);
    graph.addEgde(e2);

    graph.nbOfVertices();
    graph.nbOfEdges();
    System.out.println("The vertices "+v1+"and " +v2+ "are connected : "+isConnected(v1,v2));
    System.out.println("This Graph is connected : " +isConnected());
    System.out.println("The vertices of this graph are : "graph.getVertices());
  
  }
  
  
}