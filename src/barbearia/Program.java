package barbearia;

import static java.lang.Thread.sleep;

public class Program {

    static final int NR_BARBEIROS = 2;
    static final int NR_CLIENTES = 50;

    public static void main(String[] args) {
        Barbearia barbearia = new Barbearia();

        for (int barbeiro = 0; barbeiro < NR_BARBEIROS; barbeiro++) {
            new Barbeiro(barbearia, "Barbeiro_ " + barbeiro).start();
        }

        for (int cliente = 0; cliente < NR_CLIENTES; cliente++) {
            try { sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
            new Cliente(barbearia,"Cliente_" + cliente).start();
        }
    }
}