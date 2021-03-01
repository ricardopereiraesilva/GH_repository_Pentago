package DominioDoProblema;

public class Quadrante /* extends MatrizPosicao */  {
	
	protected Posicao posicoes[][] = new Posicao[3][3];
	
	public Quadrante() {
		super();
		iniciar();
	}
	
	private void iniciar() {
		for (int linha=0; linha<3; linha++) {
			for (int coluna=0; coluna<3; coluna++) {
				posicoes[linha][coluna] = new Posicao();
			}
		}
	}
	
	public Posicao informarPosicao(int linha, int coluna) {
		return posicoes[linha][coluna];
	}
	
	public void procederGiro(int sentido) {
		Quadrante auxQuadrante = new Quadrante();
		for (int linha=0; linha<3; linha++) {
			for (int coluna=0; coluna<3; coluna++) {
				Posicao posicao = this.informarPosicaoGiro(linha, coluna, sentido);
				Posicao auxPosicao = auxQuadrante.informarPosicao(linha, coluna);
				Jogador jogador = posicao.informarOcupante();
				auxPosicao.definirOcupante(jogador);
			}	
		}	
		for (int linha=0; linha<3; linha++) {
			for (int coluna=0; coluna<3; coluna++) {
				Posicao posicao = this.informarPosicao(linha, coluna);
				Posicao auxPosicao = auxQuadrante.informarPosicao(linha, coluna);
				Jogador jogador = auxPosicao.informarOcupante();
				posicao.definirOcupante(jogador);
			}	
		}		
	}
	
	public Posicao informarPosicaoGiro(int linha, int coluna, int sentido) {
		int auxLinha = 0;
		int auxColuna = 0;
		if (sentido == 1) {		// anti-horario
			auxLinha = coluna;
			switch (linha) {
				case 0: 
					auxColuna = 2;
					break;
				case 1: 
					auxColuna = 1;
					break;
				case 2: 
					auxColuna = 0;
					break;
			}		
			} else {			// horario
				auxColuna = linha;
				switch (coluna) {
				case 0: 
					auxLinha = 2;
					break;
				case 1: 
					auxLinha = 1;
					break;
				case 2: 
					auxLinha = 0;
					break;
			}		
		}
		return posicoes[auxLinha][auxColuna];
	}

}
