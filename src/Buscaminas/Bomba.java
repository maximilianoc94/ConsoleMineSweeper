
package Buscaminas;

/** Bomba
 *  metodo con los atributos de una bomba y su metodo para abrirse
 * @author Maximiliano Casale
 */
public class Bomba extends Casillas
{
    /** Bomba
     *  constructor donde se asigna la posicion de la bomba
     * @param i posicion en y
     * @param j posicion en x
     */
    public Bomba(int i, int j)
    {
        super(i, j);
    }
    
    /** abrir
     *  metodo donde si se abre la bomba esta se destapa y asigna una X a la marca
     */
    public void abrir()
    {
        _Oculta = false;
        _Marca = "X";
    }
}//fin de la clase
