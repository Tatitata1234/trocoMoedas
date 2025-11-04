package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Dinamica {

    private static final Logger log = LoggerFactory.getLogger(Dinamica.class);

    public static void main(String[] args) {

        log.warn("Bem vindos!");
        log.warn("Digite as moedas disponíveis de acordo com o exemplo: 1,2,5");

        Scanner input = new Scanner(System.in);

        List<Moeda> moedas = new ArrayList<>();
        Arrays.stream(input.nextLine().split(","))
                .toList().forEach(item -> moedas.add(new Moeda(Integer.parseInt(item))));

        moedas.sort((item1, item2) -> Integer.compare(item2.getValor(), item1.getValor()));

        int oo = Integer.MAX_VALUE;

        log.warn("Digite o valor que deseja dar de troco:");
        int valor = input.nextInt();
        input.nextLine();

        List<Integer> troco = new ArrayList<>(Collections.nCopies(valor + 1, oo));

        HashMap<Integer, List<Moeda>> conjuntos = new HashMap<>();

        troco.set(0, 0);
        conjuntos.put(0, new ArrayList<>());

        for (int i = 1; i <= valor; i++) {
            for (Moeda m : moedas) {
                if (m.getValor() <= i && troco.get(i - m.getValor()) + 1 < troco.get(i)) {
                    troco.set(i, troco.get(i - m.getValor()) + 1);

                    List<Moeda> novaLista = new ArrayList<>(conjuntos.get(i - m.getValor()));
                    novaLista.add(m);
                    conjuntos.put(i, novaLista);
                }
            }
        }

        log.error("Quantidade de moedas: " + troco.get(valor));

        Map<Integer, Long> contagem = new TreeMap<>(Collections.reverseOrder());
        conjuntos.get(valor).forEach(m -> contagem.put(
                m.getValor(),
                contagem.getOrDefault(m.getValor(), 0L) + 1
        ));

        contagem.forEach((valorMoeda, qtd) -> log.info("{} moedas de {}", qtd, valorMoeda));
    }
}
