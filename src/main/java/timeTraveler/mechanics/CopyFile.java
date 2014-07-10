package timeTraveler.mechanics;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Copy File Class
 * @author Charsmud
 *
 */
public class CopyFile {
	public static void copyFile(File source, File dest) throws IOException {
	
		if(!dest.exists()) {
			dest.createNewFile();
		}
        InputStream in = null;
        OutputStream out = null;
        try {
        	in = new FileInputStream(source);
        	out = new FileOutputStream(dest);
    
	        // Transfer bytes from in to out
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
        }
        finally {
        	in.close();
            out.close();
        }
        
	}
	/**
	 * Copies all files inside of a directory sourceDir to another directory destDir
	 * @param sourceDir
	 * @param destDir
	 * @throws IOException
	 */
	public static void copyDirectory(File sourceDir, File destDir) throws IOException {
		
		if(!destDir.exists()) {
			destDir.mkdir();
		}
		
		File[] children = sourceDir.listFiles();
		
		for(File sourceChild : children) {
			String name = sourceChild.getName();
			File destChild = new File(destDir, name);
			if(sourceChild.isDirectory()) {
				copyDirectory(sourceChild, destChild);
			}
			else {
				copyFile(sourceChild, destChild);
			}
		}	
	}
	
	/*public static boolean delete(File resource) throws IOException { 
		if(resource.isDirectory()) {
			File[] childFiles = resource.listFiles();
			for(File child : childFiles) {
				delete(child);
			}
						
		}
		return resource.delete();
		
	}*/
	/**
	 * Moves files from a directory source to another directory dest, moves all files.  Method is experimental
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static void moveMultipleFiles(File source, File dest) throws IOException {
		File[] files = source.listFiles();
		for(int i = 0; i < files.length; i++) {
			System.out.println(files[i].getName());
			String x = (source + "/" + files[i].getName());
			String y = (dest + "/" + files[i].getName());
			
			System.out.println(x + " " + y);
			
			File f1 = new File(x);
			File f2 = new File(y);
			f2.delete();
			f1.renameTo(new File(y));
		}
	}
}