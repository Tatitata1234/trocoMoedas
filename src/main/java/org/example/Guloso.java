package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class Guloso {

    private static final Logger log = LoggerFactory.getLogger(Guloso.class);

    //Troco em Moedas
    //Dadas denominações de moedas e um valor VVV,
    // minimizar o número de moedas para formar VVV (suprimento infinito).
    // Em sistemas canônicos (ex.: BRL/centavos, USD, EUR),
    // a abordagem gulosa é ótima. Uma “ideia gulosa”:
    // Ordenar as moedas por valor decrescente. Em cada passo,
    // escolha a maior moeda ≤ valor restante e subtraia o
    // quanto puder (por divisão inteira). Continue até zerar.
    public static void main(String[] args) {
        log.warn("Bem vindos!");
        log.warn("Digite as moedas disponíveis de acordo com o exemplo: 1,2,5");

        Scanner input = new Scanner(System.in);

        List<Moeda> moedas = new ArrayList<>();
        TreeMap<Moeda, Integer> resposta = new TreeMap<>();
        Arrays.stream(input.nextLine().split(","))
                .toList().forEach(item -> moedas.add(new Moeda(Integer.parseInt(item))));

        moedas.sort((item1, item2) -> Integer.compare(item2.getValor(), item1.getValor()));

        log.warn("Digite o valor que deseja dar de troco:");
        int valor = input.nextInt();
        input.nextLine();

        for (Moeda m : moedas) {
            int i = valor / m.getValor();
            if (i != 0) {
                valor = valor % m.getValor();
                resposta.put(m, i);
            }
        }
        log.error("Trocos:");
        resposta.forEach((chave, quant) -> log.error(quant + " moedas de " + chave.getValor()));
        if (valor > 0) {
            log.error("Não foi possível dar o troco exato sobrou: " + valor);
        }
    }
}