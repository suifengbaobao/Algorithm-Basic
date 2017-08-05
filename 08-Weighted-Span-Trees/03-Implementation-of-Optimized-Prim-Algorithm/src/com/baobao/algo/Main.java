package com.baobao.algo;

import java.util.List;

/**
 * 主测试函数
 * Created by suife on 2017/8/4.
 */
public class Main {
    public static void main(String[] args) {
        String filename = "testG4.txt";
        int V = 10000;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Prim MST
        System.out.println("Test Prim MST:");
        PrimMST<Double> primMST = new PrimMST<Double>(g);
        List<Edge<Double>> mst = primMST.getMst();
        //for( int i = 0 ; i < mst.size() ; i ++ )
            //System.out.println(mst.get(i));
        System.out.println("The Prim MST weight is: " + primMST.getMstWeight());

        System.out.println();

        // Test LazyPrim MST
        LazyPrimMST<Double> lazyPprimMST = new LazyPrimMST<Double>(g);
        List<Edge<Double>> mst2 = lazyPprimMST.getMst();
        //for( int i = 0 ; i < mst2.size() ; i ++ )
            //System.out.println(mst2.get(i));
        System.out.println("The LazyPrim MST weight is: " + lazyPprimMST.getMstWeight());

    }
}
