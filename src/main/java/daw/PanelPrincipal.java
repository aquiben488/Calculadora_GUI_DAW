package daw;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Juan Carlos Fernández Vico
 */

public class PanelPrincipal extends JPanel {

	// Atributos de la clase (privados)
	private PanelBotones botonera;
	private JTextArea areaTexto;
	// Podriamos hacer una calculadora para hacer operaciones de solo 2, pero
	// quien seria yo si no me complico la vida?
	private String operacion;

	// Constructor
	public PanelPrincipal() {
		initComponents();
		operacion = ""; // No hay operaciones en la calculadora
	}

	// Se inicializan los componentes gráficos y se colocan en el panel
	private void initComponents() {
		// Creamos el panel de botones
		botonera = new PanelBotones();
		// Creamos el área de texto
		areaTexto = new JTextArea(10, 30);
		areaTexto.setEditable(false);
		areaTexto.setBackground(Color.white);

		// Establecemos layout del panel principal
		this.setLayout(new BorderLayout());
		// Colocamos la botonera y el área texto
		this.add(areaTexto, BorderLayout.NORTH);
		this.add(botonera, BorderLayout.SOUTH);

		for (int i = 0; i < 10; i++) {
			final int index = i;
			this.botonera.getGrupoBotones()[i].addActionListener(e -> manejarNumero(
				String.valueOf(this.botonera.getGrupoBotones()[index].getText())
				));
		}

		for (int i = 10; i < 14; i++) {
			final int index = i;
			this.botonera.getGrupoBotones()[i].addActionListener(
					e -> manejarOperador(String.valueOf(this.botonera.getGrupoBotones()[index].getText())));
		}

		this.botonera.getGrupoBotones()[14].addActionListener(e -> calcularResultado());
		this.botonera.getGrupoBotones()[15].addActionListener(e -> limpiar());
	}

	private void manejarNumero(String numero) {
		operacion += numero;
		actualizarPantalla(numero);
	}

	private void manejarOperador(String operador) {
		operacion += " " + operador + " ";
		actualizarPantalla(operador);
	}

	private void calcularResultado() {
		
	}

	private void limpiar() {
		operacion = "";
		areaTexto.setText("");
	}

	private void actualizarPantalla(String texto) {
		areaTexto.setText(areaTexto.getText() + texto);
	}
}
