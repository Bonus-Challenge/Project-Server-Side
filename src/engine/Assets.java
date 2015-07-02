package engine;

import java.util.Dictionary;
import java.util.Hashtable;
/*
 * @author MCSH
 * 
 * Class to keep game-wild attributes
 */
public class Assets {
	public static String saveFileLocation;			//TODO change later
	public static Dictionary<String, String> env = new Hashtable<String, String>();
	
	
	static {
		env.put("PWD", "/");		//Current location
		env.put("?", "0");			//Last run
		/*
		 * 0 	-> 		safe exit
		 * 127	-> 		Error
		 */
		env.put("PATH", "/bin/");	//Contain path
	}
}
