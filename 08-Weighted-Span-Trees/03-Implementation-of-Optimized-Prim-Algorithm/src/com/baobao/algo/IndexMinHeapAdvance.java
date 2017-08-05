package com.baobao.algo;

/**
 * 最小索引堆
 * ===============================================
 * 构建堆前：
 *          1   2   3   4   5   6   7   8   9   10
 * items：  3   8   2   1   9   5   4   10  7   6
 * index：  1   2   3   4   5   6   7   8   9   10
 * ===============================================
 * 构建堆后：
 *          1   2   3   4   5   6   7   8   9   10
 * items：  3   8   2   1   9   5   4   10  7   6  ---> 1   3   2   7   9   5   4   10  8   6
 * index：  4   1   3   9   5   6   7   8   2   10
 * ===============================================
 * 索引堆，实际是不在具体的数据数组上交换数据，而是用一个索引数组构成堆，索引数组对应的内容则是具体数据
 * 这样做可以避免复杂对象的交换开销，以及实际生产保持原数据的位置也是非常重要的，一般原数据下标可能表示该数据的id
 * ====优化操作=====
 * change()方法原先是通过遍历索引数组来找到对应的indexes[j] = i 的j索引 这复杂度为O(n) n为数组容量（n >= 节点数）
 * 可以通过增加一个反向数组即reverse[i] = j 这样就能通过O(1)的复杂度获取到j
 * Created by suife on 2017/8/4.
 */
public class IndexMinHeapAdvance<Item extends Comparable> {
    private int N = 0;       // 数据的数量
    private int capacity = 0;// 数组的容量
    private Item[] items;    // 堆中的数据存储
    private int[] indexes;   // 索引数组（即堆）indexes[x] = i 表示索引i在x的位置
    private int[] reverse;   // 反向索引数组    reverse[i] = x 表示索引x在i的位置
    @SuppressWarnings("unchecked")
    public IndexMinHeapAdvance(int cp){
        this.capacity = cp;
        // 数组的第一个位置不存储（即0索引）代码简洁一些
        items = (Item[]) new Comparable[capacity + 1];// 初始化数据数组
        indexes = new int[capacity + 1];              // 初始化索引数组
        reverse = new int[capacity + 1];              // 初始化反向索引数组
        for( int i = 0 ; i <= capacity ; i ++ ) {
            reverse[i] = 0;   // 0索引不用，先全部初始化为0，表示没有关联关系
        }
    }

    public boolean isEmpty(){
        return N == 0;
    }

    /**
     * 堆中元素的个数
     * @return 堆中元素的个数
     */
    public int size(){
        return N;
    }

    /**
     * 插入一个元素，然后判断是否需要上浮
     * @param i     传入的索引堆用户来说是从0开始的
     * @param item  元素
     */
    public void insert(int i, Item item){
        assert (N + 1 <= capacity);
        assert (i + 1 > 0 && i + 1 <= capacity);
        if(N == capacity - 1){		// 当数组中的元素等于数组长度时减一时，对数组扩容
            expend();
        }
        i += 1;                     // 消除用户使用习惯造成的差异
        items[i] = item;
        indexes[++ N] = i;          // 索引数组记录数据的下标
        reverse[i] = N;             // 维护反向索引数组
        swim(N);// 上浮操作（）
    }

    /**
     * 删除并返回堆中最小的元素
     * @return 堆中最小元素
     */
    public Item delMin(){
        assert N > 0;
        if(N < capacity / 4 && N < 15){		    // 当数组中的元素个数小于数组长度的1/4时，缩容
            shrink();
        }
        Item min = items[indexes[1]];	// 获取堆顶的元素
        exch(1, N--);		 			// 将其和最后一个结点交换
        items[indexes[N + 1]] = null;	// 防止对象游离
        indexes[N + 1] = 0;
        reverse[0] = N + 1;
        sink(1);						// 恢复堆中的顺序
        return min;
    }

