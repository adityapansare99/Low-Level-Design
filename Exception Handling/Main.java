class CustomerNotPlusException extends Exception {
    public CustomerNotPlusException(String userId) {
        super("User " + userId + " does not have Plus access.");
    }
}

class CourseService {
    public void accessCourse(String userId) throws CustomerNotPlusException {
        if (!hasAccess(userId)) {
            throw new CustomerNotPlusException(userId);
        }
    }

    private boolean hasAccess(@SuppressWarnings("unused") String userId) {
        return false;  
    }
}

public class Main {
    public static void main(String[] args) {
        CourseService courseService = new CourseService();
        String userId = "user123";
        try {
            courseService.accessCourse(userId);
        } catch (CustomerNotPlusException e) {
            System.out.println("Access denied: " + e.getMessage());
        }
    }
}
