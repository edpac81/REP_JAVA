import javax.swing.JOptionPane;
//inicio de clase procesos
public class Procesos
{
	String nombre, direccion, telefono, cedula;
		int recaudado=0, unidades=0, precio=0, opcion1=0, opcion2=0, opcion3=0, madera=0, cemento=0, totalMadera=0,totalLaminasZinc=0, laminasZinc=0, totalCemento=0, galonesRojos=0, galonesVerdes=0, precioPinturaRoja=0, nuevaUnidad=0, precioPinturaVerde=0, galonesAmarillos=0, precioPinturaAmarilla=0;
		double subtotal=0, total=0, descuento=0;//nombre variables
		//inicio de instancia cliente
		Cliente cliente = new Cliente ("","","","");
		//fin de instancia cliente
		
		//inicio instancia pintura
		Pintura pintura = new Pintura ("",0,0);
		//fin instancia pintura
		
		//inicio metodo cliente
		public void cliente()
		{//solicitud datos del cliente
		cliente.setNombre(JOptionPane.showInputDialog("Digite el nombre del cliente"));
		cliente.setCedula(JOptionPane.showInputDialog("Digite la cedula del cliente"));
		cliente.setDireccion(JOptionPane.showInputDialog("Digite la direccion del cliente"));
		cliente.setTelefono(JOptionPane.showInputDialog("Digite el telefono del cliente"));
		}// fin metodo cliente
		
		//inicio de instancias pinturas
		Pintura roja = new Pintura ("Pintura Roja", 50, 15000);
		Pintura verde = new Pintura ("Pintura Verde", 100, 25000);
		Pintura amarilla = new Pintura ("Pintura Amarilla", 150, 30000);
		//fin instancias pinturas
		
