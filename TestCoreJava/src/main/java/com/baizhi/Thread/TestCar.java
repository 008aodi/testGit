package com.baizhi.Thread;

public class TestCar {
    private String name;
    public static void main(String[] args) throws InterruptedException {
        Thread eastTotWes = new Thread(new EastToWest());
        Thread westToEast = new Thread(new WestToEast());
        Thread northToSouth = new Thread(new NorthToSouth());
        Thread southToNorth = new Thread(new SouthToNorth());
        Thread red = new Thread(new Red(),"红灯");
        Thread greed  = new Thread(new Greed(),"绿灯");

        eastTotWes.start();
        westToEast.start();
        northToSouth.start();
        southToNorth.start();
        red.start();
        greed.start();
        eastTotWes.join();
        westToEast.join();
        northToSouth.join();
        southToNorth.join();
        red.join();
        greed.join();
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println("程序结束");

    }
}

class EastToWest implements Runnable{

    public void run() {
        System.out.println("eastTotWes通行");
    }
}
class WestToEast implements Runnable{

    public void run() {
        System.out.println("westToEast通行");
    }
}
class NorthToSouth implements Runnable{

    public void run() {
        System.out.println("northToSouth通行");
    }
}
class SouthToNorth implements Runnable{

    public void run() {
        System.out.println("southToNorth通行");
    }
}
class Red implements Runnable{

    public void run() {
        System.out.println("红灯开始所有车辆禁行");
    }
}
class Greed implements Runnable {

    public void run(){
        System.out.println("绿灯开始所有车辆通行");

    }
}
