package barbearia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Barbearia {
    final int NR_CADEIRAS = 2;
    boolean dormindo = true;
    boolean cortando = false;
    Queue<Cliente> clientesEsperando = new LinkedList<>();
    List<Cliente> clientesAtendidos = new ArrayList<>();


    public synchronized void cortar(Barbeiro barbeiro) {
        try {
            while (clientesEsperando.size() == 0) {
                System.out.println("Não tem nenhum cliente esperando, " + barbeiro.nome + " foi dormir");
                wait();
            }

            String nomeCliente = clientesEsperando.peek().nome;
            if (clientesEsperando.size() > 0) {
                // Chama cliente esperando
                clientesEsperando.poll();
                cortando = true;
                System.out.println(barbeiro.nome + " está atendendo " + nomeCliente + "......");
//                Thread.sleep((int) (Math.random() * 5000));
            }
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void entraBarbearia(Cliente cliente) {
        try {
            if (!clientesAtendidos.contains(cliente)) {
                if (clientesEsperando.size() < NR_CADEIRAS) {
                    clientesEsperando.offer(cliente);
                    clientesAtendidos.add(cliente);
                } else {
                    System.out.println("Um Cliente chegou mas não existem cadeiras vazias, ele foi embora.");
//                    Thread.sleep((int) (Math.random() * 5000));
                }
            }

            while (clientesEsperando.size() < NR_CADEIRAS) {
                if (dormindo) {
                    // Acorda o barbeiro
                    notifyAll();
                    dormindo = false;
                }
                wait();
            }
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
