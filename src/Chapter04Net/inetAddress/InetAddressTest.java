package Chapter04Net.inetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

    public static void main(String[] args) throws UnknownHostException {
        if (args.length > 0) {
            String host=args[0];
            InetAddress[] allByName = InetAddress.getAllByName(host);
            for (InetAddress inet : allByName) {
                System.out.println(inet);
            }
        }
        else
        {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
            System.out.println(localHost.getHostName());
        }
    }
}
