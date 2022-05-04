package TenstepWorkflow.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import TenstepWorkflow.services.*;
import TenstepWorkflow.entities.*;
import TenstepWorkflow.repositories.*;;
@RestController
@CrossOrigin (origins="http://localhost:4200")
public class UserController {
	
	@Autowired
	UserService UserService;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserRepository UserRepository;
	
	@GetMapping("/userinfo/{username}")
	@ResponseBody
	public User getUserinfo(@PathVariable("username") String username) {
	 return UserService.RetrieveUserinfo(username);
	}
	
	
	@PutMapping("/updateuser")
	@ResponseBody
	public User updateUser(@RequestBody User user){
	return UserService.UpdateUser(user);
	}
	

	@GetMapping("/getusers")
	@ResponseBody
	public List<User> getUsers() {
	 return UserService.RetrieveAllUsers();
	}
		
}