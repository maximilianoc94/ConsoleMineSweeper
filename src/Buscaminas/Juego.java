
package Buscaminas;

import java.io.*;
import java.util.Scanner;
import java.util.Date;
import java.util.Random;
/** Juego
 * Base de la aplicacion, contiene los metodos y atributos necesarios para
 * generar un juego de Buscaminas.
 * 
 * @author Maximiliano Casale
 */
public class Juego 
{
    private Jugador _J1;
    private Casillas[][] _Tablero;
    private Date _Tiempo;
    private Configuracion _Configuracion;
    /**
     * Juego
     * Constructor para inicializar el buscaminas
     * inicia un tablero y un jugador
     * al mismo tiempo que da la bienvenida al juego
     */
    public Juego()
    {
        //**Cada vez que se inicia el juego creamos el tablero y el jugador
        _Configuracion = new Configuracion();// leemos el Txt para configurar el tablero en el constructor
        //**Bienvenida y pedir nombre Jugador
        _Tablero = new Casillas[_Configuracion.get_Y()][_Configuracion.get_X()];
        _J1 = new Jugador();
        boolean continuar;
        String nombre;
        System.out.println("Bienvenido a Buscaminas!");            
        do
        {   
            continuar = true;
            System.out.println("Por favor, ingrese su nombre!"); 
            nombre = new Scanner(System.in).nextLine();
            for(int i = 0; i< nombre.length(); i++)
            {
                if( nombre.charAt(i) == '_')
                {
                    System.out.println("Nombre invalido, no puede ingresar '_'");
                    continuar = false;
                    break;
                }
            }
        }while(!continuar);
        _J1.setNombre(nombre);
    }
  
    /** menuPrincipal
     * Este metodo despliega un menu para seleccionar la opcion de jugar
     * o ver los records guardados en el sistema
     */
    public void menuPrincipal()
    {
        //Menu para jugar o ver los records actuales
        String sel;
        do
        {
        System.out.println("Presione 'J' para jugar o 'R' para ver los Records.");
        sel = new Scanner(System.in).nextLine();
        if(!sel.toUpperCase().equals("R") && !sel.toUpperCase().equals("J")) 
            {
                System.out.println("Letra no valida! Intente de nuevo");
            }
        } // Validacion para que inserte una letra valida.
        while(!(sel.toUpperCase().equals("R")) && !(sel.toUpperCase().equals("J")));
        switch(sel.toUpperCase())
        {
            case "R":
                this.mostrarRecords();
                break;
            case "J":
                this.iniciar();
                break;
        }
    }

