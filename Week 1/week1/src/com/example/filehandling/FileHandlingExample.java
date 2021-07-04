package com.example.filehandling;

import org.example.calculator.Calculator;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;

public class FileHandlingExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        File file  = new File("sample.txt");
//        file.createNewFile();

//        writeToFile();

//        readToFile();

//        writeToFile2();

//        readToFile2();

//        writeObject();

//        readObject();

        readOverNetwork();

    }

    public static void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("sample.txt", true);
        String text = "Hello I am fine.";
        fileWriter.write(text);

        fileWriter.close();

    }

    public static void readToFile() throws IOException {
        FileReader fileReader = new FileReader("sample.txt");

        int ch;
        while((ch=fileReader.read()) != -1) {
            System.out.print(Character.toChars(ch));
        }

        fileReader.close();
    }

    public static void writeToFile2() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("sample.txt", true);

        String text = "Hello Everyone";

        fileOutputStream.write(text.getBytes());

        fileOutputStream.close();
    }

    public static void readToFile2() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("sample.txt");

        byte[] buffer = new byte[1024];
        int ch;
        int counter=0;
        while((ch = fileInputStream.read(buffer)) != -1) {
            System.out.println(Arrays.toString(buffer));
//            for(byte b: buffer) {
//                System.out.print(Character.toChars(b));
//                if(b==0){
//                    break;
//                }
//            }
//            counter+=1;
//            System.out.println();
//            System.out.println("counter value: " + counter);
//            System.out.println();
        }

        fileInputStream.close();
    }

    public static void writeObject() throws IOException {
        Pet dog = new Pet("ABC", "white");
        Pet dog1 = new Pet("DEF", "black");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("sample.txt", false));

        objectOutputStream.writeObject(dog);

        objectOutputStream.writeObject(dog1);

        objectOutputStream.close();
    }

    public static void readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("sample.txt"));

        Pet pet = (Pet)objectInputStream.readObject();

        System.out.println(pet);

        pet = (Pet)objectInputStream.readObject();

        System.out.println(pet);

        objectInputStream.close();

        Calculator calculator = new Calculator();
        int sum = calculator.sum(1,2);
    }

    public static void readOverNetwork() throws IOException {
        URL url = new URL("https://ocw.mit.edu/ans7870/6/6.006/s08/lecturenotes/files/t8.shakespeare.txt");

        BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());

        byte[] buffer = new byte[1024];

        FileOutputStream fileOutputStream = new FileOutputStream("sample.txt");

        int ch;
        while((ch=bufferedInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer);
        }

        fileOutputStream.close();
        bufferedInputStream.close();
    }
}
