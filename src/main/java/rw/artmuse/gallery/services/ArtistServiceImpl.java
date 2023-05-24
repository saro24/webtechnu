package rw.artmuse.gallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rw.artmuse.gallery.model.Artist;
import rw.artmuse.gallery.repository.ArtistRepo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    ArtistRepo artistrepo;

    @Override
    public Artist saveArtist(Artist artist, MultipartFile portfolioFile) {
        if (!portfolioFile.isEmpty()) {
            String contentType = portfolioFile.getContentType();
            if (contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("application/pdf")) {
                try {
                    byte[] portfolio = portfolioFile.getBytes();
                    artist.setPortfolio(portfolio);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException("Only JPEG, PNG, and PDF files are allowed.");
            }
        }
        return artistrepo.save(artist);
    }



    @Override
    public Artist updateArtist(Artist artist) {
        Artist savedArtist = artistrepo.findById(artist.getId()).orElse(null);
        if (savedArtist != null) {
            Artist updateArtist = new Artist();
            updateArtist.setId(savedArtist.getId());
            updateArtist.setName(artist.getName() != null ? artist.getName() : savedArtist.getName());
            updateArtist.setEmail(artist.getEmail() != null ?artist.getEmail() : savedArtist.getEmail());
            updateArtist.setLocation(artist.getLocation() != null ? artist.getLocation() : savedArtist.getLocation());
            updateArtist.setArtType(artist.getArtType() != null ? artist.getArtType() : savedArtist.getArtType());
            updateArtist.setPortfolio(artist.getPortfolio() != null ? artist.getPortfolio() : savedArtist.getPortfolio());
            updateArtist.setGender(artist.getGender() != null ? artist.getGender() : savedArtist.getGender());


            return artistrepo.save(updateArtist);
        }
        return null;
    }

    @Override
    public void deleteArtist(int id) {
    artistrepo.deleteById(id);
    }

    @Override
    public List<Artist> ArtistList() {
       List<Artist> artists = artistrepo.findAll();
       return artists;
    }

    @Override
    public Optional<Artist> findArtistById(int id) {

        return artistrepo.findById(id);
    }

    public void saveImage(Artist artist) {
        artistrepo.save(artist);
    }

    public Page<Artist> getPaginatedArtists(Pageable pageable) {
        List<Artist> allArtists = ArtistList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allArtists.size());

        return new PageImpl<>(allArtists.subList(start, end), pageable, allArtists.size());
    }
@Override
    public List<Artist> searchByName(String searchName) {

        String artistic = searchName;
        List<Artist> allArtists = artistrepo.searchByName(artistic);
        return allArtists;

    }

    public Page<Artist> getPaginatedSearch(String name,Pageable pageable) {
        List<Artist> plots= searchByName(name);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), plots.size());

        return new PageImpl<>(plots.subList(start, end), pageable, plots.size());
    }

}
