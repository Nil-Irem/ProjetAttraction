package test;
import java.util.Scanner;



public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Menu.menuPrincipal();
		
		
		
		
		
		

	}
	
	
	public static int saisieInt(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();

	}
	public static double saisieDouble(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextDouble();
	}
	public static String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}


}
