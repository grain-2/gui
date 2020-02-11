package book.stdscore.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

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
	
	public void updateUsers(User user) {
		try {
			FileWriter fw = new FileWriter("users.txt");
			Iterator<User> it = usersSet.iterator();
			User u = null;
			while(it.hasNext()) {
				u = it.next();
				if(u.getName().equals(user.getName())&&u.getJob()==user.getJob()) {
					u.setPassword(user.getPassword());
				}
				fw.write(u.getName()+":"+u.getPassword()+":"+u.getJob()+"\r\n");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
