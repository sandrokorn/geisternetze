package de.iu.ghostnet.controller;

import de.iu.ghostnet.dao.GhostNetDAO;
import de.iu.ghostnet.model.GhostNet;
import de.iu.ghostnet.model.NetStatus;
import de.iu.ghostnet.model.Person;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class GhostNetController implements Serializable {

    private GhostNet currentNet;
    private GhostNetDAO dao;
    private List<GhostNet> allNets;

    // Eingabefelder für neues Netz (Meldende Person, optional)
    private String neuMelderName;
    private String neuMelderTel;

    // Eingabefelder für den Bergungs-Dialog
    private String bergenderName;
    private String bergenderTel;

    // Eingabefelder für den Verschollen-Dialog
    private String melderName;
    private String melderTel;

    @PostConstruct
    public void init() {
        dao = new GhostNetDAO();
        currentNet = new GhostNet();
        loadNets();
    }

    public void loadNets() {
        allNets = dao.getAllNets();
    }

    // Meldet ein neues Netz (anonym oder optional mit Melder-Daten)
    public void reportNet() {
        if (neuMelderName != null && !neuMelderName.trim().isEmpty()) {
            Person melder = dao.findOrCreatePerson(neuMelderName, neuMelderTel);
            currentNet.setMeldendePerson(melder);
        }

        currentNet.setStatus(NetStatus.GEMELDET);
        dao.saveGhostNet(currentNet);

        // Formular zurücksetzen
        currentNet = new GhostNet();
        neuMelderName = "";
        neuMelderTel = "";

        loadNets();
    }

    // Trägt eine Person für die Bergung ein
    public void bergungEintragen(GhostNet net) {
        if (bergenderName != null && !bergenderName.trim().isEmpty()) {
            Person bergender = dao.findOrCreatePerson(bergenderName, bergenderTel);
            net.setBergendePerson(bergender);
            net.setStatus(NetStatus.BERGUNG_BEVORSTEHEND);
            dao.updateGhostNet(net);
            loadNets();

            // Dialog-Felder zurücksetzen
            bergenderName = "";
            bergenderTel = "";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Name ist ein Pflichtfeld."));
        }
    }

    public void alsGeborgenMarkieren(GhostNet net) {
        net.setStatus(NetStatus.GEBORGEN);
        dao.updateGhostNet(net);
        loadNets();
    }

    public void alsVerschollenMelden(GhostNet net) {
        if (melderName != null && !melderName.trim().isEmpty() && melderTel != null && !melderTel.trim().isEmpty()) {

            // Meldende Person bei Verschollenmeldung erfassen und zuweisen
            Person melder = dao.findOrCreatePerson(melderName, melderTel);
            net.setVerschollenMelder(melder);

            net.setStatus(NetStatus.VERSCHOLLEN);
            dao.updateGhostNet(net);
            loadNets();

            // Dialog-Felder zurücksetzen
            melderName = "";
            melderTel = "";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Name und Telefonnummer sind Pflichtfelder."));
        }
    }

    // Hilfsmethoden zur Steuerung der UI-Buttons (rendered-Attribute)

    public boolean isRecoverable(GhostNet net) {
        return net.getStatus() == NetStatus.GEMELDET;
    }

    public boolean isVerschollenMeldenMoeglich(GhostNet net) {
        return net.getStatus() == NetStatus.GEMELDET || net.getStatus() == NetStatus.BERGUNG_BEVORSTEHEND;
    }

    // Getter und Setter

    public GhostNet getCurrentNet() {
        return currentNet;
    }

    public void setCurrentNet(GhostNet currentNet) {
        this.currentNet = currentNet;
    }

    public List<GhostNet> getAllNets() {
        return allNets;
    }

    public String getNeuMelderName() {
        return neuMelderName;
    }

    public void setNeuMelderName(String neuMelderName) {
        this.neuMelderName = neuMelderName;
    }

    public String getNeuMelderTel() {
        return neuMelderTel;
    }

    public void setNeuMelderTel(String neuMelderTel) {
        this.neuMelderTel = neuMelderTel;
    }

    public String getBergenderName() {
        return bergenderName;
    }

    public void setBergenderName(String bergenderName) {
        this.bergenderName = bergenderName;
    }

    public String getBergenderTel() {
        return bergenderTel;
    }

    public void setBergenderTel(String bergenderTel) {
        this.bergenderTel = bergenderTel;
    }

    public String getMelderName() {
        return melderName;
    }

    public void setMelderName(String melderName) {
        this.melderName = melderName;
    }

    public String getMelderTel() {
        return melderTel;
    }

    public void setMelderTel(String melderTel) {
        this.melderTel = melderTel;
    }
}