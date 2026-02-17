
class BankAccount {

    private final String name;
    private double balance;

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        balance -= amount;
    }

}

class TransferAmount implements Runnable {

    private final BankAccount from;
    private final BankAccount to;
    private double amount;

    public TransferAmount(BankAccount a, BankAccount b, double amount) {
        from = a;
        to = b;
        this.amount = amount;
    }

    @Override
    public void run() {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName()
                    + " locked " + from.getName());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            synchronized (to) {
                System.out.println(Thread.currentThread().getName()
                        + " locked " + to.getName());
                from.withdraw(amount);
                to.deposit(amount);
                System.out.println("Transferred " + amount + " from " +
                                   from.getName() + " to " + to.getName());
            }
        }
    }

}

public class Deadlock {
    public static void main(String[] args) {
        BankAccount a = new BankAccount("A", 1000);
        BankAccount b = new BankAccount("B", 1000);

        Thread t1=new Thread(new TransferAmount(a, b, 100), "T1");
        Thread t2=new Thread(new TransferAmount(b, a, 100), "T2");
        t1.start(); t2.start();

        try {
            t1.join();
            t2.join();
            System.out.println("Amount transferred successfully");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
