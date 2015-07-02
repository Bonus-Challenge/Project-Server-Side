package kernel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;

import filesys.MFile;

public class Exec {

	public Exec(MFile mFile, String[] args, Dictionary<String, String> env) {
		// TODO Auto-generated constructor stub
		ArrayList<String> lines = new ArrayList();
		Scanner scn = null;
		try {
			scn = new Scanner(mFile.getF());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			env.put("?", "127");
			return ;
		}
		while(scn.hasNext()){
			lines.add(scn.nextLine());
		}
		
		//TODO: Process lines
	}

}
