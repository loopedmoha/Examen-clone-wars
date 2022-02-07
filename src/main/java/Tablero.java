import java.util.Random;

public class Tablero {
    private Droide[][] droides;
    private Droide[] historial;

    private int size;
    private int nDestruidos;
    private int nDroides;
    private float nAciertos;
    private float nDisparos;
    //private int nCriticos = 0;

    /**
     * Constructor del tablero
     * @param tam int
     * @param n int
     */
    public Tablero(int tam, int n) {
        droides = new Droide[tam][tam];
        historial = new Droide[n];
        Random r = new Random();
        size = tam;
        nDroides = n;
        nDisparos = 0;
        nDestruidos = 0;
        nAciertos = 0;
        int i = 0;

        while (n > 0) {
            int fila = r.nextInt(tam);
            int columna = r.nextInt(tam);

            if (droides[fila][columna] == null) {
                droides[fila][columna] = new Droide();
                historial[i] = droides[fila][columna];
                i++;
                n--;
            }
        }
    }

    /**
     * Devuelve el tamaño de la columna
     * @return int size
     */
    public int getSize() {
        return size;
    }

    /**
     * Aumenta el numero de disparos realizados
     */
    public void increaseDisparos() {
        nDisparos++;
    }

    /**
     * Comprueba si una casilla está ocupada
     * @param fila int fila
     * @param columna int columna
     * @return true si hay un droide en la casilla
     */
    public boolean isCasillaOcupada(int fila, int columna) {
        return (droides[fila][columna] != null);
    }

    /**
     * Inflinge daño a un droide
     * @param dmg cantidad de daño
     * @param fila fila del droide
     * @param columna columna del droide
     */
    public void dmgDroide(int dmg, int fila, int columna) {
        droides[fila][columna].takeDmg(dmg);
        if(droides[fila][columna].getEnergia() <= 0){
            destroyDroide(fila, columna);
        }
        nAciertos++;
    }

    /**
     * Desplaza a un droide a una casilla donde no haya otro droide desplazando al que estaba ahí de forma recursiva
     * @param d
     * @param cFila
     * @param cCol
     */
    public void desplazarDroide(Droide d, int cFila, int cCol) {
        Random r = new Random();
        int fila = r.nextInt(droides.length);
        int columna = r.nextInt(droides.length);
        if (droides[fila][columna] == null) {
            droides[fila][columna] = d;
            droides[cFila][cCol] = null;
        } else {
            desplazarDroide(droides[fila][columna], fila, columna);
        }
    }

    /**
     * Cambia la ubicacion de todos los droides
     */
    public void shuffleTablero() {
        Droide aux;
        Random r = new Random();
        int size = droides.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (droides[i][j] != null)
                    desplazarDroide(droides[i][j], i, j);
            }
        }
    }

    /**
     * Destruye un droide
     * @param fila fila del droide
     * @param columna columna del droide
     */
    public void destroyDroide(int fila, int columna) {
        droides[fila][columna] = null;
        nDestruidos++;
        nDroides--;
    }

    /**
     * Comprueba si quedan droides
     * @return true si todos los droides han muerto
     */
    public boolean noMoreDroids() {
        return (nDroides <= 0);
    }


    /**
     * Muestra los droides que han aparecido en la simulación
     */
    public void printHistorial(){
        System.out.println("Droides colaboradores: ");
        sortHistorial();
        for (int i = 0; i < historial.length; i++) {
            System.out.print(historial[i].toString());
            System.out.println();
        }
    }

    /**
     * Ordena el historial según la vida del droide
     */
    public void sortHistorial(){
        Droide aux;
        for (int i = 0; i < historial.length; i++) {
            for (int j = i+1; j < historial.length; j++) {
                if(historial[i].getEnergia() > historial[j].getEnergia()){
                    aux = historial[i];
                    historial[i] = historial[j];
                    historial[j] = aux;
                }
            }
        }
    }

    /**
     * Muestra un informe de simulacion
     */
    public void printInforme(){
        System.out.println("Numero de droides: " + nDroides);
        System.out.println("Droides destruidos: " + nDestruidos);
        System.out.println("Numero de disparos: " + (int)nDisparos);
        System.out.println("Numero de aciertos: " + (int)nAciertos);
        float tasa = nAciertos/nDisparos * 100;
        System.out.println("Tasa de aciertos: " +tasa +"%");
    }
    public void printTablero() {
        for (int i = 0; i < droides.length; i++) {
            for (int j = 0; j < droides.length; j++) {
                System.out.print("[");
                if (droides[i][j] != null)
                    System.out.print(droides[i][j].toString());
                System.out.print("]");
            }
            System.out.println();
        }
        System.out.println("Numero de droides: " + nDroides);
        System.out.println("Droides destruidos: " + nDestruidos);
        System.out.println("Numero de disparos: " + nDisparos);
        System.out.println("Numero de aciertos: " + nAciertos);

    }
}
