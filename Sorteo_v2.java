import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.*;
public class Sorteo_v2 {

	public static void main(String[] args) 
	{
		int menuOption, elGordo = 0;
		boolean salir = false ,sorteoTerminado = false;
		ArrayList <Integer> boletosComprados = new ArrayList<Integer>();
		ArrayList <Integer> premios60k = new ArrayList<Integer>();
		ArrayList <Integer> premios20k = new ArrayList<Integer>();
		ArrayList <Integer> premios100 = new ArrayList<Integer>();
		ArrayList <Integer> pedrea = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> boletosPremiados = new ArrayList<ArrayList<Integer>>();
		boletosPremiados.add(premios60k);
		boletosPremiados.add(premios20k);
		boletosPremiados.add(premios100);
		do 
		{
			menuOption = ElegirOption(1);
			switch(menuOption) 
			{
				case 1:
					MenuBoletos(boletosComprados);
					break;
				case 2:
					if(boletosComprados.size() == 0) 
					{
						System.out.println("No has comprado boletos, vaya el menu de los boletos pulsando 1");
					}
					else 
					{
						System.out.println("Ha comprado " +boletosComprados.size() + " boletos");
						System.out.println("Boletos: " +boletosComprados);
					}
					break;
				case 3:
						if(!sorteoTerminado) 
						{
							elGordo = SacarNumeros(boletosPremiados);
							OrdenarPremios(boletosPremiados);
							System.out.println("El gordo es: " + elGordo);
							System.out.println("Todo aquel numero que acabe en: " + (elGordo%10) + " tendra 20 euros");
							sorteoTerminado = true;
						}
						else 
						{
							System.out.println("Sorteo terminado... compruebe sus premios");
						}
					break;
				case 4:
					if(elGordo == 0) 
					{
						System.out.println("Aun no se ha hecho el sorteo");
					}
					else 
					{
						ComprobarLoteria(boletosComprados, elGordo,boletosPremiados, pedrea);
						elGordo = 0;
						premios60k.clear();
						premios20k.clear();
						premios100.clear();
						boletosComprados.clear();
						pedrea.clear();
						sorteoTerminado = false;
						System.out.println("Ya puede volver a comprar y a jugar en el sorteo. suerte!");
						
					}
					break;
				case 0:
					salir = true;
					break;
			}
			
		}while(!salir);
	}
	//ORDENAR ALFABETICAMENTE
	
	public static void ComprobarLoteria(ArrayList<Integer> _BoletosComprados, int _ElGordo, ArrayList<ArrayList<Integer>> _BoletosPremiados,ArrayList<Integer> _Pedrea) 
	{
		int contadorPremios = 0, guardarPremios = 0;
		ArrayList <Integer> premios60k = _BoletosPremiados.get(0);
		ArrayList <Integer> premios20k = _BoletosPremiados.get(1);
		ArrayList <Integer> premios100 = _BoletosPremiados.get(2);
		
		System.out.println("Analizando los boletos comprados");
		for(Integer i: _BoletosComprados) 
		{
			if(_BoletosComprados.contains(_ElGordo)) 
			{
				System.out.println("Felicidades te toco el gordo, numero premiado: " + _ElGordo);
				guardarPremios += 3000000;
				contadorPremios++;
			}
			
			else if(premios60k.contains(i)) 
			{
				System.out.println("Felicidades te toco 60 mil euracos pa un piso tienes, numero premiado: " + premios60k.contains(i));
				guardarPremios += 60000;
				contadorPremios++;
			}
			
			else if(premios20k.contains(i)) 
			{
				System.out.println("Felicidades te toco 20 mil euracos pa un coche tienes, numero premiado: " + premios20k.contains(i));
				guardarPremios += 20000;
				contadorPremios++;
			}
			
			else if(premios100.contains(i)) 
			{
				guardarPremios += 100;
				contadorPremios++;
			}	
			else if(_Pedrea.contains(i)) 
			{
				guardarPremios += 20;
				contadorPremios++;
			}	
		}
		if(contadorPremios == 0) 
		{
			System.out.println("Como dice Raphael este a�o no me ha tocao nananananana... nanana");
		}
		else 
		{
			System.out.println("Ha ganado " + contadorPremios +" premios que equivalen a: " + guardarPremios);
		}
	}
	public static int ElegirOption(int _OpcionMenu)
	{
		int n;
		Scanner sc = new Scanner(System.in);
		
			System.out.println(" ---- "+(((_OpcionMenu ==1))?"MENU SORTEO NAVIDAD":"MENU BOLETOS")+" ---- ");
			System.out.println("PULSA 1 -> "+(((_OpcionMenu ==1))?"Menu para comprar boletos":"Comprar un boleto"));
			System.out.println("PULSA 2 -> " +(((_OpcionMenu ==1))?"Comprobar que boletos has comprado":"Comprar mas de un boleto(Decir cuantos boletos quieres)"));
			System.out.println("PULSA 3 -> " +(((_OpcionMenu ==1))?"Ver resultados del sorteo":"Comprar un boleto aleatorio"));
			System.out.println("PULSA 4 -> " +(((_OpcionMenu ==1))?"Comprobar resultados del sorteo":"Comprar mas de un boleto aleatorio(Decir cuantos boletos quieres)"));
			System.out.println("PULSA 0 -> Salir");
	
		n = sc.nextInt();
		return n;
	}
	
