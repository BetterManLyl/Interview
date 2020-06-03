package com.demo.interview.java.arithmetic;

/**
 * 文 件 名：Arithmetic
 * 创 建 人：李跃龙
 * 创建日期：2020/5/26 13:36
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * 算法
 */
public class Arithmetic {


    /**
     * 冒泡排序
     *
     * @param arr
     */
    void bubble_sort(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println(" ");
            for (int j = arr.length - 1; j > i; j--) {
//                System.out.println("j:" + j);
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
                for (int k = 0; k < arr.length; k++) {
                    System.out.println("arr:" + arr[k]);
                }
            }

        }
    }

    /**
     * 选择排序
     *
     * @param numbers
     */
    public void selectSort(int[] numbers) {
        int size = numbers.length; // 数组长度
        int temp = 0; // 中间变量
        for (int i = 0; i < size - 1; i++) {
            int k = i; // 待确定的位置
            // 选择出应该在第i个位置的数
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            } // 交换两个数
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }

    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();
        int[] array = new int[]{3, 7, 1, 8, 4, 9, 2};
        arithmetic.bubble_sort(array);
//        arithmetic.selectSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println("bubble:" + array[i]);
        }
    }

    void test(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }
}
