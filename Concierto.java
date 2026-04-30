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
    // ENCAPSULACIÓN: ATRIBUTOS PRIVADOS DEL CONCIERTO
    // ================================================
    // Concierto es el "GESTOR" o "MOSTRADOR" del sistema de ticketing.
    // Gestiona TODO lo que pasa internamente.
    // Las otras clases (Main) NO acceden directamente, solo piden mediante métodos.
    //
    // ANALOGÍA: Concierto es como el MOSTRADOR DE UN CINE:
    // - Tú NO entras a la bóveda o la trastienda
    // - PREGUNTAS al mostrador: "¿Hay asientos?"
    // - El mostrador busca, procesa, y TE RESPONDE
    // - Los datos internos (asientos, dinero, registros) son PRIVATE
    //
    // ¿POR QUÉ ESTOS ESPECÍFICAMENTE?
    //
    // - nombre (String): "Rosalía - Palau Sant Jordi"
    //   Para identificar el evento
    //   En un sistema real, tendrías múltiples conciertos
    //   ENCAPSULACIÓN: nombre es PRIVATE, no lo cambia nadie
    //
    // - artista (String): "Rosalía"
    //   Información del artista
    //   Para mostrar en detalles y tickets
    //   ENCAPSULACIÓN: artista es PRIVATE
    //
    // - asientos (Asiento[][]): MATRIZ 2D (10 filas × 8 columnas)
    //   ¿POR QUÉ 2D y no ArrayList?
    //   VELOCIDAD: matriz[i][j] es INSTANTÁNEO (O(1))
    //   ArrayList: buscar sería LENTO (O(n))
    //   Necesitamos acceso RÁPIDO a cada asiento
    //
    //   ¿POR QUÉ [10][8]?
    //   10 filas (A-J) × 8 columnas = 80 asientos totales
    //   Tamaño FIJO, nunca cambia
    //
    // - entradas (ArrayList<Entrada>): LISTA DE COMPRAS
    //   ¿POR QUÉ ArrayList y no matriz?
    //   FLEXIBILIDAD: número de entradas es VARIABLE (0 a 80)
    //   ArrayList permite: agregar, iterar, eliminar fácilmente
    //   Matriz sería rígida
    //
    // - contadorEntradas (int): CONTADOR AUTO-INCREMENTABLE DE IDs
    //   ¿POR QUÉ un contador?
    //   Cada entrada necesita un ID ÚNICO (0, 1, 2, 3...)
    //   Contador SIEMPRE sube, NUNCA baja
    //   Así garantizamos IDs únicos incluso si hay cancelaciones
    //   Ejemplo: Se venden 5 entradas (IDs 1,2,3,4,5)
    //            Se cancela entrada #3
    //            Se vende entrada #6 (el contador sigue subiendo)
    //            No reutilizamos el #3 (evita confusiones)
    //
    private String nombre;
    private String artista;
    private Asiento[][] asientos;
    private ArrayList<Entrada> entradas;
    private int contadorEntradas;

    // ================================================
    // PASO 3.2: CONSTRUCTOR - Preparar la estructura del concierto
    // ================================================
    // ¿PARA QUÉ?
    // Inicializar un concierto NUEVO.
    // Preparar todas las "cajas" que necesitamos.
    //
    // ANALOGÍA: Como ABRIR UN CINE NUEVO:
    // 1. Pones el nombre en la puerta: "Cine Central"
    // 2. Escribes quién toca: "Rosalía"
    // 3. Compras asientos VACÍOS (sin llenar aún)
    // 4. Preparas un registro de ventas (empty)
    // 5. Inicializas el contador de tickets en 0
    //
    // ¿QUÉ HACE?
    // 1. Guarda nombre y artista (info del evento)
    // 2. Crea matriz VACÍA (10×8) - sin asientos aún, todos null
    // 3. Crea ArrayList VACÍO - sin entradas aún
    // 4. Inicializa contador a 0
    //
    // ¿POR QUÉ NO crea asientos AQUÍ?
    // SEPARACIÓN DE RESPONSABILIDADES:
    // - Constructor: PREPARA la estructura (cajas vacías)
    // - inicializarAsientos(): LLENA la estructura (mete asientos en las cajas)
    //
    // Si metemos TODO en el constructor, sería GIGANTE
    // Mejor hacerlo en dos pasos
    //
    // ¿PASO A PASO?
    // 1. Guardar datos: this.nombre = nombre
    // 2. Guardar datos: this.artista = artista
    // 3. Crear matriz VACÍA: asientos = new Asiento[10][8]
    //    (Crea la estructura, pero sin objetos dentro)
    // 4. Crear ArrayList VACÍO: entradas = new ArrayList<>()
    //    (Crea la lista, pero vacía)
    // 5. Inicializar contador: contadorEntradas = 0
    //    (Comienza en 0, incrementará con cada venta)
    //
    public Concierto(String nombre, String artista) {
        this.nombre = nombre;
        this.artista = artista;
        asientos = new Asiento[10][8];      // Matriz vacía, lista para llenar
        entradas = new ArrayList<>();       // ArrayList vacío, lista para compras
        contadorEntradas = 0;               // Contador en 0, incrementará
    }

    // ================================================
    // PASO 3.3: inicializarAsientos() - Llenar la matriz de asientos
    // ================================================
    // ¿PARA QUÉ?
    // Llenar la matriz 10×8 con 80 OBJETOS Asiento.
    // Cada asiento sabe: fila, número, sección, precio.
    //
    // ANALOGÍA: Como NUMERAR ASIENTOS EN UN TEATRO:
    // Fila 1 (VIP): asiento 1, 2, 3, 4, 5, 6, 7, 8 → 85€ cada uno
    // Fila 2 (VIP): asiento 1, 2, 3, 4, 5, 6, 7, 8 → 85€ cada uno
    // Fila 3 (Premium): asiento 1, 2, 3, 4, 5, 6, 7, 8 → 65€ cada uno
    // ...
    // Fila 10 (Normal): asiento 1, 2, 3, 4, 5, 6, 7, 8 → 45€ cada uno
    //
    // ¿CÓMO FUNCIONA?
    // 1. Dos bucles anidados (recorrer la matriz)
    // 2. Para cada posición [i][j], determina la sección según la FILA
    // 3. Crea un Asiento con esa información
    // 4. Lo coloca en la matriz
    //
    // ¿POR QUÉ ESTOS PRECIOS?
    // ECONOMÍA: Venta de entradas es como un CINE:
    // - Filas ADELANTE (VIP): mejor vista → 85€ (más caro)
    // - Filas MEDIO (Premium): vista normal → 65€ (medio)
    // - Filas ATRÁS (Normal): vista lejana → 45€ (barato)
    //
    // ¿LÓGICA DE FILAS?
    // Filas 0-1 (primeras dos) → VIP
    //   porque: i < 2 (si i es 0 o 1)
    // Filas 2-4 (tres de las siguientes) → Premium
    //   porque: i < 5 (si i es 2, 3, o 4)
    // Filas 5-9 (las últimas cinco) → Normal
    //   porque: ninguna de las anteriores
    //
    // ¿PASO A PASO?
    // 1. For externo: recorrer filas (i = 0 a 9)
    // 2. For interno: recorrer columnas (j = 0 a 7)
    // 3. Determinar sección según i:
    //    if (i < 2) seccion="VIP", precio=85
    //    else if (i < 5) seccion="Premium", precio=65
    //    else seccion="Normal", precio=45
    // 4. Crear: new Asiento(i, j, seccion, precio)
    // 5. Guardar en matriz: asientos[i][j] = ...
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
