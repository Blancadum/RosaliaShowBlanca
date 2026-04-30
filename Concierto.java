import java.util.ArrayList;

/**
 * ================================================
 * CLASE CONCIERTO - PASO 3
 * ================================================
 *
 * ¿QUÉ ES?
 * --------
 * La clase Concierto es el "CORAZÓN" del programa.
 * Gestiona TODA la lógica del sistema de ticketing.
 *
 * Es como el "gestor" o "administrador" del concierto:
 * - Controla los 80 asientos (matriz 2D)
 * - Controla las ventas (ArrayList de Entradas)
 * - Permite comprar, cancelar, ver estadísticas
 *
 * ¿PARA QUÉ SIRVE?
 * ----------------
 * - Organizar asientos por sección y precio
 * - Permitir comprar y cancelar entradas
 * - Mostrar el mapa visual de disponibilidad
 * - Calcular ocupación y recaudación
 * - Mantener un registro de todas las ventas
 *
 * ¿POR QUÉ LA NECESITAMOS?
 * ------------------------
 * Si todo esto lo hiciéramos en Main, Main sería GIGANTE (300+ líneas).
 * Mejor separar: Concierto maneja la lógica, Main solo pide al usuario.
 *
 * ANALOGÍA: Concierto es como el "mostrador de taquilla":
 * - Tiene los asientos físicos (matriz)
 * - Tiene los registros de quién compró (ArrayList)
 * - Realiza las transacciones (comprar, cancelar)
 * - Responde preguntas (¿cuánto he vendido?, ¿cuántos asientos libres?)
 *
 * ================================================
 */
public class Concierto {

    // ================================================
    // PASO 3.1: ATRIBUTOS (Estado completo del concierto)
    // ================================================
    // ¿POR QUÉ ESTOS ESPECÍFICAMENTE?
    //
    // - nombre (String): "Rosalía - Palau Sant Jordi"
    //   Para identificar el evento. En un sistema real, tendrías múltiples conciertos.
    //
    // - artista (String): "Rosalía"
    //   Información del artista. Mostrar en detalles/tickets.
    //
    // - asientos (Asiento[][]): MATRIZ 2D
    //   ¿POR QUÉ 2D y no un ArrayList?
    //   - 2D: matriz[fila][columna] es RÁPIDO (O(1))
    //   - ArrayList: buscar sería lento (O(n))
    //   Necesitamos acceso rápido a cada asiento.
    //
    //   ¿POR QUÉ [10][8]?
    //   - 10 filas (A-J) × 8 columnas = 80 asientos totales
    //   - Tamaño fijo, nunca cambia
    //
    // - entradas (ArrayList<Entrada>): LISTA DE COMPRAS
    //   ¿POR QUÉ ArrayList y no matriz?
    //   - Número de entradas VARIABLE (0 a 80)
    //   - Necesitamos iterar frecuentemente (mostrar todas)
    //   - Necesitamos borrar del medio (cancelar)
    //   ArrayList es perfecto para esto.
    //
    // - contadorEntradas (int): CONTADOR DE IDs
    //   ¿POR QUÉ un contador?
    //   - Cada entrada necesita un ID único (0, 1, 2, 3...)
    //   - contadorEntradas siempre sube, nunca baja
    //   - Así garantizamos IDs únicos aunque haya cancelaciones
    //
    // TODO: Declara los 5 atributos privados
    // TODO: private String nombre;
    // TODO: private String artista;
    // TODO: private Asiento[][] asientos;
    // TODO: private ArrayList<Entrada> entradas;
    // TODO: private int contadorEntradas;

    // ================================================
    // PASO 3.2: CONSTRUCTOR
    // ================================================
    // ¿PARA QUÉ?
    // Inicializar un concierto nuevo.
    //
    // ¿QUÉ HACE?
    // 1. Guarda nombre y artista
    // 2. Crea matriz vacía (10×8) - sin asientos aún, todos null
    // 3. Crea ArrayList vacío - sin entradas aún
    // 4. Inicializa contador a 0
    //
    // ¿POR QUÉ NO crea asientos aquí?
    // Podría hacerlo, pero es mejor separar responsabilidades.
    // El constructor prepara la estructura, inicializarAsientos() la llena.
    //
    // TODO: Implementa el constructor
    // TODO: public Concierto(String nombre, String artista) {
    // TODO:     this.nombre = nombre;
    // TODO:     this.artista = artista;
    // TODO:     asientos = new Asiento[10][8];
    // TODO:     entradas = new ArrayList<>();
    // TODO:     contadorEntradas = 0;
    // TODO: }

