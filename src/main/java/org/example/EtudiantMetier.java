package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EtudiantMetier {
    static Scanner scanner = new Scanner(System.in);

    /***
     * Show The Menu + Get The User Choice + Return The User Choice
     * @return int (userChoice)
     */
    public static void addStudent(Etudiant etudiant) {
        try (Connection connection = DataBaseConnection.connectToDB();) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO etudiant (nom, prenom, matricule, filiere) VALUES (?, ?, ?, ?)");
            // preparedStatement.setInt(1, etudiant.getId());
            preparedStatement.setString(1, etudiant.getNom());
            preparedStatement.setString(2, etudiant.getPrenom());
            preparedStatement.setString(3, etudiant.getMatricule());
            preparedStatement.setString(4, etudiant.getFiliere());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Optional<Etudiant> getStudentById(int id) {
        ResultSet etudiantById;
        String nom = "", prenom = "", matricule = "", filiere = "";

        try (Connection connection = DataBaseConnection.connectToDB();) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM etudiant WHERE id = ?");
            preparedStatement.setInt(1, id);

            etudiantById = preparedStatement.executeQuery();

            while (etudiantById.next()) {
                nom = etudiantById.getString("nom");
                prenom = etudiantById.getString("prenom");
                matricule = etudiantById.getString("matricule");
                filiere = etudiantById.getString("filiere");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(new Etudiant(id, nom, prenom, matricule, filiere));
    }
    public static List<Etudiant> getAllStudents() {
        int id;
        String nom = "", prenom = "", matricule = "", filiere = "";
        List<Etudiant> studentsList = new ArrayList<>();

        try (Connection connection = DataBaseConnection.connectToDB();) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM etudiant");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                nom = resultSet.getString("nom");
                prenom = resultSet.getString("prenom");
                matricule = resultSet.getString("matricule");
                filiere = resultSet.getString("filiere");

                studentsList.add(new Etudiant(id, nom, prenom, matricule, filiere));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentsList;
    }
    public static List<Etudiant> getStudentsByFiliere(String filiere) {
        int id;
        String nom = "", prenom = "", matricule = "";
        List<Etudiant> studentsByFiliere = new ArrayList<>();

        try (Connection connection = DataBaseConnection.connectToDB();) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM etudiant WHERE filiere = ?");
            preparedStatement.setString(1, filiere);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                nom = resultSet.getString("nom");
                prenom = resultSet.getString("prenom");
                matricule = resultSet.getString("matricule");

                studentsByFiliere.add(new Etudiant(id, nom, prenom, matricule, filiere));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentsByFiliere;
    }
    public static List<Etudiant> getStudentsHaveMatriculeStartedWith(String matriculeStartsWith) {
        int id;
        String nom = "", prenom = "", matricule = "", filiere = "";
        List<Etudiant> studentsList = new ArrayList<>();

        try (Connection connection = DataBaseConnection.connectToDB();) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM etudiant WHERE matricule LIKE ?");
            preparedStatement.setString(1, (matriculeStartsWith + "%"));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                nom = resultSet.getString("nom");
                prenom = resultSet.getString("prenom");
                matricule = resultSet.getString("matricule");
                filiere = resultSet.getString("filiere");

                studentsList.add(new Etudiant(id, nom, prenom, matricule, filiere));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentsList;
    }
    public static int removeStudent(int id) {
        final int VALUE_IF_USER_NOT_REMOVED = 0;
        final int VALUE_IF_USER_REMOVED = 1;
        int removedInt;

        try (Connection connection = DataBaseConnection.connectToDB();) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM etudiant WHERE id = ?");
            preparedStatement.setInt(1, id);

            removedInt = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (removedInt == 0) {
            return VALUE_IF_USER_NOT_REMOVED;
        } else {
            return VALUE_IF_USER_REMOVED;
        }
    }
    public static void editStudent(int id, Etudiant newStudent) {
        Etudiant etudiantById = getStudentById(id).get();

        try (Connection connection = DataBaseConnection.connectToDB();) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE etudiant SET nom = ?, prenom = ?, matricule = ?, filiere = ? WHERE id = ?");
            preparedStatement.setString(1, newStudent.getNom() != null ? newStudent.getNom() : etudiantById.getNom());
            preparedStatement.setString(2, newStudent.getPrenom() != null ? newStudent.getPrenom() : etudiantById.getPrenom());
            preparedStatement.setString(3, newStudent.getMatricule() != null ? newStudent.getMatricule() : etudiantById.getMatricule());
            preparedStatement.setString(4, newStudent.getFiliere() != null ? newStudent.getFiliere() : etudiantById.getFiliere());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
