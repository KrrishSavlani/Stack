//this program is just an basic program which can just solve the simple expression where there is only single operator 
//todo :- convert it in dynamic after setting the rules for the operator in the stack 
// to do 1) :- set the rules 
//       2) :- make it dynamic

import java.util.*;

public class expression
{
	
	static StringBuffer o1 = new StringBuffer("+");
	static StringBuffer o2 = new StringBuffer("-");
	static StringBuffer o3 = new StringBuffer("/");
	static StringBuffer o4 = new StringBuffer("*");
	static StringBuffer o5 = new StringBuffer("^");
    static StringBuffer prefix = new StringBuffer();
	
    public static void process(StringBuffer exp)
	{
		stack stackk = new stack();
	  for(int i = 0 ; i<exp.length() ; i++)
	  {
		     //conert char to stringBUffer
			 
		StringBuffer ch = new StringBuffer(String.valueOf(exp.charAt(i)));
		   //string.valueof(exp.charAt(0)) coverting to the string  and then converted into StringBuffer
		if(ch.toString().equals(o1.toString()) || ch.toString().equals(o2.toString()) || ch.toString().equals(o3.toString()) || ch.toString().equals(o4.toString()) || ch.toString().equals(o5.toString())
		  )
		{
			//add this to stack
			stackk.push(ch);
		}
		else
		{
			 prefix.append(ch);
		}
	  }
	}
	
	public static void getResult()
	{
		stack obj = new stack();
		
		while(obj.top > -1)
		{
			prefix.append(obj.pop());
		}
		
		System.out.println("prefix :- " +prefix);
		
	}
  
    public static void main(String[] args)
    {
       System.out.println("infix : a^b");
       StringBuffer exp = new StringBuffer("a^b");
       expression obj = new expression();
	   obj.process(exp);
	   obj.getResult();
	   
    }
}

class stack{
	
    static StringBuffer [] stack = new StringBuffer[2];
	static int top = -1;
	
	public static void push(StringBuffer op)
	{
		stack[++top] = op;
	}
	
	public static StringBuffer pop()
	{
		return stack[top--] ;
	}
}