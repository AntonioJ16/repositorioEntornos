import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;
public class Sorteo_v2 {

	public static void main(String[] args) 
	{
		int menuOption;
		boolean salir = false;
		ArrayList <Integer> boletosComprados = new ArrayList();
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
				
					break;
				case 4: 
					
					break;
				case 0:
					salir = true;
					break;
			}
			
		}while(!salir);
	}
	//ORDENAR ALFABETICAMENTE
	
	public static int ElegirOption(int _OpcionMenu)
	{
		int n;
		Scanner sc = new Scanner(System.in);
		
		if(_OpcionMenu ==1) 
		{
			System.out.println("PULSA 1 -> Menu para comprar boletos");
			System.out.println("PULSA 2 -> Comprobar que boletos has comprado");
			System.out.println("PULSA 3 -> Ver resultados del sorteo");
			System.out.println("PULSA 4 -> Comprobar resultados del sorteo");
			System.out.println("PULSA 0 -> Salir");
		}
		else 
		{
			System.out.println("PULSA 1 -> Comprar un boleto");
			System.out.println("PULSA 2 -> Comprar mas de un boleto(Decir cuantos boletos quieres)");
			System.out.println("PULSA 3 -> Comprar un boleto aleatorio");
			System.out.println("PULSA 4 -> Comprar mas de un boleto aleatorio(Decir cuantos boletos quieres)");
			System.out.println("PULSA 0 -> Salir");
		}
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
	
	public static int PedirNumero(String _Texto, int _Desde, int _Hasta) 
	{
		int n;
		Scanner sc = new Scanner(System.in);
		do 
		{
			System.out.println(_Texto);
			n = sc.nextInt();
			_Texto = "Se puede desde " + _Desde + " hasta " + _Hasta;
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
				//Hacemos un ternario para boletos para saber si el usuario ha introducido en el menu si quiere escoger numeros o que sean aleatorios
				System.out.println("Boleto obtenido con el numero: " + boleto);
				_Boletos.add(boleto);
				if(_Boletos.size()<_LongitudArrayList-1) 
				{
					System.out.println("Quiere repetir numero?Y/N");
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
				if(!preguntaRepeticion)repeticiones = PedirNumero("Cuantas veces?",1,_LongitudArrayList)-1;
				for(j = 0; j < repeticiones; j++) 
				{		 
					_Boletos.add(boleto);
					preguntaRepeticion = true;
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
	
}
