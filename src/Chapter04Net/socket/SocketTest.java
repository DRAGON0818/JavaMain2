package Chapter04Net.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        try (Socket s = new Socket("time-a.nist.gov", 13);
             Scanner in = new Scanner(s.getInputStream(),"UTF-8")
        ) {
            while (in.hasNext()) {
                String s1 = in.nextLine();
                System.out.println(s1);
            }
        }

        //因为访问量较大的主机名通常为了负载均衡，存在多个主机地址
        InetAddress[] allByName = InetAddress.getAllByName("www.baidu.com");
        System.out.println(Arrays.toString(allByName));


        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        InetAddress[] localhosts = InetAddress.getAllByName("localhost");
        System.out.println(Arrays.toString(localhosts));

    }
}
