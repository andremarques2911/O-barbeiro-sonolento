package barbearia;

public class Cliente extends Thread {
    final static int TEMPO_MAXIMO = 100;
    Barbearia barbearia;
    String nome;

    public Cliente(Barbearia barbearia, String nome) {
        this.barbearia = barbearia;
        this.nome = nome;
    }

    public void run() {
//        int tempo = (int) (Math.random() * TEMPO_MAXIMO);
//        try { Thread.sleep(tempo); } catch (InterruptedException e) { e.printStackTrace(); }
        barbearia.entraBarbearia(this);
    }
}

