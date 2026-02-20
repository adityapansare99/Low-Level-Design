import java.util.*;

class Submission{
    private static int idCounter = 1; 
    private final int submissionId;
    private final String userName;

    public Submission(String userName) {
        this.userName = userName;
        this.submissionId = idCounter++; 
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public String getUserName() {
        return userName;
    }
}

public class Code_Judge {
    private final int maxSize=5;
    private Queue<Submission> submissions=new LinkedList<>();

    public synchronized void submit(Submission submission) throws InterruptedException{
        while(submissions.size()==maxSize){
            System.out.println("Queue full. " + submission.getUserName() + " is waiting to submit.");
            wait();
        }

        submissions.offer(submission);
        System.out.println("" + submission.getUserName() + " submitted code: #" + submission.getSubmissionId());
        notifyAll();
    }

    public synchronized Submission consume(String judgeName) throws InterruptedException {
        while(submissions.isEmpty()){
            System.out.println("△ " + judgeName + " waiting for submissions...");
            wait();
        }

        Submission sub = submissions.poll();
        System.out.println(judgeName + " started evaluating submission #" +
                           sub.getSubmissionId() + " from " + sub.getUserName());

        notifyAll();
        return sub;
    }

    public static void main(String[] args) {
    }
}
