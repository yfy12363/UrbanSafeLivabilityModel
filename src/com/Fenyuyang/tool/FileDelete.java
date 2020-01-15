package com.Fenyuyang.tool;

import java.io.File;

public class FileDelete {

	public static void deleteFile(File file) {
		if (file.exists()) { 
			if (file.isFile()) { 
				file.delete(); 
			} else if (file.isDirectory()) { 
				File files[] = file.listFiles(); 
				for (int i = 0; i < files.length; i++) { 
					deleteFile(files[i]); 
				}
			}
			file.delete();
		} else {
			System.out.println("The deleted file does not exist!" + '\n');
		}
	}

}
