package org.example;

public class Moeda implements Comparable<Moeda> {
    private Integer valor;

    public Moeda(Integer valor) {
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(Moeda o) {
        return Integer.compare(o.valor, this.valor);
    }
}
