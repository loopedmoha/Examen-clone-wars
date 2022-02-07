import java.util.Random;

public class XWing {
    private int critRate;
    private int dmg;
    private int critDmg;


    public XWing() {
        critRate = 15;
        dmg = 1;
        critDmg = 5;
    }

    /**
     * Escanea la casilla en busca de droides
     * @param tablero tablero
     * @param fila int fila
     * @param columna int columna
     * @return true si hay un droide
     */
    public boolean scanCasilla(Tablero tablero, int fila, int columna) {
        return tablero.isCasillaOcupada(fila, columna);
    }

    /**
     * Dispara a una casilla
     * @param tablero cuadricula
     * @return true si daña a un droide
     */
    public boolean shootDroide(Tablero tablero) {
        Random r = new Random();
        int fila = r.nextInt(tablero.getSize());
        int columna = r.nextInt(tablero.getSize());

        if (scanCasilla(tablero, fila, columna)) {
            if (r.nextInt(100) < critRate)
                tablero.dmgDroide(critDmg, fila, columna);
            else
                tablero.dmgDroide(dmg, fila, columna);
            tablero.increaseDisparos();
            return true;
        } else
            tablero.increaseDisparos();
            return false;
    }
}