	public static void MenuBoletos(ArrayList <Integer> _Boletos) 
	{
		int menuOption, cantidadBoletos = 0;
		do 
		{
			menuOption = ElegirOption(2);
			switch(menuOption) 
			{
				case 1:
					RellenarArrayList(_Boletos, 1, false);
					menuOption = 0;
					break;
				case 2:
					cantidadBoletos = PedirNumero("Cuantos boletos quieres?",1,99999);
					RellenarArrayList(_Boletos, cantidadBoletos, false);
					menuOption = 0;
					break;
				case 3:
					RellenarArrayList(_Boletos, 1, true);
					menuOption = 0;
					break;
				case 4:
					cantidadBoletos = PedirNumero("Cuantos boletos quieres?",1,99999);
					RellenarArrayList(_Boletos, cantidadBoletos, true);
					menuOption = 0;
					break;
			}
			
		}while(menuOption != 0);
		
	}
	//Collections.sort para ordenar los premios y dentros de los sysouts para que me los divida en columna de 200 todos los premios posibles
	public static void OrdenarPremios(ArrayList <ArrayList<Integer>> _BoletosPremiados) 
	{
		
		ArrayList<Integer> premios60k = _BoletosPremiados.get(0);
		ArrayList<Integer> premios20k = _BoletosPremiados.get(1);
		ArrayList<Integer> premios100 = _BoletosPremiados.get(2);
		
		
		Collections.sort(premios60k);
		Collections.sort(premios20k);
		Collections.sort(premios100);
		System.out.printf("%1$-20s %1$-20s %1$-20s %1$-20s %1$-20s %2$-20s %3$-20s", "Premios100€", "Premios de 20mil€", "Premios60mil€");
		System.out.println();
		for(int i = 0; i < 200; i++) {
			System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s \n",
			String.format("%05d", premios100.get(i)),
			String.format("%05d", premios100.get(i+200)),
			String.format("%05d", premios100.get(i+400)),
			String.format("%05d", premios100.get(i+600)),
			String.format("%05d", premios100.get(i+800)),
			i < premios20k.size() ? String.format("%05d", premios20k.get(i)) : "",
			i < premios60k.size() ? String.format("%05d", premios60k.get(i)) : "");
		}
	}
	
	public static int PedirNumero(String _Texto, int _Desde, int _Hasta) 
	{
		int n;
		Scanner sc = new Scanner(System.in);
		do 
		{
			System.out.println(_Texto);
			n = sc.nextInt();
		}while(n < _Desde || n > _Hasta);
		return n;
	}
	
	public static void RellenarArrayList(ArrayList <Integer> _Boletos, int _LongitudArrayList, boolean _Aleatorio) 
	{
		Scanner sc = new Scanner(System.in);
		String respuesta = "";
		int j, repeticiones= 0;
		boolean repetirNumero = false, preguntaRepeticion = false;
		int boleto = 0;
		
		for(int i = 0; i < _LongitudArrayList; i++) 
		{				
			if(!repetirNumero){	
				boleto =(!_Aleatorio)?PedirNumero("Dime un numero para el boleto "+(i+1),1,99999):(int)(Math.floor(Math.random()*(99999+1-1)+1));
				//Utilizo este ternario para tener en cuenta si el usuario ha aceptado escoger numeros o hacerlo de forma aleatoria asi me ahorro tener que escribir un metodo o repetir codigo
				System.out.println("Boleto obtenido con el numero: " + boleto);
				_Boletos.add(boleto);
				if(_Boletos.size()<_LongitudArrayList-1) 
				{
					System.out.println("�Quiere repetir numero?Y/N");
					respuesta = sc.nextLine();
					if(respuesta.equals("Y")) 
					{	
						preguntaRepeticion = false;
						repetirNumero = true;
					}
				}
			}
			else 
			{
				if(!preguntaRepeticion)repeticiones = PedirNumero("�Cuantas veces?",1,_LongitudArrayList)-1;
				for(j = 0; j < repeticiones; j++) 
				{		 
					_Boletos.add(boleto);
					preguntaRepeticion = true;
					System.out.println(j);
					i++;
				}
			
				if(j == repeticiones) 
				{
					repetirNumero = false;
					i--;
				}
				
			}

		}
		
	}
	
	public static void RellenarPedrea(ArrayList<Integer> _Pedrea, int _ElGordo) 
	{
		for(int i= (_ElGordo%10);i < 99999; i+=10) 
		{
			_Pedrea.add(i);
		}
	}
	/*Saca numeros aleatoriamente e introduce dependiendo del numero azar sacara un premio u otro*/
	public static int SacarNumeros(ArrayList<ArrayList<Integer>> _BoletosPremiados)
	{
		int elGordo = 0, elAzar = 0, numeroPremiado;
		ArrayList <Integer> premios60k = _BoletosPremiados.get(0);
		ArrayList <Integer> premios20k = _BoletosPremiados.get(1);
		ArrayList <Integer> premios100 = _BoletosPremiados.get(2);
		boolean salirGordo = false;
		for(int i = 0; i < 1009; i++) 
		{
			numeroPremiado = (int)(Math.floor(Math.random()*(99999+1-1)+1));
			elAzar = (int)(Math.floor(Math.random()*(10+1-1)+1));
			if(elAzar == 3 && premios60k.size() < 3 && !premios20k.contains(numeroPremiado) && !premios100.contains(numeroPremiado) && !premios60k.contains(numeroPremiado) && numeroPremiado != elGordo) 
			{
				premios60k.add(numeroPremiado);
				System.out.println("Premio de 60 mil salio en la posicion: " + i + " numero premiado: " + numeroPremiado);
			}
			else if(elAzar == 2 && premios20k.size() < 5 && !premios60k.contains(numeroPremiado) && !premios100.contains(numeroPremiado)&& !premios20k.contains(numeroPremiado) && numeroPremiado != elGordo) 
			{
				premios20k.add(numeroPremiado);
				System.out.println("Premio de 20 mil salio en la posicion: " + i + " numero premiado: " + numeroPremiado);
			}
			else if(premios100.size() < 1000 && !premios20k.contains(numeroPremiado) && !premios100.contains(numeroPremiado) && !premios60k.contains(numeroPremiado) && numeroPremiado != elGordo) 
			{
				premios100.add(numeroPremiado);
			}
			else if(elAzar == 1  && !salirGordo) 
			{
				elGordo = numeroPremiado;
				salirGordo = true;
				System.out.println("Salio el gordo en la posicion: " + i + " numero premiado: " + numeroPremiado);
			}
			else 
			{
				i--;
			}
		}
		return elGordo;
	}	
}
