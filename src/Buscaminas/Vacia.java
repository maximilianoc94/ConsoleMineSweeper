
package Buscaminas;

/** Vacia
 *  Casilla de tipo vacia donde se definen sus metodos caracteristicos
 * 
 * @author Maximiliano Casale
 */
public class Vacia extends Casillas 
{
    private int _CantBombasAdyacentes;
    private Casillas[] _Adyacentes;
    
    /**Vacia
     *  constructor donde se asigna su posicion y se inicializan
     * sus posiciones alrededor y la cantidad de bombas que puede tener
     * @param posx posicion en x en el tablero
     * @param posy  posicion en y en el tablero
     */
    public Vacia(int posx, int posy)
    {
        super(posx, posy);
        _CantBombasAdyacentes = 0;
        _Adyacentes = new Casillas[8];
    }

    /**Asignar Adyacentes
     *  metodo donde se recibe el tablero como parametro
     * y se asignan las casillas adyacentes al vector atributo
     * @param tablero matriz donde se encuentran las casillas
     */
    public void asignarAdyacentes(Casillas[][] tablero)
    {
        // para recorrer cada una de las casillas adyacentes
        // revisamos las casillas que estan una posicion a la izquierda (-1)
        // hasta la que esta  a la derecha(+1)
        int cont = 0; // para saber en que posicion estamos del vector adyacentes
        int limite_posx = tablero[_PosY].length; // maximo numero de columnas
        int limite_posy = tablero.length;// maximo numero de fila
        for(int i = -1; i <= 1;i++) //loop desde la izquierda hasta la derecha
        {
            for(int j = -1; j <= 1; j++)
            {
                if(i == 0 && j == 0) // en el caso de que estemos en la casilla
                {                   // del medio, la descontamos porque no la queremos
                    cont--;         // en el vector de adyacentes
                }
                else
                {   // validamos que este dentro de los limites de la matriz
                    if(_PosY + i >= 0 && _PosX + j >= 0 && _PosY + i < limite_posy && _PosX + j < limite_posx)
                     // si esta, asignamos
                        _Adyacentes[cont] = tablero[_PosY + i][_PosX + j];
                    else // si no esta, es null
                        _Adyacentes[cont] = null;
                }
            cont++;
            }
        }
    }
    /** abrir
     *  al abrir una casilla vacia, se cuentan cuantas bombas adyacentes tiene
     * de ser 0, abre sus respectivas adyacentes, de lo contrario se muestra un 
     * numero con la cantidad de bombas que tiene alrededor
     */
    public void abrir()
    {
        //** Lo que pasa cuando abres una casilla numero
        if(_Oculta = true)
        {
            _Oculta = false;
            this.contarAdyacentes(); // contamos si en las casillas adyacentes hay bombas
            if(_CantBombasAdyacentes == 0) // si es cero
            {
                _Marca = "0";
                this.abrirAdyacentes(); // abrimos las adyacentes
            }
            else
            {
                _Marca = Integer.toString(_CantBombasAdyacentes); //Asignamos la cantidad de bombas como marca
            }
        }
    }
    
    /** contar adyacentes
     *  cuenta la cantidad de bombas que tiene en sus casillas adyacentes
     */
    public void contarAdyacentes()
    {
        for(int i = 0; i < _Adyacentes.length; i++)
        {
            if(_Adyacentes[i] instanceof Bomba)
            {
                if(_Adyacentes[i].isOculta())
                    _CantBombasAdyacentes++; // recorriendo el vector de adyacentes, cada casilla que sea bomba suma 1
            }
        }
    }
    
    /** abrir adyacentes 
     *  abre las respectivas casillas adyacentes
     */
    public void abrirAdyacentes()
    {
        for(int i = 0; i < _Adyacentes.length ; i++)
        {
            if(_Adyacentes[i] != null)
           {
                if(_Adyacentes[i].isOculta())
                {
                    _Adyacentes[i].abrir(); //abrirception!
                } 
            }       
        }
    }
}//fin de la clase