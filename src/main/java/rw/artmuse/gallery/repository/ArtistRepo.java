package rw.artmuse.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.artmuse.gallery.model.Artist;

import java.util.List;

@Repository
public interface ArtistRepo extends JpaRepository<Artist,Integer> {

@Query("SELECT m FROM Artist m WHERE m.name LIKE ?1")
List<Artist> searchByName(String searchName);
}
