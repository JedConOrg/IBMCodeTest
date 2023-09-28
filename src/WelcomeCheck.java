/**
 * 
 */
import java.util.*;
import java.util.regex.*;
/**
 * 
 */
public class WelcomeCheck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			return;
		}
		String userName = args[0];
		String password = args[1];
		String ip = args[2];
		
		if(userName.equals(null) || userName.equals("")){
			System.out.println("empty user");
			return;
		}
		if(password.equals(null) || password.equals("")){
			System.out.println("empty password");
			return;
		}
		if(ip.equals(null) || ip.equals("")){
			System.out.println("empty IP");
			return;
		}
		
		if (!Password_Validation(password)) {
			System.out.println("bad password");
			System.out.println(password);
			return;
		}
		System.out.println("We made it");
	}
	
	
	public static boolean Password_Validation(String password) 
	{

	    if(password.length()>=8)
	    {
	        Pattern letter = Pattern.compile("[a-zA-z]");
	        Pattern digit = Pattern.compile("[0-9]");
	        Pattern special = Pattern.compile ("[#$%_]");
	        Matcher hasLetter = letter.matcher(password);
	        Matcher hasDigit = digit.matcher(password);
	        Matcher hasSpecial = special.matcher(password);

	        return hasLetter.find() && hasDigit.find() && hasSpecial.find();

	    }
	    else return false;

	}
}