    /** guardarRecord
     * Recibe el tiempo que tardo el jugador en resolver el buscaminas
     * chequea que sea un menor tiempo que los guardados en el sistema
     * y lo guarda en la respectiva posicion
     * 
     * @param j1 los atributos del jugador 
     */
    public void guardarRecord(Jugador j1)
    {
        //** ESCRIBIR EN TXT SI HAY UN NUEVO RECORD
        long nuevoRecord = j1.getRecord();
        long Record1 = 0, Record2 = 0, Record3 = 0;
        File f = new File("RecordsBuscamina.txt");
        Scanner s;
        try
        {
            s = new Scanner(f);
            s.useDelimiter("_"); // separando con _
            Record1 = Long.parseLong(s.next()); // Segundos primer record 
            s.nextLine(); // Salto el resto de la linea
            Record2 = Long.parseLong(s.next()); //Segundos segundo record
            s.nextLine(); // Salto el resto de la linea
            Record3 = Long.parseLong(s.next()); // Tercer record en segundos;
            s.nextLine(); // Salto el resto de la linea
            s.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("No se consiguio RecordsBuscaminas.txt");
            this.menuPrincipal();
        }
        // Chequear si el record que estamos pasando califica como los primeros 3   
        String nuevoRecord1, nuevoRecord2, nuevoRecord3;
        
        if(nuevoRecord <= Record1)
        {
            nuevoRecord1 = ("_" + nuevoRecord + "_" + j1.getNombre() + "_" + _Configuracion.get_X() + "_" + _Configuracion.get_Y() + "_" + _Configuracion.get_CantBombas());
            try{
            s = new Scanner(f); 
            String l1r2 = s.nextLine(); // Linea 1 pasa a ser el segundo record
            String l2r3 = s.nextLine(); // linea 2 pasa a ser tercer record
            PrintWriter out = new PrintWriter(f);
            out.println(nuevoRecord1);
            out.println(l1r2);
            out.println(l2r3);
            s.close();
            out.close();
            System.out.println("Rompiste un nuevo record! Primer lugar!");
            }
            catch(IOException e)
            {
            System.err.println("Error en Juego.guardarRecord");
            }
        }
        else if(nuevoRecord <= Record2)
        {
            nuevoRecord2 = ("_" + nuevoRecord + "_" + j1.getNombre() + "_" + _Configuracion.get_X() + "_" + _Configuracion.get_Y() + "_" + _Configuracion.get_CantBombas());
            try{
            s = new Scanner(f); 
            String l1r1 = s.nextLine(); // Linea 1 pasa a ser el segundo record
            String l2r3 = s.nextLine(); // linea 2 pasa a ser tercer record
            PrintWriter out = new PrintWriter(f);
            out.println(l1r1);
            out.println(nuevoRecord2);
            out.println(l2r3);
            s.close();
            out.close();
            System.out.println("Rompiste un nuevo record! Segundo lugar!");
            }
            catch(IOException e)
            {
            System.err.println("Error en Juego.guardarRecord");
            }
        }
        else if(nuevoRecord <= Record3)
        {
            nuevoRecord3 = ("_" + nuevoRecord + "_" + j1.getNombre() + "_" + _Configuracion.get_X() + "_" + _Configuracion.get_Y() + "_" + _Configuracion.get_CantBombas());
            try{
            s = new Scanner(f); 
            String l1r1 = s.nextLine(); // Linea 1 pasa a ser el segundo record
            String l2r2 = s.nextLine(); // linea 2 pasa a ser tercer record
            PrintWriter out = new PrintWriter(f);
            out.println(l1r1);
            out.println(l2r2);
            out.println(nuevoRecord3);
            s.close();
            out.close();
            System.out.println("Rompiste un nuevo record! Tercer lugar!");
            }
            catch(IOException e)
            {
            System.err.println("Error en Juego.guardarRecord");
            }
        }       
    }
    
