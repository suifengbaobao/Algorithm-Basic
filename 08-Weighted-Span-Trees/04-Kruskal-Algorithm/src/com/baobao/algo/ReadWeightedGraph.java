package com.baobao.algo;

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 从一个本文文件中读取一个图
 * 由于文件格式的限制，我们的文件读取类只能读取权值为Double类型的图
 * Created by suife on 2017/8/3.
 */
public class ReadWeightedGraph {
    private Scanner scanner;
    public ReadWeightedGraph(WeightedGraph graph, String fileName) {
        readFile(fileName);

        try {
            int V = scanner.nextInt();// 读取节点数
            if (V < 0) {
                throw new IllegalArgumentException("number of vertices must be nonnegative");
            }
            assert V == graph.V();    // 判断初始化图的节点数与读的节点数是否相等
            int E = scanner.nextInt();// 读取边的数目
            if (E < 0) {
                throw new IllegalArgumentException("number of edge must be nonnegative");
            }
            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                Double weight = scanner.nextDouble();
                assert v > 0 && v < V;
                assert w > 0 && w < V;
                graph.addEdge(new Edge(v, w, weight));
            }
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempt to read an 'int' value from input stream, but the next token is \"" + token + "\"");
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("attempt to read an 'int' value from input stream, but there are no more tokens are available");
        }
    }

    public void readFile(String fileName){
        assert fileName != null;
        try {
            File file = new File(fileName);
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                // 利用缓冲字节流读取
                scanner = new Scanner(new BufferedInputStream(fis), "utf-8");
            }else{
                throw new IllegalArgumentException(fileName + " don't exist.");
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + fileName, ioe);
        }
    }
}
