package fatshaw.experiment.jpa.sample.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by xiaochaojie on 15/8/16.
 */
@Entity
@Table(name = "artist_file")
public class ArtistFile {

    @Id
    private long id;
    private String name;
    @OneToOne
    private Artist artist;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}

