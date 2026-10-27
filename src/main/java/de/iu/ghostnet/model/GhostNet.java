package de.iu.ghostnet.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class GhostNet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;
    private double estimatedSize;

    @Enumerated(EnumType.STRING)
    private NetStatus status;

    @ManyToOne
    private Person meldendePerson;
    
    @ManyToOne
    private Person bergendePerson;

    @ManyToOne
    private Person verschollenMelder;

    // Standard-Konstruktor
    public GhostNet() {
    }

    // Getters und Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getEstimatedSize() {
        return estimatedSize;
    }

    public void setEstimatedSize(double estimatedSize) {
        this.estimatedSize = estimatedSize;
    }

    public NetStatus getStatus() {
        return status;
    }

    public void setStatus(NetStatus status) {
        this.status = status;
    }

    public Person getMeldendePerson() {
        return meldendePerson;
    }

    public void setMeldendePerson(Person meldendePerson) {
        this.meldendePerson = meldendePerson;
    }
    
    public Person getBergendePerson() {
        return bergendePerson;
    }

    public void setBergendePerson(Person bergendePerson) {
        this.bergendePerson = bergendePerson;
    }

    public Person getVerschollenMelder() {
        return verschollenMelder;
    }

    public void setVerschollenMelder(Person verschollenMelder) {
        this.verschollenMelder = verschollenMelder;
    }

    
    // equals() und hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GhostNet)) return false;
        return id != null && id.equals(((GhostNet) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}