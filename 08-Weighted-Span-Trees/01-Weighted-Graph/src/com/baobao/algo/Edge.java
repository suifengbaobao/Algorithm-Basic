package com.baobao.algo;

/**
 * 图的边
 * Created by suife on 2017/8/3.
 */
public class Edge<Weight extends Number & Comparable> implements Comparable<Edge>{
    private int a;          // 其中一点
    private int b;          // 边的另一点
    private Weight weight;  // 边的权值

    public Edge(int a, int b, Weight weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public Edge(Edge<Weight> e){
        this.a = e.a;
        this.b = e.b;
        this.weight = e.weight;
    }

    public Edge(){}

    public int v(){
        return a;
    }
    public int w(){
        return b;
    }
    public Weight wt(){
        return weight;
    }

    /**
     * 根据传入的参数获取另一条边
     * @param x 传入的边
     * @return 另一条边
     */
    public int other(int x){
        return x == a ? b : a;
    }

    @Override
    public String toString() {
        return a + "-" + b + ": " + weight;
    }

    /**
     * 两边之间的比较
     * @param that 传入的边
     * @return     一个整数（有符号，正数、负数、0）
     */
    @Override
    public int compareTo(Edge that) {
        if(weight.compareTo(that.wt()) > 0){
            return 1;
        }else if(weight.compareTo(that.wt()) < 0){
            return -1;
        }else{
            return 0;
        }
    }
}
