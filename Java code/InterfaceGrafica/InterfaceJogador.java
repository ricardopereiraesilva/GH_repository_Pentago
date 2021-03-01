package InterfaceGrafica;

import DominioDoProblema.EstadoPentago;
import DominioDoProblema.Lance;
import DominioDoProblema.Tabuleiro;
import Rede.InterfaceNetgamesServer;

public class InterfaceJogador {
	
	protected InterfaceNetgamesServer ngames;
	protected Tabuleiro tabuleiro;
	protected InterfacePentago gui;

	public InterfaceJogador() {
		ngames = new InterfaceNetgamesServer();
		tabuleiro = new Tabuleiro();
	}
	
	public InterfaceJogador(InterfacePentago interfacePentago) {
		super();
		iniciar(interfacePentago);
	}
	
	private void iniciar(InterfacePentago interfacePentago) {
		gui = interfacePentago;
		ngames = new InterfaceNetgamesServer();
		tabuleiro = new Tabuleiro();		
		ngames.definirInterfaceJogador(this);
	}

	public String colocarPedra(int linha, int coluna) {
		String notificacao = tabuleiro.colocarPedra(linha, coluna);
		if (notificacao == "") {
			EstadoPentago estado = tabuleiro.informarEstado();
			Lance lance = estado.informarLance();
			ngames.enviarJogada(lance);
		}
		return(notificacao);
	}
	
	public EstadoPentago informarEstado() {
		return tabuleiro.informarEstado();
	}

	public String girarQuadrante(int quadrante, int sentido) {
		String notificacao = tabuleiro.girarQuadrante(quadrante, sentido);
		if (notificacao == "") {
			EstadoPentago estado = tabuleiro.informarEstado();
			Lance lance = estado.informarLance();
			ngames.enviarJogada(lance);
		}
		return(notificacao);

	}
	
	public void receberJogada(Lance lance) {
		tabuleiro.receberJogada(lance);
		gui.exibirEstado();
	}

	public void conectar() {
		boolean conectado = ngames.informarConectado();
		if(!conectado) {
			String jogador = gui.obterNomeJogador();
			String servidor = gui.obterEnderecoServidor();
			String notificacao = ngames.conectar(servidor, jogador);
			tabuleiro.registrarJogadorLocal(jogador);
			gui.notificar(notificacao);
		} else {
			gui.notificar("Voce ja esta conectado");
		}
	}
	
	public boolean desconectar() {
		boolean conectado = ngames.informarConectado();
		boolean atualizarInterface = false;
		if(conectado) {
			atualizarInterface = tabuleiro.encerrarPartida();
			if (atualizarInterface) ngames.encerrarPartida();
			ngames.desconectar();
			gui.notificar("Voce esta desconectado");
		} else {
			gui.notificar("Voce nao esta conectado");
		}
		return atualizarInterface;
	}
	
	public boolean iniciarPartida() {
		boolean conectado = ngames.informarConectado();
		boolean atualizarInterface = false;
		if(conectado) {
			atualizarInterface = tabuleiro.encerrarPartida();
			if (atualizarInterface) ngames.encerrarPartida();
			ngames.iniciarPartida();
		} else {
			gui.notificar("Voce nao esta conectado");
		}
		return atualizarInterface;

	}
	
	public void iniciarNovaPartida(Integer ordem, String adversario) {
		tabuleiro.iniciarNovaPartida(ordem, adversario);
		gui.exibirEstado();
	}
	
	public void encerrarPartida() {
		boolean atualizar = tabuleiro.encerrarPartida();
		gui.notificar("Partida finalizada");
		if (atualizar) gui.exibirEstado();
	}


}
