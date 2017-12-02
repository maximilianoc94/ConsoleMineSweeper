
package Buscaminas;
import java.util.Scanner;

/** Casillas
 *  Clase abstracta la cual define los tipos de casillas del tablero
 * contiene los atributos y metodos generales para los tipos de casilla
 * 
 * @author Maximiliano Casale
 */
public abstract class Casillas 
{
    protected boolean _Oculta;
    protected String _Marca;
    protected int _PosX;
    protected int _PosY;
    
    /** Casillas
     *  constructor donde se asignan la posicion y el estado de la casilla
     * 
     * @param pos_x posicion en x en el tablero
     * @param pos_y  posicion en y en el tablero
     */
    
    public Casillas(int pos_x, int pos_y)
    {
        _Oculta = true;
        _Marca = " ";
        _PosX = pos_x;
        _PosY = pos_y;
        
    }
    
    public abstract void abrir();
    
    /** Marcar
     * metodo para aplicar un simbolo en la casilla
     */
    public void marcar()
    {
        //**Marcar la casilla con una P(bandera) o con una ?(Identificable)
        if(_Oculta = true)
        {
            String asignar;
            do
            {    
                System.out.println("Marque la casilla con una 'P' para bandera o con un '?' si es identificable");
                System.out.println("Si quieres desmarcar marca 'D'");
                asignar = new Scanner(System.in).nextLine();
                if(!asignar.toUpperCase().equals("P") && !asignar.toUpperCase().equals("?") && !asignar.toUpperCase().equals("D"))
                    System.out.println("Asignacion no valida. Intente de nuevo.");
            }
            while(!asignar.toUpperCase().equals("P") && !asignar.toUpperCase().equals("?") && !asignar.toUpperCase().equals("D"));
            if(asignar.toUpperCase().equals("D"))
                _Marca = " ";
            else
            _Marca = asignar.toUpperCase();
        }
        else
        {
            System.out.println("No puedes marcar una casilla abierta!");
        }
    }
    public String getMarca()
    {
        return _Marca;
    }
    
    public void setMarca(String marca)
    {
        _Marca = marca;
    }
    
    public boolean isOculta()
    {
        return _Oculta;
    }
    
    public void setOculta(boolean oculta)
    {
        _Oculta = oculta;
    }
}//fin de la clase