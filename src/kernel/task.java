package kernel;

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
	
	public task(String task, String[] args) {
		_args = args;
		_task = task;
		
		if(is_runnable(task)){
			//Run file
		}
	}
}
