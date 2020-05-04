package com;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 灰色关联度计算
 *
 * 示例：
 *
 * 请输入序列初值矩阵的行数 n:
 * 5
 * 请输入序列初值矩阵的行数 m:
 * 12
 * 请输入参考数列 y:
 * 1, 1.19, 1.44, 1.66, 1.95, 2.34, 2.71, 2.89, 3.53, 4.15, 4.52, 4.88
 * 请输入序列初始矩阵 x:
 * x1:
 * 1, 1.25, 1.54, 1.85, 2.18, 2.43, 2.67, 2.96, 3.25, 3.56, 3.82, 4.16
 * x2:
 * 1, 1.49, 2.16, 2.80, 3.82, 5.03, 5.83, 5.60, 7.07, 7.80, 8.72, 9.33
 * x3:
 * 1, 1.06, 1.08, 1.76, 1.82, 1.80, 1.63, 1.69, 1.74, 1.68, 1.63, 1.65
 * x4:
 * 1, 1.26, 1.69, 2.04, 2.63, 3.04, 3.44, 3.70, 4.23, 5.27, 5.91, 6.16
 * x5:
 * 1, 1.10, 1.27, 1.17, 1.25, 1.25, 1.29, 1.42, 1.45, 1.37, 1.14, 1.29
 *
 * 得到的灰色关联度是：
 * r0= 0.91, r1= 0.55, r2= 0.72, r3= 0.78
 */
public class GreyCorrelation {

    public static double _RATIO = 0.5;

    public static DecimalFormat df = new DecimalFormat("#.##");

    /**
     * 灰色关联度计算
     *
     * @param n 序列行数
     * @param m 序列列数
     * @param y 参考数列
     * @param initData 序列初值
     * @return
     */
    public static Double[] greyCorrelationMethod(int n, int m, double[] y, double[][] initData) {

        //灰色关联度
        Double[] r = new Double[n];

        //极小值
        double min = 0;
        //极大值
        double max = 0;

        //计算差数列
        double[][] differs = new double[n][m];
        for (int i = 0; i < initData.length; i++) {
            for (int j = 0; j < initData[i].length; j++) {
                differs[i][j] = formatDoubleResult(Math.abs(initData[i][j] - y[j]));
                if (min > differs[i][j]) {
                    min = differs[i][j];

                }

                if (max < differs[i][j]) {
                    max = differs[i][j];
                }
            }
        }

        System.out.println("差序列：");
        printX(differs);

        System.out.println("极小值：" + min);
        System.out.println("极大值：" + max);

        //算出关联系数表
        double[][] relations = new double[n][m];
        for (int i = 0; i < differs.length; i++) {
            double sum = 0;
            for (int j = 0; j < differs[i].length; j++) {
                relations[i][j] = formatDoubleResult((_RATIO * max + min) / (differs[i][j] + _RATIO * max));
                sum += relations[i][j];
                if (j == differs[i].length - 1) {
                    r[i] = formatDoubleResult(sum / m);
                }
            }
        }

        System.out.println("关联系数表：");
        printX(relations);
        return r;
    }

    /**
     * 格式化数据：保留两位小数
     *
     * @param num 需要格式化的数
     * @return
     */
    public static double formatDoubleResult(double num) {
        return Double.parseDouble(df.format(num));
    }

    /**
     * 打印结果
     *
     * @param r
     */
    public static void printR(Double[] r) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < r.length; i++) {
            if(i != r.length - 1) {
                s = s.append("r" + (i+1) + "= " + r[i] ).append(", ");
            } else {
                s = s.append("r" + (i+1) + "= " + r[i] );
            }
        }
        System.out.println(s.toString());
    }

    public static double[] initValues(double[] values) {
        double initial = values[0];

        for (int i = 0; i < values.length; i++) {
            values[i] = formatDoubleResult(values[i] / initial);
        }

        return values;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入序列初值矩阵的行数 n: ");
//        int n = scanner.nextInt();
//
//        System.out.println("请输入序列初值矩阵的行数 m: ");
//        int m = scanner.nextInt();
        int n = 3;
        int m = 20;

        System.out.println("请输入参考数列 y: ");
        String yString = null;
        while (scanner.hasNextLine()) {
            yString = scanner.nextLine();
            if(!yString.equals("")) {
                break;
            }
        }


        String[] yStrings = yString.split(",");
        double[] y = new double[m];
        for (int i = 0; i < y.length; i++) {
            y[i] = Double.parseDouble(yStrings[i]);
        }
        y = initValues(y);

        System.out.println("请输入序列初始矩阵 x: ");
        double[][] x = new double[n][m];
        for (int i = 0; i < n; i++) {
            System.out.println("x" + (i) +": ");
            String xString = null;
            while (scanner.hasNextLine()) {
                xString = scanner.nextLine() ;
                if(!xString.equals("")) {
                    break;
                }
            }
            String[] xStrings = xString.split(",");
            for (int j = 0; j < m; j++) {
                x[i][j] = Double.parseDouble(xStrings[j]);
            }
            x[i] = initValues(x[i]);
        }


        System.out.println("==================================================================================================");

        System.out.println("序列初值化：");
        printY(y);
        printX(x);

        Double[] r = greyCorrelationMethod(n, m, y, x);

        System.out.println("得到的灰色关联度：");
        printR(r);

        Thread.sleep(100);
    }

    private static void printX(double[][] x) {
        for (int i = 0; i < x.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < x[i].length; j++) {
                if(j != x[i].length - 1) {
                    sb.append(x[i][j]).append(",");
                } else {
                    sb.append(x[i][j]);
                }
            }
            System.out.println("x" + (i+1) + ": " + sb);
        }

    }

    private static void printY(double[] y) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < y.length; i++) {
            if(i != y.length - 1) {
                sb.append(y[i]).append(",");
            } else {
                sb.append(y[i]);
            }
        }
        System.out.println("y: " + sb);
    }

}
