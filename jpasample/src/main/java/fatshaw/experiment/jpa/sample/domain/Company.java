package fatshaw.experiment.jpa.sample.domain;

import com.google.common.collect.Lists;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by xiaochaojie on 15/8/16.
 */
@Entity
@Table(name = "company")
public class Company {

    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "companies")
    private List<Artist> artistList = Lists.newArrayList();

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

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }
}
