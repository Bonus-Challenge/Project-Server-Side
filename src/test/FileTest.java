package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import kernel.Task;
import engine.Assets;
import filesys.MFile;

public class FileTest {
	public static void test() {

		Assets.saveFileLocation = "/home/sajjad/.pss";
		MFile file = new MFile("test");
		System.out.println(file.getF().getAbsolutePath());
		// file.setAccess(0777);
		System.out.println(file.getAccess());
		file.getF().getParentFile().mkdirs();
		if (!file.getF().exists()) {
			System.out.println("here dudy");
			try {
				file.getF().createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			PrintWriter pw = new PrintWriter(file.getF());
			pw.write("Hello dude");
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new Task("$?", new String[0], Assets.env);

	}
}
