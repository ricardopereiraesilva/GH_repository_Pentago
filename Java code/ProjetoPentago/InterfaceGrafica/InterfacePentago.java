package InterfaceGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import DominioDoProblema.EstadoPentago;

import javax.swing.JMenu;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.ImageIcon;

public class InterfacePentago {

	private JFrame frame;
	private InterfaceJogador interfaceJogador;
	
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	
	private JLabel labelMensagem = null;
	
	private JLabel labelSetaH1 = null;
	private JLabel labelSetaV1 = null;
	private JLabel labelSetaH2 = null;
	private JLabel labelSetaV2 = null;
	private JLabel labelSetaH3 = null;
	private JLabel labelSetaV3 = null;
	private JLabel labelSetaH4 = null;
	private JLabel labelSetaV4 = null;
	
	private JLabel matrizLabels[][] = new JLabel[6][6];
	
	private JLabel label00 = null;
	private JLabel label01 = null;
	private JLabel label02 = null;
	private JLabel label03 = null;
	private JLabel label04 = null;
	private JLabel label05 = null;
	
	private JLabel label10 = null;
	private JLabel label11 = null;
	private JLabel label12 = null;
	private JLabel label13 = null;
	private JLabel label14 = null;
	private JLabel label15 = null;

	private JLabel label20 = null;
	private JLabel label21 = null;
	private JLabel label22 = null;
	private JLabel label23 = null;
	private JLabel label24 = null;
	private JLabel label25 = null;

	private JLabel label30 = null;
	private JLabel label31 = null;
	private JLabel label32 = null;
	private JLabel label33 = null;
	private JLabel label34 = null;
	private JLabel label35 = null;

	private JLabel label40 = null;
	private JLabel label41 = null;
	private JLabel label42 = null;
	private JLabel label43 = null;
	private JLabel label44 = null;
	private JLabel label45 = null;

