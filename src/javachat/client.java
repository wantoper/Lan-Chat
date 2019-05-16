package javachat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		

		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
				ServerSocket ss = new ServerSocket(8888);
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
				Socket s = new Socket("127.0.0.1",8889);
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
