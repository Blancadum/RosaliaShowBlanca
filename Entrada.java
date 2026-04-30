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
    // PASO 2.1: ENCAPSULACIÓN - ATRIBUTOS PRIVADOS
    // ================================================
    // ¿POR QUÉ?
    // Cada entrada es una compra diferente. Necesitamos guardar TODOS sus datos.
    // Una entrada SIN datos = no sabe quién compró, dónde, cuándo, cuánto.
    //
    // ANALOGÍA: Una ENTRADA es como un RECIBO DEL BANCO:
    // - ID: #12345 (número de transacción único)
    // - Asiento: A2 (qué se compró)
    // - Cliente: Juan (quién compró)
    // - Fecha: 2026-04-29 18:30 (cuándo)
    // - Precio: 85€ (cuánto)
    //
    // ¿PARA QUÉ CADA UNO?
    //
    // - id: Número ÚNICO de cada entrada (0, 1, 2, 3...)
    //   ¿Por qué único? Porque cuando cancelas, dices "cancela entrada #5"
    //   Si dos entradas tuvieran el mismo ID, sería IMPOSIBLE saber cuál cancelar
    //   ENCAPSULACIÓN: id es PRIVATE para que nadie lo cambie
    //
    // - asiento: El objeto Asiento QUE SE COMPRÓ
    //   Lo necesitamos porque:
    //   * Mostrar: "Asiento A2 | VIP | 85€"
    //   * Liberar el asiento si se cancela
    //   * Obtener el precio
    //   ENCAPSULACIÓN: asiento es PRIVATE, no puede cambiarse después de compra
    //
    // - cliente: Nombre de quién compró
    //   Para:
    //   * Mostrar "Entrada de Juan"
    //   * Verificar identidad en la puerta
    //   ENCAPSULACIÓN: cliente es PRIVATE
    //
    // - fecha: Cuándo se compró (fecha + hora)
    //   Para:
    //   * Auditoría: quién compró primero si hay disputa
    //   * Registrar timestamp del sistema
    //   * LocalDateTime.now() captura el momento exacto de compra
    //   ENCAPSULACIÓN: fecha es PRIVATE y se genera AUTOMÁTICAMENTE
    //
    // - precio: Cuánto pagó
    //   Para:
    //   * Sumar todas las ventas (recaudación total)
    //   * Mostrar en recibo
    //   Nota: Se toma del asiento al crear la entrada
    //   ENCAPSULACIÓN: precio es PRIVATE
    //
    private int id;
    private Asiento asiento;
    private String cliente;
    private LocalDateTime fecha;
    private double precio;

    // ================================================
    // PASO 2.2: CONSTRUCTOR - Crear una entrada en el momento de compra
    // ================================================
    // ¿POR QUÉ?
    // Una entrada SOLO EXISTE cuando alguien COMPRA.
    // El constructor CAPTURA todos los datos del momento de la compra.
    //
    // ANALOGÍA: Cuando PAGAS en una tienda:
    // 1. El vendedor anota quién eres (cliente)
    // 2. Anota qué compraste (asiento A2)
    // 3. Anota la hora (LocalDateTime.now())
    // 4. Anota el precio (toma del asiento)
    // 5. Te da un recibo con número único (ID)
    //
    // ¿PARÁMETROS?
    // ¿Por qué SOLO 3 y no 5?
    //
    // - int id: El número de entrada (Concierto mantiene un contador)
    //   Parámetro porque es VARIABLE (cada compra tiene ID diferente)
    //
    // - Asiento asiento: El asiento que se compró
    //   Parámetro porque VARÍA (cada cliente elige asiento diferente)
    //
    // - String cliente: Nombre de quién compra
    //   Parámetro porque VARÍA (clientes diferentes)
    //
    // - LocalDateTime fecha: ¿Por qué NO es parámetro?
    //   AUTOMATISMO: Queremos SIEMPRE el momento EXACTO de compra
    //   No lo pide manualmente porque sería error-prone
    //   LocalDateTime.now() captura automáticamente hora/fecha del sistema
    //
    // - double precio: ¿Por qué NO es parámetro?
    //   ABSTRACCIÓN: El precio está YA en el asiento
    //   No pedimos "dame el precio" manualmente
    //   EXTRAEMOS: asiento.getPrecio() (reutilizamos información)
    //
    // ¿QUÉ HACE?
    // 1. Guarda el ID único
    // 2. Guarda la REFERENCIA al asiento (no una copia, el objeto original)
    // 3. Guarda el nombre del cliente
    // 4. CAPTURA la fecha/hora ACTUAL (automático)
    // 5. TOMA el precio del asiento en ESTE MOMENTO
    //
    // ¿PASO A PASO?
    // 1. Crear constructor public Entrada(...) con 3 parámetros
    // 2. Guardar cada parámetro: this.id = id
    // 3. Para fecha: LocalDateTime.now() (sin parámetro, automático)
    // 4. Para precio: asiento.getPrecio() (sin parámetro, se extrae)
    //
    public Entrada(int id, Asiento asiento, String cliente) {
        this.id = id;
        this.asiento = asiento;
        this.cliente = cliente;
        this.fecha = LocalDateTime.now();        // Automático: fecha/hora ACTUAL
        this.precio = asiento.getPrecio();       // Automático: precio del asiento
    }

    // ================================================
    // PASO 2.3: GETTERS - Leer datos de forma SEGURA (ENCAPSULACIÓN)
    // ================================================
    // ¿POR QUÉ?
    // Los atributos son PRIVATE → nadie puede leerlos directamente.
    // entrada.id ❌ NO FUNCIONA
    // entrada.getId() ✅ FUNCIONA
    //
    // ANALOGÍA: Como un BANCO que NO MUESTRA dinero directamente:
    // - Tú NO ves la bóveda (private atributos)
    // - Preguntas al mostrador (public getters)
    // - El mostrador TE DICE la información (return)
    //
    // ¿DÓNDE SE USAN?
    // - getId(): En Concierto.cancelarEntrada() para buscar por ID
    // - getAsiento(): Para liberar el asiento en cancelarEntrada()
    // - getCliente(): Para mostrar "Entrada de Juan"
    // - getFecha(): Para mostrar cuándo se compró
    // - getPrecio(): Para sumar todas las ventas
    //
    // ABSTRACCIÓN: No necesitas saber DÓNDE está el dato, solo USAS el getter:
    // - No importa cómo se almacena el ID (int, String, long)
    // - No importa cómo se calcula la fecha
    // - Solo llamas: entrada.getId() y listo
    //
    // ¿PATRÓN?
    // Cada getter:
    // - Es public (cualquiera puede llamarlo)
    // - No recibe parámetros
    // - Devuelve el valor del atributo
    // - El tipo de retorno COINCIDE con el atributo
    //
    public int getId() { return id; }
    public Asiento getAsiento() { return asiento; }
    public String getCliente() { return cliente; }
    public LocalDateTime getFecha() { return fecha; }
    public double getPrecio() { return precio; }

    // ================================================
    // PASO 2.4: getDetalles() - ABSTRACCIÓN: Mostrar información legible
    // ================================================
    // ¿POR QUÉ?
    // Cuando haces "Ver mis entradas", queremos mostrar algo LEGIBLE.
    // No puedes mostrar: "Entrada@12345xyz" (código interno del objeto)
    //
    // ABSTRACCIÓN: Escondemos CÓMO se construye, mostramos SOLO el resultado:
    // Input: id=0, cliente="Juan", asiento=A2, fecha=2026-04-29, precio=85.0
    // Output: "Entrada #0 | Cliente: Juan | Asiento A2 | VIP | 85€"  ← Bonito y claro
    //
    // ANALOGÍA: Como un RECIBO DE COMPRA:
    // - Por dentro: objetos, datos, referencias
    // - Por fuera: "Entrada #123 | Cliente: Juan | Asiento A2 | 85€"
    // - El recibo ABSTRAE la complejidad
    //
    // ¿QUÉ MUESTRA?
    // "Entrada #0 | Cliente: Juan | Asiento A2 | VIP | 85€ | 2026-04-29"
    //
    // ¿CÓMO FUNCIONA?
    // 1. Concatena el ID: "Entrada #" + id
    // 2. Concatena el cliente: " | Cliente: " + cliente
    // 3. Concatena la info del asiento: " | Asiento " + asiento.getInfo()
    //    (REUTILIZA el método getInfo() de Asiento - abstracción)
    // 4. Concatena la fecha: " | " + fecha.toLocalDate()
    //    (toLocalDate() simplifica: solo YYYY-MM-DD, sin hora)
    //
    // ¿PARA QUÉ?
    // Para que Main pueda hacer simplemente:
    // System.out.println(entrada.getDetalles())
    // Y mostrar un recibo bonito sin compilar manualmente la información
    //
    // ¿PASO A PASO?
    // 1. Crear un String que concatene varios datos
    // 2. Empezar con "Entrada #" + id
    // 3. Agregar " | Cliente: " + cliente
    // 4. Agregar " | Asiento " + asiento.getInfo()
    //    (ABSTRACCIÓN: confiamos que getInfo() nos da el formato correcto)
    // 5. Agregar " | " + fecha.toLocalDate()
    //    (Convertir LocalDateTime a solo la fecha)
    //
    public String getDetalles() {
        return "Entrada #" + id + " | Cliente: " + cliente +
               " | Asiento " + asiento.getInfo() +
               " | " + fecha.toLocalDate();
    }

}
