package rw.artmuse.gallery.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "username")
    private String Username;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "location")
    private String location;
    @Column(name = "artType")
    private String artType;

    @Transient
    private MultipartFile portfolioFile;
    @Lob
    @Column(name = "portfolio", columnDefinition = "mediumblob")
    private byte[] portfolio;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "gender")
    private String gender;

    public Artist() {

    }

    public Artist(String username, String name, String email, String location, String artType, MultipartFile portfolioFile, byte[] portfolio, String fileName, String gender) {
        Username = username;
        this.name = name;
        this.email = email;
        this.location = location;
        this.artType = artType;
        this.portfolioFile = portfolioFile;
        this.portfolio = portfolio;
        this.fileName = fileName;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public MultipartFile getPortfolioFile() {
        return portfolioFile;
    }

    public void setPortfolioFile(MultipartFile portfolioFile) {
        this.portfolioFile = portfolioFile;
    }

    public byte[] getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(byte[] portfolio) {
        this.portfolio = portfolio;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}