import javax.swing.JOptionPane;
//inicio de la clase principal
public class Cliente
{
	String nombre, direccion, telefono, cedula;// variables o atributos
	//inicio metodo constructor
	public Cliente (String nombre, String direccion, String telefono, String cedula)
	{
		setNombre(nombre);
		setDireccion(direccion);
		setTelefono(telefono);
		setCedula(cedula);
	}//fin metodo constructor
	
	//inicio metodos set
	public void setNombre (String nombre)
	{
		this.nombre = nombre;
	}
	
	public void setDireccion (String direccion)
	{
		this.direccion = direccion;
	}
	
	public void setTelefono (String telefono)
	{
		this.telefono = telefono;
	}
	
	public void setCedula (String cedula)
	{
		this.cedula = cedula;
	}
	//fin metodos set
	
	//inicio metodos get
	public String getNombre ()
	{
		return this.nombre = nombre;
	}
	
	public String getDireccion ()
	{
		return this.direccion = direccion;
	}
	
	public String getTelefono ()
	{
		return this.telefono = telefono;
	}
	
	public String getCedula ()
	{
		return this.cedula = cedula;
	}
	//fin metodos get
	
	//metodo get de informacion
	public String getInformacion()
	{
		return "El cliente: " + nombre + " , de direccion: " + direccion + " , de cedula: " + cedula + " , de telefono: " + telefono;
	}//fin metodo get de informacion
}//fin clase principal
