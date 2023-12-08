package com.nas.gitaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GitActionApplication {

	@GetMapping("/welcome")
	public String welcome(){
		return "welcome to to the app";
	}


//	echo "# Github-actions" >> README.md
//	git init
//	git add README.md
//	git commit -m "first commit"
//	git branch -M main
//	git remote add origin https://github.com/nasihmaj/Github-actions.git
//	git push -u origin main


	public static void main(String[] args) {
		SpringApplication.run(GitActionApplication.class, args);
	}

}
