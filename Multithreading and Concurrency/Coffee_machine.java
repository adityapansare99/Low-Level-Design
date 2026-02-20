
class CoffeeMachine {

    private boolean IsReady = false;

    public synchronized void makeCoffee() throws InterruptedException {
        while (IsReady) {
            wait();
        }

        System.out.println("Coffee making started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        IsReady = true;
        System.out.println("Coffee is ready");
        notify();
    }

    public synchronized void drinkCoffee() throws InterruptedException {
        while (!IsReady) {
            wait();
        }

        System.out.println("Started to drink the coffee");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        IsReady = false;
        System.out.println("Coffee finished");
        notify();
    }
}

public class Coffee_machine {

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();

        Thread Producer = new Thread(() -> {
            while (true) {
                try {
                    machine.makeCoffee();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });

        Thread Consumer=new Thread(()->{
            while(true){
                try {
                    machine.drinkCoffee();
                } catch (InterruptedException e) {
                    
                }
            }
        });

        Producer.start();
        Consumer.start();
    }
}
