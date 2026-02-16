import java.util.concurrent.Semaphore;

class LoginTry{
    private final Semaphore slot;

    public LoginTry(int maxDevices) {
        slot =new Semaphore(maxDevices);
    }

    public boolean login(String user) throws InterruptedException {
        System.out.println("trying to login "+user);

        if(slot.tryAcquire()) {
            System.out.println("Login successfull for "+user);
            return true;
        }
        else {
            System.out.println("Login failed for "+user);
            return false;
        }
    }

    public void logout(String user) {
        System.out.println(user + " logging out.");
        slot.release(); 
    }
    
}

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        LoginTry account = new LoginTry(2);

        Thread u1 = new Thread(() -> trySession(account, "User-A"));
        Thread u2 = new Thread(() -> trySession(account, "User-B"));
        Thread u3 = new Thread(() -> trySession(account, "User-C"));

        u1.start(); u2.start();
        Thread.sleep(100);  
        u3.start();

        u1.join(); u2.join(); u3.join();
    }

    private static void trySession(LoginTry acc, String name) {
        try {
            if (acc.login(name)) {
                Thread.sleep(500); 
                acc.logout(name);
            }
        } catch (InterruptedException ignored) { }
    }
}
