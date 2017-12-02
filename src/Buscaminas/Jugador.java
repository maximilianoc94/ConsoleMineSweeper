
package Buscaminas;

import java.util.Scanner;

/** Jugador
 *  contiene los atributos necesarios del jugador
 * y los metodos que utiliza para jugar
 * 
 * @author Maximiliano Casale
 */
public class Jugador 
{
    private String _Nombre;
    private long _Record;
    private Juego _Buscaminas;
    
   /** seleccionar
    * metodo donde se despliega el menu de opciones que tiene el jugador para
    * hacer sus respectivas jugadas.
    * 
    */
    
    public void seleccionar()
    {
        //** FUNCION PRINCIPAL PARA JUGAR
        //** ESCOGER CASILLA Y DESPLEGAR MENU DE ACCIONES
        int x = (-1);
        do
        {    //Pedimos posicion en las columnas y validamos
            System.out.print("Inserte el numero de la columna que desea seleccionar: ");
            Scanner xScan = new Scanner(System.in);
            if(xScan.hasNextInt())
                x = xScan.nextInt();
            x--;
            if(x < 0 || x >= _Buscaminas.get_Configuracion().get_X())
                System.out.println("Numero no valido");
        }while(x < 0 || x >= _Buscaminas.get_Configuracion().get_X());
        int y = -1;
        do
        {   // pedimos posicion en las filas y validamos
            System.out.print("Ingrese el numero de la fila que desea seleccionar: ");
            Scanner YScan = new Scanner(System.in);
            if(YScan.hasNextInt())
                y = YScan.nextInt();
            y--;
            if(y < 0 || y >= _Buscaminas.get_Configuracion().get_Y())
                System.out.println("Numero no valido");
        }while(y < 0 || y >= _Buscaminas.get_Configuracion().get_Y());
        String sel;
        do
        {    //Pedimos la accion sobre la casilla y validamos
            System.out.println("Marque 'M' para marcar o 'A' para abrir una casilla");
            sel = new Scanner(System.in).nextLine();
            if(!sel.toUpperCase().equals("M") && !sel.toUpperCase().equals("A"))
                System.out.println("Opcion no valida.");
        }while(!sel.toUpperCase().equals("M") && !sel.toUpperCase().equals("A"));
        if(sel.toUpperCase().equals("M"))
        {
            if(_Buscaminas.getCasilla(x, y).isOculta())
                _Buscaminas.getCasilla(x,y).marcar();
            else
                System.out.println("No puedes marcar una Casilla Abierta");
        }
        else if(sel.toUpperCase().equals("A"))
        {
            if(_Buscaminas.getCasilla(x, y).getMarca().equals("P"))
                System.out.println("No puedes abrir una casilla marcada como bomba!");
            else if(_Buscaminas.getCasilla(x, y).isOculta())
                _Buscaminas.getCasilla(x,y).abrir();
            else
                System.out.println("Esta casilla ya esta abierta");
        }
    }
    
    public void setNombre(String nombre)
    {
        _Nombre = nombre;
    }
    
    public String getNombre()
    {
        return _Nombre;
    }
    
    public void setRecord(long record)
    {
        _Record = record;
    }
    public long getRecord()
    {
        return _Record;
    }

    public void setBuscaminas(Juego bm) {
        _Buscaminas = bm;
    }
    
}//fin de la clase