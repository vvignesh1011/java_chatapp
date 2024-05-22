package chatapp;

import java.util.Scanner;

public class AppMain {
public static void main(String args[]) {
	Scanner sc=new Scanner(System.in);
	System.out.println("enter '1' for run server\n'2' for run client\n'3' for run both ");
	int in=sc.nextInt();
	if(in == 1)
		new AppServer();
	if(in==2)
		new AppClient();
	if(in==3) {
		new AppServer();
		new AppClient();
		}
	
	
}
}
