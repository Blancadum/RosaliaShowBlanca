import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Asiento
 */
public class AsientoTest {

    private Asiento asiento;

    @BeforeEach
    void setUp() {
        asiento = new Asiento(0, 5, "VIP", 85.00);
    }

    // ==================== PRUEBAS DE INICIALIZACIÓN ====================

    @Test
    void testConstructor() {
        assertEquals(0, asiento.getFila());
        assertEquals(5, asiento.getNumero());
        assertEquals("VIP", asiento.getSeccion());
        assertEquals(85.00, asiento.getPrecio());
        assertTrue(asiento.isDisponible());
    }

    // ==================== PRUEBAS DE DISPONIBILIDAD ====================

    @Test
    void testDisponibleInicial() {
        assertTrue(asiento.isDisponible());
    }

    @Test
    void testOcupar() {
        asiento.ocupar();
        assertFalse(asiento.isDisponible());
    }

    @Test
    void testLiberar() {
        asiento.ocupar();
        asiento.liberar();
        assertTrue(asiento.isDisponible());
    }

    @Test
    void testMultipleOcuparLiberar() {
        asiento.ocupar();
        assertFalse(asiento.isDisponible());

        asiento.liberar();
        assertTrue(asiento.isDisponible());

        asiento.ocupar();
        assertFalse(asiento.isDisponible());
    }

    // ==================== PRUEBAS DE GETTERS ====================

    @Test
    void testGetFila() {
        assertEquals(0, asiento.getFila());
    }

    @Test
    void testGetNumero() {
        assertEquals(5, asiento.getNumero());
    }

    @Test
    void testGetSeccion() {
        assertEquals("VIP", asiento.getSeccion());
    }

    @Test
    void testGetPrecio() {
        assertEquals(85.00, asiento.getPrecio());
    }

    // ==================== PRUEBAS DE INFORMACIÓN ====================

    @Test
    void testGetInfo() {
        String info = asiento.getInfo();
        assertNotNull(info);
        assertTrue(info.contains("VIP"));
        assertTrue(info.contains("85"));
        assertTrue(info.contains("Disponible"));
    }

    @Test
    void testGetInfo_Ocupado() {
        asiento.ocupar();
        String info = asiento.getInfo();
        assertTrue(info.contains("ocupado") || info.contains("❌"));
    }

    // ==================== PRUEBAS CON DIFERENTES SECCIONES ====================

    @Test
    void testAsiento_Premium() {
        Asiento premium = new Asiento(3, 2, "Premium", 65.00);
        assertEquals("Premium", premium.getSeccion());
        assertEquals(65.00, premium.getPrecio());
    }

    @Test
    void testAsiento_Normal() {
        Asiento normal = new Asiento(7, 4, "Normal", 45.00);
        assertEquals("Normal", normal.getSeccion());
        assertEquals(45.00, normal.getPrecio());
    }

    // ==================== PRUEBAS DE PRECIO ====================

    @Test
    void testPrecioVIP() {
        Asiento vip = new Asiento(0, 0, "VIP", 85.00);
        assertEquals(85.00, vip.getPrecio());
    }

    @Test
    void testPrecioPremium() {
        Asiento premium = new Asiento(3, 0, "Premium", 65.00);
        assertEquals(65.00, premium.getPrecio());
    }

    @Test
    void testPrecioNormal() {
        Asiento normal = new Asiento(5, 0, "Normal", 45.00);
        assertEquals(45.00, normal.getPrecio());
    }

    // ==================== PRUEBAS DE POSICIÓN ====================

    @Test
    void testPosicion_PrimeraFila() {
        assertEquals(0, asiento.getFila());
    }

    @Test
    void testPosicion_UltimaFila() {
        Asiento ultimo = new Asiento(9, 7, "Normal", 45.00);
        assertEquals(9, ultimo.getFila());
    }

    @Test
    void testPosicion_PrimeraColumna() {
        Asiento primero = new Asiento(5, 0, "Normal", 45.00);
        assertEquals(0, primero.getNumero());
    }

    @Test
    void testPosicion_UltimaColumna() {
        Asiento ultimo = new Asiento(5, 7, "Normal", 45.00);
        assertEquals(7, ultimo.getNumero());
    }

    // ==================== PRUEBAS DE INTEGRACIÓN ====================

    @Test
    void testFlujoCompleto_Venta() {
        assertTrue(asiento.isDisponible());
        asiento.ocupar();
        assertFalse(asiento.isDisponible());
    }

    @Test
    void testFlujoCompleto_Cancelación() {
        asiento.ocupar();
        assertTrue(asiento.getInfo().contains("❌") || asiento.getInfo().contains("ocupado"));

        asiento.liberar();
        assertTrue(asiento.isDisponible());
    }
}
