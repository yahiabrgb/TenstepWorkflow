package TenstepWorkflow.services;

import java.util.List;

import TenstepWorkflow.entities.User;








public interface IUserService {
	List<User> RetrieveAllUsers();

	User AddUser(User u);

	void DeleteUser(Long id);

	User UpdateUser(User u);

	User RetrieveUser(String id);
	
	public User RetrieveUserinfo(String username) ;


}
