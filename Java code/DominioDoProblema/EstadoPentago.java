package DominioDoProblema;

public class EstadoPentago {
	
	protected String mensagem = "Jogo Pentago - aguardando iniciar partida";
	protected int valoresTabuleiro[][] = new int [6][6];
	protected int destaquesSetas[][] = new int [4][2];
	protected Lance lance = new Lance(false);
	
	public EstadoPentago(Lance umLance) {
		lance = umLance;
	}

	public EstadoPentago() {
		// TODO Auto-generated constructor stub
	}

	public void assumirMensagem(String umaMensagem) {
		mensagem = umaMensagem;
	}
	
	public String informarMensagem() {
		return mensagem;
	}
	
	public Lance informarLance() {
		return lance;
	}

	public void atualizarDestaquesSetas() {
		int quadrante = lance.informarQuadrante();
		int sentido = lance.informarSentido();
		switch (quadrante){
    	case 1:
    		if (sentido ==0) this.assumirValorSeta(0, 1, 1);	//v1
    		else this.assumirValorSeta(0, 0, 1); 				//h1
    		break;
    	case 2:
    		if (sentido ==0) this.assumirValorSeta(1, 0, 1);	//h2
    		else this.assumirValorSeta(1, 1, 1); 				//v2
    		break;
    	case 3:
    		if (sentido ==0) this.assumirValorSeta(2, 0, 1);	//h3
    		else this.assumirValorSeta(2, 1, 1); 				//v3
    		break;
    	case 4:
    		if (sentido ==0) this.assumirValorSeta(3, 1, 1);	//v4
    		else this.assumirValorSeta(3, 0, 1); 				//h4
    		break;
		}
	}

	public void assumirValorTabuleiro(int linha, int coluna, int valor) {
		valoresTabuleiro [linha] [coluna] = valor;
	}
	
	public int informarValorTabuleiro(int linha, int coluna) {
		return valoresTabuleiro [linha] [coluna];
	}

	public void assumirValorSeta(int linha, int coluna, int valor) {
		destaquesSetas [linha] [coluna] = valor;
	}
	
	public int informarValorSeta(int linha, int coluna) {
		return destaquesSetas [linha] [coluna];
	}
}
