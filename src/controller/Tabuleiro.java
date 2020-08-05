package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import model.Computador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controller.Principal.jogador;
import static model.Regras.empatou;
import static model.Regras.verifica_fim_do_jogo_aux;
import static model.Util.mensagemInfo;

public class Tabuleiro implements Initializable {

    public Button bt_1;
    public Button bt_2;
    public Button bt_3;
    public Button bt_4;
    public Button bt_5;
    public Button bt_6;
    public Button bt_7;
    public Button bt_8;
    public Button bt_9;
    public Label label_placar;
    public static ArrayList<Button> campos_tabuleiro;
    private Computador computador;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializa_tabuleiro();
        reseta_campos();
        inicializa_computador();
    }

    private void inicializa_computador(){
        computador = new Computador();
        computador.setNivel_dificuldade(jogador.getNivel_dificuldade());
        if(jogador.getSimbolo() == 'X'){
            computador.setSimbolo('O');
        } else {
            computador.setSimbolo('X');
        }
    }

    private void inicializa_tabuleiro(){
        campos_tabuleiro = new ArrayList<>();
        campos_tabuleiro.add(bt_1);
        campos_tabuleiro.add(bt_2);
        campos_tabuleiro.add(bt_3);
        campos_tabuleiro.add(bt_4);
        campos_tabuleiro.add(bt_5);
        campos_tabuleiro.add(bt_6);
        campos_tabuleiro.add(bt_7);
        campos_tabuleiro.add(bt_8);
        campos_tabuleiro.add(bt_9);
    }

    private void reseta_campos(){
        for (Button campo: campos_tabuleiro)
            campo.setText("");
    }

    private void atualiza_placar(){
        label_placar.setText("JOGADOR "+jogador.getN_vitorias()+" X "+computador.getN_vitorias()+ " CPU");
    }

    private void verifica_fim_do_jogo(){
        if(verifica_fim_do_jogo_aux(jogador.getSimbolo())){
            mensagemInfo("VOCÊ VENCEU!", "PARABÉNS PELO RESULTADO!");
            jogador.ganhou();
            atualiza_placar();
            reseta_campos();
        } else if(verifica_fim_do_jogo_aux(computador.getSimbolo())) {
            mensagemInfo("VOCÊ PERDEU", "TENTE NOVAMENTE!");
            computador.ganhou();
            atualiza_placar();
            reseta_campos();
        } else {
            if(empatou()){
                mensagemInfo("DEU VELHA!", "O JOGO EMPATOU!");
                reseta_campos();
            }
        }
    }


    public void bt_panel_action(ActionEvent actionEvent) {
        Button campo = ((Button) actionEvent.getSource());
        if(!campo.getText().equals("")){
            mensagemInfo("CAMPO INVÁLIDO!", "SELECIONE ALGUM CAMPO EM BRANCO!");
            return;
        }
        campo.setText(jogador.getSimbolo()+"");
        verifica_fim_do_jogo();
        computador.jogada_computador(campos_tabuleiro);
        verifica_fim_do_jogo();

    }
}
