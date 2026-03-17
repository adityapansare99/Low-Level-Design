class service1{
   public service1(){

   } 

   public void method(){

   }
}

class service2{
   public service2(){

   } 

   public void method(){

   }
}

public class Problem {
    private final service1 service1=new service1();
    private final service2 service2=new service2();
    
    public void method(){
        service1.method();
        service2.method();
    }
}
