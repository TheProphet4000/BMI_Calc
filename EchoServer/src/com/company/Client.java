package src.com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void Activate(){
        try {

            //-------------------- SOCKET--------------------
            Socket socket = new Socket("localhost", 8080); //Lytter på port 8080
            String ip = socket.getLocalAddress().getHostAddress(); //gemmer IP fra denne enhed

            //opsætter Data I/O Streams
            DataInputStream inputStream = new DataInputStream(socket.getInputStream()); //Alt indadgående trafik
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream()); //Alt udadgående trafik
            //---------------------------------------------

            //Handles user data and calcs
            Scanner height = new Scanner(System.in);
            System.out.println("Please type in your Height (in M) :");

            Scanner weight = new Scanner(System.in);
            System.out.println("Thank you, and now your Weight (in Kg) ");

            //BMI = vægt(kg) / højde(m)2
            float bmi = weight / (height * height);

            //Sends everything
            while(true) {
                outputStream.writeUTF(" : sent from IP " + ip + " : User BMI is " + bmi); //Sender besked + IP
                outputStream.flush(); //sender scanner text via outputsteam
                System.out.println(inputStream.readUTF());
            }
            //socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
