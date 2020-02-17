package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.User;

@SpringBootApplication
@RestController
public class UserController implements CommandLineRunner {

	private List<User> usersList = new ArrayList<User>();

	public static void main(String args[]) {
		SpringApplication.run(UserController.class);
	}

	@Override
	public void run(String... arg0) throws Exception {
		this.usersList.add(new User("User1", "Sriram"));
		this.usersList.add(new User("User2", "Jaichu"));
		this.usersList = new ArrayList<User>(this.usersList);
	}

	@PostMapping(path = "/UserService/createUser")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		Optional<User> optUser = this.usersList.stream().filter(u -> u.getId().equals(user.getId())).findAny();
		if (!(optUser.isPresent())) {
			this.usersList.add(user);
			return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("User already exists", HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/UserService/getAllUsers")
	public List<User> getAllUsers() {
		return this.usersList;
	}

	@GetMapping("/UserService/getUserDetails")
	public User getUserDetails(@RequestParam(value = "userid") String userid) {
		Optional<User> user = this.usersList.stream().filter(u -> u.getId().equals(userid)).findAny();
		if (user.isPresent()) {
			return user.get();
		} else {
			return new User("DEFAULT", "DEFAULT");
		}
	}

	@DeleteMapping("/UserService/deleteUser")
	public ResponseEntity<Object> deleteUser(@RequestBody User user) {
		Optional<User> optUser = this.usersList.stream().filter(u -> u.getId().equals(user.getId())).findAny();
		if (optUser.isPresent()) {
			this.usersList.remove(user);
			return new ResponseEntity<>("User " + user.getId() + ":" + user.getName() + ": removed from the list",
					HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("User " + user.getId() + ":" + user.getName() + ": does not exist in the list",
					HttpStatus.ACCEPTED);
		}
	}
	
	
}