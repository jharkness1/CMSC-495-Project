package dao;

import models.LoginInfo;

public interface LoginInfoDao {

	LoginInfo getLoginInfo(String username);

	boolean isLocked(LoginInfo loginInfo, int maxFailed, int LockoutPeriod);

	boolean authenticate(LoginInfo loginInfo, String password);

	boolean resetFailedLogin(LoginInfo loginInfo);

	boolean updateFailedLogin(LoginInfo loginInfo);

}
