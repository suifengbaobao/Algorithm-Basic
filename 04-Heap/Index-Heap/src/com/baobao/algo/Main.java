package com.baobao.algo;

import java.util.Random;

/**
 * 测试索引堆
 * Created by suife on 2017/8/4.
 */
public class Main {
    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(10);
        MinHeap<Integer> minHeap = new MinHeap<>(10);
        for(int i = 0; i < 50; i ++){
            maxHeap.insert(new Random().nextInt(100));
            minHeap.insert(new Random().nextInt(100));
        }
        System.out.print("MaxHeap: ");
        for(int i = 0; i < 50; i ++){
           System.out.print(maxHeap.delMax() + " ");
        }
        System.out.print("\nMinHeap: ");
        for(int i = 0; i < 50; i ++){
            System.out.print(minHeap.delMin() + " ");
        }
    /*======================================================================*/
        // 测试索引数组
        IndexMinHeap<Integer> stringIndexMinHeap = new IndexMinHeap<>(10);
        IndexMaxHeap<Integer> integerIndexMaxHeap = new IndexMaxHeap<>(10);
        for(int i = 0; i < 50; i ++){
            stringIndexMinHeap.insert(i, new Random().nextInt(100));
            integerIndexMaxHeap.insert(i, new Random().nextInt(100));
        }
        System.out.print("\nIndexMinHeap: ");
        for(int i = 0; i < 50; i ++){
            System.out.print(stringIndexMinHeap.delMin() + " ");
        }
        System.out.print("\nIndexMaxHeap: ");
        for(int i = 0; i < 50; i ++){
            System.out.print(integerIndexMaxHeap.delMax() + " ");
        }
    }
}
