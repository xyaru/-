package com.krry.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.krry.entity.User;
import com.krry.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;

//使用@Controller 才可以返回html页面，使用@ResController 返回的是字符串
@Controller
@RequestMapping(value="/index")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	//spring boot建议不要使用JSP，默认使用Thymeleaf来做动态页面传递参数到html页面
	//Model是request级别的
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Model model,HttpServletRequest request){

		User user = (User) request.getAttribute("user");

		if(user == null){
//    		System.out.println(user);
			User usero = new User("##errors##","1234");
			model.addAttribute("user", usero);
		}

		return "index";
	}

	/**
	 * 打开登录界面
	 * @return
	 */
	@RequestMapping(value="/oplogin")
	public String oplogin(){

		return "login";
	}

	/**
	 * 登录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request){
		//获取用户和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");


		//根据用户名查询，用户是否存在
		User user = userRepository.findByUsername(username);

		//如果存在
		if(user!=null){

			if(password.equals(user.getPassword())){
				//如果密码正确
				//将用户信息放入到会话中...
				request.setAttribute("user", user);

				return "index";

			}else{
				//如果密码错误
				System.out.println("密码错误");
				return "ferror";
			}
		}else{
			//如果不存在，代码邮箱和密码输入有误
			System.out.println("用户不存在");
			return "ferror";
		}
	}



	/**
	 * 打开注册界面
	 * @return
	 */
	@RequestMapping("/resgi")
	public String resgi(){

		return "resgi";
	}


	/**
	 * 注册控制层
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/rege")
	public String resig(HttpServletRequest request){
		//获取用户和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//根据昵称查询，用户是否存在
		User user = userRepository.findByUsername(username);

		//若存在
		if(user != null){ //昵称重复
			return "ferror";
		}

		//若不存在
		User newUser = new User(username,password);
		//注册
		userRepository.save(newUser);

		//将信息设置session作用域
		request.setAttribute("user", newUser);

		return "index";
	}

	@ResponseBody
	@RequestMapping("/findByName")
	public User findByName(){

		return userRepository.findByUsername("krryxq");
	}

	@ResponseBody
	@RequestMapping("/find")
	public List<User> find(){

		return mongoTemplate.findAll(User.class);
	}
}