	private JLabel label50 = null;
	private JLabel label51 = null;
	private JLabel label52 = null;
	private JLabel label53 = null;
	private JLabel label54 = null;
	private JLabel label55 = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacePentago window = new InterfacePentago();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfacePentago() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		interfaceJogador = new InterfaceJogador(this);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 720);
		frame.setTitle("PENTAGO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		this.criarLabelMensagem(20, 25, 80);
		this.criarSetasVerticais(20, 25, 80);
		this.criarSetasHorizontais(20, 25, 80);	
		this.criarQuadrantes(20, 25, 80);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 640, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Jogo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmConectar = new JMenuItem("conectar");
		mntmConectar.setAction(action);
		mnNewMenu.add(mntmConectar);
		
		JMenuItem mntmDesconectar = new JMenuItem("desconectar");
		mntmDesconectar.setAction(action_1);
		mnNewMenu.add(mntmDesconectar);
		
		JMenuItem mntmIniciarPartida = new JMenuItem("iniciar partida");
		mntmIniciarPartida.setAction(action_2);
		mnNewMenu.add(mntmIniciarPartida);
	}
	
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "conectar");
			putValue(SHORT_DESCRIPTION, "conectar a Netgames Server");
		}
		public void actionPerformed(ActionEvent e) {
			interfaceJogador.conectar();
		}
	}
	
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "desconectar");
			putValue(SHORT_DESCRIPTION, "desconectar de Netgames Server");
		}
		public void actionPerformed(ActionEvent e) {
			boolean atualizarInterface = interfaceJogador.desconectar();
			if (atualizarInterface) exibirEstado();
		}
	}
	private class SwingAction_2 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "iniciar partida");
			putValue(SHORT_DESCRIPTION, "iniciar partida do seu jogo");
		}
		public void actionPerformed(ActionEvent e) {
			boolean atualizarInterface = interfaceJogador.iniciarPartida();
			if (atualizarInterface) exibirEstado();
		}
	}
	
	private void criarSetasHorizontais(int dOrigem, int dBase, int dPosicao) {
		labelSetaH1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaL.png")));
		labelSetaH1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.girarQuadrante(1, 1);
				if (notificacao != "") notificar(notificacao);
				else exibirEstado();
			}
		});
		labelSetaH1.setBounds((dOrigem+(2*dBase)+dPosicao-(2*(dBase/5))), (dOrigem+(2*dBase)), (4*dBase), dBase);		
		frame.getContentPane().add(labelSetaH1);
		
		labelSetaH2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaR.png")));
		labelSetaH2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.girarQuadrante(2, 0);
				if (notificacao != "") notificar(notificacao);
				else exibirEstado();
			}
		});
		labelSetaH2.setBounds((dOrigem+(2*dBase)+(4*dPosicao)-(dBase/5)), (dOrigem+(2*dBase)), (4*dBase), dBase);		
		frame.getContentPane().add(labelSetaH2);

		labelSetaH3 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaL.png")));
		labelSetaH3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.girarQuadrante(3, 0);
				if (notificacao != "") notificar(notificacao);
				else exibirEstado();
			}
		});
		labelSetaH3.setBounds((dOrigem+(2*dBase)+dPosicao-(2*(dBase/5))), (dOrigem+(5*dBase)+(6*dPosicao+(dBase/5))), (4*dBase), dBase);		
		frame.getContentPane().add(labelSetaH3);
		
		labelSetaH4 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaR.png")));
		labelSetaH4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.girarQuadrante(4, 1);
				if (notificacao != "") notificar(notificacao);
				else exibirEstado();
			}
		});
		labelSetaH4.setBounds((dOrigem+(2*dBase)+(4*dPosicao)-(dBase/5)), (dOrigem+(5*dBase)+(6*dPosicao+(dBase/5))), (4*dBase), dBase);		
		frame.getContentPane().add(labelSetaH4);
	}

	private void criarSetasVerticais(int dOrigem, int dBase, int dPosicao) {
		labelSetaV1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaU.png")));
		labelSetaV1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.girarQuadrante(1, 0);
				if (notificacao != "") notificar(notificacao);
				else exibirEstado();
			}
		});
		labelSetaV1.setBounds(dOrigem, (dOrigem+(4*dBase)+dPosicao-(2*(dBase/5))), dBase, (4*dBase));		
		frame.getContentPane().add(labelSetaV1);
		
		labelSetaV2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaU.png")));
		labelSetaV2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.girarQuadrante(2, 1);
				if (notificacao != "") notificar(notificacao);
				else exibirEstado();			}
		});
		labelSetaV2.setBounds((dOrigem+(3*dBase)+(6*dPosicao+(dBase/5))), (dOrigem+(4*dBase)+dPosicao-(2*(dBase/5))), dBase, (4*dBase));		
		frame.getContentPane().add(labelSetaV2);

		labelSetaV3 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaD.png")));
		labelSetaV3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.girarQuadrante(3, 1);
				if (notificacao != "") notificar(notificacao);
				else exibirEstado();			}
		});
		labelSetaV3.setBounds(dOrigem, (dOrigem+(4*dBase)+(4*dPosicao)+(dBase/5)), dBase, (4*dBase));		
		frame.getContentPane().add(labelSetaV3);
		
		labelSetaV4 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaD.png")));
		labelSetaV4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.girarQuadrante(4, 0);
				if (notificacao != "") notificar(notificacao);
				else exibirEstado();			}
		});
		labelSetaV4.setBounds((dOrigem+(3*dBase)+(6*dPosicao+(dBase/5))), (dOrigem+(4*dBase)+(4*dPosicao)+(dBase/5)), dBase, (4*dBase));		
		frame.getContentPane().add(labelSetaV4);
	}

	private void criarLabelMensagem(int dOrigem, int dBase, int dPosicao) {
		labelMensagem = new JLabel("Jogo Pentago - aguardando iniciar partida");
		labelMensagem.setBounds((dOrigem+(2*dBase)), (dOrigem+(dOrigem/2)), (6*dPosicao), dBase);		
		frame.getContentPane().add(labelMensagem);
	}

	private void criarQuadrantes(int dOrigem, int dBase, int dPosicao) {
		int x = dOrigem+(4*dBase);
		int y = dOrigem+(2*dBase);
		
		criarLinha1(x, y, dOrigem, dBase, dPosicao);
		x = x + dPosicao;	
		criarLinha2(x, y, dOrigem, dBase, dPosicao);
		x = x + dPosicao;
		criarLinha3(x, y, dOrigem, dBase, dPosicao);
		x = x + dPosicao + (dBase/5);
		criarLinha4(x, y, dOrigem, dBase, dPosicao);
		x = x + dPosicao;	
		criarLinha5(x, y, dOrigem, dBase, dPosicao);
		x = x + dPosicao;
		criarLinha6(x, y, dOrigem, dBase, dPosicao);
		
		matrizLabels[0][0] = label00;
		matrizLabels[0][1] = label01;
		matrizLabels[0][2] = label02;
		matrizLabels[0][3] = label03;
		matrizLabels[0][4] = label04;
		matrizLabels[0][5] = label05;

		matrizLabels[1][0] = label10;
		matrizLabels[1][1] = label11;
		matrizLabels[1][2] = label12;
		matrizLabels[1][3] = label13;
		matrizLabels[1][4] = label14;
		matrizLabels[1][5] = label15;
					
		matrizLabels[2][0] = label20;
		matrizLabels[2][1] = label21;
		matrizLabels[2][2] = label22;
		matrizLabels[2][3] = label23;
		matrizLabels[2][4] = label24;
		matrizLabels[2][5] = label25;
					
		matrizLabels[3][0] = label30;
		matrizLabels[3][1] = label31;
		matrizLabels[3][2] = label32;
		matrizLabels[3][3] = label33;
		matrizLabels[3][4] = label34;
		matrizLabels[3][5] = label35;
					
		matrizLabels[4][0] = label40;
		matrizLabels[4][1] = label41;
		matrizLabels[4][2] = label42;
		matrizLabels[4][3] = label43;
		matrizLabels[4][4] = label44;
		matrizLabels[4][5] = label45;
					
		matrizLabels[5][0] = label50;
		matrizLabels[5][1] = label51;
		matrizLabels[5][2] = label52;
		matrizLabels[5][3] = label53;
		matrizLabels[5][4] = label54;
		matrizLabels[5][5] = label55;
	}
	
	private void criarLinha1(int x, int y, int dOrigem, int dBase, int dPosicao) {
		
		label00 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label00.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(0, 0);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label00.setBounds(y, x, dPosicao, dPosicao);		
		frame.getContentPane().add(label00);
		
		y = y + dPosicao;
		
		label01 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(0, 1);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}

			}
		});
		label01.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label01);
		
		y = y + dPosicao;
		
		label02 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(0, 2);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}

			}
		});
		label02.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label02);
		
		y = y + dPosicao + (dBase/5);
		
		label03 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label03.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(0, 3);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}

			}
		});
		label03.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label03);
		
		y = y + dPosicao;
		
		label04 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label04.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(0, 4);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}

			}
		});
		label04.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label04);
		
		y = y + dPosicao;
		
		label05 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label05.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(0, 5);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}

			}
		});
		label05.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label05);	
	}
	
	private void criarLinha2(int x, int y, int dOrigem, int dBase, int dPosicao) {
		
		label10 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(1, 0);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label10.setBounds(y, x, dPosicao, dPosicao);		
		frame.getContentPane().add(label10);
		
		y = y + dPosicao;
		
		label11 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(1, 1);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label11.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label11);
		
		y = y + dPosicao;
		
		label12 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(1, 2);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label12.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label12);
		
		y = y + dPosicao + (dBase/5);
		
		label13 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(1, 3);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label13.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label13);
		
		y = y + dPosicao;
		
		label14 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(1, 4);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label14.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label14);
		
		y = y + dPosicao;
		
		label15 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(1, 5);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label15.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label15);	
	}

	private void criarLinha3(int x, int y, int dOrigem, int dBase, int dPosicao) {
		
		label20 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(2, 0);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label20.setBounds(y, x, dPosicao, dPosicao);		
		frame.getContentPane().add(label20);
		
		y = y + dPosicao;
		
		label21 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(2, 1);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label21.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label21);
		
		y = y + dPosicao;
		
		label22 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label22.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(2, 2);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label22.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label22);
		
		y = y + dPosicao + (dBase/5);
		
		label23 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label23.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(2, 3);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label23.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label23);
		
		y = y + dPosicao;
		
		label24 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label24.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(2, 4);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label24.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label24);
		
		y = y + dPosicao;
		
		label25 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label25.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(2, 5);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label25.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label25);	
	}

	private void criarLinha4(int x, int y, int dOrigem, int dBase, int dPosicao) {
		
		label30 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label30.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(3, 0);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label30.setBounds(y, x, dPosicao, dPosicao);		
		frame.getContentPane().add(label30);
		
		y = y + dPosicao;
		
		label31 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label31.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(3, 1);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label31.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label31);
		
		y = y + dPosicao;
		
		label32 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label32.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(3, 2);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label32.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label32);
		
		y = y + dPosicao + (dBase/5);
		
		label33 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label33.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(3, 3);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label33.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label33);
		
		y = y + dPosicao;
		
		label34 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label34.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(3, 4);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label34.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label34);
		
		y = y + dPosicao;
		
		label35 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label35.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(3, 5);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label35.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label35);	
	}

	private void criarLinha5(int x, int y, int dOrigem, int dBase, int dPosicao) {
		
		label40 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label40.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(4, 0);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label40.setBounds(y, x, dPosicao, dPosicao);		
		frame.getContentPane().add(label40);

		
		y = y + dPosicao;
		
		label41 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label41.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(4, 1);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label41.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label41);
		
		y = y + dPosicao;
		
		label42 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label42.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(4, 2);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label42.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label42);
		
		y = y + dPosicao + (dBase/5);
		
		label43 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label43.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(4, 3);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label43.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label43);
		
		y = y + dPosicao;
		
		label44 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label44.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(4, 4);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label44.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label44);
		
		y = y + dPosicao;
		
		label45 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label45.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(4, 5);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label45.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label45);	
	}

	private void criarLinha6(int x, int y, int dOrigem, int dBase, int dPosicao) {
		
		label50 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label50.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(5, 0);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label50.setBounds(y, x, dPosicao, dPosicao);		
		frame.getContentPane().add(label50);
		
		y = y + dPosicao;
		
		label51 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label51.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(5, 1);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label51.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label51);
		
		y = y + dPosicao;
		
		label52 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label52.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(5, 2);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label52.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label52);
		
		y = y + dPosicao + (dBase/5);
		
		label53 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label53.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(5, 3);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label53.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label53);
		
		y = y + dPosicao;
		
		label54 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label54.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(5, 4);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
			}
		});
		label54.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label54);
		
		y = y + dPosicao;
		
		label55 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png")));
		label55.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String notificacao = interfaceJogador.colocarPedra(5, 5);
				if (notificacao != "") {
					notificar(notificacao);
				} else {
					exibirEstado();
				}
