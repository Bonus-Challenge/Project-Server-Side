
package filesys;

import java.io.File;

import engine.Assets;

/*
 * @author MCSH
 * 
 * File class
 */
public class MFile {

	private File f;

	public MFile(String pathname) {
		pathname = Assets.saveFileLocation + "/" + pathname;
		f = new File(pathname);
	}

	public File getF() {
		return f;
	}

}
