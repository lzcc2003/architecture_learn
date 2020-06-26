package com.joy.programmer.joyce.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.math.stat.descriptive.moment.Variance;

public class ConHashTest {
    private static final String IP_PREFIX = "192.168.1.";// 机器节点IP前缀
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();// 每台真实机器节点上保存的记录条数
        HashFunction hashFunction = new HashFunctionImpl();
        //HashFunction hashFunction = new MurmurHashFunctionImpl();
        //真实物理节点
        List<Node> realNodes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            map.put(IP_PREFIX + i, 0);// 每台真实机器节点上保存的记录条数初始为0

            Node node = new Node(IP_PREFIX + i, "node" + i);
            realNodes.add(node);
        }


        ConsistentHash consistentHash = new ConsistentHash(hashFunction,100,realNodes);
        // 将1000000条记录尽可能均匀的存储到10台机器节点
        for (int i = 0; i < 1000000; i++) {
            // 产生随机一个字符串当做一条记录，可以是其它更复杂的业务对象,比如随机字符串相当于对象的业务唯一标识
            String data = UUID.randomUUID().toString() + i;
            // 通过记录找到真实机器节点
            Node node = consistentHash.get(data);
            // 这里可以通过其它工具将记录存储真实机器节点上，比如MemoryCache等
            // ...
            // 每台真实机器节点上保存的记录条数加1
            map.put(node.getIp(), map.get(node.getIp()) + 1);
        }

        double[] array = new double[10];
        // 打印每台真实机器节点保存的记录条数
        for (int i = 1; i <= 10; i++) {
            System.out.println(IP_PREFIX + i + "节点记录条数：" + map.get("192.168.1." + i));
            BigDecimal data = new BigDecimal(map.get("192.168.1." + i));
            BigDecimal base = new BigDecimal(100000);
            BigDecimal result = data.divide(base);
            array[i-1] = result.doubleValue();
        }
        Variance variance = new Variance();//方差
        System.out.println(variance.evaluate(array));
    }

}