package barbearia;

public class Barbeiro extends Thread {
    final static int TEMPO_MAXIMO = 10000;
    Barbearia barbearia;
    String nome;

    public Barbeiro(Barbearia barbearia, String nome) {
        this.barbearia = barbearia;
        this.nome = nome;
    }

    public void run() {
        int tempo;
        while (true) {
            tempo = (int) (Math.random() * TEMPO_MAXIMO);
            try { Thread.sleep(tempo); } catch (InterruptedException e) { e.printStackTrace(); }
            barbearia.cortar(this);
        }
    }
}