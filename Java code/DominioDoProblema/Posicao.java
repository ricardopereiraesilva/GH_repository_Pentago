package DominioDoProblema;

public class Posicao {
	
	protected Jogador ocupante;
	
	public void definirOcupante(Jogador jogador) {
		ocupante = jogador;
	}

	public boolean informarOcupada() {
		return (ocupante != null);
	}

	public Jogador informarOcupante() {
		return ocupante;
	}
	
	public boolean ocupadaPor(Jogador jogador) {
		return (ocupante == jogador);
	}

}
