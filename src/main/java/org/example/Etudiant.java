package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class Etudiant {
    private static int usersId = 0;
    private int id;
    private String nom;
    private String prenom;
    private String matricule;
    private String filiere;

    public Etudiant() {}

    public Etudiant(String nom, String prenom, String matricule, String filiere) {
        usersId++;

        List<Integer> listOfIds = EtudiantMetier.getAllStudents().stream()
                .map(tempEtudiant -> tempEtudiant.getId())
                .collect(Collectors.toList());

        while (listOfIds.contains(Integer.valueOf(usersId))) {
            usersId++;
        }
        this.id = usersId;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.filiere = filiere;
    }

    public Etudiant(int id, String nom, String prenom, String matricule, String filiere) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.filiere = filiere;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", matricule='" + matricule + '\'' +
                ", filiere='" + filiere + '\'' +
                '}';
    }
}

/*
    currentId++;
        List<Integer> listOfIds = etudiantList.stream()
        .map(tempEtudiant -> tempEtudiant.getId())
        .collect(Collectors.toList());

        while (listOfIds.contains(Integer.valueOf(currentId))) {
        currentId++;
        }

 */