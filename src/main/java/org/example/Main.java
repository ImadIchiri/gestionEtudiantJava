package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static int getMenu() {
        Scanner scanner = new Scanner(System.in);
        int userChoice = -1;

        System.out.println("Veuillez choisir une operation:");
        System.out.println("\n0- exit");
        System.out.println("1- Ajouter un étudiant");
        System.out.println("2- Sélectionner un étudiant par id");
        System.out.println("3- Sélectionner tous les étudiants");
        System.out.println("4- Sélectionner la liste des étudiants d’une filière");
        System.out.println("5- Sélectionner la liste des étudiants par matricule");
        System.out.println("6- Suppression d’un étudiant");
        System.out.println("7- La modification d’un étudiant");

        do {
            try {
                System.out.print("\nVotre choix est: ");
                userChoice = scanner.nextInt();
                scanner.nextLine(); // To Cosume The \n
            } catch (InputMismatchException exception) {
                System.out.println("Seulement Les Entier Sont Autoriser !");
                scanner.next(); // To Avoid Infinit Loop
            }

            if (userChoice < 0 || userChoice > 7) {
                System.out.println("Votre choix doit etre entre 1 et 7 !");
            }
        } while (userChoice < 0 || userChoice > 7);

        return userChoice;
    }

    public static String choisirFiliere() {
        Scanner scanner = new Scanner(System.in);
        int filiereIndex;
        String filiere;

        System.out.println("Filières: ");
        final List<Filieres> FILIERES = Arrays.asList(Filieres.values());
        final int filieresSize = FILIERES.size();
        for (int i = 0; i < filieresSize; i++) {
            System.out.println("\t" + (i + 1) + "- " + FILIERES.get(i));
        }
        do {
            System.out.print("Le nombre de votre filière: ");
            filiereIndex = scanner.nextInt();

            if (filiereIndex < 1 || filiereIndex > filieresSize) {
                System.out.println("L nombre doit etre entre 1 et " + filieresSize);
            }
        } while (filiereIndex < 1 || filiereIndex > filieresSize);

        filiere = String.valueOf(FILIERES.get(filiereIndex - 1));

        return filiere;
    }
    public static void main(String[] args) {
        List<Etudiant> initialStudentsList = new ArrayList<>(Arrays.asList(
                new Etudiant(0, "Ichiri", "Imad", "AB123", String.valueOf(Filieres.WEB_DEV)),
                new Etudiant(1, "Izgaz", "Rajae", "EF546", String.valueOf(Filieres.NETWORKS)),
                new Etudiant(2, "Slimani", "Salma", "EF453", String.valueOf(Filieres.CLOUD)),
                new Etudiant(3, "Mustach", "Samir", "AB563", String.valueOf(Filieres.MATHEMATICS)),
                new Etudiant(4, "Lmalki", "Abir", "AB564", String.valueOf(Filieres.MATHEMATICS)),
                new Etudiant(5, "Noumani", "Saad", "EF673", String.valueOf(Filieres.CLOUD)),
                new Etudiant(6, "Mourade", "Mouad", "IJ785", String.valueOf(Filieres.MOBILE_DEV)),
                new Etudiant(7, "Bouchtarat", "Hamza", "IJ443", String.valueOf(Filieres.NETWORKS)),
                new Etudiant(8, "Ermili", "Mohammed", "IJ123", String.valueOf(Filieres.CLOUD)),
                new Etudiant(9, "Raiss", "Redwan", "IJ653", String.valueOf(Filieres.PHYSICS)),
                new Etudiant(10, "Lehlou", "Rayan", "AK765", String.valueOf(Filieres.WEB_DEV))
        ));

        // Insert Initial List To Our Program
        /*
            for (Etudiant etudiant : initialStudentsList) {
                EtudiantMetier.addStudent(etudiant);
            }
         */


        Scanner scanner = new Scanner(System.in);
        final int EXIT_NUMBER = 0;
        int userChoice = 1; // InitialValue To Enter The WHILE Loop

        System.out.println("\n######## Welcome to Students Management System ########\n");

        while (userChoice != EXIT_NUMBER) {
            userChoice = getMenu(); // Show The Menu + Get The User Choice + Return The User Choice

            if (userChoice == EXIT_NUMBER) {
                break;
            } else if (userChoice >= 1 && userChoice <= 7) {
                switch (userChoice) {
                    case 1:
                        int id = -1;
                        String nom, prenom, matricule, filiere;
                        final Etudiant etudiantCreer;

                        System.out.print("Avec ou sans id (a/s) ?: ");
                        String withOrWithoutId = scanner.next();
                        scanner.nextLine(); // To Consume The \n

                        if (withOrWithoutId.equalsIgnoreCase("a")) {
                            List<Integer> listOfIds = EtudiantMetier.getAllStudents().stream()
                                    .map(tempEtudiant -> tempEtudiant.getId())
                                    .collect(Collectors.toList());

                            do {
                                System.out.print("Id: ");
                                id = scanner.nextInt();
                                scanner.nextLine(); // To Consume The \n

                                if (listOfIds.contains(id)) {
                                    System.out.println("Veuillez saisir différente ID !");
                                }
                            } while (listOfIds.contains(id));
                        }

                        System.out.print("Nom: ");
                        nom = scanner.nextLine();

                        System.out.print("Prénom: ");
                        prenom = scanner.nextLine();

                        System.out.print("Matricule: ");
                        matricule = scanner.nextLine();

                        filiere = choisirFiliere();

                        if (id != -1) {
                            etudiantCreer = new Etudiant(id, nom, prenom, matricule, filiere);
                        } else {
                            etudiantCreer = new Etudiant(nom, prenom, matricule, filiere);
                        }

                        EtudiantMetier.addStudent(etudiantCreer);
                        break;
                    case 2:
                        System.out.println("##### Sélectionner un étudiant par id #####");
                        System.out.print("L'id de l'étudiant: ");
                        id = scanner.nextInt();

                        Optional<Etudiant> selectedStudent = EtudiantMetier.getStudentById(id);

                        if (selectedStudent.isPresent()) {
                            System.out.println(selectedStudent.get());
                        }
                        break;
                    case 3:
                        System.out.println("\t##### Liste des étudiants #####");

                        for (Etudiant etudiant : EtudiantMetier.getAllStudents()) {
                            System.out.println(etudiant);
                        }
                        break;
                    case 4:
                        System.out.println("##### Liste des étudiants d'une filière  #####");

                        filiere = choisirFiliere();

                        List<Etudiant> studentsByFiliere = EtudiantMetier.getStudentsByFiliere(filiere);

                        if (studentsByFiliere.size() > 0) {
                            System.out.println("Les étudiants de la filére " + filiere + ":");
                            for(Etudiant etudiant : studentsByFiliere) {
                                System.out.println(etudiant);
                            }
                        } else {
                            System.out.println("Pas d'étudiant dans cette filiére (" + filiere + ") !");
                        }
                        break;
                    case 5:
                        System.out.println("\t##### Liste des étudiants par matricule #####");

                        System.out.print("Veuillez saisir la chaine des caractères: ");
                        String matriculeStartsWith = scanner.nextLine();

                        List<Etudiant> studentsByMatriclue = EtudiantMetier.getStudentsHaveMatriculeStartedWith(matriculeStartsWith);

                        if (studentsByMatriclue.size() > 0) {
                            System.out.println("La liste des étudiants leur matricule commence par " + matriculeStartsWith + ":");
                            List<Etudiant> finalStudentsList = studentsByMatriclue;
                            for (Etudiant etudiant : finalStudentsList) {
                                System.out.println(etudiant);
                            }
                        } else {
                            System.out.println("Ces matricules n'existent pas !");
                        }
                        break;
                    case 6:
                        System.out.println("##### Liste des étudiants d'une filière  #####");
                        System.out.print("L'ID de l'étudiant a supprimer: ");
                        id = scanner.nextInt();

                        if (EtudiantMetier.removeStudent(id) == 1) {
                            System.out.println("L'étudiant est supprimer !");
                        } else {
                            System.out.println("Pas D'étudiant Avec L'ID '" + id + "' !");
                        }
                        break;
                    case 7:
                        Etudiant newStudent = new Etudiant();
                        System.out.println("##### Modification D'un Etudiant  #####");
                        System.out.print("L'ID de l'étudiant a modifier: ");
                        id = scanner.nextInt();

                        Optional<Etudiant> optionalStudentToEdit = EtudiantMetier.getStudentById(id);

                        if (optionalStudentToEdit.isEmpty()) {
                            System.out.println("Pas D'étudiant Avec L'ID '" + id + "' !");
                        } else {
                            Etudiant studentToEdit = optionalStudentToEdit.get();
                            System.out.print("Editer le nom (y/n)?: ");
                            if (scanner.next().equalsIgnoreCase("y")) {
                                scanner.nextLine(); // To Consume the \n
                                System.out.print("Nom (" + studentToEdit.getNom() + " -> ?): ");
                                nom = scanner.nextLine();
                                newStudent.setNom(nom);

                            }

                            System.out.print("Editer le prenom (y/n)?: ");
                            if (scanner.next().equalsIgnoreCase("y")) {
                                scanner.nextLine(); // To Consume the \n
                                System.out.print("Prenom (" + studentToEdit.getPrenom() + " -> ?): ");
                                prenom = scanner.nextLine();
                                newStudent.setPrenom(prenom);

                            }

                            System.out.print("Editer le matricule (y/n)?: ");
                            if (scanner.next().equalsIgnoreCase("y")) {
                                scanner.nextLine(); // To Consume the \n
                                System.out.print("matricule (" + studentToEdit.getMatricule() + " -> ?): ");
                                matricule = scanner.nextLine();
                                newStudent.setMatricule(matricule);

                            }

                            System.out.print("Editer la filiére (y/n)?: ");
                            if (scanner.next().equalsIgnoreCase("y")) {
                                scanner.nextLine(); // To Consume the \n
                                System.out.print("Filiere (" + studentToEdit.getFiliere() + " -> ?): ");

                                filiere = choisirFiliere();
                                newStudent.setFiliere(filiere);
                            }

                            EtudiantMetier.editStudent(id, newStudent);
                        }
                        break;
                    default:
                        System.out.println("Ce choix n'est pas valide !");
                }
            }

            if (userChoice != 0) {
                System.out.print("\nVoulez-vous refaire le traitement (y/n): ");
                String userAnswer = scanner.next();
                scanner.nextLine(); // To Consume The \n

                if (!userAnswer.equalsIgnoreCase("y")) {
                    break;
                }
            }
        }
    }
}