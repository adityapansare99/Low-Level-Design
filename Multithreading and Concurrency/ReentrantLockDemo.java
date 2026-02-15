import java.util.concurrent.locks.ReentrantLock;

class TicketBooking{
    private final ReentrantLock lock=new ReentrantLock();
    private int availableTickets=10;

    public void TicketBooking1(String user){
        System.out.println("Ticket Booking for "+user);
        lock.lock();

        try{
            System.out.println(user+"acquired lock");
            if(availableTickets>0){
                availableTickets--;
                System.out.println("Available tickets left "+availableTickets);
            }

            else{
                System.out.println("No tickets available");
            }
        }

        finally{
            System.out.println(user+"released lock");
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        TicketBooking ticketBooking=new TicketBooking();
        Thread t1=new Thread(()->ticketBooking.TicketBooking1("user1"));
        Thread t2=new Thread(()->ticketBooking.TicketBooking1("user2"));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}