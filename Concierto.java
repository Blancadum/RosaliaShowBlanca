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
    // ¿PASO A PASO?
    // 1. Declara "private String nombre" (info del evento)
    // 2. Declara "private String artista" (info del artista)
    // 3. Declara "private Asiento[][] asientos" (matriz 2D para los asientos)
    // 4. Declara "private ArrayList<Entrada> entradas" (lista de compras)
    // 5. Declara "private int contadorEntradas" (contador para IDs únicos)
    //
    private String nombre;
    private String artista;
    private Asiento[][] asientos;
    private ArrayList<Entrada> entradas;
    private int contadorEntradas;

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
    // ¿PASO A PASO?
    // 1. Guardar nombre con "this.nombre = nombre"
    // 2. Guardar artista con "this.artista = artista"
    // 3. Crear matriz: "asientos = new Asiento[10][8]" (vacía, sin elementos)
    // 4. Crear ArrayList: "entradas = new ArrayList<>()" (vacío, sin entradas)
    // 5. Inicializar contador: "contadorEntradas = 0" (comienza en 0)
    //
    public Concierto(String nombre, String artista) {
        this.nombre = nombre;
        this.artista = artista;
        asientos = new Asiento[10][8];
        entradas = new ArrayList<>();
        contadorEntradas = 0;
    }

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
    // ¿PASO A PASO?
    // 1. Bucle externo: for (int i = 0; i < asientos.length; i++) → recorre filas 0-9
    // 2. Bucle interno: for (int j = 0; j < asientos[i].length; j++) → recorre columnas 0-7
    // 3. Para cada [i][j], determinar sección:
    //    - Si i < 2 → VIP (85€)
    //    - Si i < 5 → Premium (65€)
    //    - Si no → Normal (45€)
    // 4. Crear nuevo Asiento con esos datos
    // 5. Guardarlo en la matriz: asientos[i][j] = new Asiento(...)
    //
    public void inicializarAsientos() {
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                String seccion;
                double precio;
                if (i < 2) {
                    seccion = "VIP";
                    precio = 85.00;
                } else if (i < 5) {
                    seccion = "Premium";
                    precio = 65.00;
                } else {
                    seccion = "Normal";
                    precio = 45.00;
                }
                asientos[i][j] = new Asiento(i, j, seccion, precio);
            }
        }
    }

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
    // ¿PASO A PASO?
    // 1. Imprimir títulos y leyenda
    // 2. Bucle externo: for (int i = 0; i < asientos.length; i++)
    // 3. Para cada fila i, convertir a letra: char letraFila = (char)('A' + i)
    // 4. Imprimir la letra: System.out.print(letraFila + " ")
    // 5. Bucle interno: for (int j = 0; j < asientos[i].length; j++)
    // 6. Para cada asiento, imprimir su marca: asientos[i][j].getMarcaAsiento()
    // 7. Imprimir nueva línea al final de cada fila: System.out.println()
    //
    public void mostrarMapa() {
        System.out.println("\n=== MAPA DE ASIENTOS ===");
        System.out.println("✅ = Disponible | ❌ = Ocupado\n");
        for (int i = 0; i < asientos.length; i++) {
            char letraFila = (char)('A' + i);
            System.out.print(letraFila + " ");
            for (int j = 0; j < asientos[i].length; j++) {
                System.out.print(asientos[i][j].getMarcaAsiento() + " ");
            }
            System.out.println();
        }
    }

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
    // ¿PASO A PASO?
    // 1. Crear un if que verifique si fila o numero están fuera de rango
    // 2. Condiciones para validar: fila < 0 || fila >= 10 || numero < 0 || numero >= 8
    // 3. Si alguna condición es true → return null (asiento inválido)
    // 4. Si todas pasan → return asientos[fila][numero] (asiento válido)
    //
    public Asiento buscarAsiento(int fila, int numero) {
        if (fila < 0 || fila >= asientos.length || numero < 0 || numero >= asientos[0].length) {
            return null;
        }
        return asientos[fila][numero];
    }

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
    // ¿PASO A PASO?
    // 1. Buscar el asiento: Asiento asiento = buscarAsiento(fila, numero)
    // 2. Si asiento == null → throw IllegalArgumentException("Asiento inválido")
    // 3. Si !asiento.isDisponible() → throw IllegalArgumentException("Asiento ya ocupado")
    // 4. Incrementar contador: contadorEntradas++
    // 5. Crear entrada: new Entrada(contadorEntradas, asiento, cliente)
    // 6. Ocupar el asiento: asiento.ocupar()
    // 7. Guardar en lista: entradas.add(entrada)
    // 8. Retornar el ID de la entrada: return contadorEntradas
    //
    public int comprarEntrada(int fila, int numero, String cliente) throws IllegalArgumentException {
        Asiento asiento = buscarAsiento(fila, numero);
        if (asiento == null) {
            throw new IllegalArgumentException("Asiento inválido");
        }
        if (!asiento.isDisponible()) {
            throw new IllegalArgumentException("Asiento ya ocupado");
        }
        contadorEntradas++;
        Entrada entrada = new Entrada(contadorEntradas, asiento, cliente);
        asiento.ocupar();
        entradas.add(entrada);
        return contadorEntradas;
    }

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
    // ¿PASO A PASO?
    // 1. For con índice: for (int i = 0; i < entradas.size(); i++)
    // 2. Comparar: if (entradas.get(i).getId() == idEntrada)
    // 3. Si encuentra, obtener la entrada: Entrada entrada = entradas.get(i)
    // 4. Liberar el asiento: entrada.getAsiento().liberar()
    // 5. Eliminar del ArrayList: entradas.remove(i)
    // 6. Retornar para salir del método
    //
    public void cancelarEntrada(int idEntrada) {
        for (int i = 0; i < entradas.size(); i++) {
            if (entradas.get(i).getId() == idEntrada) {
                Entrada entrada = entradas.get(i);
                entrada.getAsiento().liberar();
                entradas.remove(i);
                return;
            }
        }
    }

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
    // ¿PASO A PASO?
    // 1. Calcular total de asientos: asientos.length × asientos[0].length (10 × 8 = 80)
    // 2. Obtener entradas vendidas: entradas.size()
    // 3. Dividir: (double) entradas.size() / total (cast a double para precisión)
    // 4. Retornar el resultado (0.0 a 1.0)
    //
    public double calcularOcupacion() {
        int total = asientos.length * asientos[0].length;
        return (double) entradas.size() / total;
    }

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
    // ¿PASO A PASO?
    // 1. Declarar variable acumuladora: double total = 0
    // 2. For-each para iterar entradas: for (Entrada e : entradas)
    // 3. Sumar en cada iteración: total += e.getPrecio()
    // 4. Retornar total al finalizar
    //
    public double calcularRecaudacion() {
        double total = 0;
        for (Entrada e : entradas) {
            total += e.getPrecio();
        }
        return total;
    }

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
    // ¿PASO A PASO?
    // 1. Verificar si ArrayList está vacío: if (entradas.isEmpty())
    // 2. Si está vacío → System.out.println("No hay entradas vendidas") y return
    // 3. Si no está vacío → imprimir título
    // 4. For-each para iterar: for (Entrada e : entradas)
    // 5. Mostrar detalles: System.out.println(e.getDetalles())
    //
    public void verEntradasVendidas() {
        if (entradas.isEmpty()) {
            System.out.println("No hay entradas vendidas");
            return;
        }
        System.out.println("\n=== ENTRADAS VENDIDAS ===");
        for (Entrada e : entradas) {
            System.out.println(e.getDetalles());
        }
    }

    // ================================================
    // GETTER para acceder a las entradas (necesario en Main)
    // ================================================
    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }

}
