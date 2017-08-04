package com.baobao.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * LazyPrim算法实现的最小生成树
 * Created by suife on 2017/8/3.
 */
public class LazyPrimMST<Weight extends Number & Comparable> {
    private WeightedGraph<Weight> G;        // 加权图的引用
    private List<Edge<Weight>> mst;         // 最小生成树包含的所有边
    private MinHeap<Edge<Weight>> pq;       // 最小堆，算法的辅助数据结构
    private boolean[] marked;               // 标记数组，算法在运行过程中节点i是否被访问
    private Number mstWeight;               // 最小生成树的权值
    // 构造器初始化
    public LazyPrimMST(WeightedGraph<Weight> graph){
        this.G = graph; // 初始化图
        mst = new ArrayList<>(G.V());
        pq = new MinHeap<>(G.E());
        marked = new boolean[G.V()];

        // LazyPrim Algorithm core code
        visit(0);// 从0节点开始访问

        // 每访问一个节点，找出最小的权值的边添加到最小生成树种mst
        while(! pq.isEmpty()){
            // 使用最小堆找出已经访问的边中权值最小的边
            Edge<Weight> e = pq.delMin();
            // 如果这条边的两个节点都被访问，则仍掉这条边
            if(marked[e.v()] == marked[e.w()]){
                continue;
            }
            // 否则这条边应该在最小生成树中
            mst.add(e);
            // 继续访问这条边的另一个节点
            if(! marked[e.v()]){
                visit(e.v());
            }else if(! marked[e.w()]){
                visit(e.w());
            }
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
        assert !marked[v];
        marked[v] = true;// 标记访问过的节点
        for(Edge<Weight> edge : G.adj(v)){// 遍历该节点相连的边
            if(! marked[edge.other(v)]){// 如果边的另一个节点没有被访问过，则表示该边不存在堆中
                pq.insert(edge);
            }
        }
    }
}
