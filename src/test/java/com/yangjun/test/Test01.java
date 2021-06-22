package com.yangjun.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Test01 {

    @Test
    public void test01() {

        List<Integer> list = Arrays.asList(1, 2, 3);

        list.add(4);

        System.out.println(list);
    }

    @Test
    public void test02() {

        Clerk clerk = new Clerk();

        Producter producter = new Producter(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(producter,"生产者").start();

        new Thread(consumer,"消费者").start();



    }

    class Clerk {

        private int num = 0;


        public synchronized void get() {

            if (num >= 1) {
                try {
                    System.out.println("产品已满");
                    this.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("开始生产");
                System.out.println(Thread.currentThread().getName() + " 当前数量 ：" + ++num);
                this.notifyAll();
            }
        }

        public synchronized void sale() {
            if (num <= 0) {
                try {
                    System.out.println("目前缺货！");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("开始消费");
                System.out.println(Thread.currentThread().getName() + " 当前数量 ：" + --num);
                this.notifyAll();
            }
        }
    }

    class Producter implements Runnable {
        private Clerk clerk;

        public Producter (Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {

            for (int i = 0; i < 20; i++) {
                clerk.get();
            }

        }
    }

    class Consumer implements Runnable {

        private Clerk clerk;

        public Consumer (Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {

            for (int i = 0; i < 20; i++) {

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clerk.sale();
            }

        }
    }



    @Test
    public void test03() {

        ThreadDemo02 threadDemo02 = new ThreadDemo02();

        FutureTask<Integer> task = new FutureTask<Integer>(threadDemo02);

        new Thread(task).start();

        try {
            Integer sum = task.get();

            System.out.println(sum);
            System.out.println("==========================");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    class ThreadDemo02 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {

            Integer sum = 0;
            for (int i = 0; i <= 1000; i++) {
                sum += i;
            }
            return sum;
        }
    }

    class Ticket implements Runnable {
        @Override
        public void run() {

        }
    }





}
