package com.baobao.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏图（邻接表实现）
 * Created by suife on 2017/8/3.
 */
public class SparseWeightedGraph<Weight extends Number & Comparable>implements WeightedGraph {
    private int n;// 图节点的个数
    private int m;// 图边的个数
    private boolean directed;       // 是否为有向图
    private List<Edge<Weight>>[] g; // 图的具体数据

    public SparseWeightedGraph(int n, boolean directed){
        this.n = n;
        this.directed = directed;
        this.m = 0;// 初始化边的数目为0，表示一条边也没有
        g = new List[n];
        for(int i = 0; i < n; i ++){
            g[i] = new ArrayList<>();
        }
    }
    @Override
    public int V() {
        return n;
    }

    @Override
    public int E() {
        return m;
    }

    @Override
    public void addEdge(Edge edge) {
        g[edge.v()].add(edge);
        if(edge.v() != edge.w() && !directed){
            g[edge.w()].add(new Edge(edge.w(), edge.v(), edge.wt()));
        }
        m ++;       // 更新边数目
    }
    // 两个节点之间是否有边
    @Override
    public boolean hasEdge(int v, int w) {
        for(int i = 0; i < g[v].size(); i ++){
            if(g[v].get(w).other(v) == w){
                return true;
            }
        }
        return false;
    }
    /*
    * 打印图
    * i (to:1,wt:.37)   (to:2,wt:.38)    ……
    * */
    @Override
    public void show() {
        for(int i = 0; i < n; i ++){
            System.out.print("vertex" + i + "\t");
            for(int j = 0; j < g[i].size(); j ++){
                Edge edge = g[i].get(j);
                System.out.print("(to:" + edge.other(i) + ",wt:" + edge.wt() + ")\t");
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        return g[v];// 类型检查真是个奇怪的东西（为什么同样的在稠密图中，就没问题呢？）
    }
}
