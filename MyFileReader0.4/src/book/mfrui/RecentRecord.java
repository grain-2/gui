package book.mfrui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RecentRecord {
	private String currFile;
	private String[] files;
	
	public RecentRecord() {
		files = new String[5];
		String str;
		try {
			FileReader fr = new FileReader("recent.rcd");
			BufferedReader bfr = new BufferedReader(fr);
			for (int i=0;i<5;i++) {
				str = bfr.readLine();
				if(str!=null && !"".equals(str)) {
					files[i] = str;
				} else {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	public void addMenuFile(String file) {
		currFile = file;
		try {
			FileWriter fw = new FileWriter("recent.rcd");
			fw.write(file);
			boolean skip = false;
			for(int i=0;i<4 && files[i]!=null;i++) {
				if(files[i].equals(file)) {
					skip = true;
					continue;
				}
				fw.write("\r\n"+files[i]);
			}
			if(skip && files[4]!=null)
				fw.write("\r\n"+files[4]);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public String getCurrFile() {
		return currFile;
	}

	public String[] getFiles() {
		return files;
	}
		
}
