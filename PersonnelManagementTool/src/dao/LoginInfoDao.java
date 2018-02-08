package dao;

import models.LoginInfo;

public interface LoginInfoDao {
	
	LoginInfo getLoginInfo(String username); 
	boolean isLocked(LoginInfo loginInfo); 
	boolean authenticate(LoginInfo loginInfo); 

}
