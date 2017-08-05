package com.baobao.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Prim算法实现的最小生成树算法复杂度为O(ElgV)
 * Created by suife on 2017/8/3.
 */
public class PrimMST<Weight extends Number & Comparable> {
    private WeightedGraph<Weight> G;        // 加权图的引用
    private List<Edge<Weight>> mst;         // 最小生成树包含的所有边
    private IndexMinHeapAdvance<Weight> ipq;// 最小索引堆，算法的辅助数据结构
    private Edge<Weight>[] edgeTo;          // 访问的点所对应的边, 算法辅助数据结构
    private boolean[] marked;               // 标记数组，算法在运行过程中节点i是否被访问
    private Number mstWeight;               // 最小生成树的权值
    // 构造器初始化
    public PrimMST(WeightedGraph<Weight> graph){
        this.G = graph; // 初始化图
        mst = new ArrayList<>(G.V());
        ipq = new IndexMinHeapAdvance<>(G.V());
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];
        for(int i = 0; i < G.V(); i ++){
            marked[i] = false;
            edgeTo[i] = null;
        }

        // Prim Algorithm core code
        visit(0);// 从0节点开始访问

        // prim
        while(! ipq.isEmpty()){
            int v = ipq.delMinIndex();
            mst.add(edgeTo[v]);
            assert (edgeTo[v] != null);
            visit(v);
        }
        // 计算最小生成树的权值
        mstWeight = mst.get(0).wt();
        for(int i = 1; i < mst.size(); i ++){
            mstWeight = mstWeight.doubleValue() + mst.get(i).wt().doubleValue();
        }
    }

    /**
     * 返回最小生成树
     * @return mst
     */
    public List<Edge<Weight>> getMst(){
        return mst;
    }

    /**
     * 返回最小生成树的权值
     * @return mstWeight
     */
    public Number getMstWeight(){
        return mstWeight;
    }
    /**
     * 访问一个节点的所有边，找出堆中没有的边加入堆中
     * @param v 节点
     */
    private void visit(int v) {
        assert (! marked[v]);
        marked[v] = true;
        for(Edge<Weight> e : G.adj(v)){
            int w = e.other(v);
            if(! marked[w]){
                // 如果从没有考虑过这个端点, 直接将这个端点和与之相连接的边加入索引堆
                if(edgeTo[w] == null){
                    edgeTo[w] = e;
                    ipq.insert(w, e.wt());
                }else if(e.wt().compareTo(edgeTo[w].wt()) < 0){
                // 这个端点相连的其他边，如果权值比之前存储的端点权值小则进行更新
                    ipq.change(w, e.wt());
                    edgeTo[w] = e;
                }
            }
        }
    }
}
