package daw;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel {

	// Números del 0 al 9 y seis botones más para
	// suma, resta, multiplicacion, división, resultado y limpiar
	JButton[] grupoBotones = new JButton[16];

	public PanelBotones() {
		initComponents();
	}

	private void initComponents() {

		// Creación de los botones
		// Los números del 0 al 9
		for (int i = 0; i < 10; i++) {
			grupoBotones[i] = new JButton(Integer.toString(i));
		}
		// Operadores y botones especiales
		grupoBotones[10] = new JButton(" + ");
		grupoBotones[11] = new JButton(" - ");
		grupoBotones[12] = new JButton(" * ");
		grupoBotones[13] = new JButton(" / ");
		grupoBotones[14] = new JButton("=");
		grupoBotones[15] = new JButton("C");

		// Establecemos el layout (4 filas, 4 columnas)
		this.setLayout(new GridLayout(4, 4));

		// Añadimos los botones según la distribución pedida:
		// Fila 1: 7 8 9 +
		this.add(grupoBotones[7]); // 7
		this.add(grupoBotones[8]); // 8
		this.add(grupoBotones[9]); // 9
		this.add(grupoBotones[10]); // +
		
		// Fila 2: 4 5 6 -
		this.add(grupoBotones[4]); // 4
		this.add(grupoBotones[5]); // 5
		this.add(grupoBotones[6]); // 6
		this.add(grupoBotones[11]); // -
		
		// Fila 3: 1 2 3 *
		this.add(grupoBotones[1]); // 1
		this.add(grupoBotones[2]); // 2
		this.add(grupoBotones[3]); // 3
		this.add(grupoBotones[12]); // *
		
		// Fila 4: 0 / C =
		this.add(grupoBotones[0]); // 0
		this.add(grupoBotones[13]); // /
		this.add(grupoBotones[15]); // C
		this.add(grupoBotones[14]); // =
	}

	public JButton[] getGrupoBotones() {
		return grupoBotones;
	}

}