    // ================================================
    // PASO 3.3: inicializarAsientos()
    // ================================================
    // ¿PARA QUÉ?
    // Llenar la matriz con 80 Asientos, cada uno con su info.
    //
    // ¿CÓMO FUNCIONA?
    // 1. Dos bucles anidados: for (fila 0-9) for (columna 0-7)
    // 2. Para cada posición, determina la sección según la fila:
    //    - Fila 0-1 (primeras) → VIP (85€)
    //    - Fila 2-4 (medio) → Premium (65€)
    //    - Fila 5-9 (fondo) → Normal (45€)
    // 3. Crea un Asiento con esa información
    // 4. Lo coloca en la matriz: asientos[i][j] = new Asiento(...)
    //
    // ¿POR QUÉ ESTOS PRECIOS?
    // - VIP: adelante, mejor vista → más caro
    // - Premium: medio → precio medio
    // - Normal: atrás → más barato
    //
    // TODO: Implementa inicializarAsientos()
    // TODO: public void inicializarAsientos() {
    // TODO:     for (int i = 0; i < asientos.length; i++) {
    // TODO:         for (int j = 0; j < asientos[i].length; j++) {
    // TODO:             String seccion;
    // TODO:             double precio;
    // TODO:             if (i < 2) {
    // TODO:                 seccion = "VIP";
    // TODO:                 precio = 85.00;
    // TODO:             } else if (i < 5) {
    // TODO:                 seccion = "Premium";
    // TODO:                 precio = 65.00;
    // TODO:             } else {
    // TODO:                 seccion = "Normal";
    // TODO:                 precio = 45.00;
    // TODO:             }
    // TODO:             asientos[i][j] = new Asiento(i, j, seccion, precio);
    // TODO:         }
    // TODO:     }
    // TODO: }

    // ================================================
    // PASO 3.4: mostrarMapa()
    // ================================================
    // ¿PARA QUÉ?
    // Mostrar una vista VISUAL de qué asientos están disponibles.
    //
    // ¿EJEMPLO?
    // A ✅ ✅ ❌ ✅ ✅ ✅ ✅ ✅
    // B ✅ ❌ ✅ ✅ ✅ ✅ ❌ ✅
    // C ...
    //
    // ¿CÓMO FUNCIONA?
    // 1. Imprime encabezado
    // 2. Para cada fila (0-9):
    //    - Imprime la letra de la fila (A, B, C, ...)
    //    - Para cada columna (0-7):
    //      - Imprime ✅ si está disponible
    //      - Imprime ❌ si está ocupado
    // 3. Imprime nueva línea al final de cada fila
    //
    // TODO: Implementa mostrarMapa()
    // TODO: public void mostrarMapa() {
    // TODO:     System.out.println("\n=== MAPA DE ASIENTOS ===");
    // TODO:     System.out.println("✅ = Disponible | ❌ = Ocupado\n");
    // TODO:     for (int i = 0; i < asientos.length; i++) {
    // TODO:         char letraFila = (char)('A' + i);
    // TODO:         System.out.print(letraFila + " ");
    // TODO:         for (int j = 0; j < asientos[i].length; j++) {
    // TODO:             System.out.print(asientos[i][j].getMarcaAsiento() + " ");
    // TODO:         }
    // TODO:         System.out.println();
    // TODO:     }
    // TODO: }

    // ================================================
    // PASO 3.5: buscarAsiento()
    // ================================================
    // ¿PARA QUÉ?
    // Encontrar un asiento específico en la matriz.
    //
    // ¿POR QUÉ NO acceder directamente a asientos[fila][numero]?
    // Porque queremos VALIDAR que los índices sean válidos.
    // Si el usuario pide fila 20, no queremos crash, queremos null.
    //
    // ¿CÓMO FUNCIONA?
    // 1. Valida: ¿fila está 0-9 y número 0-7?
    // 2. Si sí: retorna asientos[fila][numero]
    // 3. Si no: retorna null
    //
    // TODO: Implementa buscarAsiento()
    // TODO: public Asiento buscarAsiento(int fila, int numero) {
    // TODO:     if (fila < 0 || fila >= asientos.length || numero < 0 || numero >= asientos[0].length) {
    // TODO:         return null;
    // TODO:     }
    // TODO:     return asientos[fila][numero];
    // TODO: }

