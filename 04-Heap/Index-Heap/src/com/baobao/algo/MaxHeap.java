package com.baobao.algo;

/**
 * 最大堆——堆顶的元素总是最大的
 * Created by suife on 2017/8/4.
 */
public class MaxHeap<Item extends  Comparable> {
    private int N = 0;       // 堆数据的数目
    private Item[] items;    // 存储的数据
    @SuppressWarnings("unchecked")
    public MaxHeap(int capacity){
        // 初始化堆中的容量,不使用数组的第一个元素
        items = (Item[]) new Comparable[capacity + 1];
    }

    /**
     * 堆是否为空
     * @return 空 返回 true
     */
    public boolean isEmpty(){
        return N == 0;
    }
    /**
     * 堆中数据量的大小
     * @return 堆中的数据数量
     */
    public int size(){
        return N;
    }

    /**
     * 向堆中插入一个元素
     * @param item item
     */
    public void insert(Item item){
        // 检查堆是否已经满了
        if(N == items.length - 1){
            extend();
        }
        items[++ N] = item;
        // 上浮，维护堆
        swim(N);
    }

    /**
     * 获取堆中最大的元素
     * @return 堆中最大元素
     */
    public Item delMax(){
        // 如果元素的个数少于数组大小的1/4，则缩容节省内存空间
        if(N == items.length / 4){
            shrink();// 缩容
        }
        Item item = items[1];   // 获取堆顶元素即最大的元素
        exch(1, N--);        // 将堆顶元素与最后一个元素交换位置，并更新N值
        items[N + 1] = null;    // 防止对象游离
        sink(1);                // 下沉操作，维护堆的顺序
        return item;
    }

    /**
     * 从底向上 逐次比较 维护堆
     * @param k 元素所在索引
     */
    private void swim(int k){
        while(k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k / 2;
        }
    }

    /**
     * 下沉操作 从上向下 逐次比较
     * @param k k
     */
    private void sink(int k){
        while(2 * k <= N){
            int j = 2 * k;
            // 保证j+1不越界
            if(j < N && less(j, j+1)){
                j ++;
            }
            if(! less(k, j)){
               break;
            }
            // 交换使得较大的元素到堆顶上去
            exch(k, j);
            k = j;
        }
    }
    /**
     * 比较两个索引位置的元素大小
     * @param i i
     * @param j j
     * @return 如果索引i的元素比索引j的元素小返回 true 否则返回 false
     */
    @SuppressWarnings("unchecked")
    private boolean less(int i, int j){
        return items[i].compareTo(items[j]) < 0;
    }

    /**
     * 扩展堆，为原来堆大小的2倍
     */
    @SuppressWarnings("unchecked")
    private void extend(){
        int newCapacity = items.length * 2; // 容量扩充为原来的两倍
        Item[] newItems = (Item[]) new Comparable[newCapacity];
        for(int i = 1; i < items.length; i ++){
            newItems[i] = items[i];
        }
        items = newItems;                   // 将原来数据存储指向新数组地址
    }

    /**
     * 缩容
     */
    @SuppressWarnings("unchecked")
    private void shrink(){
        // 将数组容量变为原来的1/2
        int newCapacity = items.length / 2;
        Item[] newItems = (Item[]) new Comparable[newCapacity];
        for(int i = 1; i <= N; i ++){
            newItems[i] = items[i];
        }
        items = newItems;
    }
    /**
     * 交换两个索引元素的位置
     * @param i i
     * @param j j
     */
    private void exch(int i, int j){
        Item temp  = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
}
