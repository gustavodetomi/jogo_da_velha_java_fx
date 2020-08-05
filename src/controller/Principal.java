package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Jogador;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.ResourceBundle;

import static model.Util.mensagemAlerta;

public class Principal implements Initializable {

    public RadioButton radio_facil;
    public RadioButton radio_normal;
    public RadioButton radio_dificil;
    public RadioButton radio_o;
    public RadioButton radio_x;
    public Button bt_voltar;
    public TextField edit_nome;
    @FXML
    private AnchorPane tela_principal;

    // Referência da tela principal
    public static AnchorPane tela_principal_ref;

    private int nivel_dificuldade;
    public static char simbolo_escolhido;

    // Referência dados jogador
    public static Jogador jogador;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(tela_principal != null)
            tela_principal_ref = tela_principal;
        bt_voltar.setVisible(false);
        // SETA OS RADIO BUTTONS PARA SEU GRUPO
        ToggleGroup dificuldades = new ToggleGroup();
        radio_facil.setToggleGroup(dificuldades);
        radio_normal.setToggleGroup(dificuldades);
        radio_dificil.setToggleGroup(dificuldades);
        radio_facil.setSelected(true);
        nivel_dificuldade = 1;

        ToggleGroup simbolos = new ToggleGroup();
        radio_o.setToggleGroup(simbolos);
        radio_x.setToggleGroup(simbolos);
        radio_x.setSelected(true);
        simbolo_escolhido = 'X';
    }

    private void troca_de_tela(String nome_tela){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/" + nome_tela + ".fxml"));
            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 5.0);
            AnchorPane.setRightAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            tela_principal.getChildren().add(n);
            bt_voltar.setVisible(true);
        }catch (Exception e){
            System.out.println("ERRO NA TROCA DA TELA " + nome_tela +".fxml "+ e);
        }
    }

    public void bt_iniciar_jogo(ActionEvent actionEvent) {
        jogador = new Jogador();
        jogador.setNivel_dificuldade(nivel_dificuldade);
        jogador.setSimbolo(simbolo_escolhido);
        troca_de_tela("tabuleiro");
    }

    public void action_dificuldade_radio(ActionEvent actionEvent) {
        String dificuldade_valor = ((RadioButton)actionEvent.getSource()).getText().trim();
        switch (dificuldade_valor){
            case "Fácil":
                nivel_dificuldade = 1;
                break;
            case "Normal":
                nivel_dificuldade = 2;
                break;
            case "Difícil":
                nivel_dificuldade = 3;
                break;
        }
    }

    public void bt_historico(ActionEvent actionEvent) {
    }

    public void bt_voltar(ActionEvent actionEvent) {
        if (tela_principal.getChildren().size() > 1) {
            tela_principal.getChildren().remove(tela_principal.getChildren().size() - 1);
        } else {
            mensagemAlerta("Atenção","Não é possível voltar!");
        }
    }

    public void action_simbolo(ActionEvent actionEvent) {
        String simbolo = ((RadioButton)actionEvent.getSource()).getText().trim();
        switch (simbolo){
            case "X":
                simbolo_escolhido = 'X';
                break;
            case "O":
                simbolo_escolhido = 'O';
                break;
        }
    }
}
