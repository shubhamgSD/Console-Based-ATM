// Name Shubham Gupta
// Training at Jaipur
// Student Id is  8645-R51WV8 
//Batch STP-2016-JPR-CJ-JUN-1
// Project for ATM 

import java.util.Scanner;
import java.sql.*;
// CLASS FOR WITHDRAW BALANCE
class Withdraw
{
	int AtmBal;
	int CardBal;
	int[] arr = new int[4];
	
 void enterAmount(String CardNumber , Connection con)throws Exception
 {   
     Scanner sc1 = new Scanner(System.in);
	 Scanner sc2 = new Scanner(System.in);
	 System.out.print("\nENTER AMOUNT: ");
	 int inputBal = sc1.nextInt();
	 int tInputBal = inputBal;
	 
	System.out.print("DO YOU WANT TO TAKE RECEIPT (YES/NO): ");
	String receipt = sc2.nextLine();
	 
	 String p = "Select AtmBal , CardBal from acc where CardNumber=?";
	 PreparedStatement pst = con.prepareStatement(p);
	 pst.setString(1,CardNumber);
	 ResultSet rs = pst.executeQuery();
	 
	 if(rs!=null && rs.next())
	 {
		 CardBal = rs.getInt("CardBal");
		 AtmBal = rs.getInt("AtmBal");
	}
	pst.close();
	if(inputBal>CardBal || inputBal>AtmBal )
	{
		System.out.println("Sorry !");
	}
	else
	{
		if(inputBal>=1000)
			 {
				 arr[0]=inputBal/1000;
				 inputBal=inputBal%1000;
			 }
			 if(inputBal>=500)
			 {
			     arr[1]=inputBal/500;
				 inputBal=inputBal%500;
			 }
			 if(inputBal>=100)
			 {
				 arr[2]=inputBal/100;
				 inputBal=inputBal%100;
			 }
			 if(inputBal>=50)
			 {
				 arr[3]=inputBal/50;
				 inputBal=inputBal%50;
			 }
			 if(inputBal==0)
			{   CardBal=CardBal-tInputBal;
		        AtmBal=AtmBal-tInputBal;
				String q = "Update acc SET CardBal=? WHERE CardNumber=?";
				String r = "Update acc SET AtmBal=?";
	            PreparedStatement pst1 = con.prepareStatement(q);
				PreparedStatement pst2 = con.prepareStatement(r);
	            pst1.setInt(1,CardBal);
	            pst1.setString(2,CardNumber);
				pst2.setInt(1,AtmBal);
	            int status1 = pst1.executeUpdate();
				int status2 = pst2.executeUpdate();
	            if(status1>0 && status2>0)
	            {
		           //System.out.println("Success updated! ");
	            }
	            else
	            {
		           //System.out.println("Failed updated! ");
	            }
	            pst1.close();
				pst2.close();
				String receipt2 = "YES";
               if(receipt.equalsIgnoreCase(receipt2))
			   {
				   printDetail(CardBal,tInputBal,CardNumber);
			   }	
               else
			   {
				   System.out.println("\nTRANSACTION SUCCESSFUL! ");
			   }				   
			}
			else
			{
				System.out.println("\nENTER CORRECT AMOUNT!");
			}
	}	
	
}
// Function for Print the detail of Account  
void printDetail(int CardBal , int tInputBal,String CardNumber)
{
	System.out.println("\n\n     STATE BANK OF INDIA");
	   System.out.println("  _________________________");
	   System.out.println("\n  DATE     TIME     ATM Id\n\n");
	   System.out.print("  CARD NUMBER   XXXX");
	   for(int i=4;i<8;i++)
	   {
		   System.out.print(CardNumber.charAt(i));
	   }
	   System.out.println("");
	   System.out.println("  WITHDRAWAL    RS.        "+tInputBal);
	   System.out.println("  AVAIL BAL.    RS.        "+CardBal);
	   System.out.println("\n  MAKE YOUR PURCHASES WITH STATE BANK \n  DEBIT CARDS. EARN FREEDOM POINTS AND\n  GET FREE GIFTS. CALL 18002098500. ");
       System.out.println("\n  Visit us at www.statebankofindia.com");
       System.out.println("  ____________________________________");
	   System.out.println("\n  TRANSACTION SUCCESSFUL!\n  Thank You!");
}

}
 //CLASS FOR BALANCE ENQUIRIE 
