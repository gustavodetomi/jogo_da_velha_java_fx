package model;

import javafx.scene.control.Button;

import static controller.Tabuleiro.campos_tabuleiro;

public class Regras {

    private static boolean venceu(int n1, int n2, int n3, char c){
        return campos_tabuleiro.get(n1).getText().equals(c + "")
                && campos_tabuleiro.get(n2).getText().equals(c + "")
                && campos_tabuleiro.get(n3).getText().equals(c + "");
    }

    public static boolean verifica_fim_do_jogo_aux(char c){
        // linha
        if(venceu(0, 1, 2, c) || venceu(3, 4, 5, c) || venceu(6, 7, 8, c))
            return true;
        // coluna
        if(venceu(0, 3, 6, c) || venceu(1, 4, 7, c) || venceu(2, 5, 8, c))
            return true;
        // diagonal
        if(venceu(6, 4, 2, c) || venceu(0, 4, 8, c))
            return true;
        return false;
    }

    public static boolean empatou(){
        boolean empate = true;
        for (Button campo: campos_tabuleiro){
            if(campo.getText().equals(""))
                empate = false;
        }
        return empate;
    }
}
