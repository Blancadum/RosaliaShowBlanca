
public class Asiento {
    // ================================================================================
    //  🔒 ATRIBUTOS PRIVADOS 🔒: ENCAPSULACIÓN (🔑 Clase Asiento)
    // ================================================================================
    // Estos datos están en una "CAJA FUERTE" 🔒
    // Solo esta clase (Asiento) puede acceder 🔑
    // Otras clases NO pueden hacer: asiento.precio = -500 
    //
    // ANALOGÍA DEL MUNDO REAL:
    // Como un CAJERO AUTOMÁTICO:
    // - Tú NO accedes directamente al dinero (private)
    // - Solo usas BOTONES seguros (métodos public)
    // - Si intentas: cajero.dinero = -500 → ❌ BLOQUEADO
    // - Debes usar: cajero.sacarDinero(100) → ✅ PERMITIDO
    //
    // Cada asiento tiene 5 características:
    // - Fila (A-J) y Número (0-7) → NO CAMBIAN después de crear
    // - Sección (VIP, Premium, Normal) → NO CAMBIA
    // - Precio (€) → NO CAMBIA después de crear
    // - Disponibilidad → SÍ CAMBIA (compra → cancelación)
    //
    private int fila;           // 0-9 (A-J) - INMUTABLE después de crear
    private int numero;         // 0-7 - INMUTABLE después de crear
    private String seccion;     // "VIP", "Premium", "Normal" - INMUTABLE
    private double precio;      // € - INMUTABLE después de crear
    private boolean disponible; // true=libre, false=vendida - MUTABLE

    // ================================================
    // PASO 1.1: CONSTRUCTOR - Inicializar un asiento
    // ================================================
    // ¿POR QUÉ?
    // Necesitamos una forma de crear asientos COMPLETOS y VÁLIDOS.
    // No podemos tener asientos "medio creados" o con datos faltantes.
    //
    // ¿PARA QUÉ?
    // El constructor es como un FORMULARIO en una web:
    // - Pide los datos que NECESITA (fila, numero, seccion, precio)
    // - Valida que sean correctos (no acepta basura)
    // - Crea el objeto de forma SEGURA
    //
    // ¿CÓMO FUNCIONA?
    // 1. Recibe 4 parámetros: fila, numero, seccion, precio
    //    (¿Por qué 4 y no 5? Porque disponible SIEMPRE es true al crear)
    // 2. Guarda cada parámetro en su atributo con "this."
    // 3. Inicializa disponible = true automáticamente
    //
    // ANALOGÍA: Como rellenar una FICHA DE REGISTRO:
    // - Campo requerido: Fila ________________
    // - Campo requerido: Número ______________
    // - Campo requerido: Sección ______________
    // - Campo requerido: Precio _______________
    // - Campo AUTOMÁTICO: Disponible = SÍ (no se pregunta)
    //
    // ¿EJEMPLO?
    // Asiento a1 = new Asiento(0, 2, "VIP", 85.0);
    // → Crea: fila=0, numero=2, seccion="VIP", precio=85.0, disponible=true
    //
    // Asiento a2 = new Asiento(3, 5, "Premium", 65.0);
    // → Crea: fila=3, numero=5, seccion="Premium", precio=65.0, disponible=true
    //
    // ¿PASO A PASO?
    // 1. Guardar los 4 parámetros en sus atributos: this.fila = fila
    // 2. Guardar numero, seccion, precio igual
    // 3. Inicializar disponible a true (automático, no viene como parámetro)
    //
    public Asiento(int fila, int numero, String seccion, double precio) {
        this.fila = fila;
        this.numero = numero;
        this.seccion = seccion;
        this.precio = precio;
        this.disponible = true;  // Siempre true, porque es un asiento nuevo
    }

