PR(A) = (1-d) + d (PR(T1)/C(T1) + ... + PR(Tn)/C(Tn)) 
其中：PR(A):页面A的网页级别, 
PR(Ti)：页面Ti的网页级别，页面Ti链向页面A， 
C(Ti)：页面Ti链出的链接数量，
d：阻尼系数，取值在0－1之间.

package com.joy.programmer.joyce.util;

public class PageRank_My {

    public static void main(String[] args) {
        // 定义阻尼系数
        double damp = 0.85;

        /* 测试数据，表示四个网页相互之间的有向链接矩阵
         * 用矩阵形式表示
         *    A  B  C  D
         * A  0  1  1  0
         * B  0  0  1  0
         * C  1  1  0  0
         * D  0  1  1  0
         * */

        int[][] link = {
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {1, 1, 0, 0},
            {0, 1, 1, 0}

        };
        // 各点的总链出数量
        int[] linkOut = new int[link.length];
        for (int i = 0; i < link.length; i++) {
            for (int j = 0; j < link.length; j++) {
                linkOut[i] += link[i][j];
            }
        }
        //定义一个初始数组并初始化，设所有的页面初始PR值都为1；
        double[] pr = new double[link.length];

        double[] init = new double[link.length];
        for (int i = 0; i < link.length; i++) {
            init[i] = 1.0;

        }
        pr = pagerank(link, damp, linkOut, init);

        //我们进行10次迭代计算pagerank的值
        for (int i = 0; i < 10; i++) {
            System.arraycopy(pr, 0, init, 0, link.length);
            pr = pagerank(link, damp, linkOut, pr);
        }

        for (int i = 0; i < link.length; i++) {
            System.out.println("PR(" + i + ") = " + pr[i]);
        }

    }

    /*计算pagerank的函数
     * @param link 页面的链接图
     * @param damp 设置的阻尼系数，佩奇设的是0.85
     * @param linkout 每个页面的链接总数
     * @param init 上次迭代的结果
     *
     * */
    public static double[] pagerank(int[][] link, double damp, int[] linkout,
        double[] init) {

        double[] pr = new double[init.length];
        double num = 0;
        for (int i = 0; i < init.length; i++) {
            for (int j = 0; j < init.length; j++) {
                // 接下来的判断是关键,计算非本页面，和链接数不为0的页面,并且那个页面有连到本页面的值，即是links[j][i] !=0。
                if ((i != j) && (linkout[j] != 0) && (link[j][i] != 0)) { num = num + init[j] / linkout[j]; }

            }
            // pagerank的计算公式
            pr[i] = damp + (1 - damp) * num;
        }
        return pr;
    }
}