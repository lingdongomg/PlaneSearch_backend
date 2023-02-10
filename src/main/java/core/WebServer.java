package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebServer��Ŀ����Ҫ��
 * ��������࣬����WebServer����
 * ���������������֮�󣬿���ʹ�����������������
 * ��ô��д��������������Ťȥ������Ӧ��Ч��
 *
 * @author xzy
 */
public class WebServer {
    private ServerSocket server;
    private ExecutorService threadPoll;
    public static Map<String, String> mimeMapping = new HashMap<>();

    static {
        mimeMapping.put("html", "text/html");
        mimeMapping.put("js", "application/javascript");
        mimeMapping.put("ico", "image/x-icon");
        mimeMapping.put("css", "text/css");
        mimeMapping.put("woff", "font/woff");
        mimeMapping.put("jpg", "image/jpeg");
    }

    public WebServer() {
        try {
            System.out.println("��������������");
            server = new ServerSocket(80);
            threadPoll = Executors.newFixedThreadPool(30);
            System.out.println("�������������");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                System.out.println("�ȴ��ͻ�������");
                Socket socket = server.accept();
                System.out.println("һ���ͻ���������");
                ClientHandler handler = new ClientHandler(socket);
                threadPoll.execute(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebServer s = new WebServer();
        s.start();
    }

}
