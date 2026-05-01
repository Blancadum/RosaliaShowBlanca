import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Concierto concierto = new Concierto("Rosalía - Palau Sant Jordi", "Rosalía");
        concierto.inicializarAsientos();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n🎤 === SISTEMA DE ENTRADAS ROSALÍA ===");
            System.out.println("1. Ver mapa de asientos");
            System.out.println("2. Ver precios");
            System.out.println("3. Comprar entrada");
            System.out.println("4. Ver mis entradas");
            System.out.println("5. Cancelar entrada");
            System.out.println("6. Ver estadísticas");
            System.out.println("7. Generar ticket");
            System.out.println("8. Salir");
            System.out.print("Elige: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1: verMapa(concierto); break;
                    case 2: verPrecios(concierto); break;
                    case 3: comprarEntrada(concierto, scanner); break;
                    case 4: verEntradas(concierto); break;
                    case 5: cancelarEntrada(concierto, scanner); break;
                    case 6: verEstadisticas(concierto); break;
                    case 7: generarTicket(concierto, scanner); break;
                    case 8: salir = true; System.out.println("👋 ¡Que disfrutes el concierto!"); break;
                    default: System.out.println("❌ Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Debes ingresar un número");
            }
        }
        scanner.close();
    }

    private static void verMapa(Concierto concierto) {
        concierto.mostrarMapa();
    }

    private static void verPrecios(Concierto concierto) {
        concierto.verPrecios();
    }

    private static void verEntradas(Concierto concierto) {
        concierto.verEntradasVendidas();
    }

    private static void comprarEntrada(Concierto concierto, Scanner scanner) {
        concierto.mostrarMapa();
        System.out.print("Fila (A-J): ");
        String filaStr = scanner.nextLine().toUpperCase();
        int fila = filaStr.charAt(0) - 'A';  // A(65) - A(65) = 0, B(66) - A(65) = 1...
        System.out.print("Número (0-7): ");
        try {
            int numero = Integer.parseInt(scanner.nextLine());
            System.out.print("Tu nombre: ");
            String nombre = scanner.nextLine();
            int idEntrada = concierto.comprarEntrada(fila, numero, nombre);
            System.out.println("✅ ¡Entrada comprada! ID: " + idEntrada);
        } catch (NumberFormatException e) {
            System.out.println("❌ Número inválido");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }


    private static void cancelarEntrada(Concierto concierto, Scanner scanner) {
        System.out.print("ID de entrada a cancelar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            concierto.cancelarEntrada(id);
        } catch (NumberFormatException e) {
            System.out.println("❌ ID debe ser un número");
        }
    }
    
    private static void verEstadisticas(Concierto concierto) {
        double ocupacion = concierto.calcularOcupacion();
        double recaudacion = concierto.calcularRecaudacion();
        int vendidas = (int)(ocupacion * 80);
        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println("Entradas vendidas: " + vendidas + "/80");
        System.out.println("Ocupación: " + String.format("%.1f", ocupacion * 100) + "%");
        System.out.println("Recaudación: " + String.format("%.2f", recaudacion) + "€");
    }

    private static void generarTicket(Concierto concierto, Scanner scanner) {
        System.out.print("ID de entrada: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            for (Entrada e : concierto.getEntradas()) {
                if (e.getId() == id) {
                    System.out.println("\n🎫 === TICKET ===");
                    System.out.println(e.getDetalles());
                    System.out.println("Concierto: Rosalía");
                    System.out.println("Lugar: Palau Sant Jordi");
                    System.out.println("====================\n");
                    return;
                }
            }
            System.out.println("❌ Entrada no encontrada");
        } catch (NumberFormatException e) {
            System.out.println("❌ ID debe ser un número");
        }
    }
}
