package filesys;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import engine.Assets;

/*
 * @author MCSH
 * 
 * File class
 */
public class MFile {

	private final static String accessS = ".accs";

	private File f;
	private String path; // in game file path
	private int access;
	private String pathname; // real file path

	public MFile(String pathname) {
		this.path = pathname;
		pathname = Assets.saveFileLocation + "/" + pathname;
		f = new File(pathname);
		this.pathname = pathname;
		access = 0655;
		File a = new File(Assets.saveFileLocation + "/" + accessS + "/"
				+ path);
		if (a.exists()) {
			try {
				Scanner scn = new Scanner(a);
				access = scn.nextInt();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		setAccess(access);
	}

	public File getF() {
		return f;
	}

	public String getPath() {
		return path;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
		File a = new File(Assets.saveFileLocation + "/" + accessS + "/" + path);
		a.getParentFile().mkdirs();
		if (!a.exists())
			try {
				a.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			PrintWriter pw = new PrintWriter(a);
			pw.write( String.valueOf(access));
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
