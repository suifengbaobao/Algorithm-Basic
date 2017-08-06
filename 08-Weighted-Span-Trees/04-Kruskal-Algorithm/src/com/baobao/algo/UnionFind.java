package com.baobao.algo;
/**
 * 基于路径压缩的并查集实现
 * @author sfbaobao
 *
 */
public class UnionFind {
	private int[] parent;
	private int[] rank;
	private int count;
	
	public UnionFind(int count){
		parent = new int[count];// 父节点的id
		rank = new int [count]; // 集合的高度
		this.count = count;     // 并查集的大小
		for(int i = 0; i < count; i ++){
			parent[i] = i;		// 每个节点一开始指向自己
			rank[i] = 1;		// 初始高度为1
		}
	}
	
	/**
	 * 查找某个节点的根节点
	 * 在查找的过程中进行路径压缩
	 * @param p 某一节点
	 * @return 根节点id
	 */
	public int find(int p){
		assert(p >= 0 && p < count);
		while(p != parent[p]){
			// 如果发现寻找的元素的父元素的id不为根节点， 则直接将该元素指向其父亲的父亲节点 路径压缩
			// 理论上下面一种实现的效率应该更高，但实际是这种效率高，因为在递归的 过程中也会花费一些时间
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
//		if(p != parent[p]){
            // 一直递归到根节点（使得一个并查集的高度为2）
//			parent[p] = find(parent[p]);
//		}
//		return parent[p];
	}
	/**
	 * 判断两个节点是否相连
	 * @param p 一个节点
	 * @param q 另一个节点
	 * @return  连接则返回 true
	 */
	public boolean isConnected(int p, int q){
		return find(p) == find(q);
	}
	
	/**
	 * 并两个节点，两个集合所在的根节点一样
	 * @param p 一个节点
	 * @param q 另一个节点
	 */
	public void union(int p, int q){
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot){
			return;
		}
		if(rank[pRoot] < rank[qRoot]){// 层数少点向层数多的并
			parent[pRoot] = qRoot;
		}else if(rank[pRoot] > rank[qRoot]){
			parent[qRoot] = pRoot;
		}else{
			parent[pRoot] = qRoot;
			rank[qRoot] += 1;// 当两个结合层数一样时，选择一个集合指向另一个集合， 相应的被指向的集合告诉加1
		}
	}
}