    /** mostrarRecords
     *  lee un archivo txt donde estan guardados los records 
     * y los imprime al sistema
     */
    public void mostrarRecords()
    {
        //** LEER TXT Y IMPRIMIR LOS RECORDS GUARDADOS                           
        File f = new File("RecordsBuscamina.txt"); //abrimos el txt
	Scanner s;
	try 
        {
            s = new Scanner(f); //scanneo al txt
            s.useDelimiter("_");
            System.out.println("Records:");
            String Record1 = ("1ero: " + s.next() + " segundos. Por: " + s.next() + ". Tabla: " + s.next() + "x" + s.next() + " Bombas: " + s.next()); // primer Record
            System.out.println(Record1);
            String Record2 = ("2do: " + s.next() + " segundos. Por: " + s.next() + ". Tabla: " + s.next() + "x" + s.next() + " Bombas: " + s.next()); // segundo Record
            System.out.println(Record2);
            String Record3 = ("3er: " + s.next() + " segundos. Por: " + s.next() + ". Tabla: " + s.next() + "x" + s.next() + " Bombas: " + s.next()); // tercer Record
            System.out.println(Record3);
            s.close();
	} 
        catch (FileNotFoundException e) //excepcion por si no se consigue el archivo
        {
        System.err.println("No se consigio RecordBuscaminas.txt");
        }
        this.menuPrincipal();
    }
    /** iniciar
     *  metodo donde se genera el nuevo tablero para jugar y se 
     * comienza el timer para los records, mantiene al jugador en un loop
     * mientras no termine el juego una vez terminado el juego, si gana o pierde
     * muestra un respectivo mensaje y envia al jugador de vuelta al menu principal
     * 
     */
    public void iniciar()
    {
        //** Metodo para arrancar el juego!
        this.nuevoTablero();
        this.generarTablero();
        this.startTiempo();
        do
        {
        this.printTablero();
        _J1.seleccionar();
        }
        while(!this.checkWin() && !this.checkLost()); //si no ha ganado ni perdido, sigue jugando.
        this.printTablero();
        if(this.checkWin())
        {
            //mensaje de ganar. 
            System.out.println("Felicidades!! " + _J1.getNombre() + " has ganado!");
            //chequear el record.
            Date tiempoFinal = new Date(); // marcamos cuando termino
            Date tiempoInicial = this.getTiempo(); //buscamos cuando empezo
            long record = ( (tiempoFinal.getTime() - tiempoInicial.getTime())/1000 ); // conseguimos cuanto tardo en segundos
            _J1.setRecord(record);
            System.out.println("Finalizaste en " + _J1.getRecord() + " segundos!");
            //guardarlo
            this.guardarRecord(_J1);
            //volver al menu principal
            this.menuPrincipal();
        }
        else if(this.checkLost())
        {
            //Mensaje de perdiste y mandar al menu principal
            System.out.println(_J1.getNombre() + " has perdido!");
            this.menuPrincipal();
        }
        
    }

        /**generar tablero
     * Asigna la cantidad de bombas al azar en el tablero
     *   luego pasa por todas las casillas, si es nula, la hacemos vacia.
     *  finalmente a las casillas vacias le asigna sus respectivas casillas
     * adyacentes
     */

    public void generarTablero()
    {
        //** ASIGNAR LA CANTIDAD DE BOMBAS RANDOMIZADAS AL TABLERO
        // luego pasar por todas las casillas, si es nula, la hacemos vacia.

        for(int i = 0; i< _Configuracion.get_CantBombas(); i++) //Con la cantidad de bombas
        {
            int random_X = new Random().nextInt(_Configuracion.get_X()); //por cada bomba hacemos una posicion X random y la convertimos a int
            int random_Y = new Random().nextInt(_Configuracion.get_Y()); // igual aqui pero para la Y
            if(_Tablero[random_Y][random_X] == null) // En caso de que este null el espacio agarrado random
            {                                       // vamos a poner una bomba en ese espacio
                _Tablero[random_Y][random_X] = new Bomba(random_Y, random_X);
            }
            else
            {
                i--; //en caso de que no, descontamos uno en i para pasar otra vez sin alterar la cantidad
            }
        }
        // luego de que esten todas las bombas, pasamos por cada casilla del tablero
        for(int i = 0; i< _Tablero.length; i++)
        {
            for(int j = 0; j < _Tablero[i].length; j++)
            {   // mientras el espacio en el que estemos sea null
                if (!(_Tablero[i][j] instanceof Bomba))
                {   //ponemos una casilla vacia ahi
                    _Tablero[i][j] = new Vacia(j, i);
                }
            }
        }
        // luego de asignar todas las casillas, asignamos las adyacentes
        for(int i = 0; i< _Tablero.length; i++)
        {
            for(int j = 0; j < _Tablero[i].length; j++)
            {   // mientras el espacio en el que estemos sea null
                if ((_Tablero[i][j] instanceof Vacia))
                {   //ponemos una casilla vacia ahi
                    ((Vacia)_Tablero[i][j]).asignarAdyacentes(_Tablero);
                }
            }
        }
    }

