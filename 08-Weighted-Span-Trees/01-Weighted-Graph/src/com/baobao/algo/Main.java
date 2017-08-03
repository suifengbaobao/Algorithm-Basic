package com.baobao.algo;

/**
 * 测试稠密加权图和稀疏加权图
 * Created by suife on 2017/8/3.
 */
public class Main {
    public static void main(String[] args) {
        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "testG1.txt";

        DenseWeightedGraph denseWeightedGraph = new DenseWeightedGraph(8, false);
        new ReadWeightedGraph(denseWeightedGraph, filename);
        denseWeightedGraph.show();
        System.out.println();

        SparseWeightedGraph sparseWeightedGraph = new SparseWeightedGraph(8, false);
        new ReadWeightedGraph(sparseWeightedGraph, filename);
        sparseWeightedGraph.show();
        System.out.println();
    }
}
