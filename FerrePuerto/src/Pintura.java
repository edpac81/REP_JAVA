import javax.swing.JOptionPane;
//inicio de la clase principal
public class Pintura
{
	String color;
	int unidades=0, precio=0;//variables o atributos
	//inicio metodo constructor
	public Pintura (String color, int unidades, int precio)
	{
		setColor(color);
		setUnidades(unidades);
		setPrecio(precio);
	}// fin metodo constructor
	
	//inicio metodos set
	public void setColor (String color)
	{
		this.color = color;
	}
	
	public void setUnidades (int unidades)
	{
		this.unidades = unidades;
	}
	
	public void setPrecio (int precio)
	{
		this.precio = precio;
	}
	//fin metodos set
	
	//inicio metodos get
	public String getColor()
	{
		return this.color = color;
	}
	
	public int getUnidades()
	{
		return this.unidades = unidades;
	}
	
	public int getPrecio()
	{
		return this.precio = precio;
	}
	//fin metodos get
	
	//inicio metodo get de informacion
	public String getInformacion()
	{
		return "Pintura color: " + color + ", unidades: " + unidades + ", tienen un precio de: " + precio;
	}//fin metodos get de informacion
}
