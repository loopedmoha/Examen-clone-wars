import Modelo.Modelo;

import java.util.Random;

public class Droide {
    private Modelo tipo;
    private int energia;

    /**
     * Constructor de droides
     */
    public Droide(){
        Random r = new Random();

        if(r.nextInt(100) < 50){
            tipo = Modelo.SW447;
            energia = r.nextInt(100);
        }else if(r.nextInt(100) < 80){
            tipo = Modelo.SW348;
            energia = r.nextInt(50);
        }else{
            tipo = Modelo.SW4421;
            energia = r.nextInt(50)+100;
        }
    }

    /**
     * Devuelve el tipo del droide
     * @return Modelo
     */
    public Modelo getTipo() {
        return tipo;
    }

    /**
     * Asigna el tipo
     * @param tipo Modelo
     */
    public void setTipo(Modelo tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve la energia del droide
     * @return int energia
     */
    public int getEnergia() {
        return energia;
    }

    /**
     * Asigan la energia del droide
     * @param energia int
     */
    public void setEnergia(int energia) {
        this.energia = energia;
    }

    /**
     * Inflinge daño a un droide
     * @param dmg cantidad de daño
     */
    public void takeDmg(int dmg){
        setEnergia(getEnergia()-dmg);
    }

    /**
     * Comprueba si un droide se ha quedado sin energia
     * @return true si está muerto
     */
    public boolean isDead(){
        return(getEnergia() <= 0);
    }

    /**
     * Sobrecarga del metodo toString
     * @return cadena con la info del droide
     */
    @Override
    public String toString() {
        return "Droide{" +
                "tipo=" + tipo +
                ", energia=" + energia +
                '}';
    }
}
