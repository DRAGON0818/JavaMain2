package Chapter04Net.InterruptibleSocketTest;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 套接字并不能直接通过interrupt来解除阻塞。
 * 而是需要通过java.nio的SocketChannel类来处理，当线程发生中断的时候，并不会陷入阻塞，而是会抛出异常.
 * <h1>如果是阻塞套接字  ，如果发生中断，只能够等待服务器主动关闭连接<h1/>
 */

public class InterruptibleSocketTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new InterrupibleSocketFrame();
            frame.setTitle("InterrupibleSocketTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class InterrupibleSocketFrame extends JFrame{
    private Scanner in;
    final private JButton interrupibleButton;
    final private JButton blockingButton;
    private JButton cancelButton;
    final private JTextArea messages;
    private Thread connectThread;

    public InterrupibleSocketFrame(){
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);

        final int TEXT_ROWS=20;
        final int TEXT_COLUMNS=60;
        messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(messages));

        interrupibleButton = new JButton("Interrupible");
        blockingButton = new JButton("Blocking");

        northPanel.add(interrupibleButton);
        northPanel.add(blockingButton);

        interrupibleButton.addActionListener(e->{
            interrupibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread=new Thread(()->{
                try {
                    connetInterrupiblly();
                } catch (IOException e1) {
                    messages.append("\nInterrupibleSocketTest.connectInterrupibly : " + e);
                }
            });
            connectThread.start();
        });

        blockingButton.addActionListener(e->{
            interrupibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread=new Thread(()->{
                try {
                    connetBlocking();
                } catch (IOException e1) {
                    messages.append("\nInterrupibleSocketTest.connectBlocking : " + e1);
                }
            });
            connectThread.start();
        });

        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);
        northPanel.add(cancelButton);

        cancelButton.addActionListener(e->{
            connectThread.interrupt();
            cancelButton.setEnabled(false);
        });
        TestServer server = new TestServer();
        new Thread(server).start();
        pack();
    }

    public void connetInterrupiblly() throws IOException {
        messages.append("Interrupible:\n");
        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8190))) {
            in = new Scanner(channel, "UTF-8");
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNext()) {
                    String s = in.nextLine();
                    messages.append(s);
                    messages.append("\n");
                }
            }
        }finally {
            EventQueue.invokeLater(()->{
                messages.append("Channel closed!\n");
                interrupibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    public void connetBlocking() throws IOException {
        messages.append("Blocking :\n");
        try (Socket s = new Socket("localhost", 8190)) {
            in = new Scanner(s.getInputStream(), "UTF-8");
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNext()) {
                    String s1 = in.nextLine();
                    messages.append(s1);
                    messages.append("\n");
                }
            }
        }finally {
            EventQueue.invokeLater(()->{
                messages.append("Socket closed\n");
                interrupibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    class TestServer implements Runnable{

        @Override
        public void run() {
            try (ServerSocket s = new ServerSocket(8190)) {
                while (true) {
                    Socket accept = s.accept();
                    Runnable testServerHandler = new TestServerHandler(accept);
                    Thread t = new Thread(testServerHandler);
                    t.start();
                }

            }catch (IOException e){
                messages.append("\nTestServer.run: " + e);
            }
        }
    }

    class TestServerHandler implements Runnable{

        private Socket incoming;
        private int counter;

        private TestServerHandler(Socket incoming){
            this.incoming=incoming;
        }

        @Override
        public void run() {
            try {
                try {
                    OutputStream outputStream = incoming.getOutputStream();
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
                    while (counter < 100) {
                        counter++;
                        if (counter <= 10) out.println(counter);
                        Thread.sleep(100);
                    }
                } finally {
                    incoming.close();
                    messages.append("Closing server\n");
                }
            } catch (Exception e) {
                messages.append("\nTestServerHandler.run : " + e);
            }
        }
    }
}


