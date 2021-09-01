/**
 * Developed by András Ács (acsandras@gmail.com)
 * Zealand / www.zealand.dk
 * Licensed under the MIT License
 * 30/08/2021
 */
package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void Activate(){
        try {
            Socket socket = new Socket("localhost", 8080); //Lytter på port 8080
            String ip = socket.getLocalAddress().getHostAddress(); //gemmer IP fra denne enhed

            //opsætter Data I/O Streams
            DataInputStream inputStream = new DataInputStream(socket.getInputStream()); //Alt indadgående trafik
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream()); //Alt udadgående trafik

            Scanner scanner = new Scanner(System.in);
            System.out.println("Skriv din besked her :");
            while(true) {
                outputStream.writeUTF(scanner.nextLine() + " :From IP " + ip); //Sender besked + IP
                outputStream.flush(); //sender scanner text via outputsteam
                System.out.println(inputStream.readUTF());

            }



            //socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
