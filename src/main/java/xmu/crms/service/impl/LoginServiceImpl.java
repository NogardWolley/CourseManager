package xmu.crms.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.mapper.LoginMapper;

import xmu.crms.service.LoginService;
/**
 * 
 * @author Zhao zhengyu
 * @version 2017-12-24
 */
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginMapper loginMapper;
	@Override
	public User signInWeChat(BigInteger userId, String code, String state, String successUrl)
			throws UserNotFoundException {
		
		return null;
	}

	@Override
	public void signUpWeChat(User user) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public User signInPhone(User user) throws UserNotFoundException {
		// 若user为空，则用户名或密码不正确
		User userRec=loginMapper.signInPhone(user);
		if(userRec==null)
		{
			throw new UserNotFoundException();
		}
			
		return userRec;
	}

	@Override
	public User signUpPhone(User user) {
		// TODO Auto-generated method stub
		User userRec=loginMapper.signInPhone(user);
		
		if(userRec==null)
		{
			loginMapper.signUpPhone(user);
			return loginMapper.signInPhone(user);
		}
		else
		{
		return null;
		}
	}

	@Override
	public void deleteTeacherAccount(BigInteger userId) throws IllegalArgumentException, UserNotFoundException {
		BigInteger a1 = new BigInteger("4294967295");
		BigInteger a2= new BigInteger("0");
		if (userId.compareTo(a2)==1&&userId.compareTo(a1)==-1)
		{
			throw new IllegalArgumentException();
		}
		else if(loginMapper.deleteTeacherAccount(userId)==0)
		{
			throw new  UserNotFoundException();
		}
		
	}

	@Override
	public void deleteStudentAccount(BigInteger userId) throws IllegalArgumentException, UserNotFoundException {
		// TODO Auto-generated method stub
		BigInteger a1 = new BigInteger("4294967295");
		BigInteger a2= new BigInteger("0");
		if (userId.compareTo(a2)==1&&userId.compareTo(a1)==-1)
		{
			throw new IllegalArgumentException();
		}
		else if(loginMapper.deleteTeacherAccount(userId)==0)
		{
			throw new  UserNotFoundException();
		}
	}

	

}
