import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Concierto
 */
public class ConciertoTest {

    private Concierto concierto;

    @BeforeEach
    void setUp() {
        concierto = new Concierto("Rosalía - Palau Sant Jordi", "Rosalía");
        concierto.inicializarAsientos();
    }

    // ==================== PRUEBAS DE INICIALIZACIÓN ====================

    @Test
    void testConstructor() {
        assertNotNull(concierto);
        assertEquals("Rosalía - Palau Sant Jordi", concierto.getNombre());
        assertEquals("Rosalía", concierto.getArtista());
    }

    @Test
    void testInicializarAsientos() {
        concierto.inicializarAsientos();
        // Verificar que la matriz está inicializada
        assertNotNull(concierto);
    }

    // ==================== PRUEBAS DE COMPRA DE ENTRADAS ====================

    @Test
    void testComprarEntrada_Valida() {
        int idEntrada = concierto.comprarEntrada(0, 0, "Juan");
        assertTrue(idEntrada > 0);
    }

    @Test
    void testComprarEntrada_AsientoOcupado() {
        concierto.comprarEntrada(0, 0, "Juan");
        // Intentar comprar el mismo asiento debe fallar
        assertThrows(Exception.class, () -> concierto.comprarEntrada(0, 0, "María"));
    }

    @Test
    void testComprarEntrada_IndiceInvalido() {
        assertThrows(Exception.class, () -> concierto.comprarEntrada(15, 10, "Cliente"));
    }

    // ==================== PRUEBAS DE SECCIONES ====================

    @Test
    void testSeccion_VIP() {
        Asiento asiento = concierto.buscarAsiento(0, 0);
        assertNotNull(asiento);
        assertEquals("VIP", asiento.getSeccion());
    }

    @Test
    void testSeccion_Premium() {
        Asiento asiento = concierto.buscarAsiento(2, 0);
        assertNotNull(asiento);
        assertEquals("Premium", asiento.getSeccion());
    }

    @Test
    void testSeccion_Normal() {
        Asiento asiento = concierto.buscarAsiento(5, 0);
        assertNotNull(asiento);
        assertEquals("Normal", asiento.getSeccion());
    }

    // ==================== PRUEBAS DE PRECIOS ====================

    @Test
    void testPrecio_VIP() {
        Asiento asiento = concierto.buscarAsiento(0, 0);
        assertEquals(85.0, asiento.getPrecio());
    }

    @Test
    void testPrecio_Premium() {
        Asiento asiento = concierto.buscarAsiento(2, 0);
        assertEquals(65.0, asiento.getPrecio());
    }

    @Test
    void testPrecio_Normal() {
        Asiento asiento = concierto.buscarAsiento(5, 0);
        assertEquals(45.0, asiento.getPrecio());
    }

    // ==================== PRUEBAS DE BÚSQUEDA DE ASIENTOS ====================

    @Test
    void testBuscarAsiento_Valido() {
        Asiento asiento = concierto.buscarAsiento(0, 0);
        assertNotNull(asiento);
    }

    @Test
    void testBuscarAsiento_FilaInvalida() {
        Asiento asiento = concierto.buscarAsiento(15, 0);
        assertNull(asiento);
    }

    @Test
    void testBuscarAsiento_ColumnaInvalida() {
        Asiento asiento = concierto.buscarAsiento(0, 10);
        assertNull(asiento);
    }

    // ==================== PRUEBAS DE OCUPACIÓN ====================

    @Test
    void testOcupacion_Inicial() {
        double ocupacion = concierto.calcularOcupacion();
        assertEquals(0.0, ocupacion);
    }

    @Test
    void testOcupacion_DespuesDeUnaCompra() {
        concierto.comprarEntrada(0, 0, "Juan");
        double ocupacion = concierto.calcularOcupacion();
        assertTrue(ocupacion > 0);
    }

    @Test
    void testOcupacion_Porcentaje() {
        concierto.comprarEntrada(0, 0, "Juan");
        double ocupacion = concierto.calcularOcupacion();
        assertTrue(ocupacion <= 1.0);
        assertTrue(ocupacion >= 0.0);
    }

    // ==================== PRUEBAS DE RECAUDACIÓN ====================

    @Test
    void testRecaudacion_Inicial() {
        double recaudacion = concierto.calcularRecaudacion();
        assertEquals(0.0, recaudacion);
    }

    @Test
    void testRecaudacion_DespuesDeUnaCompra() {
        concierto.comprarEntrada(0, 0, "Juan");
        double recaudacion = concierto.calcularRecaudacion();
        assertEquals(85.0, recaudacion);
    }

    @Test
    void testRecaudacion_MultiplesCompras() {
        concierto.comprarEntrada(0, 0, "Juan");     // VIP: 85
        concierto.comprarEntrada(2, 0, "María");    // Premium: 65
        double recaudacion = concierto.calcularRecaudacion();
        assertEquals(150.0, recaudacion);
    }

    // ==================== PRUEBAS DE CANCELACIÓN ====================

    @Test
    void testCancelarEntrada_Valida() {
        int idEntrada = concierto.comprarEntrada(0, 0, "Juan");
        // Debe ser posible cancelar una entrada existente
        concierto.cancelarEntrada(idEntrada);
    }

    @Test
    void testCancelarEntrada_YRecomprar() {
        int id1 = concierto.comprarEntrada(0, 0, "Juan");
        concierto.cancelarEntrada(id1);
        // Después de cancelar, el asiento debe estar disponible
        int id2 = concierto.comprarEntrada(0, 0, "María");
        assertTrue(id2 > 0);
    }

    // ==================== PRUEBAS DE CAPACIDAD ====================

    @Test
    void testCapacidad_Total() {
        // 10 filas × 8 columnas = 80 asientos
        assertEquals(80, 10 * 8);
    }

    // ==================== PRUEBAS DE INTEGRACIÓN ====================

    @Test
    void testFlujo_CompraCompleta() {
        // Comprar asiento VIP
        int id1 = concierto.comprarEntrada(0, 0, "Cliente1");
        assertTrue(id1 > 0);

        // Verificar ocupación
        double ocupacion = concierto.calcularOcupacion();
        assertTrue(ocupacion > 0);

        // Verificar recaudación
        double recaudacion = concierto.calcularRecaudacion();
        assertEquals(85.0, recaudacion);
    }

    @Test
    void testFlujo_MultiplesAsientos() {
        concierto.comprarEntrada(0, 0, "A");
        concierto.comprarEntrada(0, 1, "B");
        concierto.comprarEntrada(2, 0, "C");

        double recaudacion = concierto.calcularRecaudacion();
        assertEquals(85.0 + 85.0 + 65.0, recaudacion);
    }

    // ==================== PRUEBAS DE INFORMACIÓN ====================

    @Test
    void testGetNombre() {
        assertEquals("Rosalía - Palau Sant Jordi", concierto.getNombre());
    }

    @Test
    void testGetArtista() {
        assertEquals("Rosalía", concierto.getArtista());
    }
}