    /** nuevoTablero
     * Resetea el tablero para poder jugar cuantas veces se desee
     *
     */
    public void nuevoTablero()
    {
        for(int i = 0; i < _Tablero.length; i++)
        {
            for(int j = 0; j< _Tablero[i].length; j++)
                if(_Tablero[i][j] != null)
                    _Tablero[i][j] = null;
        }
    }

    /** printTablero
     *  imprime el tablero donde se esta jugando
     */
    public void printTablero()
    {
        //** IMPRIMIR TABLERO
        for(int i = 0; i < _Configuracion.get_Y() + 1; i++)
        {
            for(int j = 0; j <= _Configuracion.get_X(); j++)
            {
                if(i == 0)
                {
                    System.out.print("-" + j + "-");
                }
                else
                {
                    if(j==0)
                        System.out.print("-" + i + "-");
                    else
                        System.out.print("|" + _Tablero[i-1][j-1].getMarca() + "|");
                }
            }
            System.out.println();
        }
    }

    /** checkWin
     * CHEQUEAR EL TABLERO Y RETORNAR TRUE SI EL JUGADOR GANO
     * revisar la cantidad de casillas vacias con OCulta = false y tiene que ser igual
     *  a la cantidad de casillas total menos la cantidad de minas
     *
     * @return un boolean para saber si el jugador gano o no
     */
    public boolean checkWin()
    {
        //** CHEQUEAR EL TABLERO Y RETORNAR TRUE SI EL JUGADOR GANO
        /* revisar la cantidad de casillas con OCulta = false y tiene que ser igual
        a la cantidad de casillas total menos la cantidad de minas*/
        int cont = 0;
        for(int i = 0; i< _Tablero.length; i++)
        {
            for(int j = 0; j<_Tablero[i].length; j++)
            {
                if(!_Tablero[i][j].isOculta()) //Si la casilla no esta oculta, contamos
                {
                    if(_Tablero[i][j] instanceof Vacia)
                        cont++;
                }
            }
        }
        if(cont == ((_Configuracion.get_X()*_Configuracion.get_Y())-_Configuracion.get_CantBombas()))
        {
            return true;
        }
        return false;
    }

    /** checkLost
     * CHEQUEAR EL TABLERO Y RETORNAR TRUE SI EL JUGADOR PERDIO
     * revisar todas las casillas, si hay una bomba no oculta returna true
     *
     * @return un boolean para saber si el jugador perdio o no
     */
        public boolean checkLost()
    {
        //** CHEQUEAR EL TABLERO Y RETORNAR TRUE SI EL JUGADOR PERDIO
        /* revisar todas las casillas, si hay una bomba no oculta returna true*/
        for(int i = 0; i< _Tablero.length; i++)
        {
            for(int j = 0; j<_Tablero[i].length; j++)
            {
                if(_Tablero[i][j] instanceof Bomba && !_Tablero[i][j].isOculta()) //si la bomba no esta oculta. perdiste.
                {
                    for(int k = 0; k < _Tablero.length ; k++)
                    {
                        for(int l = 0; l < _Tablero[i].length; l++ )
                        {
                            if(_Tablero[k][l] instanceof Bomba) //Mostramos todas las casillas que son bomba.
                                _Tablero[k][l].abrir();
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /** startTiempo
     *  arranca el timer de los segundos que tarda el jugador
     */
    public void startTiempo()
    {
        _Tiempo = new Date();
    }

    public Date getTiempo()
    {
        return _Tiempo;
    }

    public Casillas getCasilla(int x, int y)
    {
        if(_Tablero[y][x] instanceof Vacia)
        {
            return (Vacia)_Tablero[y][x];
        }
            return (Bomba)_Tablero[y][x];
    }

    public Jugador getJ1() {
        return _J1;
    }

    public Configuracion get_Configuracion() {
        return _Configuracion;
    }

    public static void main(String[] args) 
    {
       Juego BM = new Juego();
       BM.getJ1().setBuscaminas(BM);
       BM.menuPrincipal();
    }
    
}//fin de la clase