class EnquirieB
{
	void BalEnquirie(String CardNumber , Connection con) throws Exception
	{
		String p = "Select CardBal FROM acc WHERE CardNumber=?";
		PreparedStatement pst = con.prepareStatement(p);
		pst.setString(1,CardNumber);
		ResultSet rs = pst.executeQuery();
		if(rs!=null && rs.next())
		{
			String blce = rs.getString("CardBal");
			System.out.println("\n\n     STATE BANK OF INDIA");
	   System.out.println("  _________________________");
	   System.out.println("\n  DATE     TIME     ATM Id\n\n");
	   System.out.println("  BAL. INQUIRY       ");
	   System.out.print("  CARD NUMBER   XXXX");
	   for(int i=4;i<8;i++)
	   {
		   System.out.print(CardNumber.charAt(i));
	   }
	   System.out.println("");
	   System.out.println("  AVAIL BAL.    RS.        "+blce);
	   System.out.println("\n  MAKE YOUR PURCHASES WITH STATE BANK \n  DEBIT CARDS. EARN FREEDOM POINTS AND\n  GET FREE GIFTS. CALL 18002098500. ");
       System.out.println("\n  Visit us at www.statebankofindia.com");
       System.out.println("  ____________________________________");
		}
		pst.close();
		
	}
}
// Class for Balance Transfer
class Transfer
{
	void transfer(String CardNumber , Connection con) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		String O_CardNumber = new String();
		
		System.out.print("ENTER CARD NUMBER: ");
		O_CardNumber = sc.nextLine();
		
		System.out.print("ENTER AMOUNT: ");
		Scanner sci = new Scanner(System.in);
		int money = sci.nextInt();
		int CardBal=0;
		
	 String p = "Select CardBal from acc where CardNumber=?";
	 PreparedStatement pst = con.prepareStatement(p);
	 pst.setString(1,CardNumber);
	 ResultSet rs = pst.executeQuery();
	 
	 if(rs!=null && rs.next())
	 {
		 CardBal = rs.getInt("CardBal");
	 }
	 pst.close();
	
		
		
		String temp0=CardNumber.substring(0,6);
		String temp1=O_CardNumber.substring(0,6);
		
		if(temp0.equals(temp1) && !(CardNumber.equals(O_CardNumber)))
		{
			if(temp0.equals(temp1))
			{
				if(money <= CardBal)
				{
					 String q = "Select CardBal from acc where CardNumber=?";
	                 PreparedStatement pst2 = con.prepareStatement(q);
	                 pst2.setString(1,O_CardNumber);
	                 ResultSet rs2 = pst2.executeQuery();
					 int CardBal1 =0;
	 
	                 if(rs2!=null && rs2.next())
	                 {
		                CardBal1 = rs2.getInt("CardBal");
	                 }
					 pst2.close();
					 
                    CardBal1 = CardBal1 + money;					
					CardBal = CardBal-money;
					//update
					
				String r= "Update acc SET CardBal=? WHERE CardNumber=?";
				String s = "Update acc SET CardBal=? WHERE CardNumber=?";
				PreparedStatement pst3 = con.prepareStatement(r);
				PreparedStatement pst4 = con.prepareStatement(s);
	            pst3.setInt(1,CardBal1);
	            pst3.setString(2,O_CardNumber);
				
	            pst4.setInt(1,CardBal);
	            pst4.setString(2,CardNumber);
				
				int status1 = pst3.executeUpdate();
				int status2 = pst4.executeUpdate();
	            if(status1>0 && status2>0)
	            {
		           //System.out.println("Success updated! ");
	            }
	            else
	            {
		           //System.out.println("Failed updated! ");
	            }
	            pst3.close();
				pst4.close();
				
				}
				else
				{
					System.out.println("YOUR BANK BALANCE IS NOT ENOUGH TO TRANSFER!");
				}
			}
			else
			{
			System.out.println("ENTERED CARD NUMBER IS WRONG :");	
			}
		}
		else
		{
		System.out.println("ENTERED CARD NUMBER IS WRONG :");	
		}
		
	}
	
}
// Class for Change the Pin of Particular Account
class ChangePin
{
	void changePin(String CardNumber , String Pin , Connection con )throws Exception
	{
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<3;i++)
		{
		System.out.print("\nENTER CURRENT PIN: ");
		String pin = sc.nextLine();
		System.out.print("ENTER NEW PIN [4 DIGIT] ");
		String new_Pin1 = sc.nextLine();
		System.out.print("ENTER NEW PIN AGAIN: ");
		String new_Pin2 = sc.nextLine();
		
		int len = new_Pin1.length();
		
		if(len==4)
		{
		if(pin.equals(Pin))
		{
			if(new_Pin1.equals(new_Pin2))
			{
				
				String r= "Update acc SET Pin=? WHERE CardNumber=?";
				PreparedStatement pst3 = con.prepareStatement(r);
	            pst3.setString(1,new_Pin1);
	            pst3.setString(2,CardNumber);
				
				int status1 = pst3.executeUpdate();
	            if(status1>0)
	            {
		           //System.out.println("Success updated! ");
	            }
	            else
	            {
		           //System.out.println("Failed updated! ");
	            }
	            pst3.close();
				break;
				
			}
			else
			{
				System.out.println("\nYOUR NEW PINS ARE NOT SAME");
				if(i==2)
				{
					System.out.println("\nATTEMPT LIMIT EXCEED");
					break;
				}
			}
		}
		else
		{
			System.out.println("\nYOU ENTERED WRONG CURRENT PIN !");
			if(i==2)
				{
					System.out.println("\nATTEMPT LIMIT EXCEED");
					break;
				}
		}
		}
		else
		{
			System.out.println("\nPLEASE ENTER 4 DIGIT PIN!");
			if(i==2)
				{
					System.out.println("\nATTEMPT LIMIT EXCEED");
					break;
				}
		}
		}
	}
}

 //Class for Main Method 
