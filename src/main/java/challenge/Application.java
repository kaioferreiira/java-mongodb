package challenge;

import challenge.domain.Recipe;
import challenge.domain.RecipeComment;
import challenge.repository.RecipeCommentRepository;
import challenge.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application implements  CommandLineRunner {

	@Autowired
	private RecipeCommentRepository recipeCommentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

//		RecipeComment recipeComment = new RecipeComment("Bolo incrivel");
//		RecipeComment recipeComment2 = new RecipeComment("Bolo incrivel 2");
//
//		recipeCommentRepository.saveAll(Arrays.asList(recipeComment, recipeComment2));
	}

}
