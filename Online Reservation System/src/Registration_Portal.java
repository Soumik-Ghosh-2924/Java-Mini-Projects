import java.util.Scanner;
public class Registration_Portal 
{ 
	private static boolean s[] = new boolean[101];
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.println(" \n\n1. View Seat Arrangements. ");
			System.out.println("2. Reserve your seat. ");
			System.out.println("3. Cancel Your Reservation.");
			System.out.println("4. Wrong choice.");
			
			int ch;
			System.out.println("Enter your choice :");
			ch=sc.nextInt();
			
			switch(ch)
			{
				case 1:
					view_seat_status();
					break;
					
				case 2:
					reserve_seat();
					break;
				
				case 3:
					cancel_reservation();
					break;
					
				case 4:
					System.exit(0);
				
				case 5:
					System.out.println("Entered wrong choice.");
					break;
			}
					
		}
	}
	
	private static void view_seat_status()
	{
		System.out.println("Current seat status .");
		for(int i=1; i<s.length;i++)
		{
			if(s[i])
			{
				System.out.print((i)+" - X      ");
			}
			else
			{
				System.out.print((i)+" - empty  ");
			}

			if((i)%10==0)
			{
				System.out.println();
			}
		}
		
	}
	
	private static void reserve_seat()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the seat number u want to reserve :");
		int sn=sc.nextInt();
		if(s[sn]==true)
		{
			System.out.println("This seat is already reserved. \n\n");
		}
		else if(sn<1||sn>101)
		{
			System.out.println("Wrong choice of seat.\n\n");
		}
		else
		{
			s[sn]=true;
			System.out.println("Your seat is reserved. \n\n");
		}
		
	}
	
	private static void cancel_reservation()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the seat number u have been allotted to : ");
		int sn=sc.nextInt();
		if(sn<1||sn>101)
		{
			System.out.println("Wrong choice of seat. \n\n");
		}
		else
		{
			s[sn]=false;
			System.out.println("Your seat reservation has been cancelled. \n\n");
		}
		
	}
}
		