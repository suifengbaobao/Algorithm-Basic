package com.baobao.algo;

/**
 * 测试类
 * Created by suife on 2017/8/5.
 */
public class Main {
    public static void main(String[] args) {
        int N = 1000000;
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<Integer>(N);
        for( int i = 0 ; i < N ; i ++ )
            indexMaxHeap.insert( i , (int)(Math.random()*N) );
        System.out.println( indexMaxHeap.testIndexes());
    }
}
