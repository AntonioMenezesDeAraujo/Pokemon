package pokemon.projetoandroid.com.br.pokemon;

/**
 * Created by antonio on 12/02/18.
 */

public class Pokemon {
    private Long id;
    private String name;
    private String ThumbnailImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailImage() {
        return ThumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        ThumbnailImage = thumbnailImage;
    }
}
