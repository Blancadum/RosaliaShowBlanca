
public class Asiento {
    // Declaración de los 5 atributos privados
    private int fila;
    private int numero;
    private String seccion;
    private double precio;
    private boolean disponible;

    // ================================================
    // PASO 1.1: Constructor - Inicializar un asiento
    // ================================================
    // ¿POR QUÉ?
    // Cada asiento tiene 5 características únicas que NUNCA cambiarán:
    // - Fila (A, B, C...) → número 0, 1, 2...
    // - Número (1, 2, 3...)
    // - Sección (VIP, General, Preferente...)
    // - Precio (cada sección cuesta diferente)
    // - Disponibilidad (al crear, SIEMPRE está disponible)
    //
    // ¿PARA QUÉ?
    // Necesitamos una forma de crear asientos completos con datos válidos.
    // No podemos tener un asiento "vacío" sin saber dónde está o cuánto cuesta.
    //
    // ¿CÓMO FUNCIONA?
    // 1. Recibe 4 parámetros: fila, numero, seccion, precio
    // 2. Guarda cada parámetro en su atributo correspondiente usando "this."
    // 3. El disponible NO viene como parámetro porque SIEMPRE es true al crear
    //
    // ¿EJEMPLO?
    // Asiento a1 = new Asiento(0, 2, "VIP", 85.0);
    // → fila=0, numero=2, seccion="VIP", precio=85.0, disponible=true
    //
    // Asiento a2 = new Asiento(3, 15, "General", 35.0);
    // → fila=3, numero=15, seccion="General", precio=35.0, disponible=true
    //
    public Asiento(int fila, int numero, String seccion, double precio) {
        this.fila = fila;
        this.numero = numero;
        this.seccion = seccion;
        this.precio = precio;
        this.disponible = true;
    }

    // ================================================
    // PASO 1.2: Los 5 Getters - Obtener información
    // ================================================
    // ¿POR QUÉ?
    // Los atributos son PRIVATE (privados) → nadie puede acceder directamente.
    // Si Concierto quiere saber el precio de un asiento, NO puede hacer asiento.precio
    // Necesita un método público que LE PERMITA leerlo de forma controlada.
    //
    // ¿PARA QUÉ?
    // - getFila() → saber en qué fila está el asiento
    // - getNumero() → saber el número del asiento
    // - getSeccion() → saber a qué sección pertenece
    // - getPrecio() → cobrar el precio correcto
    // - isDisponible() → saber si se puede comprar
    //
    // ¿CÓMO FUNCIONA?
    // Cada getter:
    // 1. Es un método público (cualquiera puede llamarlo)
    // 2. NO recibe parámetros
    // 3. DEVUELVE el valor del atributo (return)
    // 4. El tipo de return coincide con el atributo (int, String, boolean, double)
    //
    // ¿PATRÓN PARA GETTERS?
    // - Si atributo es int → "public int getNombreAtributo() { return nombreAtributo; }"
    // - Si atributo es String → "public String getNombreAtributo() { return nombreAtributo; }"
    // - Si atributo es boolean → "public boolean isNombreAtributo() { return nombreAtributo; }"
    // NOTA: Para booleanos usamos "is" en lugar de "get"
    //
    // ¿EJEMPLO?
    // Asiento a = new Asiento(0, 2, "VIP", 85.0);
    // int f = a.getFila();        // f = 0
    // int n = a.getNumero();      // n = 2
    // String s = a.getSeccion();  // s = "VIP"
    // double p = a.getPrecio();   // p = 85.0
    // boolean d = a.isDisponible(); // d = true
    //
    public int getFila() { return fila; }
    public int getNumero() { return numero; }
    public String getSeccion() { return seccion; }
    public double getPrecio() { return precio; }
    public boolean isDisponible() { return disponible; }

    // ================================================
    // PASO 1.3: ocupar() - Marcar asiento como comprado
    // ================================================
    // ¿POR QUÉ?
    // Cuando un cliente compra una entrada, el asiento DEBE pasar de disponible
    // a NO disponible. No podemos dejar que dos personas compren el mismo asiento.
    //
    // ¿PARA QUÉ?
    // - Cliente compra asiento A2 → ocupar() → disponible = false
    // - Ahora nadie más puede comprar A2
    // - Si intentan, isDisponible() devolverá false y rechazaremos la compra
    //
    // ¿CÓMO FUNCIONA?
    // - Es un método void (no devuelve nada)
    // - Solo debe CAMBIAR el estado de disponible
    // - De true a false
    // - No recibe parámetros (el cambio es siempre el mismo)
    //
    // ¿PASO A PASO?
    // 1. Acessar el atributo disponible (que es private)
    // 2. Cambiar su valor a false
    // 3. Listo, método terminado
    //
    // ¿EJEMPLO?
    // Asiento asiento = new Asiento(0, 2, "VIP", 85.0);
    // System.out.println(asiento.isDisponible()); // true
    // asiento.ocupar();
    // System.out.println(asiento.isDisponible()); // false
    //
    // TODO: public void ocupar() {
    // TODO:     // Aquí: cambiar disponible a false
    // TODO: }

    // ================================================
    // PASO 1.4: liberar() - Marcar asiento como disponible
    // ================================================
    // ¿POR QUÉ?
    // Si un cliente cancela su compra o se devuelve una entrada,
    // el asiento DEBE volver a estar disponible para que otro lo compre.
    //
    // ¿PARA QUÉ?
    // - Cliente cancela entrada → liberar() → disponible = true
    // - Ahora otro cliente SÍ puede comprar A2
    //
    // ¿CÓMO FUNCIONA?
    // - Es el OPUESTO de ocupar()
    // - Si ocupar() pone false, liberar() pone true
    // - También es void, sin parámetros
    //
    // ¿PASO A PASO?
    // 1. Acceder al atributo disponible
    // 2. Cambiar su valor a true
    // 3. Listo, método terminado
    //
    // ¿EJEMPLO?
    // Asiento asiento = new Asiento(0, 2, "VIP", 85.0);
    // asiento.ocupar();  // Compra
    // System.out.println(asiento.isDisponible()); // false
    // asiento.liberar(); // Cancelación
    // System.out.println(asiento.isDisponible()); // true
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
