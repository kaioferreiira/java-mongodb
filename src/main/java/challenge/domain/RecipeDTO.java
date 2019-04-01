package challenge.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe para mapear a receita no MongoDB
 *
 */
public class RecipeDTO {

    private String id;
    private String title;
    private String description;
    private List<String> likes;
    private List<String> ingredients;
    private List<RecipeComment> comments = new ArrayList();

    public RecipeDTO(Recipe recipeObj) {
    }

    public RecipeDTO(String id, String title, String description, List<String> likes, List<String> ingredients, List<RecipeComment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.ingredients = ingredients;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeComment> getComments() {
        return comments;
    }

    public void setComments(List<RecipeComment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDTO recipeDTO = (RecipeDTO) o;
        return Objects.equals(id, recipeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", likes=" + likes +
                ", ingredients=" + ingredients +
                ", comments=" + comments +
                '}';
    }
}
