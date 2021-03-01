package DominioDoProblema;

public class Tabuleiro {
	
	protected Quadrante quadrantes[][] = new Quadrante[2][2];
	protected MatrizTabuleiro matriz;
	protected Jogador jogadorLocal;
	protected Jogador jogadorRemoto;
	protected boolean partidaEmAndamento = false;
	protected boolean jogadaEmAndamento = false;
	protected EstadoPentago estado;
	
	public Tabuleiro() {
		super();
		iniciar();
	}	
	
	private void iniciar() {
		partidaEmAndamento = false;
		jogadaEmAndamento = false;
		matriz = new MatrizTabuleiro();
		for (int linha=0; linha<2; linha++) {
			for (int coluna=0; coluna<2; coluna++) {
				quadrantes[linha][coluna] = new Quadrante();
			}
		}
		for (int linha=0; linha<6; linha++) {
			for (int coluna=0; coluna<6; coluna++) {
				if (linha>=0 && linha<=2) {
					if (coluna>=0 && coluna<=2) { 	// quadrante 00
						matriz.definirPosicao(linha, coluna, quadrantes[0][0].informarPosicao(linha, coluna));
					} else {  						// quadrante 01
						matriz.definirPosicao(linha, coluna, quadrantes[0][1].informarPosicao(linha, coluna-3));
					}
				} else {
					if (coluna>=0 && coluna<=2) { 	// quadrante 10
						matriz.definirPosicao(linha, coluna, quadrantes[1][0].informarPosicao(linha-3, coluna));
					} else { 					 	// quadrante 11
						matriz.definirPosicao(linha, coluna, quadrantes[1][1].informarPosicao(linha-3, coluna-3));
					}			
				}			
			}
		}
	}

	public void efetuarColocacaoPedra(int linha, int coluna) {
		Jogador jogadorTurno;
		boolean turnoLocal = jogadorLocal.informarTurno();
		if (turnoLocal) jogadorTurno = jogadorLocal;
			else jogadorTurno = jogadorRemoto;
		matriz.colocarPedra(linha, coluna, jogadorTurno);
		this.avaliarEncerramentoPartida();
		this.definirJogadaAndamento(true);
		Lance lance = new Lance(false);
		lance.definirPosicao(linha, coluna);
		this.atualizarEstado(lance);
	}
	
	public void definirJogadaAndamento(boolean valor) {	
		jogadaEmAndamento = valor;
	}
	
	public EstadoPentago informarEstado(Lance lance) {	
		return estado;
	}
	
	public String colocarPedra(int linha, int coluna) {
		String notificacao = "";
		if (partidaEmAndamento) {
			boolean turno = jogadorLocal.informarTurno();
			if (turno) {
				if (!jogadaEmAndamento) {
					Posicao posicao = matriz.informarPosicao(linha, coluna);
					boolean ocupada = posicao.informarOcupada();
					if (!ocupada) {
						this.efetuarColocacaoPedra(linha, coluna);
					} else {
						notificacao = "Posicao ocupada";
					}
				} else {
				notificacao = "Gire quadrante";
			}
			} else {
				notificacao = "Nao e seu turno";
			}
		} else {
			notificacao = "Nao ha partida em andamento";
		}
		return notificacao;
	}

	public EstadoPentago informarEstado() {	
		return estado;
	}

	public void atualizarEstado(Lance lance) {	
		EstadoPentago novoEstado = new EstadoPentago(lance);
		String mensagem;
		if (partidaEmAndamento) {
			if (!jogadaEmAndamento) {
				jogadorLocal.inverterTurno();
				jogadorRemoto.inverterTurno();
			}
			boolean turno = jogadorLocal.informarTurno();
			String nome = jogadorRemoto.informarNome();
			if (turno) nome = jogadorLocal.informarNome();
			mensagem = "Vez de "+nome;
		} else {
			boolean localVencedor = jogadorLocal.informarVencedor();
			boolean remotoVencedor = jogadorRemoto.informarVencedor();
			if (localVencedor || remotoVencedor) {
				String nome = jogadorRemoto.informarNome();
				if (localVencedor) nome = jogadorLocal.informarNome();
				mensagem = "VENCEDOR: "+nome;
			} else {
				mensagem = "Partida encerrada sem vencedor";
			}
		}
		novoEstado.assumirMensagem(mensagem);	
		boolean lanceGiro = lance.informarGiro();
		if (lanceGiro) novoEstado.atualizarDestaquesSetas();
		for (int linha=0; linha<6; linha++) {
			for (int coluna=0; coluna<6; coluna++) {
				int valor = matriz.informarValor(linha, coluna);
				novoEstado.assumirValorTabuleiro(linha, coluna, valor);
			}
		}
		this.assumirEstado(novoEstado);
	}
	
	public void assumirEstado(EstadoPentago umEstado) {
		estado = umEstado;
	}
	
	public String girarQuadrante(int quadrante, int sentido) {
		String notificacao = "";
		if (partidaEmAndamento) {
			boolean turno = jogadorLocal.informarTurno();
			if (turno) {
				if (jogadaEmAndamento) {
					this.efetuarGiroQuadrante(quadrante, sentido);
				} else {
				notificacao = "Coloque pedra";
			}
			} else {
				notificacao = "Nao e seu turno";
			}
		} else {
			notificacao = "Nao ha partida em andamento";
		}
		return notificacao;
	}

	
	public void efetuarGiroQuadrante(int quadrante, int sentido) {
		this.procederGiro(quadrante, sentido);
		this.avaliarEncerramentoPartida();
		this.definirJogadaAndamento(false);
		Lance lance = new Lance(true);
		lance.definirGiro(quadrante, sentido);
		this.atualizarEstado(lance);
	}
	
