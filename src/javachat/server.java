package javachat;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {

	public static void main(String[] args) throws IOException {
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
				ServerSocket ss = new ServerSocket(8889);
				Socket s = ss.accept();
				while(true){
					DataInputStream dis = new DataInputStream(s.getInputStream());
					System.out.println("接收到消息:"+dis.readUTF());
					
				}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
		
		t2.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		Thread t1 = new Thread(new Runnable() {
		
			@Override
			public void run() {
				try{
				Socket s = new Socket("127.0.0.1",8888);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				Scanner sc = new Scanner(System.in);
				while(true){
						dos.writeUTF(sc.next());		
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		t1.start();

	}

}