		//inicio metodo menu
		public void menu ()
		{
		do// inicio do while menu principal
		{
			//solicitud de opcion del menu principal
			opcion1 = Integer.parseInt(JOptionPane.showInputDialog("Escoja la opcion que desea realizar: \n1.Facturacion de productos \n2.Control de caja \n3.Salir"));
			
			switch ( opcion1 )//inicio switch
			{
				case 1://inicio del case 1
				
				do//inicio do while menu de compra
				{
					//solicitud de opcion de compra
					opcion2 = Integer.parseInt(JOptionPane.showInputDialog("Escoja lo que desea comprar: \n1.Compra de madera \n2.Pintura \n3.Compra de Cemento \n4.Compra de Zinc \n5.Facturacion Final \n6.Saliendo al menu principal"));
				
					switch (opcion2)//switch menu de compra
					{
						case 1:
						//inicio de case 1 menu compras
						if (madera == 0)
						{
							madera = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de metros cuadrados que desea comprar"));
							totalMadera += madera * 25000;
						}
						
						else
						{
							JOptionPane.showMessageDialog(null, "Ya no puede realizar esta compra");
						}
						break;
						// fin case 1 menu compras
						case 2:
						//inicio case 2 menu compras
						
						//solicitud de opcion color pintura
						opcion3 = Integer.parseInt(JOptionPane.showInputDialog("Escoja el color que desea comprar: \n1.Roja \n2.Verde \n3.Amarilla"));
						
						switch (opcion3) //switch menu color pintura
						{
							case 1://inicio case 1 menu pintura
							
							if (galonesRojos ==0)
							{
								galonesRojos = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de unidades que desea comprar"));
								if (galonesRojos<roja.getUnidades())
								{
									precioPinturaRoja += galonesRojos * roja.getPrecio();
									nuevaUnidad = roja.getUnidades() - galonesRojos;
									roja.setUnidades(nuevaUnidad);
								}
								
								else// no se cumple el if
								{
									JOptionPane.showMessageDialog(null, "No puede realizar la compra porque sobrepasa el limite de unidades");
								}								
							}
							
							else// no se cumple el if principal
							{
								JOptionPane.showMessageDialog(null, "Ya no puede realizar la compra");
							}
							break;// cierra case 1 menu pintura
							
							case 2:
							//inicio case 2 menu pintura
							
							if (galonesVerdes ==0)
							{
								galonesVerdes = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de unidades que desea comprar"));
								if (galonesVerdes<verde.getUnidades())
								{
									precioPinturaVerde += galonesVerdes * verde.getPrecio();
									nuevaUnidad = verde.getUnidades() - galonesVerdes;
									verde.setUnidades(nuevaUnidad);
								}
								
								else// no se cumple if
								{
									JOptionPane.showMessageDialog(null, "No puede realizar la compra porque sobrepasa el limite de unidades");
								}								
							}
							
							else// no se cumple el if principal
							{
								JOptionPane.showMessageDialog(null, "Ya no puede realizar la compra");
							}
							break;// cierra case 2 menu pintura
							
							case 3:// inicio del case 3 menu pintura
							
							if (galonesAmarillos ==0)
							{
								galonesAmarillos = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de unidades que desea comprar"));
								if (galonesAmarillos<amarilla.getUnidades())
								{
									precioPinturaAmarilla += galonesAmarillos * amarilla.getPrecio();
									nuevaUnidad = amarilla.getUnidades() - galonesAmarillos;
									amarilla.setUnidades(nuevaUnidad);
								}
								
								else// no se cumple el if 
								{
									JOptionPane.showMessageDialog(null, "No puede realizar la compra porque sobrepasa el limite de unidades");
								}								
							}
							
							else// no se cumple el if del menu principal
							{
								JOptionPane.showMessageDialog(null, "Ya no puede realizar la compra");
							}
							}	//switch
							
							break; //Cierra case 3 menu pinturas
							
							
						
							
						
							
						case 3:// inicio del case 3 del menu compras
							
						if (cemento == 0)
						{
							cemento = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de metros cuadrados que desea comprar"));
							totalCemento += cemento * 50000;
						}
						
						else// no se cumple el if 
						{
							JOptionPane.showMessageDialog(null, "Ya no puede realizar esta compra");
						}
						break;
							
						case 4:// inicio del case 4 del menu compras 
						
						if (laminasZinc==0)
						{
							laminasZinc = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de laminas de zinc que desea comprar"));
							totalLaminasZinc += laminasZinc * 10000;
						}
						
						else// no se cumple el if
						{
							JOptionPane.showMessageDialog(null, "Ya no puedo realizar esta compra");
						}
						break;// cierra case 4
						
						case 5://inicio del case 5 facturacion
						
						subtotal = totalLaminasZinc + totalCemento + precioPinturaRoja + precioPinturaVerde + precioPinturaAmarilla + totalMadera;
						
						if (subtotal<100000)
						{
							descuento = subtotal * 0.05;
							total = subtotal - descuento;
						}
						
						else//no se cumple el if para el descuento
						{
							if(subtotal>=100000 && subtotal<300000)
							{
								descuento = subtotal * 0.10;
								total = subtotal - descuento;
							}
							
							else// no se cumple el if para el descuento
							{
								descuento = subtotal * 0.20;
								total = subtotal - descuento;
							}
						}
						
						JOptionPane.showMessageDialog(null, cliente.getInformacion() + "\nEl total de madera es de: " + totalMadera + "\nEl total de pintura roja es de: " + precioPinturaRoja + "\nEl total de pintura verde es de: " + precioPinturaVerde + "\nEl total de pintura amarilla es de: " + precioPinturaAmarilla + "\nEl total de cemento es de: " + totalCemento + "\nEl total de laminas de zinc es de: " + totalLaminasZinc + "\nEl subtotal es de: " + subtotal + "\nEl descuento es de: " + descuento + "\nEl total es de: " + total);
						
						break; //finaliza case 5 de facturacion
						
						case 6:// inicio del case 6 para salir del menu compra
						
						JOptionPane.showMessageDialog(null, "Saliendo del menu compra");
						break;// finaliza el menu compra
					}	
					
				} while (opcion2!=6); //fin do while
				madera=0;
				galonesRojos=0;
				galonesVerdes=0;
				galonesAmarillos=0;
				cemento=0;
				laminasZinc=0;
					
				break;// finaliza case 1 menu principal
				
				case 2:// inicio del case 2 muestra lo recaudado de la caja
				
				recaudado += total;
				JOptionPane.showMessageDialog(null, "El monto total de la caja es de: " + recaudado);
				
				break; // finaliza case 2 menu principal
				
				case 3:// inicio del case 3 salir del programa
				
				JOptionPane.showMessageDialog(null, "Saliendo del programa");
				break; //finaliza case 3 menu principal
							
			}//finaliza menu principal
						
		} while ( opcion1!=3); //finaliza do while menu principal
	}//fin metodo menu
		
	
	
	public void procesoFerre()
	{
		cliente();
		menu();
	}//llama todos los procesos
	
}//fin clase principal
		 
	

