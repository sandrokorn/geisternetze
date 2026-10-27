package de.iu.ghostnet.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String telefonnummer;

    @OneToMany(mappedBy = "meldendePerson")
    private List<GhostNet> gemeldeteNetze;
    
    @OneToMany(mappedBy = "bergendePerson")
    private List<GhostNet> zuBergendeNetze;

    @OneToMany(mappedBy = "verschollenMelder")
    private List<GhostNet> alsVerschollenGemeldeteNetze;

    // Standard-Konstruktor
    public Person() {
    }

    // Getters und Setters

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

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public List<GhostNet> getGemeldeteNetze() {
        return gemeldeteNetze;
    }

    public void setGemeldeteNetze(List<GhostNet> gemeldeteNetze) {
        this.gemeldeteNetze = gemeldeteNetze;
    }
    
    public List<GhostNet> getZuBergendeNetze() {
        return zuBergendeNetze;
    }

    public void setZuBergendeNetze(List<GhostNet> zuBergendeNetze) {
        this.zuBergendeNetze = zuBergendeNetze;
    }

    public List<GhostNet> getAlsVerschollenGemeldeteNetze() {
        return alsVerschollenGemeldeteNetze;
    }

    public void setAlsVerschollenGemeldeteNetze(List<GhostNet> alsVerschollenGemeldeteNetze) {
        this.alsVerschollenGemeldeteNetze = alsVerschollenGemeldeteNetze;
    }

    // equals() und hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        return id != null && id.equals(((Person) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}