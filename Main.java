package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static Connection connection = null;

    public static void main(String[] args) {
        // Connexion à la base de données
	    Connexion();
        // Insertion des étudiants
        InsertEtudiant();
    }

    // Class d'insertion des étudiants dans la base de données
    public static void InsertEtudiant() {
        String nom, postNom, prenom, matricule;
        int nb_etudiants = 0;
        Scanner scanner = new Scanner(System.in);

        // Demande du nombre d'étudiants à inserer
        System.out.print("Nombre d'étudiants à inserer : ");
        // stockage de la valeur saisie au clavier
        nb_etudiants = scanner.nextInt();

        for (int i = 0; i < nb_etudiants; i++) {
            System.out.print("Nom : "); nom = scanner.next();
            System.out.print("Post-nom : "); postNom = scanner.next();
            System.out.print("Prénom : "); prenom = scanner.next();
            System.out.print("Matricule : "); matricule = scanner.next();

            try {
                Statement requete = connection.createStatement();

                // Insertion des données dans la base de données
                requete.executeUpdate(
                        "insert into etudiant(id,nom,postnom,prenom,matricule) values(null,'"+nom+"','"+postNom+"','"+prenom+"','"+matricule+"')"
                );
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void Connexion() {
        try {
            //Pour ceux qui n'utilise pas mysql, le paramètre serverTimeZone n'est pas nécessaire
            //A la place de "jdbc:mysql://localhost:3306/java", remplacer par le nom de votre url de base de
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?serverTimezone=UTC", "root", "");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
