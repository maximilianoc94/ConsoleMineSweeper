
package Buscaminas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Configuracion
 *
 * Conjunto de metodos y atributos para configurar un juego de buscaminas
 *
 * @autor Maximiliano Casale
 */

public class Configuracion
{
    private int _TamañoX;
    private int _TamañoY;
    private int _CantBombas;

    /** Configuracion
     * Constructor donde se lee el archivo de texto con las configuraciones
     * y se guardan los valores en los respectivos atributos
     * 
     */
    public Configuracion()
    {
        //**LEER LA CONFIGURACION DEL TABLERO DEL TXT
        	File f = new File("configBuscaminas.txt"); //abrimos el txt
		Scanner s;
		try //el try-catch es para arrojar el error por si pasa algo inesperado
                {
			s = new Scanner(f); //scanneo al txt
			String linea1 = s.nextLine(); // primera linea
			Scanner sl = new Scanner(linea1); //Scanneo a la primera linea
			sl.useDelimiter("_"); // separamos la primera linea con _
			sl.next(); // primera parte = filas
                        // para la segunda parte, como es el numero
                        // convierte el numero en el string a int y lo asigna a Y
                        int YPrueba = Integer.parseInt(sl.next());
                        if(YPrueba < 1) //validacion para que no sea menor que 2
                            _TamañoY = 1;
                        else
                            _TamañoY = YPrueba;
			String linea2 = s.nextLine(); // segunda linea
			Scanner s2 = new Scanner(linea2); // Scanneo a la segunda linea
                        s2.useDelimiter("_"); // separamos otra vez mediante el _
                        s2.next(); // primera parte = columnas
			//para la segunda parte de la segunda linea
                        //convertimos el numero en el string a int y lo asignamos a X
                        int XPrueba = Integer.parseInt(s2.next());
                        if(XPrueba < 1) //validacion para que no sea menor que 2
                            _TamañoX = 1;
                        else
                            _TamañoX = XPrueba;
			String linea3 = s.nextLine(); // tercera linea
			Scanner s3 = new Scanner(linea3); // scaneo a la 3era linea
                        s3.useDelimiter("_"); // separamos con el _
                        s3.next(); // primera parte = Bombas
                        //segunda parte de la tercera fila
                        // agarramos el string del numero convertimos a int
                        // asignamos a la cantidad de bombas;
                        int CantBombasPrueba = Integer.parseInt(s3.next());
                        if(CantBombasPrueba > _TamañoX*_TamañoY)
                            _CantBombas = _TamañoX*_TamañoY - 1;
                        else if(CantBombasPrueba <= 0)
                            _CantBombas = 0;
                        else
                            _CantBombas = CantBombasPrueba;
			s.close(); //Dejamos de escanear el archivo
		}
                catch (FileNotFoundException e) //excepcion por si no se consigue el archivo
                {
			System.err.println("No se consigio configBuscaminas.txt");
                        System.exit(0);
                }
    }

    public int get_CantBombas()
    {
        return _CantBombas;
    }

    public int get_X()
    {
        return _TamañoX;
    }

    public int get_Y()
    {
        return _TamañoY;
    }

}// fin de la clase
