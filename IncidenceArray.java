package graph;

public class IncidenceArray implements GraphOperations{
  private int n;
  private int nbVertices = 0;   //nbr de sommets
  public int nbEdges = 0;     //nbr d'arretes

  private Vertex [] vertices = new Vertex[n]  ;
  private Edge [] edges= new Edge[n];
  private Edge [][] IncidenceArray ;


  public IncidenceArray(int n){
    this.n=n;
  }
  public int nbOfVertices(){
    //incrementation de nbOfVertices en fonction de addVertex
    nbVertices+=1;
    return nbVertices;
  }

  public int nbOfEdges(){
    //incrementation de nbOfEdges en fonction de addEdge
    this.nbEdges+=1;
    return this.nbEdges;
  }

  public void addVertex(Vertex x){
    // ajouter le nouveau vertex si non utiliser dans le tableau de vertex
    vertices[x.getId()]= x;
    this.nbVertices++;
  }

  public void addEdge(Vertex x,Vertex y,Edge k){
    //parcourt de tableau pour voir si vertex x et y sont present 
    if(vertices[x.getId()]== x){
      if(vertices[y.getId()]==y){
        //si'ls sont presents alors rajouter des connections
        if(k instanceof DirectedEdge){  
          edges[k.getId()]= k;
          int i=0,j=0;
          if(x!=y){
            while(IncidenceArray[x.getId()][i]!=null){
              i++;
            }
            IncidenceArray[x.getId()][i]=k;
            while(IncidenceArray[y.getId()][j]!=null){
              j++;
            }
            IncidenceArray[x.getId()][j]=k;
          }
        }
        else{
          edges[k.getId()]=k;
        }
      }
      else{
        addVertex(y);
        addEdge(x,y,k);
      }
    }//sinon appeler addVertex
    else{
      addVertex(x);
      addEdge(x, y,k);
    }

  }

  public boolean isConnected(Vertex x,Vertex y){
    if(x==y){return true;}
    else{
      int [] state = new int[nbVertices];
      boolean found = false;
      /*state[i]==0 si id not examined, 1 if not, 2 if treated */
      for(int i=0;i<nbVertices;i++){
        state[i]=0;
      }
      Vertex current = x;
      state[current.getId()]=1;
      while(state[y.getId()]!=1 && current!=null){
        int i=0;
        while(i<n && IncidenceArray[current.getId()][i]!=null){
          Vertex v= otherEnd(IncidenceArray[current.getId()][i],current);
          if (state[v.getId()]==0){
            state[v.getId()]=1;
          }
          i++;
        }
        state[current.getId()]=2;
        if(state[y.getId()]==1){
          return !found;
        }
        else{
          while(i<=nbVertices && state[i]!=1){
            i++;
          }
          current=vertices[i];
        }
      }
      return found;
    }
  }

  public Vertex otherEnd(Edge e,Vertex v){
    if(e instanceof UndirectedEdge){
      Vertex []sommet = e.getEnds();
      if(v==sommet[0]){
        return sommet[1];
      }
      else{
      return sommet[0];
      }
    }
    if(e instanceof DirectedEdge){
      if(v==e.getSource()){
        return e.getSink();
      }
      else{
        return e.getSource();
      }
    }
    
  }
  
  public boolean isConnected(){
    //fonction qui dit si tous les vertex sont interconnecter
    //si oui alors return true sinon false 
    boolean isConn=true;
    Vertex v1 = vertices[0];
    int i=1;
    while(isConn && i<nbVertices){
      isConn = isConnected(v1,vertices[i]);
      i++;
    }
    return isConn;
  }

  public Edge[] getEdges(Vertex x, Vertex y){
    int i=0;
    int compt=0;
    Edge[] e;
    if (isConnected(x,y)){
      while(i<nbEdges){
        for (int j=0;j<nbEdges;j++){
          if(IncidenceArray[x.getId()][j]==edges[i] && IncidenceArray[y.getId()][j]==edges[i]){
            e[compt]=edges[i];
            compt++;
          }
        }
        i++;
      }
    }
    return e; 

    // return les edge qui connectent les 2 Vertex
    // si graphe non oriente alors la fonction retourne un tableau avec un seul edge (x->y ou y->x)
    //si le graphe est orienté alors il va retourner un tableau de  2 edge (x->y et y->x)
  }

  public Edge [] getEdges(){
    /*retourne un tableau avec toutes les edges du graphes*/
    return edges; 
  }
  
  public Vertex [] getVertices(){
    /*retourne un tableau avec toutes les vertex du graphes*/
    return vertices; 
  } 
   
  public Edge[] getNeighborEdges(Vertex x){
    /*retourne tous les edges connecté au vertex x*/
    Edge[] NeighborEdges ;
    int i=0;
    while(IncidenceArray[x.getId()][i]!=null && i<= nbEdges){
      NeighborEdges[i]=IncidenceArray[x.getId()][i];
    }
    return NeighborEdges;
  }
}