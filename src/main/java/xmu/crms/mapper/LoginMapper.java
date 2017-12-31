package xmu.crms.mapper;

import java.math.BigInteger;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;

/**
 * @author Zhao Zhengyu
 * @date 2017/12/24
 */
@Mapper
@Component
public interface LoginMapper {
	
	/**
	 * 微信登录.
	 * <p>微信登录<br> 
	 * @param userId - 用户Id
	 * @param code - 微信小程序/OAuth2授权的Code
	 * @param state- 微信OAuth2授权的state。对于小程序，值恒为 MiniProgram
	 * @param successUrl- 微信OAuth2授权后跳转到的网址
	 * @return user 该用户信息
	 * @throws UserNotFoundException- 登录失败时抛出
	 */
	User signInWeChat( @Param(value = "userId")BigInteger userId, 
			@Param(value = "code")String code,
			 @Param(value = "state")String state,
			 @Param(value = "successUrl")String successUrl);
	
	/**
	 * 微信登录后用户绑定.
	 * <p>User中只有phone和password，userId是注册后才有并且在数据库自增<br>
	 * @param user 用户信息
	 * @throws IllegalArgumentException user中信息有误
	 */
	 void signUpWeChat( @Param(value = "user")User user) ;
	 
	 /**
		 * 手机号登录.
		 * <p>手机号登录 (.Net使用),User中只有phone和password，用于判断用户名密码是否正确<br>
		 * @param user 用户信息(手机号Phone和密码Password)
		 * @return user 该用户信息
		 * @exception UserNotFoundException 登录失败时抛出
		 */
		 User signInPhone( @Param(value = "user")User user) ;
		 
		 
		/**
		 * 手机号注册.
		 * <p>手机号注册 (.Net使用),User中只有phone和password，userId是注册后才有并且在数据库自增<br> 
		 * @param user 用户信息(手机号Phone和密码Password)
		 * @return user 该用户信息
		 */
		 Integer signUpPhone( @Param(value = "user")User user);
	 
	/**
	 * 用户解绑. 
	 * <p>教师解绑账号(j2ee使用)<br>
	 * @param userId - 用户id
	 * @throws java.lang.IllegalArgumentException- 信息不合法，id格式错误
	 * @throws UserNotFoundException- 未找到对应用户
	 */
		
		Integer deleteTeacherAccount( @Param(value = "userId") BigInteger userId);
	
	
	
	/**
     * 用户解绑.
     * <p>学生解绑账号(j2ee使用)<br>
     * @param userId - 用户id
     * @return null
     * @exception java.lang.IllegalArgumentException - 信息不合法，id
     * @exception 格式错误UserNotFoundException - 未找到对应用户
     * @see ClassService.deleteCourseSelectionById(BigInteger userId,BigInteger classId)
     */

		Integer deleteStudentAccount( @Param(value = "userId") BigInteger userId);

}
