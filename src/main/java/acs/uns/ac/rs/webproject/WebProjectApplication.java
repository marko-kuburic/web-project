package acs.uns.ac.rs.webproject;

import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.entity.User;
import acs.uns.ac.rs.webproject.repository.ShelfItemRepository;
import acs.uns.ac.rs.webproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class WebProjectApplication implements CommandLineRunner {

	@Autowired
	private ShelfItemRepository shelfItemRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) {


		List<ShelfItem> shelves = this.shelfItemRepository.findAll();

		for (ShelfItem e : shelves){
			System.out.println(e);
		}

		List<User> users = this.userRepository.findAll();

		for (User u : users){
			System.out.println(u);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}

}