    // ================================================
    // PASO 1.2: GETTERS - Leer datos de forma SEGURA
    // ================================================
    // ¿POR QUÉ?
    // Los atributos son PRIVATE → nadie puede acceder directamente.
    // asiento.precio ❌ NO FUNCIONA
    // Necesita un "PORTERO" que PERMITA lectura segura.
    //
    // ANALOGÍA: Como pedir información al MOSTRADOR DE UN BANCO:
    // - Tú NO entras a la bóveda (private atributos)
    // - PREGUNTAS al mostrador (public getters)
    // - El mostrador TE DICE la información (return)
    // - Ejemplo: "¿Cuál es mi saldo?"
    //           Mostrador: "Tu saldo es 1000€"
    //
    // ¿PARA QUÉ CADA UNO?
    // - getFila() → ¿En qué fila está? (para mostrar A, B, C...)
    // - getNumero() → ¿Cuál es el número? (para mostrar 0, 1, 2...)
    // - getSeccion() → ¿Qué sección? (VIP, Premium, Normal)
    // - getPrecio() → ¿Cuánto cuesta? (para cobrar)
    // - isDisponible() → ¿Puedo comprarlo? (true=sí, false=no)
    //
    // ¿CÓMO FUNCIONA?
    // Cada getter:
    // 1. Es PUBLIC (cualquiera puede llamarlo)
    // 2. NO tiene parámetros
    // 3. Devuelve (return) el valor del atributo
    // 4. El tipo de return COINCIDE con el atributo
    //
    // ¿PATRÓN?
    // - Si atributo es int: public int getNombre() { return nombre; }
    // - Si atributo es String: public String getNombre() { return nombre; }
    // - Si atributo es boolean: public boolean isNombre() { return nombre; }
    // NOTA: Para boolean usamos "is" en lugar de "get"
    //
    // ABSTRACCIÓN: No necesitas saber CÓMO se calcula, solo USAS el método:
    // - No importa si fila está en un int o un char
    // - No importa si disponible se calcula o se almacena
    // - Solo llamas: asiento.getFila() y recibes el resultado
    //
    // ¿EJEMPLO?
    // Asiento a = new Asiento(0, 2, "VIP", 85.0);
    // int fila = a.getFila();           // fila = 0
    // int numero = a.getNumero();       // numero = 2
    // String seccion = a.getSeccion();  // seccion = "VIP"
    // double precio = a.getPrecio();    // precio = 85.0
    // boolean disponible = a.isDisponible(); // disponible = true
    //
    public int getFila() { return fila; }
    public int getNumero() { return numero; }
    public String getSeccion() { return seccion; }
    public double getPrecio() { return precio; }
    public boolean isDisponible() { return disponible; }

    // ================================================
    // PASO 1.3: ocupar() - Marcar asiento como vendido
    // ================================================
    // ¿POR QUÉ?
    // Cuando un cliente COMPRA una entrada, el asiento cambia de ESTADO:
    // disponible = true  →  disponible = false
    // No podemos dejar que dos personas compren el mismo asiento.
    //
    // ANALOGÍA: Como un ASIENTO DE AVIÓN:
    // Paso 1: Asiento vacío (disponible = true)
    // Paso 2: Persona se sienta (ocupar() → disponible = false)
    // Paso 3: Nadie más puede sentarse ahí
    //
    // ¿PARA QUÉ?
    // - Cliente compra asiento A2 → ocupar() → disponible = false
    // - Ahora si otro intenta comprar A2, dirá: "❌ Ocupado"
    //
    // ¿CÓMO FUNCIONA?
    // - Es un método void (no devuelve nada, solo HACE cosas)
    // - Solo CAMBIA el estado de disponible
    // - De true a false
    // - No recibe parámetros (siempre hace lo mismo)
    //
    // ENCAPSULACIÓN: ocupar() controla CÓMO cambia el estado
    // - No puedes hacer: asiento.disponible = false directamente
    // - DEBES usar: asiento.ocupar()
    // - Así el cambio está CONTROLADO
    //
    // ¿PASO A PASO?
    // 1. Acceder al atributo disponible (que es private)
    // 2. Cambiar su valor a false
    // 3. Listo, método terminado
    //
    // ¿EJEMPLO?
    // Asiento asiento = new Asiento(0, 2, "VIP", 85.0);
    // System.out.println(asiento.isDisponible()); // Imprime: true
    // asiento.ocupar();  // Cambiar estado
    // System.out.println(asiento.isDisponible()); // Imprime: false
    //
    // TODO: public void ocupar() {
    // TODO:     // Aquí: cambiar disponible a false
    // TODO: }

