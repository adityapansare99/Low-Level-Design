class service1{
    void method1(){
        System.out.println("service1 method1");
    }
}

class service2{
    void method2(){
        System.out.println("service2 method2");
    }
}

class services{
    service1 s1;
    service2 s2;

    services(service1 s1, service2 s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    void method3(){
        s1.method1();
        s2.method2();
    }
}

public class Solution {
    public static void main(String[] args) {
        services service=new services(new service1(), new service2());
        service.method3();
    }
}
