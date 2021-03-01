package DominioDoProblema;

public class Jogador {
	
	protected String nome;
	protected int cor = 2;
	protected boolean seuTurno = false;
	protected boolean vencedor = false;
	
	public boolean informarTurno() {
		return seuTurno;
	}

	public void inverterTurno() {
		if (seuTurno) {
			seuTurno = false;
		} else {
			seuTurno = true;
		}
	}

	public String informarNome() {
		return nome;
	}

	public boolean informarVencedor() {
		return vencedor;
	}

	public int informarCor() {
		return cor;
	}

	public void iniciar() {
		cor = 2;
		seuTurno = false;
		vencedor = false;
	}

	public void definirNome(String jogador) {
		nome = jogador;
	}

	public void definirComoPrimeiro() {
		cor = 1;
		seuTurno = true;
	}

	public void definirVencedor(boolean valor) {
		vencedor = valor;
	}

}
