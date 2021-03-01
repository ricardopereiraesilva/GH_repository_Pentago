package DominioDoProblema;

public class MatrizTabuleiro /* extends MatrizPosicao */ {
	
	protected Posicao posicoes[][] = new Posicao[6][6];
	
	public Posicao informarPosicao(int linha, int coluna) {
		return posicoes[linha][coluna];
	}

	public void colocarPedra(int linha, int coluna, Jogador jogador) {
		Posicao posicao = this.informarPosicao(linha, coluna);
		posicao.definirOcupante(jogador);
	}
	
	public int informarValor(int linha, int coluna) {
		int valor;
		Posicao posicao = this.informarPosicao(linha, coluna);
		boolean ocupada = posicao.informarOcupada();
		if (ocupada) {
			Jogador jogador = posicao.informarOcupante();
			valor = jogador.informarCor();
		} else {
			valor = 0;
		}
		return valor;
	}
	
	public RenquePosicao informarLinha(int indice) {
		RenquePosicao auxLinha = new RenquePosicao(6);
		for (int coluna=0; coluna<6; coluna++) {
			auxLinha.definirPosicao(coluna, this.informarPosicao(indice, coluna));			
		}
		return auxLinha;
	}

	public RenquePosicao informarColuna(int indice) {
		RenquePosicao auxColuna = new RenquePosicao(6);
		for (int linha=0; linha<6; linha++) {
			auxColuna.definirPosicao(linha, this.informarPosicao(linha, indice));			
		}
		return auxColuna;
	}

	public RenquePosicao informarDiagonal(int indice) {
		RenquePosicao auxDiagonal;
		int linha = 0;
		int coluna = 0;
		if (indice == 1 || indice == 4) auxDiagonal = new RenquePosicao(6);
		else auxDiagonal = new RenquePosicao(5);
		switch (indice) {
		case 0: 
			linha = 0;
			coluna = 1;
			break;
		case 1: 
			linha = 0;
			coluna = 0;
			break;
		case 2: 
			linha = 1;
			coluna = 0;
			break;
		case 3: 
			linha = 4;
			coluna = 0;
			break;
		case 4: 
			linha = 5;
			coluna = 0;
			break;
		case 5: 
			linha = 5;
			coluna = 1;
			break;
		}		
		auxDiagonal.definirPosicao(0, this.informarPosicao(linha, coluna));
		if (indice == 1 || indice == 4) {
			for (int cont=1; cont<6; cont++) {
				coluna = coluna + 1;
				if (indice == 1 ) linha = linha + 1;
				else linha = linha - 1;
				auxDiagonal.definirPosicao(cont, this.informarPosicao(linha, coluna));	
			}
		} else {
			for (int cont=1; cont<5; cont++) {
				coluna = coluna + 1;
				if (indice == 0 ||  indice == 2) linha = linha + 1;
				else linha = linha - 1;
				auxDiagonal.definirPosicao(cont, this.informarPosicao(linha, coluna));	
			}
		}
		return auxDiagonal;
	}

	public void definirPosicao(int linha, int coluna, Posicao posicao) {
		posicoes[linha][coluna] = posicao;
	}
}
