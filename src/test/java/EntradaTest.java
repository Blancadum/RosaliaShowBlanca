import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Entrada
 */
public class EntradaTest {

    private Asiento asiento;
    private Entrada entrada;

    @BeforeEach
    void setUp() {
        asiento = new Asiento(0, 5, "VIP", 85.00);
        entrada = new Entrada(1, asiento, "Juan García");
    }

    // ==================== PRUEBAS DE INICIALIZACIÓN ====================

    @Test
    void testConstructor() {
        assertEquals(1, entrada.getId());
        assertEquals("Juan García", entrada.getCliente());
        assertEquals(85.00, entrada.getPrecio());
        assertNotNull(entrada.getFecha());
        assertNotNull(entrada.getAsiento());
    }

    // ==================== PRUEBAS DE GETTERS ====================

    @Test
    void testGetId() {
        assertEquals(1, entrada.getId());
    }

    @Test
    void testGetCliente() {
        assertEquals("Juan García", entrada.getCliente());
    }

    @Test
    void testGetPrecio() {
        assertEquals(85.00, entrada.getPrecio());
    }

    @Test
    void testGetAsiento() {
        assertNotNull(entrada.getAsiento());
        assertEquals(0, entrada.getAsiento().getFila());
        assertEquals(5, entrada.getAsiento().getNumero());
    }

    // ==================== PRUEBAS DE FECHA ====================

    @Test
    void testGetFecha() {
        assertNotNull(entrada.getFecha());
    }

    @Test
    void testFechaNoNula() {
        Entrada e = new Entrada(2, asiento, "María");
        assertNotNull(e.getFecha());
    }

    // ==================== PRUEBAS DE PRECIO ====================

    @Test
    void testPrecioVIP() {
        Asiento vip = new Asiento(0, 0, "VIP", 85.00);
        Entrada entradaVIP = new Entrada(1, vip, "Cliente VIP");
        assertEquals(85.00, entradaVIP.getPrecio());
    }

    @Test
    void testPrecioPremium() {
        Asiento premium = new Asiento(3, 0, "Premium", 65.00);
        Entrada entradaPremium = new Entrada(2, premium, "Cliente Premium");
        assertEquals(65.00, entradaPremium.getPrecio());
    }

    @Test
    void testPrecioNormal() {
        Asiento normal = new Asiento(5, 0, "Normal", 45.00);
        Entrada entradaNormal = new Entrada(3, normal, "Cliente Normal");
        assertEquals(45.00, entradaNormal.getPrecio());
    }

    // ==================== PRUEBAS DE INFORMACIÓN ====================

    @Test
    void testGetDetalles() {
        String detalles = entrada.getDetalles();
        assertNotNull(detalles);
        assertTrue(detalles.contains("1"));  // ID
        assertTrue(detalles.contains("Juan García"));  // Cliente
        assertTrue(detalles.contains("85"));  // Precio
    }

    @Test
    void testGetDetalles_ContieneAsiento() {
        String detalles = entrada.getDetalles();
        assertTrue(detalles.contains("0") && detalles.contains("5"));  // Fila y número
    }

    // ==================== PRUEBAS CON DIFERENTES CLIENTES ====================

    @Test
    void testEntrada_ClienteDiferente() {
        Entrada e2 = new Entrada(2, asiento, "María López");
        assertEquals("María López", e2.getCliente());
    }

    @Test
    void testEntrada_ClienteConEspacios() {
        Entrada e = new Entrada(3, asiento, "Pedro Antonio Hernández García");
        assertEquals("Pedro Antonio Hernández García", e.getCliente());
    }

    // ==================== PRUEBAS DE IDs DIFERENTES ====================

    @Test
    void testEntrada_IDDiferente() {
        Entrada e1 = new Entrada(1, asiento, "Cliente1");
        Entrada e2 = new Entrada(2, asiento, "Cliente2");
        Entrada e3 = new Entrada(3, asiento, "Cliente3");

        assertEquals(1, e1.getId());
        assertEquals(2, e2.getId());
        assertEquals(3, e3.getId());
    }

    // ==================== PRUEBAS DE CONSISTENCIA ====================

    @Test
    void testConsistencia_PrecioAsiento() {
        // El precio de la entrada debe coincidir con el del asiento
        assertEquals(asiento.getPrecio(), entrada.getPrecio());
    }

    @Test
    void testConsistencia_AsientoYSeccion() {
        assertEquals(asiento.getSeccion(), entrada.getAsiento().getSeccion());
    }

    // ==================== PRUEBAS DE INTEGRACIÓN ====================

    @Test
    void testFlujoCompleto_CompraEntrada() {
        Asiento a = new Asiento(1, 3, "Premium", 65.00);
        Entrada e = new Entrada(1, a, "Roberto");

        assertEquals(1, e.getId());
        assertEquals("Roberto", e.getCliente());
        assertEquals(65.00, e.getPrecio());
        assertEquals("Premium", e.getAsiento().getSeccion());
    }

    @Test
    void testMultiplesEntradas() {
        Asiento a1 = new Asiento(0, 0, "VIP", 85.00);
        Asiento a2 = new Asiento(0, 1, "VIP", 85.00);

        Entrada e1 = new Entrada(1, a1, "Cliente1");
        Entrada e2 = new Entrada(2, a2, "Cliente2");

        assertEquals(1, e1.getId());
        assertEquals(2, e2.getId());
        assertEquals("Cliente1", e1.getCliente());
        assertEquals("Cliente2", e2.getCliente());
    }
}
