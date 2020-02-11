package book.stdscore.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class UsersSet {
	private HashSet<User> usersSet;

	public UsersSet() {
		super();
		// TODO 自动生成的构造函数存根
		usersSet = new HashSet<User>() ;
		String str = null ;
		String[] userStr = null  ; 
		try {
			FileReader fir = new FileReader("users.txt") ;
			BufferedReader bir = new BufferedReader(fir) ;
			while((str=bir.readLine())!=null) {
				userStr = str.split(":") ;
				usersSet.add(new User(userStr[0].trim(), userStr[1].trim(), Integer.parseInt(userStr[2]))) ; 
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public boolean isValid(User user) {
		return usersSet.contains(user) ;
	}

}
