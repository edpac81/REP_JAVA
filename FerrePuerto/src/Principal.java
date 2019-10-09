import javax.swing.JOptionPane;

public class Principal
{
	public static void main (String agrs[])
	{
		Procesos ferrePuerto = new Procesos ();
		ferrePuerto.procesoFerre();
		
		JOptionPane.showMessageDialog(null, "Gracias por usar el programa");
		
		System.exit(0);
	}
}
