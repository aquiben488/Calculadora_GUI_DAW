package daw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
					String.valueOf(this.botonera.getGrupoBotones()[index].getText())));
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
		// aqui hay un problema, debido a la prioridad de los operadores,
		// primero se deben realizar las multiplicaciones y divisiones
		// y despues las sumas y restas
		// para ellos vamos a dar 2 vueltas, la primera para realizar las
		// multiplicaciones y divisiones
		// y la segunda para realizar las sumas y restas

		String[] operacionArray1 = operacion.split(" ");
		List<String> operacionArray2 = new ArrayList<>();

		// flag para saber si se ha realizado una operacion de multiplicacion o division
		boolean flag = false;
		// ultimo resultado de la operacion de multiplicacion o division
		double ultimoResultado = 0;

		double operando1 = 0;
		double operando2;

		for (int i = 1; i < operacionArray1.length; i += 2) {
			if (operacionArray1[i].equals("*") || operacionArray1[i].equals("/")) {
				// si flag es true, se ha realizado una operacion de multiplicacion o division
				// por lo que se debe usar el ultimo resultado
				operando1 = flag ? ultimoResultado : Double.parseDouble(operacionArray1[i - 1]);
				operando2 = Double.parseDouble(operacionArray1[i + 1]);
				ultimoResultado = switch (operacionArray1[i]) {
					case "*" -> operando1 * operando2;
					case "/" -> operando1 / operando2;
					default -> 0;
				};
				// se ha realizado una operacion de multiplicacion o division
				// por lo que se debe poner flag a true
				flag = true;
			} else {
				// si flag es true, se ha realizado una operacion de multiplicacion o division
				// por lo que se debe usar el ultimo resultado
				operacionArray2.add(flag ? String.valueOf(ultimoResultado) : operacionArray1[i - 1]);

				operacionArray2.add(operacionArray1[i]);
				// se ha realizado una operacion de suma o resta
				// por lo que se debe poner flag a false
				flag = false;
			}

		}
		operacionArray2.add(flag ? String.valueOf(ultimoResultado) : operacionArray1[operacionArray1.length - 1]);


		operando1 = Double.parseDouble(operacionArray2.get(0));
		for (int i = 1; i < operacionArray2.size() - 1; i += 2) {
			operando2 = Double.parseDouble(operacionArray2.get(i + 1));
			operando1 = switch (operacionArray2.get(i)) {
				case "+" -> operando1 + operando2;
				case "-" -> operando1 - operando2;
				default -> 0;
			};
		}
		String resultado = String.format("%.2f", operando1);
		areaTexto.setText(resultado);
	}

	private void limpiar() {
		operacion = "";
		areaTexto.setText("");
	}

	private void actualizarPantalla(String texto) {
		areaTexto.setText(areaTexto.getText() + texto);
	}
}
