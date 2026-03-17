interface service{
    void method();
}

class service1 implements service{
    @Override
    public void method(){};
}

class service2 implements service{
    @Override
    public void method(){};
}

class services{
    service s1;
    service s2;

    services(service s1, service s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    void method3(){
        s1.method();
        s2.method();
    }
}

public class Interface {
    public static void main(String[] args) {
        services service=new services(new service1(), new service2());
        service.method3();
    }
}
