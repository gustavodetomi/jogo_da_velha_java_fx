package model;

public class Usuario {
    private Boolean turn;
    private int n_vitorias;
    private char simbolo;
    private int nivel_dificuldade;

    Usuario(){
        n_vitorias = 0;
        turn = false;
    }

    public Boolean getTurn() {
        return turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

    public int getN_vitorias() {
        return n_vitorias;
    }

    public void setN_vitorias(int n_vitorias) {
        this.n_vitorias = n_vitorias;
    }

    public int getNivel_dificuldade() {
        return nivel_dificuldade;
    }

    public void setNivel_dificuldade(int nivel_dificuldade) {
        this.nivel_dificuldade = nivel_dificuldade;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        if(simbolo == 'X' || simbolo == 'O') {
            this.simbolo = simbolo;
        } else {
            System.out.println("Simbolo inválido");
        }
    }

    public void ganhou(){
        this.n_vitorias++;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "turn=" + turn +
                ", n_vitorias=" + n_vitorias +
                '}';
    }
}
