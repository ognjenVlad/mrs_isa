package controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import DTOs.UserDTO;
import service.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO login(UserDTO user){
		return userService.login(user.getEmail(),user.getPassword());
		
	}
}