    // ================================================
    // PASO 3.6: comprarEntrada()
    // ================================================
    // ¿PARA QUÉ?
    // La operación principal: VENDER una entrada.
    //
    // ¿FLUJO?
    // Usuario: "Quiero fila 0, número 2"
    // 1. ¿Existe ese asiento? → buscar
    // 2. ¿Está disponible? → check
    // 3. ✅ → Incrementar contador, crear Entrada, ocupar asiento, guardar
    // 4. Retornar ID de la entrada
    //
    // ¿VALIDACIONES?
    // - Asiento inválido (índices fuera de rango) → null → throw error
    // - Asiento ya ocupado → isDisponible()=false → throw error
    //
    // ¿POR QUÉ throws?
    // Así Main puede capturar con try-catch y mostrar al usuario.
    // Separamos lógica (Concierto) de presentación (Main).
    //
    // ¿POR QUÉ AQUÍ ocupa el asiento?
    // Después de crear la Entrada, el asiento ya no está disponible.
    // asiento.ocupar() marca disponible=false.
    //
    // TODO: Implementa comprarEntrada()
    // TODO: public int comprarEntrada(int fila, int numero, String cliente) throws IllegalArgumentException {
    // TODO:     Asiento asiento = buscarAsiento(fila, numero);
    // TODO:     if (asiento == null) {
    // TODO:         throw new IllegalArgumentException("Asiento inválido");
    // TODO:     }
    // TODO:     if (!asiento.isDisponible()) {
    // TODO:         throw new IllegalArgumentException("Asiento ya ocupado");
    // TODO:     }
    // TODO:     contadorEntradas++;
    // TODO:     Entrada entrada = new Entrada(contadorEntradas, asiento, cliente);
    // TODO:     asiento.ocupar();
    // TODO:     entradas.add(entrada);
    // TODO:     return contadorEntradas;
    // TODO: }

    // ================================================
    // PASO 3.7: cancelarEntrada()
    // ================================================
    // ¿PARA QUÉ?
    // REVERTIR una compra anterior.
    //
    // ¿FLUJO?
    // Usuario: "Cancela entrada #3"
    // 1. Buscar entrada con ID=3 en ArrayList
    // 2. Si encontrada:
    //    - Obtener su asiento
    //    - Marcar asiento como disponible (liberar)
    //    - Eliminar entrada del ArrayList
    // 3. Si no encontrada: mensaje de error
    //
    // ¿POR QUÉ con índice, no foreach?
    // No puedes hacer .remove() dentro de un foreach.
    // Causa ConcurrentModificationException.
    // For tradicional con índice es seguro.
    //
    // TODO: Implementa cancelarEntrada()
    // TODO: public void cancelarEntrada(int idEntrada) {
    // TODO:     for (int i = 0; i < entradas.size(); i++) {
    // TODO:         if (entradas.get(i).getId() == idEntrada) {
    // TODO:             Entrada entrada = entradas.get(i);
    // TODO:             entrada.getAsiento().liberar();
    // TODO:             entradas.remove(i);
    // TODO:             return;
    // TODO:         }
    // TODO:     }
    // TODO: }

    // ================================================
    // PASO 3.8: calcularOcupacion()
    // ================================================
    // ¿PARA QUÉ?
    // Saber qué porcentaje del concierto se ha vendido.
    //
    // ¿FÓRMULA?
    // Entradas vendidas / Asientos totales
    // = entradas.size() / 80
    // = 40 / 80 = 0.5 (50%)
    //
    // ¿POR QUÉ retorna double 0.0-1.0 en lugar de porcentaje?
    // Es más flexible. Main puede:
    // - Mostrar como 50% (multiplica por 100)
    // - Usar para comparaciones: if (ocupacion > 0.75) warning()
    // - Guardar en base de datos
    //
    // TODO: Implementa calcularOcupacion()
    // TODO: public double calcularOcupacion() {
    // TODO:     int total = asientos.length * asientos[0].length;
    // TODO:     return (double) entradas.size() / total;
    // TODO: }

    // ================================================
    // PASO 3.9: calcularRecaudacion()
    // ================================================
    // ¿PARA QUÉ?
    // Saber cuánto dinero se ha ganado en total.
    //
    // ¿CÓMO?
    // Iterar todas las Entradas y sumar sus precios.
    //
    // ¿EJEMPLO?
    // 10 VIP (85€ c/u) = 850€
    // 10 Premium (65€ c/u) = 650€
    // 5 Normal (45€ c/u) = 225€
    // Total = 1725€
    //
    // TODO: Implementa calcularRecaudacion()
    // TODO: public double calcularRecaudacion() {
    // TODO:     double total = 0;
    // TODO:     for (Entrada e : entradas) {
    // TODO:         total += e.getPrecio();
    // TODO:     }
    // TODO:     return total;
    // TODO: }

    // ================================================
    // PASO 3.10: verEntradasVendidas()
    // ================================================
    // ¿PARA QUÉ?
    // Mostrar un listado de todas las entradas vendidas.
    //
    // ¿FLUJO?
    // 1. ¿ArrayList está vacío? → mostrar "No hay entradas"
    // 2. Si no → iterar y mostrar detalles de cada una
    //
    // TODO: Implementa verEntradasVendidas()
    // TODO: public void verEntradasVendidas() {
    // TODO:     if (entradas.isEmpty()) {
    // TODO:         System.out.println("No hay entradas vendidas");
    // TODO:         return;
    // TODO:     }
    // TODO:     System.out.println("\n=== ENTRADAS VENDIDAS ===");
    // TODO:     for (Entrada e : entradas) {
    // TODO:         System.out.println(e.getDetalles());
    // TODO:     }
    // TODO: }

}
