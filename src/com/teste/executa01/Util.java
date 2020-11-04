package com.teste.executa01;

import java.util.HashMap;
import java.util.Map;

public class Util {
	
	public int mostrarSolucao(int n) {
		Map<Integer, Integer> ocorrencias = new HashMap<>();
		Integer total = this.separadorDeDigito(n, ocorrencias);
		Integer resultado;
		Integer[] valores;

		valores = ocorrencias.values().toArray(new Integer[ocorrencias.size()]);
		resultado = this.calcularVezesDeCombinacoes(total, valores);

		// Desconsidera os zeros no início
		if (ocorrencias.containsKey(0)) {
			Double quantidadeDeZeros = Double.valueOf(ocorrencias.get(0));
			Double quantidadeDeDigitos = Double.valueOf(total);
			Double provisorio = Double.valueOf(resultado);

			provisorio = provisorio - (provisorio / (quantidadeDeDigitos / quantidadeDeZeros));
			resultado = provisorio.intValue();
		}
		
		return resultado;
	}

	public Integer calcularVezesDeCombinacoes(Integer total, Integer... combinacoes) {
		Long denominador = 1L;
		Long numerador;
		Long resultado;

		for (Integer combinacao : combinacoes) {
			if (combinacao > 1) {
				denominador = denominador * this.fator(combinacao);
			}
		}

		numerador = this.fator(total);
		resultado = numerador / denominador;

		return resultado.intValue();
	}

	private Integer separadorDeDigito(int numero, Map<Integer, Integer> ocorrencias) {
		Integer total = 0;

		while (numero != 0) {
			int digito = numero % 10;
			numero = numero / 10;
			if (ocorrencias.containsKey(digito)) {
				ocorrencias.put(digito, ocorrencias.get(digito) + 1);
			} else {
				ocorrencias.put(digito, 1);
			}
			total++;
		}
		return total;
	}

	private Long fator(Integer numero) {
		Long resultado = 1L;
		for (int fator = 2; fator <= numero; fator++) {
			resultado = resultado * fator;
		}
		return resultado;
	}
}
