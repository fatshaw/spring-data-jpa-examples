package fatshaw.experiment.jpa;

import com.google.common.collect.Lists;
import fatshaw.experiment.jpa.context.JPAContext;
import fatshaw.experiment.jpa.sample.domain.Artist;
import fatshaw.experiment.jpa.sample.domain.ArtistFile;
import fatshaw.experiment.jpa.sample.domain.Company;
import fatshaw.experiment.jpa.sample.domain.Track;
import fatshaw.experiment.jpa.sample.repo.ArtistRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by xiaochaojie on 15/8/16.
 */
public class app {

    static Logger logger = LogManager.getLogger(app.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new
                AnnotationConfigApplicationContext(JPAContext.class);
        ArtistRepository artistRepository = annotationConfigApplicationContext.getBean(ArtistRepository.class);


        Artist artist = new Artist();
        artist.setId(1L);
        artist.setName("test");

        ArtistFile artistFile = new ArtistFile();
        artistFile.setId(1L);
        artistFile.setName("filename");
        artistFile.setArtist(artist);

        Track t = new Track();
        t.setName("track");
        t.setId(1L);
        t.setArtist(artist);

        Company company = new Company();
        company.setId(1L);
        company.setName("company");
        company.getArtistList().add(artist);

        artist.getCompanies().add(company);

        List<Track> trackList = Lists.newArrayList();
        trackList.add(t);
        artist.getTrackList().add(t);

        artistRepository.save(artist);

        Artist artist1 = artistRepository.findOne(1L);
        logger.info(artist1.getName());
    }
}
