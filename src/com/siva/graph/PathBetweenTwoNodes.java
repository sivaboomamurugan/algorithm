package com.siva.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by sivam on 12/5/15.
 */
public class PathBetweenTwoNodes {

    static Map<Integer, Node> nodeMapping = new HashMap<>();

    public static void main(String[] args) {
        Graph graph = new Graph();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n0 = new Node(9);
        Node n11 = new Node(9);
        Node n12 = new Node(9);

        graph.nodes.addAll(new ArrayList<Node>() {{add(n1);add(n2);add(n3);add(n4);add(n5);add(n6);
            add(n7);add(n8);add(n9);add(n0);add(n11);add(n12);}});

        n0.connectedNodes.addAll(new ArrayList<Node>() {{add(n2);add(n3);add(n4);}});
        n1.connectedNodes.addAll(new ArrayList<Node>() {{add(n5);add(n4);}});
        n2.connectedNodes.addAll(new ArrayList<Node>() {{add(n4);}});
        n3.connectedNodes.addAll(new ArrayList<Node>() {{add(n6);add(n4);}});
        n4.connectedNodes.addAll(new ArrayList<Node>() {{add(n8);add(n9);}});
        n5.connectedNodes.addAll(new ArrayList<Node>() {{add(n7);add(n8);}});
        n6.connectedNodes.addAll(new ArrayList<Node>() {{add(n9);add(n11);}});

        System.out.println(isNodesConnected(graph, n0,n0));
    }

    private static boolean isNodesConnected(Graph graph, int start, int end) {
     return isNodesConnected(graph, nodeMapping.get(start), nodeMapping.get(end));
    }

    private static boolean isNodesConnected(Graph graph, Node start, Node end) {
        if(start == end)
            return true;

        LinkedList<Node> nodes = new LinkedList<>();

        for(Node node : graph.nodes)
            node.state = STATE.UNVISITED;

        start.state = STATE.VISITING;
        nodes.add(start);
        Node visitingNode;
        while (!nodes.isEmpty()) {
            visitingNode = nodes.removeFirst();
            if(null != visitingNode) {
                for(Node connectedNode : visitingNode.connectedNodes) {
                    if(connectedNode == end)
                        return true;
                    else if(connectedNode.state == STATE.UNVISITED) {
                        connectedNode.state = STATE.VISITING;
                        nodes.add(connectedNode);
                    }
                }
                visitingNode.state = STATE.VISITED;
            }
        }
        return false;
    }

    static class Graph {
        ArrayList<Node> nodes = new ArrayList<>();
        public ArrayList<Node> getAllNodes() {
            return nodes;
        }
    }

    static class Node {
        int value;
        STATE state;
        ArrayList<Node> connectedNodes = new ArrayList<>();

        public Node(int i) {
            this.value = i;
        }
    }

    enum STATE {
        VISITED,UNVISITED,VISITING;
    }
}
