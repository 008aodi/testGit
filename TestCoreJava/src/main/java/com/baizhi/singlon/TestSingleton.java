package com.baizhi.singlon;

public class TestSingleton {
    public static void main(String[] args) {
        ClassA a1 = ClassA.newInstance();
        ClassA a2 = ClassA.newInstance();
        System.out.println(a1 == a2);
    }
}
//饿汉式
     class ClassA{
        private static final ClassA instance = new ClassA();
        public static ClassA newInstance(){
            return instance;
        }
        private ClassA(){}
    }
    //懒汉式
    class ClassB{
    private static ClassB instance = null;
    public static ClassB newInstance(){
        if(instance==null){
            instance=new ClassB();

        }
        return instance;
    }
    private ClassB(){}
    }

