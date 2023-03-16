import java.io.IOException;

public class MyProblemException extends IOException {

	public MyProblemException()  
	   {  
	       super();  
	   }  
	   public MyProblemException(String message)  
	   {  
	       super(message);  
	   }  
	   public MyProblemException(Throwable cause)  
	   {  
	       super(cause);  
	   }  
	   public MyProblemException(String message, Throwable cause) 
	   {  
	       super(message, cause);  
	   }  
}
