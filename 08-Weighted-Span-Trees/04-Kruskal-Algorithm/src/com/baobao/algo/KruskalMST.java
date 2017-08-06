package com.baobao.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Kruskal算法生成最小生成树
 * 思想：
 *     首先找到权值最小的一条边，这条边肯定是一个节点的最短横切边，即是属于最小生成树中
 *     接下来找到权值次小的边，判断如果将这条边加入最小生成树，是否构成环，如果构成环，
 *     则说明这条边不是最小生成树中的一条边，依次判断，直到最小生成树中有了（节点数-1）
 *     的边，则找到了最小生成树
 * Created by suife on 2017/8/5.
 */
public class KruskalMST<Weight extends Number & Comparable>{
    private List<Edge<Weight>> mst;   // 最小生成树存储的数据
    private Number weight;            // 最小生成树的权值

    public KruskalMST(WeightedGraph<Weight> graph){
        // 存放所有边的最小堆
        MinHeap<Edge<Weight>> pq = new MinHeap<>(graph.E());
        mst = new ArrayList<>(graph.V());
        // 将所有的边加入到最小堆中
        for(int v = 0; v < graph.V(); v ++){
            for(Edge<Weight> e : graph.adj(v)){
                if(e.v() < e.w()){// 因为这里默认实现的是无向图（如果是有向图，则加入一条边就可以了）
                    pq.insert(e);
                }
            }
        }
        // 并查集，用来判断生成树中是否存在环
        UnionFind uf = new UnionFind(graph.V());
        // kruskal核心算法
        while(! pq.isEmpty() && mst.size() < graph.V()){
            Edge<Weight> edge = pq.delMin();
            // 判断这两个端点是否是连接的，如果不连接则加入到最小生成树中
            if(! uf.isConnected(edge.v(), edge.w())){
               mst.add(edge);
               // 并两个节点
               uf.union(edge.v(), edge.w());
            }
        }
        weight = mst.get(0).wt().doubleValue();
        for(int i = 1; i < mst.size(); i ++){
            weight = weight.doubleValue() + mst.get(i).wt().doubleValue();
        }
    }

    /**
     * 获取最小生成树的权值
     * @return weight
     */
    public Number getWeight(){
        return weight;
    }

    /**
     * 获取最小生成树
     * @return mst
     */
    public List<Edge<Weight>> getMst(){
        return mst;
    }
}
