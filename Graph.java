
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Graph
// Files:           (None)
// Course:          CS400-004, Spring, 2019
// Due Date:        04/16
//
// Author:          Yixing Yao
// Email:           yao68@wisc.edu
// Lecturer's Name: Andrew Kuemmel
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Yue Wu
// Partner Email:   ywu399@wisc.edu
// Lecturer's Name: Andrew Kuemmel
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X__ Write-up states that pair programming is allowed for this assignment.
//   _X__ We have both read and understand the course Pair Programming Policy.
//   _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Filename: Graph.java Project: p4 Authors: Yixing Yao, Yue Wu
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {

  private List<Graphnode<String>> vertices;

  /*
   * Default no-argument constructor
   */
  public Graph() {
    vertices = new ArrayList<Graphnode<String>>();
  }

  class Graphnode<String> {
    private String name;
    private boolean visited;
    private List<Graphnode<String>> dependencies;

    private Graphnode(String data) {
      this.name = data;
    }
  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or
   * throwing an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
   * the graph
   */
  public void addVertex(String vertex) {
    // vertex is null
    if (vertex == null) {
      return;
    }
    // vertex already exists
    for (int i = 0; i < vertices.size(); i++) {
      if (vertices.get(i).name.equals(vertex)) {
        return;
      }
    }

    vertices.add(new Graphnode(vertex));
  }

  /**
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex,
   * edges, or throwing an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
   * the graph
   */
  public void removeVertex(String vertex) {
    if (vertex == null) {
      return;
    }
    for (int i = 0; i < vertices.size(); i++) {
      // found vertex in node, remove node
      if (vertices.get(i).name.equals(vertex)) {
        vertices.remove(i);
      }

      // found vertex in node's neighbor, remove node
      for (int j = 0; j < vertices.get(i).dependencies.size(); j++) {
        if (vertices.get(i).dependencies.get(j).equals(vertex)) {
          vertices.get(i).dependencies.remove(j);
        }
      }
    }

  }

  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and
   * unweighted) If either vertex does not exist, no edge is added and no
   * exception is thrown. If the edge exists in the graph, no edge is added and no
   * exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
   * the graph 3. the edge is not in the graph
   */
  public void addEdge(String vertex1, String vertex2) {
    if (vertex1 == null || vertex2 == null) {
      return;
    }

    // find if vertex2 exists
    for (int i = 0; i < vertices.size(); i++) {
      // vertex 2 exists
      if (vertices.get(i).name.equals(vertex2)) {
        // find if vertex 1 exists
        for (int j = 0; j < vertices.size(); j++) {
          // vertex 1 exists
          if (vertices.get(j).name.equals(vertex1)) {
            // add an edge from vertex1 to vertex2 (vertex2's dependency is vertex1)
            // find if edge already existed
            if (vertices.get(i) != null && vertices.get(i).dependencies != null) {
              for (int k = 0; k < vertices.get(i).dependencies.size(); k++) {
                // edge already existed
                if (vertices.get(i).dependencies.get(k).name.equals(vertex1)) {
                  return;
                }
              }

              // edge does not exist, add edge
              vertices.get(i).dependencies.add(vertices.get(j));
              System.out.print("debug: add vertices");
            }
          }
        }
      }
    }
  }

  /**
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed
   * and unweighted) If either vertex does not exist, or if an edge from vertex1
   * to vertex2 does not exist, no edge is removed and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
   * the graph 3. the edge from vertex1 to vertex2 is in the graph
   */
  public void removeEdge(String vertex1, String vertex2) {

  }

  /**
   * Returns a Set that contains all the vertices
   * 
   */
  public Set<String> getAllVertices() {
    return null;
  }

  /**
   * Get all the neighbor (adjacent) vertices of a vertex
   *
   */
  public List<String> getAdjacentVerticesOf(String vertex) {
    return null;
  }

  /**
   * Returns the number of edges in this graph.
   */
  public int size() {
    return -1;
  }

  /**
   * Returns the number of vertices in this graph.
   */
  public int order() {
    return vertices.size();
  }

  /**
   * This method visualizes the graph.
   */
  public void printGraph() {
    System.out.println("vertice------dependencies");
    // print each vertices and its dependencies in a line
    // looping through each vertices
    for (int i = 0; i < vertices.size(); ++i) {
      // print the vertex
      System.out.print(i + 1 + " " + vertices.get(i).name + " --- ");
      // looping through the dependencies list
      if(vertices.get(i).dependencies != null)
      for (int j = 0; j < vertices.get(i).dependencies.size(); ++j) {
        // print the dependencies
        System.out.print( vertices.get(i).dependencies.get(j).name + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) throws Exception {
    Graph graph = new Graph();
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");
    graph.addVertex("D");
    graph.addEdge("A", "B");
    graph.addEdge("A", "C");
    graph.addEdge("A", "D");
    graph.addEdge("C", "B");
    graph.printGraph();
  }

}