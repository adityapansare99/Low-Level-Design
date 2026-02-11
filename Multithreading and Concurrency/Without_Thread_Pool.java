
class RideService {

    public void FindRide() {
        try {
            Thread.sleep(1000);
            System.out.println("Ride Found");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

public class Without_Thread_Pool {

    public static void main(String[] args) {
        RideService rideService1 = new RideService();
        RideService rideService2 = new RideService();

        rideService1.FindRide();
        rideService2.FindRide();

    }
}
