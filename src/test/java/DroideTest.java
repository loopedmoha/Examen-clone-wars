import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroideTest {

    private Droide droide = new Droide();
    @BeforeEach
    void resetDroide(){
        droide = new Droide();
    }
    @Test
    void takeDmg() {
        int energia = droide.getEnergia();
        droide.takeDmg(1);
        assertEquals(energia-1, droide.getEnergia());
    }
}