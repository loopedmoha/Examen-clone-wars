public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("FALTAN ARGUMENTOS");
        } else {
            int columnas = Integer.parseInt(args[0]);
            int droides = Integer.parseInt(args[1]);
            int t = Integer.parseInt(args[2]) * 1000;

            if (columnas < 5 || columnas > 9) {
                System.out.println("Error de dimensiones.");
            } else if (droides < 5 || droides > 30 || droides > (columnas * columnas)) {
                System.out.println("Error de droides.");
            } else {
                System.out.println("Comenzando simulacion...");
                Tablero tab = new Tablero(columnas, droides);
                XWing xwing = new XWing();
                int shuffle = 300;
                int cd = 100;
                while (t > 0 && !tab.noMoreDroids()) {
                    xwing.shootDroide(tab);
                    try {

                        Thread.sleep(100);
                        shuffle -= cd;
                        t -= cd;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (shuffle <= 0) {
                        tab.shuffleTablero();
                        shuffle = 300;
                    }
                }
                tab.printInforme();
                tab.printHistorial();
            }
        }
    }
}
