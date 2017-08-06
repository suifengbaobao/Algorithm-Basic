package com.baobao.algo;

/**
 * 使用堆数据结构实现优先队列
 * 每次出队的元素是优先级最小的元素
 * Created by suife on 2017/8/3.
 */
public class MinHeap<Item extends Comparable> {
    private int N = 0;   // 数组长度
    private Item[] items;// 堆中的数据存储
    @SuppressWarnings("unchecked")
    public MinHeap(int N){
        // 数组的第一个位置不存储（即0索引）代码简洁一些
        items = (Item[]) new Comparable[N + 1];// 初始化数组
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    /**
     * 插入一个元素，然后判断是否需要上浮
     * @param item item
     */
    public void insert(Item item){
        if(N == items.length - 1){		// 当数组中的元素等于数组长度时减一时，对数组扩容
            expend();
        }
        items[++ N] = item;
        swim(N);// 上浮操作（）
    }

    /**
     * 删除并返回堆中最小的元素
     * @return 堆中最小元素
     */
    public Item delMin(){
        if(N < items.length / 4){		// 当数组中的元素个数小于数组长度的1/4时，缩容
            shrink();
        }
        Item min = items[1];		    // 获取堆顶的元素
        exch(1, N--);		 			// 将其和最后一个结点交换
        items[N + 1] = null;	  		// 防止对象游离
        sink(1);						// 恢复堆中的顺序
        return min;
    }
    /**
     * 插入元素时，通过元素上浮，保持堆中元素的顺序
     * @param k 当前插入元素的索引
     */
    private void swim(int k){
        while(k > 1 && !less(k/2, k)){
            exch(k/2 , k);
            k = k / 2;
        }
    }

    /**
     * 父元素优先级改变时，通过元素下沉，保持堆中元素的顺序
     * @param k k
     */
    private void sink(int k){
        while(2 * k <= N){
            int j = 2 * k;
            if(j < N && !less(j, j + 1)){
                j ++;
            }
            if(less(k, j)){
                break;
            }
            exch(k, j);
            k = j;
        }
    }
    /**
     * 比较两个元素的大小（优先级）
     * @param i i
     * @param j j
     * @return 如果索引为 i 的元素小于索引为 j 的元素则返回 true
     */
    @SuppressWarnings("unchecked")
    private boolean less(int i, int j){
        return items[i].compareTo(items[j]) < 0;
    }

    /**
     * 交换数组中索引为 i 和 j 的元素的位置
     * @param i i
     * @param j j
     */
    private void exch(int i, int j){
        Item temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    /**
     * 数组扩容
     */
    private void expend(){
        int n = items.length * 2;
        @SuppressWarnings("unchecked")
        Item[] newItems = (Item[]) new Comparable[n];
        for(int i = 1; i < items.length; i ++){
            newItems[i] = items[i];
        }
        items = newItems;
    }

    /**
     * 数组缩容
     */
    private void shrink(){
        int n = items.length / 2;
        @SuppressWarnings("unchecked")
        Item[] newItems = (Item[]) new Comparable[n];
        for(int i = 1; i <= N; i ++){
            newItems[i] = items[i];
        }
        items = newItems;
    }
}
