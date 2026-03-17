class service1 {
    public void method1() {
        System.out.println("service1 method1");
    }
}

class service2 {
    public void method2() {
        System.out.println("service2 method2");
    }
}

class services{
    service1 s1;
    service2 s2;

    public void setS1(service1 s1) {
        this.s1 = s1;
    }

    public void setS2(service2 s2) {
        this.s2 = s2;
    }

    void method3() {
        s1.method1();
        s2.method2();
    }
}

public class Setter {
    public static void main(String[] args) {
        services service = new services();
        service.setS1(new service1());
        service.setS2(new service2());
        service.method3();
    }
}