//				ImageIcon branco = new ImageIcon(getClass().getClassLoader().getResource("Imagens/branco.png"));
//				label55.setIcon(branco);
			}
		});
		label55.setBounds(y, x, dPosicao, dPosicao);
		frame.getContentPane().add(label55);	
	}
	
	// METODOS DE MANIPULACAO E ATUALIZACAO DE INTERFACE
	
	public void colocarPedra(int linha, int coluna) {
	}
	
	public void notificar(String notificacao) {
		JOptionPane.showMessageDialog(null, notificacao);
	}
	
	public void exibirEstado() {
		EstadoPentago estado;
		estado = interfaceJogador.informarEstado();
		this.exibirMensagem(estado.informarMensagem());
		this.exibirSetas(estado);
		this.exibirPosicoes(estado);
		}
	
	private void exibirMensagem(String mensagem) {
		labelMensagem.setText(mensagem);
	}		

	private void exibirSetas(EstadoPentago estado) {
		ImageIcon setaL = new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaL.png"));
		ImageIcon setaR = new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaR.png"));
		ImageIcon setaD = new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaD.png"));
		ImageIcon setaU = new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaU.png"));
		ImageIcon setaLS = new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaLS.png"));
		ImageIcon setaRS = new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaRS.png"));
		ImageIcon setaDS = new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaDS.png"));
		ImageIcon setaUS = new ImageIcon(getClass().getClassLoader().getResource("Imagens/setaUS.png"));
		
		int valorSetaH1 = estado.informarValorSeta(0, 0);
		int valorSetaV1 = estado.informarValorSeta(0, 1);
		int valorSetaH2 = estado.informarValorSeta(1, 0);
		int valorSetaV2 = estado.informarValorSeta(1, 1);
		int valorSetaH3 = estado.informarValorSeta(2, 0);
		int valorSetaV3 = estado.informarValorSeta(2, 1);
		int valorSetaH4 = estado.informarValorSeta(3, 0);
		int valorSetaV4 = estado.informarValorSeta(3, 1);
		
		if (valorSetaH1 == 0) {
			labelSetaH1.setIcon(setaL);
		} else {
			labelSetaH1.setIcon(setaLS);
		}
		if (valorSetaV1 == 0) {
			labelSetaV1.setIcon(setaU);
		} else {
			labelSetaV1.setIcon(setaUS);
		}
		if (valorSetaH2 == 0) {
			labelSetaH2.setIcon(setaR);
		} else {
			labelSetaH2.setIcon(setaRS);
		}
		if (valorSetaV2 == 0) {
			labelSetaV2.setIcon(setaU);
		} else {
			labelSetaV2.setIcon(setaUS);
		}
		if (valorSetaH3 == 0) {
			labelSetaH3.setIcon(setaL);
		} else {
			labelSetaH3.setIcon(setaLS);
		}
		if (valorSetaV3 == 0) {
			labelSetaV3.setIcon(setaD);
		} else {
			labelSetaV3.setIcon(setaDS);
		}
		if (valorSetaH4 == 0) {
			labelSetaH4.setIcon(setaR);
		} else {
			labelSetaH4.setIcon(setaRS);
		}
		if (valorSetaV4 == 0) {
			labelSetaV4.setIcon(setaD);
		} else {
			labelSetaV4.setIcon(setaDS);
		}
	}
	
	private void exibirPosicoes(EstadoPentago estado) {
		ImageIcon branco = new ImageIcon(getClass().getClassLoader().getResource("Imagens/branco.png"));
		ImageIcon vermelho = new ImageIcon(getClass().getClassLoader().getResource("Imagens/vermelho.png"));
		ImageIcon vazia = new ImageIcon(getClass().getClassLoader().getResource("Imagens/vazia.png"));
		int valor = 0;
		for (int linha=0; linha<6; linha++){
			for (int coluna=0; coluna<6; coluna++){
				valor = estado.informarValorTabuleiro(linha, coluna);
				switch (valor){
				case 0: matrizLabels[(linha)][(coluna)].setIcon(vazia);
				break;
				case 1: matrizLabels[(linha)][(coluna)].setIcon(branco);
				break;
				case 2: matrizLabels[(linha)][(coluna)].setIcon(vermelho);
				}
			};
		};		
	}

	public void girarQuadrante(int quadrante, int sentido) {
	}
	
	public String obterNomeJogador() {
		String nome = JOptionPane.showInputDialog("Qual o seu nome?");
		return nome;
	}
	
	public String obterEnderecoServidor() {
		String idServidor = ("localhost");
		idServidor = JOptionPane.showInputDialog(null, "Insira o endereço do servidor", idServidor);
		return idServidor;
	}
		
}
