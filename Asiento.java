
public class Asiento {
    private int fila;           // 0-9 (A-J) - INMUTABLE después de crear
    private int numero;         // 0-7 - INMUTABLE después de crear
    private String seccion;     // "VIP", "Premium", "Normal" - INMUTABLE
    private double precio;      // € - INMUTABLE después de crear
    private boolean disponible; // true=libre, false=vendida - MUTABLE

    public Asiento(int fila, int numero, String seccion, double precio) {
        this.fila = fila;
        this.numero = numero;
        this.seccion = seccion;
        this.precio = precio;
        this.disponible = true;  // Siempre true, porque es un asiento nuevo
    }

    public int getFila() { return fila; }
    public int getNumero() { return numero; }
    public String getSeccion() { return seccion; }
    public double getPrecio() { return precio; }
    public boolean isDisponible() { return disponible; }

    public void liberar(){
        this.disponible = true;
    }
    // ocupar() Control de ESCRITURA
    public void ocupar(){
        this.disponible = false; 
    }

    public String getInfo(){
        char letraFila =(char)('A' + fila);
        String estado = disponible ? "✅ Disponible" : "❌ Ocupado";
        return letraFila + "" + numero + " | " + seccion + " | " + precio + "€ | " + estado;
    }

    public String getMarcaAsiento(){
        if (this.disponible == true) {
            return "✅";
        } else {
            return "❌";
        }
    }
}
