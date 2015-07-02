package kernel;

import java.util.Dictionary;

import engine.Assets;
import filesys.MFile;

public class Task {

	private String _task;
	private String[] _args;
	private Dictionary<String, String> _env;

	private boolean is_runnable(String t) {
		MFile file = new MFile(t);
		if (file.getF().exists()) {
			// TODO: If access met
			return true;
		}
		return false;
	}

	private boolean inpath(String task) {
		// TODO Auto-generated method stub
		if (!task.contains("/")) { // Check path
			String[] ps = _env.get("PATH").split(":");
			for (String p : ps)
				if (is_runnable(p + task))
					return true;

		}
		return false;
	}

	private void runpath(String task) {
		String[] ps = _env.get("PATH").split(":");
		for (String p : ps)
			if (is_runnable(p + task))
				new Exec(new MFile(p + task), _args, _env);
	}

	public Task(String task, String[] args, Dictionary<String, String> env) {
		_args = args;
		_task = task;
		_env = env;

		// TODO: Set ? in env as return status of last app
		// TODO: Check path in env

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
		} else if (inpath(task)) {
			// Run from path
			runpath(task);
		} else if (task.contains("=")) {
			// env assignment
			// warning: args are ignored
			String[] st = task.split(":");
			String a = st[1];
			for (int i = 2; i < st.length; i++)
				a += "=" + st[i];
			env.put(st[0], a);
			env.put("?", "0");
		} else {
			// Error
			env.put("?", "127"); // Command not found errs
		}
	}

}
