package model;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static controller.Principal.simbolo_escolhido;
import static model.Regras.verifica_fim_do_jogo_aux;

public class Computador extends Usuario {

    @Override
    public String toString() {
        return "Computador{" +super.toString();
    }

    public void jogada_computador(ArrayList<Button> campos){
        if(super.getNivel_dificuldade() == 1){
            campos.get(nivel_facil(campos)).setText(super.getSimbolo()+"");
        } else if(super.getNivel_dificuldade() == 2){
            campos.get(nivel_normal(campos)).setText(super.getSimbolo()+"");
        }else if(super.getNivel_dificuldade() == 3){
            campos.get(nivel_dificil(campos)).setText(super.getSimbolo()+"");
        }
    }

    // Retorna uma escolha aleatória sem nenhum critério
    private int nivel_facil(ArrayList<Button> campos){
        ArrayList<Integer> campos_livres = new ArrayList<>();

        for (int i=0; i<campos.size(); i++)
            if(campos.get(i).getText().equals(""))
                campos_livres.add(i);

        Random rand = new Random();
        return campos_livres.get(rand.nextInt(campos_livres.size()));
    }

    // VERIFICA SE É POSSÍVEL GANHAR, E RETORNA A POSIÇÃO QUE IRÁ GANAHR A PARTIDA EM CASO POSITIVO
    private int ataque(ArrayList<Button> campos){
        String valor;
        for(int i=0;i<campos.size();i++){
            if(campos.get(i).getText().equals("")) {
                valor = campos.get(i).getText();
                campos.get(i).setText(super.getSimbolo() + "");
                if (verifica_fim_do_jogo_aux(super.getSimbolo())) {
                    campos.get(i).setText(valor);
                    return i;
                }
                campos.get(i).setText(valor);
            }
        }
        return -1;
    }

    // VERIFICA SE É NECESSÁRIO DEFENDER, SE SIM RETORNA A POSIÇÃO
    private int defesa(ArrayList<Button> campos){
        String valor;
        for(int i=0;i<campos.size();i++){
            if(campos.get(i).getText().equals("")) {
                valor = campos.get(i).getText();
                campos.get(i).setText(simbolo_escolhido + "");
                if (verifica_fim_do_jogo_aux(simbolo_escolhido)) {
                    campos.get(i).setText(valor);
                    return i;
                }
                campos.get(i).setText(valor);
            }
        }
        return -1;
    }

    private int nivel_normal(ArrayList<Button> campos){
        int ataque = ataque(campos);
        int defesa = defesa(campos);
        if(ataque != -1){
            return ataque;
        } else if(defesa != -1){
            return defesa;
        } else {
            if(super.getNivel_dificuldade() == 2)
                return nivel_facil(campos);
            if(super.getNivel_dificuldade() == 3)
                return -1;
        }
        return nivel_facil(campos);
    }

    private ArrayList<Integer> campos_proximos_aux(int a1, int a2, int a3, int a4){
        ArrayList<Integer> aux = new ArrayList<>();
        aux.add(a1);
        aux.add(a2);
        aux.add(a3);
        aux.add(a4);
        return aux;
    }

    private ArrayList<Integer> campos_proximos_aux(int a1, int a2, int a3){
        ArrayList<Integer> aux = new ArrayList<>();
        aux.add(a1);
        aux.add(a2);
        aux.add(a3);
        return aux;
    }

    // RETORNA OS CAMPOS PROXIMOS DE CADA POSIÇÃO
    private ArrayList<Integer> campos_proximos(int id_campo){
        if(id_campo == 0)
            return campos_proximos_aux(1,3,4);
        if(id_campo == 1)
            return campos_proximos_aux(0,4,2);
        if(id_campo == 2)
            return campos_proximos_aux(1,4,5);
        if(id_campo == 3)
            return campos_proximos_aux(0,6,4);
        if(id_campo == 4)
            return campos_proximos_aux(0,2,6,8);
        if(id_campo == 5)
            return campos_proximos_aux(4,2,8);
        if(id_campo == 6)
            return campos_proximos_aux(3,4,7);
        if(id_campo == 7)
            return campos_proximos_aux(4,6,8);
        if(id_campo == 8)
            return campos_proximos_aux(5,7,4);
        return campos_proximos_aux(0,0,0);
    }

    private int nivel_dificil(ArrayList<Button> campos){
        int normal = nivel_normal(campos);
        if(normal != -1)
            return normal;
        // preferência pela casa central
        if(campos.get(4).getText().equals(""))
            return 4;

        ArrayList<Integer> campos_livres = new ArrayList<>();
        ArrayList<Integer> campos_do_cpu = new ArrayList<>();

        for (int i=0; i<campos.size(); i++) {
            if (campos.get(i).getText().equals(""))
                campos_livres.add(i);
            if (campos.get(i).getText().equals(super.getSimbolo()+""))
                campos_do_cpu.add(i);
        }

        // EMBARALHA LISTA DE CAMPOS
        Arrays.sort(new ArrayList[]{campos_do_cpu});
        try {
            for (Integer value : campos_do_cpu) {
                ArrayList<Integer> aux = campos_proximos(campos_livres.get(value));
                for (Integer integer : aux) {
                    for (Integer campos_livre : campos_livres) {
                        if (integer.equals(campos_livre)) {
                            return integer;
                        }
                    }
                }
            }
        }catch (Exception e){}

        return nivel_facil(campos);
    }


}