class ATM
{
public static void main(String[] args)throws Exception
	{
		//load the driver manager
		Class.forName("com.mysql.jdbc.Driver");
		// Create a connection
		Connection con = DriverManager.getConnection
		("jdbc:mysql://localhost:3306/atm","root","statement");
		Scanner sc1 = new Scanner(System.in);
		System.out.println("\n\n<--- SBI ATM ---> \n");
		System.out.print("\nENTER YOUR CARD NUMBER: ");
		String CardNumber = sc1.next();
		
		System.out.print("ENTER YOUR PIN : ");
		String Pin = sc1.next();
		
		String q = "Select Pin from acc where CardNumber=?";
		PreparedStatement pst1 =  con.prepareStatement(q);
		pst1.setString(1,CardNumber);
		ResultSet rs = pst1.executeQuery();
		
		if(rs !=null && rs.next())
		{
			String str=rs.getString("Pin");
			String check;
			String check2;
			if(str.equals(Pin))
			{
				do
			{
             System.out.print("\n\nENTER YOUR CHOICE \n\n1.WITHDRAW\n2.BALANCE ENQUIRIE\n3.TRANSFER\n4.PIN CHANGE\n\nCHOICE : ");
             Scanner sc2 = new Scanner(System.in);
             int choice = sc2.nextInt();
             switch(choice)
			 {
				 case 1:
				 Withdraw ob1 = new Withdraw();
				 ob1.enterAmount(CardNumber,con);
	             break;
				 
				 case 2:
				 EnquirieB ob2 = new EnquirieB();
				 ob2.BalEnquirie(CardNumber,con);
				 break;
				 
				 case 3:
				 Transfer ob3 = new Transfer();
				 ob3.transfer(CardNumber,con);
				 break;
				 
				 case 4:
				 ChangePin ob4 = new ChangePin();
				 ob4.changePin(CardNumber,Pin,con);
				 break;
				 
				 default:
				 System.out.println("Wrong Choice !");
			}
			
			System.out.print("\nDO YOU WANT TO CONTINUE YOUR TRANSACTION(YES/NO): ");
			Scanner sc3 = new Scanner(System.in); 
			check = sc3.nextLine();
			check2 = "YES";
			}while(check.equalsIgnoreCase(check2));
			}
			else
			{
				System.out.println("\nINCORRECT PIN !");
			}
		}
		System.out.println("\n\n<-----TERMINATED!----->");
	}
}	