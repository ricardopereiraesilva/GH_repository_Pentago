package DominioDoProblema;

public class RenquePosicao {
	
	protected Posicao posicoes[];

	public RenquePosicao(int tamanho) {
		posicoes = new Posicao[tamanho];
	}
	
	public boolean avaliarCondicaoVitoria(Jogador jogador) {
		int contagem = 0;
		boolean condicao;
		int tamanho = this.informarTamanho();
		for (int i = 0; i < tamanho; i++) {
			condicao = posicoes[i].ocupadaPor(jogador);
			if (condicao) contagem = contagem +1;
			else 
				if (contagem < 5) contagem = 0;
		}
		return (contagem >= 5);
	}
	
	public int informarTamanho() {	
		return posicoes.length;
	}

	public void definirPosicao(int indice, Posicao posicao) {
		posicoes[indice] = posicao;
	}
	
}
