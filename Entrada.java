import java.time.LocalDateTime;

/**
 * ================================================
 * CLASE ENTRADA - PASO 2
 * ================================================
 *
 * ¿QUÉ ES?
 * --------
 * Una Entrada es un "TICKET" que representa una compra realizada.
 * Es como el recibo que te dan cuando compras una entrada en una taquilla.
 *
 * DIFERENCIA CON ASIENTO:
 * ----------------------
 * - ASIENTO: Existe antes de cualquier compra. Es físico en el cine/teatro.
 * - ENTRADA: Se crea SOLO cuando alguien compra. Es la prueba de compra.
 *
 * ¿PARA QUÉ SIRVE?
 * ----------------
 * - Guardar datos de cada compra (quién, dónde, cuándo, cuánto pagó)
 * - Tener un registro de todas las ventas
 * - Permitir cancelar entradas: buscar por ID y revertir la compra
 * - Mostrar "mis entradas" a un cliente
 * - Calcular recaudación total
 *
 * ¿POR QUÉ LA NECESITAMOS?
 * ------------------------
 * Imagina que 10 personas compran entradas. Necesitas:
 * - Saber QUIÉN compró (nombre)
 * - CUÁL asiento compró
 * - CUÁNDO compró (para demostrar quién fue primero si hay conflictos)
 * - CUÁNTO pagó (para cobrar)
 * - Un ID único para cada compra (para cancelar después)
 *
 * Una lista de Entrada es como el "registro de ventas" de toda la empresa.
 *
 * ANALOGÍA: Entrada es como un "recibo de caja":
 * - ID: #12345 (número de recibo único)
 * - Asiento: A12 (qué se compró)
 * - Cliente: Juan García (quién compró)
 * - Fecha: 2026-04-29 18:30 (cuándo)
 * - Precio: 85€ (cuánto)
 *
 * ================================================
 */
public class Entrada {

    // ================================================
    // PASO 2.1: ATRIBUTOS (Datos de una compra)
    // ================================================
    // ¿POR QUÉ?
    // Cada entrada es una compra diferente. Necesitamos guardar sus datos.
    //
    // ¿PARA QUÉ CADA UNO?
    // - id: Número único de cada entrada (0, 1, 2, 3...). Para buscarla después.
    //   ¿Por qué único? Porque cuando cancelas, dices "cancela entrada #5".
    //   Si dos entradas tuvieran el mismo ID, sería confuso.
    //
    // - asiento: El objeto Asiento que se compró. Lo necesitamos porque:
    //   * Para mostrar dónde está el asiento (A12 | VIP | 85€)
    //   * Para liberar el asiento si se cancela
    //   * Para saber el precio (lo tomamos del asiento)
    //
    // - cliente: Nombre de quién compró. Para:
    //   * Mostrar quién es el dueño de la entrada
    //   * En sistemas reales, para verificar identidad en la puerta
    //
    // - fecha: Cuándo se compró. Para:
    //   * Registrar auditoría (quién compró primero, etc)
    //   * En sistemas reales, generar recibos con timestamp
    //
    // - precio: Cuánto pagó. Para:
    //   * Sumar todas las ventas (calcular recaudación)
    //   * Mostrar en el recibo
    //   * Nota: Es redundante (podríamos tomar asiento.getPrecio()),
    //     pero lo guardamos por si el precio cambia después (no en este ejercicio)
    //
    // TODO: Declara los 5 atributos privados
    // TODO: private int id;
    // TODO: private Asiento asiento;
    // TODO: private String cliente;
    // TODO: private LocalDateTime fecha;
    // TODO: private double precio;

    // ================================================
    // PASO 2.2: CONSTRUCTOR
    // ================================================
    // ¿POR QUÉ?
    // Para crear una Entrada necesitamos inicializar todos sus datos.
    // Se llama SOLO cuando alguien compra una entrada.
    //
    // ¿PARÁMETROS?
    // - int id: El número de entrada (Concierto mantiene un contador)
    // - Asiento asiento: El asiento que se compró (lo buscamos en la matriz)
    // - String cliente: Nombre de quién compra (lo pide Main por teclado)
    //
    // ¿QUÉ HACE?
    // 1. Guarda el ID
    // 2. Guarda la referencia al asiento (el objeto mismo, no una copia)
    // 3. Guarda el nombre del cliente
    // 4. Toma la fecha AHORA (cuando se ejecuta este constructor)
    // 5. Copia el precio del asiento al momento de la compra
    //
    // ¿POR QUÉ fecha = LocalDateTime.now()?
    // Porque queremos saber CUÁNDO se compró. Si lo hiciéramos manual,
    // habría que pasar la fecha como parámetro y sería error-prone.
    // now() automáticamente toma la fecha/hora actual del sistema.
    //
    // ¿POR QUÉ precio = asiento.getPrecio()?
    // Porque el precio ya está determinado en el asiento.
    // No lo pedimos como parámetro, lo extraemos de ahí.
    //
    // TODO: Implementa el constructor
    // TODO: public Entrada(int id, Asiento asiento, String cliente) {
    // TODO:     this.id = id;
    // TODO:     this.asiento = asiento;
    // TODO:     this.cliente = cliente;
    // TODO:     this.fecha = LocalDateTime.now();
    // TODO:     this.precio = asiento.getPrecio();
    // TODO: }

    // ================================================
    // PASO 2.3: GETTERS (Leer datos de la entrada)
    // ================================================
    // ¿POR QUÉ?
    // Para acceder a los datos privados de forma controlada.
    //
    // ¿DÓNDE SE USAN?
    // - getId(): En Concierto.cancelarEntrada() para buscar por ID
    // - getAsiento(): Para liberar el asiento en cancelarEntrada()
    // - getCliente(): Para mostrar "Entrada de Juan"
    // - getFecha(): Para mostrar cuándo se compró
    // - getPrecio(): Para sumar todas las ventas
    //
    // TODO: Implementa los 5 getters
    // TODO: public int getId() { return id; }
    // TODO: public Asiento getAsiento() { return asiento; }
    // TODO: public String getCliente() { return cliente; }
    // TODO: public LocalDateTime getFecha() { return fecha; }
    // TODO: public double getPrecio() { return precio; }

    // ================================================
    // PASO 2.4: getDetalles() - Información formateada
    // ================================================
    // ¿POR QUÉ?
    // Cuando haces "Ver mis entradas", queremos mostrar algo legible, no objetos.
    //
    // ¿QUÉ MUESTRA?
    // "Entrada #0 | Cliente: Juan | Asiento A2 | VIP | 85€"
    // O similar con todos los detalles.
    //
    // ¿CÓMO FUNCIONA?
    // 1. Concatena el ID: "Entrada #" + id
    // 2. Concatena el cliente: " | Cliente: " + cliente
    // 3. Concatena la info del asiento: " | Asiento " + asiento.getInfo()
    //    (Nota: asiento.getInfo() ya incluye fila/numero/seccion/precio/estado)
    // 4. Concatena la fecha: " | " + fecha.toLocalDate()
    //    (toLocalDate() muestra solo YYYY-MM-DD, sin hora)
    //
    // ¿PARA QUÉ?
    // Para que Main pueda hacer: System.out.println(entrada.getDetalles())
    // Y mostrar algo bonito.
    //
    // TODO: Implementa getDetalles()
    // TODO: public String getDetalles() {
    // TODO:     return "Entrada #" + id + " | Cliente: " + cliente +
    // TODO:            " | Asiento " + asiento.getInfo() +
    // TODO:            " | " + fecha.toLocalDate();
    // TODO: }

}
