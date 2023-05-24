package rw.artmuse.gallery.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import rw.artmuse.gallery.model.Artist;

import javax.mail.Multipart;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface ArtistService {



    Artist saveArtist(Artist artist, MultipartFile portfolioFile);

    Artist updateArtist(Artist artist);
    void deleteArtist(int id);
    List<Artist> ArtistList();
    Page<Artist> getPaginatedArtists(Pageable pageable);
    Optional<Artist>  findArtistById(int id);

    List<Artist> searchByName(String searchName);
    Page<Artist> getPaginatedSearch(String name,Pageable pageable);


}
