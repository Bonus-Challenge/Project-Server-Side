package engine;

import java.util.Dictionary;
import java.util.Hashtable;
/*
 * @author MCSH
 * 
 * Class to keep game-wild attributes
 */
public class Assets {
	public static String saveFileLocation;
	public static Dictionary<String, String> env = new Hashtable<String, String>();
	
	
	static {
		env.put("PWD", "/");		//Current location
	}
}
