package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebServer项目的主要类
 * 启动这个类，开启WebServer程序
 * 这个程序功能是启动之后，可以使用浏览器来发送请求
 * 我么编写代码对浏览器的七扭去做出响应的效果
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
            System.out.println("服务器正在启动");
            server = new ServerSocket(80);
            threadPoll = Executors.newFixedThreadPool(30);
            System.out.println("服务器启动完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                System.out.println("等待客户端连接");
                Socket socket = server.accept();
                System.out.println("一个客户端连接了");
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
