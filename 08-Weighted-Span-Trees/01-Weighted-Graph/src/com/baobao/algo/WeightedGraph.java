package com.baobao.algo;

/**
 * 加权图规范接口
 * Created by suife on 2017/8/3.
 */
public interface WeightedGraph<Weight extends Number & Comparable>{
    /**
     * 获取节点数
     * @return 加权图节点的数目
     */
    int V();

    /**
     * 获取加权图的边数目
     * @return 加权图边数目
     */
    int E();

    /**
     * 添加一条边
     * @param edge 边
     */
    void addEdge(Edge<Weight> edge);

    /**
     * 判断两个节点之间是否有边
     * @param v 一个节点
     * @param w 另一个节点
     * @return true 有边 false 无边
     */
    boolean hasEdge(int v, int w);

    /**
     * 打印图
     */
    void show();

    /**
     * 获取所有跟v相关联的边
     * @param v 一个节点
     * @return  可迭代对象（包含了与v相关的边）
     */
    Iterable<Edge<Weight>> adj(int v);
}
