import java.util.*;
class Number_Guess
{
    public int u_input,system_choice;
    Number_Guess()
    {
        Random r=new Random();
        this.system_choice=r.nextInt(100)+1;
    }
    public void getdata()
    {
        Scanner S=new Scanner(System.in);
        System.out.println("Enter your guess:");
        u_input=S.nextInt();
    }
    public boolean number_g()
    {
        boolean k=false;
        if(u_input==system_choice)
        {
            k=true;
        }
        else
        {
            k=false;
        }
        return k;
    }

    public static void main(String args[])
    {
       int attempts=5,round=1,score=0;
       boolean b;
       Number_Guess obj=new Number_Guess();
       b=obj.number_g();
       while(round>0)
       {
           while(attempts>0)
           {
               obj.getdata();
               if(b==true)
               {
                   System.out.println("You guessed the number correctly");
                   break;
               }
               else
               {
                   if(obj.u_input>obj.system_choice)
                   System.out.println("Too high!");
                   else
                   System.out.println("Too low!");
               }
               attempts--;
           }
           round--;
        }
       
       
       if(b==true)
       {
    	   if(attempts==0)
    		   score=10;
    	   else if(attempts==1)
    		   score=20;
    	   else if(attempts==2)
    		   score=30;
    	   else if(attempts==3)
    		   score=40;
    	   else 
    		   score=50;
       }
       System.out.println("Score:"+score);


       System.out.println("The Actual System's Choice was : "+ obj.system_choice);
    }
}