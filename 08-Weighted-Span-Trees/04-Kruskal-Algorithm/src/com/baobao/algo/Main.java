package com.baobao.algo;

/**
 * 测试两种算法的效率
 * LazyPrim O(ElgE)
 * Prim O(ElgV)
 * Kruskal O(ElgE)
 * Created by suife on 2017/8/6.
 */
public class Main {
    public static void main(String[] args) {
        String filename1 = "testG1.txt";
        int V1 = 8;

        String filename2 = "testG2.txt";
        int V2 = 250;

        String filename3 = "testG3.txt";
        int V3 = 1000;

        String filename4 = "testG4.txt";
        int V4 = 10000;
        // 文件读取
        SparseWeightedGraph<Double> g1 = new SparseWeightedGraph<Double>(V1, false);
        ReadWeightedGraph readGraph1 = new ReadWeightedGraph(g1, filename1);
        System.out.println( filename1 + " load successfully.");

        SparseWeightedGraph<Double> g2 = new SparseWeightedGraph<Double>(V2, false);
        ReadWeightedGraph readGraph2 = new ReadWeightedGraph(g2, filename2);
        System.out.println( filename2 + " load successfully.");

        SparseWeightedGraph<Double> g3 = new SparseWeightedGraph<Double>(V3, false);
        ReadWeightedGraph readGraph3 = new ReadWeightedGraph(g3, filename3);
        System.out.println( filename3 + " load successfully.");

        SparseWeightedGraph<Double> g4 = new SparseWeightedGraph<Double>(V4, false);
        ReadWeightedGraph readGraph4 = new ReadWeightedGraph(g4, filename4);
        System.out.println( filename4 + " load successfully.");

        long startTime, endTime;
        // Test Lazy Prim MST
        System.out.println("\nTest Lazy Prim MST:");
        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST1 = new LazyPrimMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime-startTime) + "ms.");
        System.out.println("Test for G1 weight : " + lazyPrimMST1.getMstWeight());

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST2 = new LazyPrimMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime-startTime) + "ms.");
        System.out.println("Test for G2 weight : " + lazyPrimMST2.getMstWeight());

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST3 = new LazyPrimMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime-startTime) + "ms.");
        System.out.println("Test for G3 weight : " + lazyPrimMST3.getMstWeight());

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST4 = new LazyPrimMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime-startTime) + "ms.");
        System.out.println("Test for G4 weight : " + lazyPrimMST4.getMstWeight());

        // Test Prim MST
        System.out.println("\nTest Prim MST:");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST1 = new PrimMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime-startTime) + "ms.");
        System.out.println("Test for G1 weight : " + primMST1.getMstWeight());

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST2 = new PrimMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime-startTime) + "ms.");
        System.out.println("Test for G2 weight : " + primMST2.getMstWeight());

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST3 = new PrimMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime-startTime) + "ms.");
        System.out.println("Test for G3 weight : " + primMST3.getMstWeight());

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST4 = new PrimMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime-startTime) + "ms.");
        System.out.println("Test for G4 weight : " + primMST4.getMstWeight());

        System.out.println("\nTest Kruskal:");
        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST = new KruskalMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1:" + (endTime - startTime) + "ms");
        System.out.println("Test for G1 weight:" + kruskalMST.getWeight());

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST2 = new KruskalMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2:" + (endTime - startTime) + "ms");
        System.out.println("Test for G2 weight:" + kruskalMST2.getWeight());

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST3 = new KruskalMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3:" + (endTime - startTime) + "ms");
        System.out.println("Test for G3 weight:" + kruskalMST3.getWeight());

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST4 = new KruskalMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4:" + (endTime - startTime) + "ms");
        System.out.println("Test for G4 weight:" + kruskalMST4.getWeight());


    }
}
