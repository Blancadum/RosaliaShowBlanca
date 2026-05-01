import java.util.ArrayList;

public class Concierto {

    private String nombre;
    private String artista;
    private Asiento[][] asientos;
    private ArrayList<Entrada> entradas;
    private int contadorEntradas;
    
    public Concierto(String nombre, String artista) {
        this.nombre = nombre;
        this.artista = artista;
        asientos = new Asiento[10][8];      // Matriz vacía, lista para llenar
        entradas = new ArrayList<>();       // ArrayList vacío, lista para compras
        contadorEntradas = 0;               // Contador en 0, incrementará
    }

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

    public void verPrecios() {
        System.out.println("\n=== TABLA DE PRECIOS ===");
        System.out.println("VIP (Filas A-B):        85.00€");
        System.out.println("Premium (Filas C-E):    65.00€");
        System.out.println("Normal (Filas F-J):     45.00€");
        System.out.println("========================\n");
    }

    public Asiento buscarAsiento(int fila, int numero) {
        if (fila < 0 || fila >= asientos.length || numero < 0 || numero >= asientos[0].length) {
            return null;
        }
        return asientos[fila][numero];
    }

    public int comprarEntrada(int fila, int numero, String cliente) throws IllegalArgumentException {
        if (fila < 0 || fila > 9){
            throw new IllegalArgumentException("Fila debe estar entre 0 y 9");
        }
        if (numero < 0 || numero > 7){
            throw new IllegalArgumentException("Número debe estar entre 0 y 7");
        }
        
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

    public double calcularOcupacion() {
        int total = asientos.length * asientos[0].length;
        return (double) entradas.size() / total;
    }

    public double calcularRecaudacion() {
        double total = 0;
        for (Entrada e : entradas) {
            total += e.getPrecio();
        }
        return total;
    }

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
    
    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }
}