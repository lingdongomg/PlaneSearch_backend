package core;
/**
 * 用于处理客户端交互的业务类
 * @author xzy
 *
 */
import HTTP.EmptyRequestException;
import HTTP.HttpRequest;
import HTTP.HttpResponse;
import servlet.SearchServlet;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable{
	private Socket socket;
	public ClientHandler(Socket socket) {
		this.socket=socket;
	}
	public void run() {
		try {
			HttpRequest request=new HttpRequest(socket);
			HttpResponse response=new HttpResponse(socket);
			String path=request.getRequestUri();
			System.out.println("path:"+path);
			if(path.equals("/searchTickets")) {
				SearchServlet searchController = new SearchServlet();
				searchController.service(request, response);
			}else {
				File file=new File("./myweb"+path);
				if(file.exists()) {
					System.out.println("资源路径正确");
					response.setEntity(file);
				}else {
					System.out.println("请求资源不存在");
					File notFoundFile=new File("./myweb"+"/404.html");
					response.setStatusCode(404);
					response.setStatusReason("Not Found");
					response.setEntity(notFoundFile);
				}
			}
			response.flush();
		}catch (EmptyRequestException e) {
			System.out.println("服务器接受了一个空请求");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
