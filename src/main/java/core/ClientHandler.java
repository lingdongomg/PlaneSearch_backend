package core;
/**
 * ���ڴ���ͻ��˽�����ҵ����
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
					System.out.println("��Դ·����ȷ");
					response.setEntity(file);
				}else {
					System.out.println("������Դ������");
					File notFoundFile=new File("./myweb"+"/404.html");
					response.setStatusCode(404);
					response.setStatusReason("Not Found");
					response.setEntity(notFoundFile);
				}
			}
			response.flush();
		}catch (EmptyRequestException e) {
			System.out.println("������������һ��������");
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
