package kernel;

import java.util.Dictionary;

import engine.Assets;
import filesys.MFile;

public class Task {

	String _task;
	String[] _args;

	private boolean is_runnable(String t) {
		MFile file = new MFile(t);
		if (file.getF().exists()) {
			// TODO: If access met
			return true;
		}
		return false;
	}

	public Task(String task, String[] args, Dictionary<String, String> env) {
		_args = args;
		_task = task;

		// TODO: Check path in env

		// TODO: Set ? in env as return status of last app

		if (is_runnable(task)) { // If the file is absolute path of a runnable
			// Run file
			// TODO:
			new Exec(new MFile(task), args, env);
		} else if (is_runnable(env.get("PWD") + task)) { // If it is relative
			// Run file
			new Exec(new MFile(env.get("PWD") + task), args, env);
		} else if (task.equals(".")) {
			// Source the first file
			// TODO:
		} else {
			// Error
			env.put("?", "127");		//Command not found errs
		}
	}
}
