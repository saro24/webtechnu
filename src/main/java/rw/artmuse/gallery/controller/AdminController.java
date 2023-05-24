package rw.artmuse.gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.artmuse.gallery.model.Artist;
import rw.artmuse.gallery.services.ArtistService;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private ArtistService artistservice;


    @RequestMapping(value = {"/admin/dashboard"}, method = RequestMethod.GET)
    public String adminHome(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 3; // Number of rows to display per page
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Artist> artistsPage = artistservice.getPaginatedArtists(pageable);
        List<Artist> artists = artistsPage.getContent();

        model.addAttribute("allArtists", artists);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", artistsPage.getTotalPages());

        return "admin/dashboard";
    }

    @GetMapping("/admin/dashboard/delete/{id}")
    public String deleteArtist(@PathVariable int id) {
        artistservice.deleteArtist(id);
        return "/home";
    }

    @GetMapping("/artist/download/{id}")
    public ResponseEntity<byte[]> downloadPortfolio(@PathVariable("id") int id) {
       Optional <Artist> artist = artistservice.findArtistById(id);
        if (artist != null ) {
            Artist art = artist.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", art.getFileName());

 return new ResponseEntity<>(art.getPortfolio(), headers, HttpStatus.OK);

        } else {

             return null;
        }
    }

    @GetMapping("/search/admin")
    public String SearchArtist(@RequestParam("search") String name, Model model) {
        List<Artist> plots=artistservice.searchByName(name);
        model.addAttribute("listplot", plots);


        return "/home";
    }

    @GetMapping("/search")
    public String SearchArtist(@RequestParam("search") String name, Model model, @RequestParam(defaultValue = "0") int page) {
//        List<PlotRegistration> plots=plotResidenceService.searchByName(name);
//        model.addAttribute("listplot", plots);
        int pageSize = 3; // Number of rows to display per page
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Artist> artistsPage = artistservice.getPaginatedSearch(name,pageable);
        List<Artist> artists = artistsPage.getContent();

        model.addAttribute("listplot", artists);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", artistsPage.getTotalPages());

        return "home";

    }

    @PutMapping("/artist/update/{id}")
    public String updateArtist(@ModelAttribute Artist artist) {
        Artist newArtist = artistservice.updateArtist(artist);
        return "admin/dashboard";
    }
}
