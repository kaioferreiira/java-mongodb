package challenge.configuration;

import challenge.domain.Recipe;
import challenge.domain.RecipeComment;
import challenge.repository.RecipeCommentRepository;
import challenge.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {


    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeCommentRepository recipeCommentRepository;

    @Override
    public void run(String... args) throws Exception {

        recipeRepository.deleteAll();
        recipeCommentRepository.deleteAll();

        String title = "Bolo de cenoura";
        String description = "Bolo caseiro";

        List<String> likes = new ArrayList<>();
        likes.add("123");
        likes.add("423");
        likes.add("543");

        List<String> ingredients = new ArrayList<>();;
        ingredients.add("123");
        ingredients.add("423");
        ingredients.add("543");

        RecipeComment recipeComment1 = new RecipeComment("Bolo incrivel");
        RecipeComment recipeComment2 = new RecipeComment("Bolo incrivel 2");

        List<RecipeComment> comments = new ArrayList<>();
        comments.add(recipeComment1);
        comments.add(recipeComment2);

        Recipe recipe = new Recipe(null,title,description,likes,ingredients,comments);

        recipeCommentRepository.saveAll(Arrays.asList(recipeComment1, recipeComment2));
        recipeRepository.save(recipe);
    }
}
