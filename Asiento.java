
public class Asiento {
    // Declaración de los 5 atributos privados
    private int fila;
    private int numero;
    private String seccion;
    private double precio;
    private boolean disponible;

    // constructor
    public Asiento(int fila, int numero, String seccion, double precio) {

        this.fila = fila;
        this.numero = numero;
        this.seccion = seccion;
        this.precio = precio;
        this.disponible = true;
    }

    // Implementa los 5 getters
    public int getFila() { return fila; }
    public int getNumero() { return numero; }
    public String getSeccion() { return seccion; }
    public double getPrecio() { return precio; }
    public boolean isDisponible() { return disponible; }

    // Métodos controlados para la clase private
    // TODO: Implementa ocupar() y liberar()
    // TODO: public void ocupar() { disponible = false; }
    // TODO: public void liberar() { disponible = true; }

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
