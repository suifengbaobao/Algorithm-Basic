package com.baobao.algo;

/**
 * 加权图中的边
 * Created by suife on 2017/8/3.
 */
public class Edge<Weight extends Number & Comparable> implements Comparable<Edge>{
    private int a;// 一个节点
    private int b;// 边的另一个节点
    private Weight weight;

    public Edge(Edge<Weight> e){
        this.a = e.a;
        this.b = e.b;
        this.weight = e.weight;
    }

    public Edge(int v, int w, Weight weight){
        this.a = v;
        this.b = w;
        this.weight = weight;
    }

    public Edge(){}
    /**
     * 返回边的权值
     * @return 权值
     */
    public Weight wt(){
        return weight;
    }
    public int v(){
        return a;
    }

    public int w(){
        return b;
    }
    /**
     * 获取边的一个节点
     * @return 边两端点之一
     */
    public int either(){
        return a;
    }

    /**
     * 返回另一个节点
     * @param x 一个节点
     * @return  另一个节点
     */
    public int other(int x){
        return x == a ? b : a;
    }

    @Override
    public int compareTo(Edge that) {
        if(weight.compareTo(that.weight) > 0){
            return 1;
        }else if(weight.compareTo(that.weight) < 0){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return a + "-" + b + ": " + weight;
    }
}
