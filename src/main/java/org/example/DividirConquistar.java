package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DividirConquistar {
    private static final int OO = Integer.MAX_VALUE / 2;

    private static final Logger log = LoggerFactory.getLogger(DividirConquistar.class);

    public static int trocoMinimo(int valor, List<Integer> moedas, Map<Integer, Integer> memo, Map<Integer, Integer> escolha) {
        if (valor == 0) return 0;
        if (valor < 0) return OO;
        if (memo.containsKey(valor)) return memo.get(valor);

        int melhor = OO;
        int moedaEscolhida = -1;

        for (int m : moedas) {
            int resultado = 1 + trocoMinimo(valor - m, moedas, memo, escolha);
            if (resultado < melhor) {
                melhor = resultado;
                moedaEscolhida = m;
            }
        }

        memo.put(valor, melhor);
        if (moedaEscolhida != -1) escolha.put(valor, moedaEscolhida);
        return melhor;
    }

    static List<Integer> reconstruirTroco(int valor, Map<Integer, Integer> escolha) {
        List<Integer> resultado = new ArrayList<>();
        while (valor > 0 && escolha.containsKey(valor)) {
            int moeda = escolha.get(valor);
            resultado.add(moeda);
            valor -= moeda;
        }
        return resultado;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        log.warn("Digite as moedas disponíveis de acordo com o exemplo: 1,2,5");
        List<Integer> moedas = new ArrayList<>();
        Arrays.stream(input.nextLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .forEach(moedas::add);

        log.warn("Digite o valor que deseja dar de troco:");
        int valor = input.nextInt();

        Map<Integer, Integer> memo = new HashMap<>();
        Map<Integer, Integer> escolha = new HashMap<>();

        int minimo = trocoMinimo(valor, moedas, memo, escolha);

        log.error("Mínimo de moedas: " + minimo);

        List<Integer> conjunto = reconstruirTroco(valor, escolha);

        Map<Integer, Long> contagem = new TreeMap<>(Collections.reverseOrder());
        conjunto.forEach(m -> contagem.put(m, contagem.getOrDefault(m, 0L) + 1));

        log.error("Trocos:");
        contagem.forEach((moeda, n) -> log.error(n + " moedas de " + moeda));
    }

}
