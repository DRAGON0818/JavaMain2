package Chapter04Net.threadServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadEchoServer {
    public static void main(String[] args) throws IOException {
        try(ServerSocket s = new ServerSocket(8189)) {
            int i=1;
            while(true){
                Socket accept = s.accept();
                System.out.println("Swaping " + i++);
                Runnable threadEchoHandler = new ThreadEchoHandler(accept);
                Thread thread = new Thread(threadEchoHandler);
                thread.start();
            }
        }
    }
}

class ThreadEchoHandler implements Runnable {

    private Socket comming;

    public ThreadEchoHandler(Socket socket) {
        this.comming = socket;
    }

    @Override
    public void run() {

        try (InputStream in=comming.getInputStream();
        OutputStream out=comming.getOutputStream()){
            Scanner scanner = new Scanner(in, "UTF-8");
            /*while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }*/
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(out), true);
            printWriter.println("Hello,Enter BYE to exit!");

            boolean done=false;

            while (!done && scanner.hasNext()) {
                String s = scanner.nextLine();
                printWriter.println("Echo :" + s);
                if("BYE".equals(s.trim())) {
                    done=false;
                    comming.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
