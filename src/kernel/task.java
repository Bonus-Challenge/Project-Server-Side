package kernel;

import java.util.Dictionary;

import engine.Assets;
import filesys.MFile;

public class task {
	
	String _task;
	String[] _args;
	
	private boolean is_runnable(String t){
		MFile file = new MFile(t);
		if(file.getF().exists()){
			//TODO: If access met
			return true;
		}
		return false;
	}
	
	public task(String task, String[] args, Dictionary env) {
		_args = args;
		_task = task;
		
		if(is_runnable(task)){		//If the file is absolute path of a runnable
			//Run file
		} else if(is_runnable(env.get("PWD")+task)){			//If it is relative path
			//Run file
		}
	}
}
