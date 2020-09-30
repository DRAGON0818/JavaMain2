package Chapter04Net.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        try(ServerSocket s=new ServerSocket(8189)){
            try(Socket coming=s.accept()){
                InputStream inputStream = coming.getInputStream();
                OutputStream outputStream = coming.getOutputStream();

                try (Scanner in = new Scanner(inputStream, "UTF-8")) {
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
                    printWriter.println("Hello,Enter BYE to exit!");

                    boolean done=false;
                    while (!done && in.hasNext()) {
                        String s1 = in.nextLine();
                        printWriter.println("Echo :" + s1);
                        if("BYE".equals(s1.trim())) done=true;
                    }
                }
            }
        }
    }
}