    // ================================================
    // PASO 1.4: liberar() - Marcar asiento como disponible
    // ================================================
    // ¿POR QUÉ?
    // Si un cliente CANCELA su compra, el asiento vuelve a estar DISPONIBLE.
    // Para que otro cliente pueda comprarlo.
    //
    // ANALOGÍA: Como el ASIENTO DE AVIÓN nuevamente:
    // Paso 1: Persona se levanta (liberar() → disponible = true)
    // Paso 2: El asiento vuelve a estar disponible
    // Paso 3: Otra persona SÍ puede sentarse
    //
    // ¿PARA QUÉ?
    // - Cliente cancela entrada → liberar() → disponible = true
    // - Ahora otro cliente SÍ puede comprar ese asiento
    //
    // ¿CÓMO FUNCIONA?
    // - Es el OPUESTO EXACTO de ocupar()
    // - Si ocupar() → false, liberar() → true
    // - También es void, sin parámetros
    // - Solo CAMBIA el estado
    //
    // ¿PASO A PASO?
    // 1. Acceder al atributo disponible
    // 2. Cambiar su valor a true
    // 3. Listo, método terminado
    //
    // ¿EJEMPLO DE CICLO COMPLETO?
    // Asiento asiento = new Asiento(0, 2, "VIP", 85.0);
    // System.out.println(asiento.isDisponible()); // true (nuevo)
    // asiento.ocupar();  // Cliente compra
    // System.out.println(asiento.isDisponible()); // false (vendido)
    // asiento.liberar(); // Cliente cancela
    // System.out.println(asiento.isDisponible()); // true (disponible de nuevo)
    //
    // TODO: public void liberar() {
    // TODO:     // Aquí: cambiar disponible a true
    // TODO: }

    // ================================================
    // PASO 1.5: getInfo() - Información formateada
    // ================================================
    // ¿POR QUÉ?
    // Necesitamos mostrar al usuario información del asiento de forma legible.
    // No podemos mostrar "fila=0, numero=2, seccion=VIP..." - eso es feo.
    //
    // ¿PARA QUÉ?
    // - Mostrar en Entrada: "Asiento A2 | VIP | 85€ | ✅ Disponible"
    // - Mostrar en detalles: "A2 | VIP | 85€ | ✅ Disponible"
    //
    // ¿CÓMO FUNCIONA?
    // - Fila 0 → letra 'A', Fila 1 → 'B', etc: (char)('A' + fila)
    // - Disponible true → "✅ Disponible", false → "❌ Ocupado"
    // - Concatena todo con separadores |
    //
    // ¿EJEMPLO?
    // Asiento: fila=0, numero=2, seccion="VIP", precio=85.0, disponible=false
    // Salida: "A2 | VIP | 85.0€ | ❌ Ocupado"
    //
    // ¿POR QUÉ (char)('A' + fila)?
    // 'A' en ASCII es 65. Si sumamos 0, da 65 = 'A'.
    // Si sumamos 1, da 66 = 'B'. Si sumamos 5, da 70 = 'F'.
    // Así convertimos números en letras automáticamente.
    //
    // TODO: Implementa getInfo()
    // TODO: public String getInfo() {
    // TODO:     char letraFila = (char)('A' + fila);
    // TODO:     String estado = disponible ? "✅ Disponible" : "❌ Ocupado";
    // TODO:     return letraFila + "" + numero + " | " + seccion + " | " + precio + "€ | " + estado;
    // TODO: }

    // ================================================
    // PASO 1.6: MÉTODO PARA MOSTRAR EN MAPA (Opcional)
    // ================================================
    // ¿POR QUÉ?
    // En Concierto.mostrarMapa() queremos mostrar una matriz visual rápida.
    // No necesitamos toda la información, solo ✅ o ❌.
    //
    // ¿PARA QUÉ?
    // Que Concierto pueda hacer: System.out.print(asiento.getMarcaAsiento())
    // Y mostrar ✅ si está disponible, ❌ si está ocupado.
    //
    // TODO: Implementa getMarcaAsiento() (opcional pero útil)
    // TODO: public String getMarcaAsiento() {
    // TODO:     return disponible ? "✅" : "❌";
    // TODO: }

}
