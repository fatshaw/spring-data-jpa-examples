package fatshaw.experiment.jpa.sample.domain;

import com.google.common.collect.Lists;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by xiaochaojie on 15/8/16.
 */
@Entity
@Table(name = "artist")
public class Artist {

    @Id
    private long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist", fetch = FetchType.EAGER)
    private List<Track> trackList = Lists.newArrayList();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "artist")
    private ArtistFile artistFile;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "artist_company", joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"))
    private List<Company> companies = Lists.newArrayList();


    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public ArtistFile getArtistFile() {
        return artistFile;
    }

    public void setArtistFile(ArtistFile artistFile) {
        this.artistFile = artistFile;
    }

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

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}
