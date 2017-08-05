package com.baobao.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * 加权稠密图（实现方法为邻接矩阵）
 * Created by suife on 2017/8/3.
 */
public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {
    private int n;// 节点数
    private int m;// 边数
    private boolean directed;   // 是否为有向图
    private Edge<Weight>[][] g; // 图的具体数据
    // 构造函数
    public DenseWeightedGraph(int n, boolean directed){
        this.n = n;
        this.directed = directed;
        this.m = 0;             // 表示一开始没有边
        g = new Edge[n][n];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < n; j ++){
                g[i][j] = null; // 初始化所有边为null
            }
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
    // 添加一条边
    @Override
    public void addEdge(Edge edge) {
        if(hasEdge(edge.v(), edge.w())){
            return; // 如果两点之间已经有边直接返回
        }
        g[edge.v()][edge.w()] = new Edge(edge);
        if(edge.v() != edge.w() && !directed){// 如果是无向图，则再添加一条反向边
            g[edge.w()][edge.v()] = new Edge(edge.w(), edge.v(), edge.wt());
        }
        m ++;        // 更新边的数目
    }
    // 判断两个节点间是否有边
    @Override
    public boolean hasEdge(int v, int w) {
        return g[v][w] != null;
    }
    // 打印图
    @Override
    public void show() {
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < n; j ++){
                if(g[i][j] != null){
                    System.out.printf("%4s", g[i][j].wt() + "\t");
                }else{
                    System.out.print("NULL\t");
                }
            }
            System.out.println();
        }
    }
    // 返回这个节点的所有边
    @Override
    public Iterable<Edge> adj(int v) {
        List<Edge> list = new ArrayList<>();
        for(int i = 0; i < n; i ++){
            if(g[v][i] != null){
                list.add(g[v][i]);
            }
        }
        return list;
    }
}
