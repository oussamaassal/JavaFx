package org.example.controllers.Boutique;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.models.Boutique.Produit;
import org.example.services.Boutique.ServiceProduit;
import org.example.utils.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class AfficherProduit {

    @FXML
    private GridPane produitContainer;
    @FXML
    private Button btnArticlles;

    @FXML
    private Button btnUsers;

    @FXML
    private Button btnReclamations;
    @FXML
    private Button btnTerrain;
    @FXML
    private VBox vbox;
    @FXML
    private Button btnAccueil;

    @FXML
    private Button btnBoutique;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnContrats;

    @FXML
    private Button btnElection;

    @FXML
    private Button btnJoueurs;

    @FXML
    private Button btnMatch;
    @FXML
    private Button btnReservation;
    @FXML
    private Button btnSignout;



    private final ServiceProduit serviceProduit = new ServiceProduit();

    private List<Produit> produits;

    @FXML
    public void initialize() {
        try {
            produits = serviceProduit.afficherStore();
            afficherProduits();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        btnMatch.setOnAction(e -> {
            naviguezVers("/Article/affichermatch.fxml");
        });
        btnReservation.setOnAction(e -> {
            naviguezVers("/Reservation/listeReservation.fxml");
        });
        btnTerrain.setOnAction(e -> {
            naviguezVers("/Reservation/Reservation.fxml");
        });
        btnJoueurs.setOnAction(e -> {
            naviguezVers("/Employee/AffichageJoueur.fxml");
        });
        btnContrats.setOnAction(e -> {
            naviguezVers("/Employee/Contrat.fxml");
        });
        btnBoutique.setOnAction(e -> {
            naviguezVers("/Boutique/Store.fxml");
        });
        btnElection.setOnAction(e -> {
            naviguezVers("/Election/DashbordElection.fxml");
        });
        btnArticlles.setOnAction(e -> {
            naviguezVers("/Article/afficherarticles.fxml");
        });
        btnReclamations.setOnAction(e -> {
            naviguezVers("/User/tablereclamation.fxml");
        });
        btnUsers.setOnAction(e -> {
            naviguezVers("/User/Crud.fxml");
        });
        btnSignout.setOnAction(e -> {
            Session.getSession().clearSession();
            naviguezVers("/User/Login.fxml");
        });



    }

    private void afficherProduits() {
        produitContainer.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (Produit produit : produits) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Boutique/CarteDashBoard.fxml"));
                AnchorPane cardPane = fxmlLoader.load();
                CarteDashBoard card = fxmlLoader.getController();
                card.setData(produit);
                // Mettre à jour le contrôleur CarteDashBoard avec le contrôleur AfficherProduit
                card.setAfficherProduitController(this);
                produitContainer.add(cardPane, column++, row);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                produitContainer.setMargin(cardPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour rafraîchir l'affichage des produits après une suppression
    public void refreshProducts() {
        try {
            produits = serviceProduit.afficherStore();
            afficherProduits();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Ajouter(ActionEvent event) {
        naviguezVers("/Boutique/AjouterProduit.fxml");
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Boutique/AjouterProduit.fxml"));
//            Parent root = loader.load();
//
//
//
//            // Afficher la nouvelle scène
//            Scene scene = new Scene(root,1920,1080);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setTitle("Ajouter Produit");
//            stage.show();
//
//            ((Stage) btnAjouter.getScene().getWindow()).close();

    }
//    @FXML
//    void Article(ActionEvent event) {
//
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Article/afficherarticles.fxml"));
//            Parent root = loader.load();
//
//
//
//            // Afficher la nouvelle scène
//            Scene scene = new Scene(root,1920,1080);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setTitle("Articles");
//            stage.show();
//
//            ((Stage) btnAjouter.getScene().getWindow()).close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//    @FXML
//    void Election(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Election/AfficherElection.fxml"));
//            Parent root = loader.load();
//
//
//
//            // Afficher la nouvelle scène
//            Scene scene = new Scene(root,1920,1080);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setTitle("Elections");
//            stage.show();
//
//            ((Stage) btnAjouter.getScene().getWindow()).close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void naviguezVers(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            btnBoutique.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


}
