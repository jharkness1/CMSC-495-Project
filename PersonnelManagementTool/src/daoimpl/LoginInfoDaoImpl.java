package daoimpl;

import dao.LoginInfoDao;
import models.LoginInfo;

public class LoginInfoDaoImpl implements LoginInfoDao {

	@Override
	public LoginInfo getLoginInfo(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLocked(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean authenticate(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
