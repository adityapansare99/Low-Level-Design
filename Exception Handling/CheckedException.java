import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckedException {
    public void readFile(String filepath) throws IOException {
        FileReader reader=new FileReader(filepath);
        try (BufferedReader br = new BufferedReader(reader)) {
            String line=br.readLine();
            System.out.println(line);
        }
    }
    public static void main(String[] args) {
        CheckedException reader = new CheckedException();
        try {
            reader.readFile("somefile.txt"); 
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }  
    }
}
