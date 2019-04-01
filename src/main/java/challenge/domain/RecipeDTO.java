package challenge.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private RecipeComment coments;

    public RecipeDTO() {
    }

    public RecipeDTO(Recipe recipe) {
          id = recipe.getId();
          title = recipe.getTitle();
          description =  recipe.getDescription();
          likes = recipe.getLikes();
          ingredients = recipe.getIngredients();
//          coments = recipe.getComents();
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

    public RecipeComment getComents() {
        return coments;
    }

    public void setComents(RecipeComment coments) {
        this.coments = coments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDTO recipe = (RecipeDTO) o;
        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", likes=" + likes +
                ", ingredients=" + ingredients +
                ", coments=" + coments +
                '}';
    }
}