    /**
     * 获取最小堆元素
     * @return 最小堆元素
     */
    public Item getMin(){
        assert N > 0;
        return items[indexes[1]];
    }
    /**
     * 删除并返回最小堆元素的索引
     * @return 最小堆元素索引
     */
    public int delMinIndex(){
        assert N > 0;
        // 考虑到用户使用的索引是从0开始的
        int minIndex = indexes[1] - 1;
        exch(1, N--);
        reverse[indexes[N + 1]] = 0;
        sink(1);
        return minIndex;
    }

    /**
     * 获取最小堆元素索引
     * @return 最小堆元素索引
     */
    public int getMinIndex(){
        assert N > 0;
        return indexes[1] - 1;
    }

    /**
     * 根据索引获取相应的堆元素
     * @param i 索引
     * @return  对应元素
     */
    public Item getItem(int i){
        assert contain(i);
        // 用户使用的索引是从0开始
        return items[i+1];
    }

    /**
     * 将最小索引堆中的指定索引元素换为新的元素
     * @param i 指定索引
     * @param newItem 新元素
     */
    public void change(int i, Item newItem){
        i += 1;
        items[i] = newItem;
        // 此时元素是更换成功了，但是堆需要重新维护
        // 先找到索引 i 对应的 indexes[j] = i j代表元素在堆中的位置
        // 然后分别swim(), sink()一下
//        for(int j = 1; j <= N; j ++){
//            if(indexes[j] == i){
//                swim(j);
//                sink(j);
//                return;
//            }
//        }
        sink(reverse[i]);
        swim(reverse[i]);
    }

    /**
     * 看索引i所在的位置是否存在元素
     * @param i 索引
     * @return true 存在元素
     */
    private boolean contain( int i ){
        assert  i + 1 >= 1 && i + 1 <= capacity;
        return reverse[i+1] != 0;
    }
    /**
     * 插入元素时，通过元素上浮，保持堆中元素的顺序
     * @param k 当前插入元素的索引
     */
    private void swim(int k){
        while(k > 1 && !less(indexes[k/2], indexes[k])){
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
            if(j + 1 <= N && !less(indexes[j], indexes[j+1])){
                j ++;
            }
            if(less(indexes[k], indexes[j])){
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
        return items[i].compareTo(items[j]) <= 0;
    }

    /**
     * 交换数组中索引为 i 和 j 的元素的位置
     * @param i i
     * @param j j
     */
    private void exch(int i, int j){
        int temp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = temp;
        reverse[indexes[i]] = i;
        // reverse[temp] = j --> indexes[j] = temp --> reverse[indexes[j]] = j;
        reverse[indexes[j]] = j;
    }

    /**
     * 数组扩容(数据数组和索引数组)
     */
    private void expend(){
        capacity = items.length * 2;
        @SuppressWarnings("unchecked")
        Item[] newItems = (Item[]) new Comparable[capacity];
        int[] newIndexes = new int[capacity];
        int[] newReverseIndex = new int[capacity];
        for(int i = 1; i < items.length; i ++){
            newItems[i] = items[i];
            newIndexes[i] = indexes[i];
            newReverseIndex[indexes[i]] = i;
        }
        items = newItems;
        indexes = newIndexes;
        reverse = newReverseIndex;

    }
    /**
     * 数组缩容(数据数组和索引数组)
     */
    private void shrink(){
        capacity = capacity / 2;
        @SuppressWarnings("unchecked")
        Item[] newItems = (Item[]) new Comparable[capacity];
        int[] newIndexes = new int[capacity];
        int[] newReverseIndex = new int[capacity];
        for(int i = 1; i <= N; i ++){
            // 缩容的时候要注意一些细节
            newIndexes[i] = i;
            newReverseIndex[i] = i;
            // 新的元素要等于原来索引指向的元素
            newItems[i] = items[indexes[i]];
        }
        items = newItems;
        indexes = newIndexes;
        reverse = newReverseIndex;
    }
}
