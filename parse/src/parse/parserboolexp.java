/*
 * Parsing a boolean Logic expression 
 * recursive descent parser Implememted
 * Author Mira G 
 * 
 * 
 */
package parse;
import java.io.*;
import java.util.*;

public class parserboolexp {

    static StringTokenizer st;
    static String NextInputChar;
    
    
    static void next() {
	try {
	    NextInputChar=st.nextToken().intern();
            
	    
	} catch( NoSuchElementException e) {
	    NextInputChar=null;
	}
    }

    static void error(String msg) {
	System.err.println(msg);
        System.exit(0); 
	
    }

    static void parseE() {
	// E -> T Estar 
        
        parseT();
	parseEstar(); 
        
    }

    static void parseEstar() {
	// Estar -> v T Estar | epsilon
        
	if (NextInputChar == "v") {
            next();
            if (NextInputChar=="$" || NextInputChar == ")" || NextInputChar == " " ){
               error("second operand is missing!"); 
            }
            else
	    parseT();
	    parseEstar();
	} else if(NextInputChar == ")" || NextInputChar == "$" || NextInputChar == "v" || NextInputChar == "^" ) {
           
	} else {
	    error("error parsing Estar :"+NextInputChar);
	}
          
    }

    static void parseT() {
	// T -> F Tstar
        parseF();
	parseTstar();
        
    }

    static void parseTstar() {
	// Tstar -> ^F Tstar | epsilon
	if (NextInputChar == "^") {
	    next();
            if (NextInputChar=="$"|| NextInputChar == ")" || NextInputChar == " "){
               error("second operand is missing!"); 
            }
            else
	    parseF();
	    parseTstar();
	} else if(NextInputChar == "v" || NextInputChar == "v"|| NextInputChar == ")" || NextInputChar == "$"){ 
           
        }
        else {
	    error("error parsing Tstar  :"+NextInputChar);
	}
    }
    
    static void parseF() {
	// F -> ( E ) | !E | 0 | 1 
        if( NextInputChar=="(") {
	    next();
            if (NextInputChar==")"){
              error("  not valid string! ");  
            }
            else
	    parseE();
	    if(NextInputChar==")") {
		next();
	        } else {
		error (") expected!!");
	    }
	} 
        else if( NextInputChar=="!") { 
            next();
            if (NextInputChar==")" ||NextInputChar == "$"){
              error(" not valid string! ");  
            }
            else
	    parseE();
        }
        else if(NextInputChar=="0" || NextInputChar == "1") {
	    next();
	        } 
        else if(NextInputChar=="v" || NextInputChar == "^" || NextInputChar ==")" || NextInputChar == "$") {
            
                } 
        else {
	          error("error parsing F :"+NextInputChar);
	}
    }
    static void print( String s){
    if(NextInputChar== "$") {
	      
               System.out.println(" Parsing is done successfully for " +s);
               }else {
	       error("Expression not valid");
	       } 
    }
    static void test()
    {   String s; 
        
         s  ="( 1 v 0 )";
         st = new StringTokenizer(s+" $"," ");
         next();
         parseE();
         print(s);
         s="! ( 1 v 0 )";
         st = new StringTokenizer(s+" $"," ");
         next();
         parseE();
         print(s);
         s="! ( 1 ^ 0 )";
         st = new StringTokenizer(s+" $"," ");
         next();
         parseE();
         print(s);
         s="( ! ( 1 v 0 ) )";
         st = new StringTokenizer(s+" $"," ");
         next();
         parseE();
         print(s);
         s="( ( 1 v 0 ) ^ ( 1 v 1 ) )";
         st = new StringTokenizer(s+" $"," ");
         next();
         parseE();
         print(s);
         s="( ! ( 1 v 0 ) ^ ( 1 v 1 ) )";
         st = new StringTokenizer(s+" $"," ");
         next();
         parseE();
         print(s);
         s="( ( 1 v 0 ) ^ ( 1 v 1 ) v ( 0 ^ 0 ) )";
         st = new StringTokenizer(s+" $"," ");
         next();
         parseE();
         print(s);
         
    }
    public static void main(String args []) throws IOException {
         boolean answer;
         String InputStr;
         
         test();
         Scanner in = new Scanner(System.in);
         
         answer = query(in, "\nWould you like to try some expressions of your own?");
         if (!answer) System.exit(0); 
               
         System.out.println("\nPlease type a Boolean expression made from");
         System.out.println("operations: ^ (AND),v (OR), ! (NOT) .  The ");
         System.out.println("expression must be fully parenthesized and fully spaced.");
      
         do
         {
            System.out.print("Your expression: ");
            InputStr= in.nextLine( );
            st = new StringTokenizer(InputStr+" $"," ");
	    
            try
            {
                          
               next();
               if (NextInputChar != "$"){   // to check to empty string. it means if the next token is "$" 
               parseE();
               print(InputStr);
	                     
            }
            }
                catch (Exception e)
               {
                  System.out.println("Error." + e.toString( ));
               }
         }
         while (query(in, "Another string?"));
         
        
      }
       
       public static boolean query(Scanner input, String prompt)  {
         String answer;
      
         System.out.print(prompt + " [y or n]: ");
         answer = input.nextLine( ).toUpperCase( );
         
         if (answer.startsWith("N"))
        	 System.exit(0);
         
         while (!answer.startsWith("Y") && !answer.startsWith("N"))
         {
            System.out.print("Invalid response. Please type Y or N: ");
            answer = input.nextLine( ).toUpperCase( );
            
         }
         return answer.startsWith("Y");
         
      }
       
	
    }

