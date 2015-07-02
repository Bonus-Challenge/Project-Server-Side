package kernel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;

import filesys.MFile;

public class Exec {

	private String[] _args;
	private Dictionary<String, String> _env;

	private String process(String string) {
		String ans = "";
		int last = 0, end = string.length();

		int t = string.indexOf('$', last);
		ans += string.substring(0, t);

		while (string.indexOf('$', last) != -1) {
			last = string.indexOf('$', last);
			if (last > 0 && string.charAt(last - 1) != '\\') {
				end = string.length();
				for (int i = last; i < string.length(); i++) {
					char tmp = string.charAt(i);
					if (tmp == '/' || tmp == ' ') {
						end = i;
					}
				}

				String var = string.substring(last + 1, end);
				last = end;
				try {
					try {
						int v = Integer.valueOf(var);
						ans += _args[v];
					} catch (NumberFormatException e) {
						ans += _env.get(var);
					}
				} catch (IndexOutOfBoundsException e) {

				}
			}
		}

		ans += string.substring(end);

		return ans;
	}

	public Exec(MFile mFile, String[] args, Dictionary<String, String> env) {

		_args = args;
		_env = env;

		// TODO Auto-generated constructor stub
		ArrayList<String> lines = new ArrayList<String>();
		Scanner scn = null;
		try {
			scn = new Scanner(mFile.getF());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			env.put("?", "127");
			return;
		}
		while (scn.hasNext()) {
			lines.add(scn.nextLine());
		}

		for (int i = 0; i < lines.size(); i++) {
			// TODO: Split line into command
			String[] splits = lines.get(i).split(" ");
			String task = splits[0];

			ArrayList<String> nArgs = new ArrayList<String>();

			char quote = '.';
			// TODO: does not handle an unfinished quote
			for (int j = 1; j < splits.length; j++) {
				if (splits[j].length() == 0) {
					if (quote != '.') {
						nArgs.set(nArgs.size() - 1, nArgs.get(nArgs.size() - 1)
								+ " ");
					}
					continue;
				}
				if (quote == '.') {
					// Check to see if there is quote
					if (splits[j].charAt(0) == '\'') {
						quote = '\'';

						nArgs.add(splits[j].substring(1));

					} else if (splits[j].charAt(0) == '"') {
						quote = '"';

						nArgs.add(splits[j].substring(1));

					} else {

						splits[j] = process(splits[j]);

						nArgs.add(splits[j]);
					}
				} else {

					nArgs.set(nArgs.size() - 1, nArgs.get(nArgs.size() - 1)
							+ " ");

					if (splits[j].charAt(splits[j].length() - 1) == quote
							&& splits[j].charAt(splits[j].length() - 2) != '\\') {

						nArgs.set(
								nArgs.size() - 1,
								nArgs.get(nArgs.size() - 1)
										+ (splits[j].substring(0,
												splits[j].length() - 1)));

						quote = '.';
					} else {
						nArgs.set(nArgs.size() - 1, nArgs.get(nArgs.size() - 1)
								+ (splits[j]));
					}

				}
			}

			new Task(task, nArgs.toArray(new String[nArgs.size()]), env);
		}
	}

}
