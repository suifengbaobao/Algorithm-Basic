package com.baobao.algo;

/**
 * 主测试函数
 * Created by suife on 2017/8/4.
 */
public class Main {
    public static void main(String[] args) {
        String fileName = "testG1.txt";
        // 创建一个加权稠密图
        DenseWeightedGraph<Double> graph = new DenseWeightedGraph<>(8, false);
        // 读取一个图
        new ReadWeightedGraph(graph, fileName);
        // 生成最小社会生成树
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(graph);
        for(Edge<Double> e : lazyPrimMST.getMst()){
            System.out.println(e);
        }
        System.out.println("mstWeight: " + lazyPrimMST.getMstWeight());
    }
}
