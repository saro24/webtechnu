package rw.artmuse.gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rw.artmuse.gallery.model.Artist;
import rw.artmuse.gallery.services.ArtistService;

import java.io.IOException;


@Controller
public class ArtistController {

    @Autowired
    private ArtistService artistservice;



    @PostMapping(value = "/artist/save")
public String saveArtist(@ModelAttribute Artist artist, @RequestParam("portfolioFile") MultipartFile portfolioFile) {
    if (!portfolioFile.isEmpty()) {
        try {
            byte[] portfolio = portfolioFile.getBytes();
            artist.setFileName(portfolioFile.getOriginalFilename());
            artist.setPortfolio(portfolio);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            artist.setUsername(currentPrincipalName );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Artist newArtist = artistservice.saveArtist(artist, portfolioFile );
    return "redirect:/registration";
}





}