	public void receberJogada(Lance lance) {
		boolean giro = lance.informarGiro();
		if (giro) {
			int quadrante = lance.informarQuadrante();
			int sentido = lance.informarSentido();
			this.efetuarGiroQuadrante(quadrante, sentido);
		} else {
			int linha = lance.informarLinha();
			int coluna = lance.informarColuna();
			this.efetuarColocacaoPedra(linha, coluna);
		}	
	}

	public boolean encerrarPartida() {	
		if (partidaEmAndamento) {
			this.encerrarPartidaLocalmente();
			return true;
		} else return false;
	}

	public void iniciarNovaPartida(Integer ordem, String adversario) {
		this.esvaziar();
		jogadorLocal.iniciar();
		jogadorRemoto = new Jogador();
		jogadorRemoto.definirNome(adversario);
		if (ordem.equals(1)) jogadorLocal.definirComoPrimeiro();
			else jogadorRemoto.definirComoPrimeiro();
		partidaEmAndamento = true;	
		this.definirEstadoInicial();
	}
	
	public void esvaziar() {	
		this.iniciar();
	}

	public void encerrarPartidaLocalmente() {	
		this.esvaziar();
		jogadorLocal.iniciar();
		jogadorRemoto = new Jogador();
		jogadorRemoto.iniciar();
		estado = new EstadoPentago();
	}

	public void avaliarEncerramentoPartida() {	
		Jogador jogadorTurno;
		Jogador adversario;
		boolean adversarioVencedor = false;
		boolean registroTurnoVencedor = false;
		RenquePosicao auxLinha;
		RenquePosicao auxColuna;
		RenquePosicao auxDiagonal;
		if (jogadorLocal.informarTurno()) {
			jogadorTurno = jogadorLocal;
			adversario = jogadorRemoto;
		} else {
			jogadorTurno = jogadorRemoto;
			adversario = jogadorLocal;		}
		for (int indice=0; indice<6; indice++) {		// avaliacao de linhas
			auxLinha = matriz.informarLinha(indice);
			if (!adversarioVencedor) adversarioVencedor = auxLinha.avaliarCondicaoVitoria(adversario);
			if (!adversarioVencedor) 
				if (!registroTurnoVencedor) registroTurnoVencedor = auxLinha.avaliarCondicaoVitoria(jogadorTurno);
		}
		if (!adversarioVencedor) {
			for (int indice=0; indice<6; indice++) {	// avaliacao de colunas
				auxColuna = matriz.informarColuna(indice);
				if (!adversarioVencedor) adversarioVencedor = auxColuna.avaliarCondicaoVitoria(adversario);
				if (!adversarioVencedor) 
					if (!registroTurnoVencedor) registroTurnoVencedor = auxColuna.avaliarCondicaoVitoria(jogadorTurno);
			}
		}
		if (!adversarioVencedor) {
			for (int indice=0; indice<6; indice++) {	// avaliacao de diagonais
				auxDiagonal = matriz.informarDiagonal(indice);
				if (!adversarioVencedor) adversarioVencedor = auxDiagonal.avaliarCondicaoVitoria(adversario);
				if (!adversarioVencedor) 
					if (!registroTurnoVencedor) registroTurnoVencedor = auxDiagonal.avaliarCondicaoVitoria(jogadorTurno);
			}
		}
		if (adversarioVencedor) {		// verificacao de existencia de vencedor
			adversario.definirVencedor(true);
			this.definirPartidaEmAndamento(false);
		} else {
			if (registroTurnoVencedor) {
				jogadorTurno.definirVencedor(true);
				this.definirPartidaEmAndamento(false);
			}
		}
		if (partidaEmAndamento) {	// verificacao de partida encerrada sem vencedor
			boolean tabuleiroCheio = this.informarCheio();
			if (tabuleiroCheio && jogadaEmAndamento)
				this.definirPartidaEmAndamento(false);
		}
	}
	
	public void definirPartidaEmAndamento(boolean valor) {	
		partidaEmAndamento = valor;
	}
	
	public boolean informarCheio() {
		for (int linha=0; linha<6; linha++) {
			for (int coluna=0; coluna<6; coluna++) {
				if (matriz.informarValor(linha, coluna) == 0) return false;
			}
		}
		return true;
	}
	
	public void procederGiro(int quadrante, int sentido) {
		Quadrante quadranteIdentificado = this.informarQuadrante(quadrante);
		quadranteIdentificado.procederGiro(sentido);
	}
	
	public Quadrante informarQuadrante(int quadrante) {
		Quadrante selecionado = null;
		switch (quadrante){
        	case 1:
        		selecionado = quadrantes[0][0];
        		break;
        	case 2:
        		selecionado = quadrantes[0][1];
        		break;
        	case 3:
        		selecionado = quadrantes[1][0];
        		break;
        	case 4:
        		selecionado = quadrantes[1][1];
        		break;
		}
		return selecionado;
	}
	
	public void registrarJogadorLocal(String jogador) {
		jogadorLocal = new Jogador();
		jogadorLocal.definirNome(jogador);
		jogadorLocal.iniciar();
	}
	
	public void definirEstadoInicial() {
		estado = new EstadoPentago();
		if (jogadorLocal.informarTurno()) {
			estado.assumirMensagem("Vez de " + jogadorLocal.informarNome());
		}	else {
			estado.assumirMensagem("Vez de " + jogadorRemoto.informarNome());
		}
		this.definirPartidaEmAndamento(true);
	}

}
