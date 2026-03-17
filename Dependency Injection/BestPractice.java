interface NotificationService {
    void send(String message);
}


class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}

class UserService {
    private final NotificationService notificationService;

    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void register(String user) {
        System.out.println("User registered: " + user);
        notificationService.send("Welcome " + user);
    }
}

public class BestPractice {
    public static void main(String[] args) {
        NotificationService service = new EmailNotificationService();

        UserService userService = new UserService(service);

        userService.register("raj");
    }   
}
