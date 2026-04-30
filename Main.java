import java.util.Scanner;

/**
 * ================================================
 * CLASE MAIN - PASO 4 (LA INTERFAZ CON EL USUARIO)
 * ================================================
 *
 * ¿QUÉ ES?
 * --------
 * Main es el "punto de entrada" del programa.
 * Es la INTERFAZ con el usuario - lo que ves en pantalla.
 *
 * ¿PARA QUÉ SIRVE?
 * ----------------
 * - Mostrar un menú para que el usuario elija qué hacer
 * - Pedir datos al usuario (fila, nombre, etc)
 * - Llamar a Concierto para realizar acciones
 * - Mostrar resultados de forma bonita
 * - Manejar errores y mostrar mensajes
 *
 * ¿POR QUÉ SEPARADA DE CONCIERTO?
 * --------------------------------
 * PRINCIPIO: SEPARACIÓN DE RESPONSABILIDADES
 *
 * - Concierto: LÓGICA (qué pasa internamente)
 *   Ejemplo: calcular si un asiento está disponible
 *
 * - Main: PRESENTACIÓN (cómo se ve al usuario)
 *   Ejemplo: mostrar en pantalla "¿Qué deseas hacer?"
 *
 * Si mezclamos ambas, el código es un caos. Mejor separado.
 *
 * ANALOGÍA:
 * - Concierto = el mecanismo del reloj (engranajes)
 * - Main = la pantalla del reloj (lo que ves)
 *
 * ================================================
 */
public class Main {

    public static void main(String[] args) {
        // ================================================
        // PASO 4.1: INICIALIZACIÓN
        // ================================================
        // ¿POR QUÉ?
        // Preparar todo lo que necesitamos antes del menú.
        //
        // ¿QUÉ HACEMOS?
        // 1. Crear el concierto
        //    → new Concierto() instancia una clase que gestiona todo
        // 2. Inicializar asientos
        //    → llena la matriz 10×8 con 80 Asientos
        // 3. Crear Scanner
        //    → permite leer input del usuario (teclado)
        // 4. Variable para controlar el loop
        //    → salir=false inicialmente, true cuando elige opción 7
        //
        // TODO: Implementa la inicialización
        // TODO: Concierto concierto = new Concierto("Rosalía - Palau Sant Jordi", "Rosalía");
        // TODO: concierto.inicializarAsientos();
        // TODO: Scanner scanner = new Scanner(System.in);
        // TODO: boolean salir = false;

        // ================================================
        // PASO 4.2: LOOP DEL MENÚ
        // ================================================
        // ¿POR QUÉ while (!salir)?
        // Mientras el usuario NO elija salir, seguimos mostrando menú.
        // Cuando elige opción 7, salir=true y se rompe el loop.
        //
        // ¿QUÉ PASA EN CADA ITERACIÓN?
        // 1. Mostrar menú
        // 2. Pedir opción al usuario
        // 3. Procesar opción (switch)
        // 4. Volver al inicio (siguiente iteración)
        //
        // TODO: Implementa el loop con el menú
        // TODO: while (!salir) {
        // TODO:     System.out.println("\n🎤 === SISTEMA DE ENTRADAS ROSALÍA ===");
        // TODO:     System.out.println("1. Ver mapa de asientos");
        // TODO:     System.out.println("2. Comprar entrada");
        // TODO:     System.out.println("3. Ver mis entradas");
        // TODO:     System.out.println("4. Cancelar entrada");
        // TODO:     System.out.println("5. Ver estadísticas");
        // TODO:     System.out.println("6. Generar ticket");
        // TODO:     System.out.println("7. Salir");
        // TODO:     System.out.print("Elige: ");

        // ================================================
        // PASO 4.3: SWITCH PARA PROCESAR OPCIÓN
        // ================================================
        // ¿POR QUÉ switch y no if-else?
        // Switch es más limpio cuando tienes muchos casos.
        // Cada case llama a un método diferente.
        //
        // ¿QUÉ HACE CADA CASO?
        // - case 1: mostrar mapa visual
        // - case 2: flujo de compra (pedir fila, número, nombre)
        // - case 3: listar todas las entradas vendidas
        // - case 4: cancelar una entrada por ID
        // - case 5: mostrar ocupación y dinero ganado
        // - case 6: generar un "ticket bonito"
        // - case 7: salir del programa
        //
        // TODO: Implementa el switch
        // TODO:     try {
        // TODO:         int opcion = Integer.parseInt(scanner.nextLine());
        // TODO:         switch (opcion) {
        // TODO:             case 1: verMapa(concierto); break;
        // TODO:             case 2: comprarEntrada(concierto, scanner); break;
        // TODO:             case 3: verEntradas(concierto); break;
        // TODO:             case 4: cancelarEntrada(concierto, scanner); break;
        // TODO:             case 5: verEstadisticas(concierto); break;
        // TODO:             case 6: generarTicket(concierto, scanner); break;
        // TODO:             case 7: salir = true; System.out.println("👋 ¡Que disfrutes el concierto!"); break;
        // TODO:             default: System.out.println("❌ Opción no válida");
        // TODO:         }
        // TODO:     } catch (NumberFormatException e) {
        // TODO:         System.out.println("❌ Debes ingresar un número");
        // TODO:     }
        // TODO: }
        // TODO: scanner.close();
    }

    // ================================================
    // MÉTODOS AUXILIARES (cada uno maneja una opción del menú)
    // ================================================
    // ¿POR QUÉ SEPARADOS?
    // Para que main() no sea gigante (1000 líneas).
    // Mejor: main llama a métodos pequeños y claros.
    //
    // ¿POR QUÉ static?
    // Porque main es static, y necesita llamar a otros métodos.
    // Los métodos static se pueden llamar sin crear instancia.

    // ================================================
    // OPCIÓN 1: Ver mapa
    // ================================================
    // ¿PARA QUÉ?
    // Mostrar visualmente qué asientos están disponibles.
    //
    // ¿CÓMO?
    // Solo llamar a concierto.mostrarMapa().
    // Concierto se encarga de toda la lógica.
    //
    // TODO: Implementa verMapa()
    // TODO: private static void verMapa(Concierto concierto) {
    // TODO:     concierto.mostrarMapa();
    // TODO: }

    // ================================================
    // OPCIÓN 2: Comprar entrada
    // ================================================
    // ¿PARA QUÉ?
    // Flujo de compra completo.
    //
    // ¿FLUJO?
    // 1. Mostrar mapa (para que usuario sepa qué comprar)
    // 2. Pedir fila (convertir letra a número: A→0, B→1)
    // 3. Pedir número de asiento (0-7)
    // 4. Pedir nombre del cliente
    // 5. Llamar a concierto.comprarEntrada()
    // 6. Mostrar resultado (éxito o error)
    //
    // ¿POR QUÉ try-catch?
    // - NumberFormatException: usuario escribe "abc" en lugar de número
    // - IllegalArgumentException: asiento inválido/ya ocupado (lanzada por Concierto)
    //
    // TODO: Implementa comprarEntrada()
    // TODO: private static void comprarEntrada(Concierto concierto, Scanner scanner) {
    // TODO:     concierto.mostrarMapa();
    // TODO:     System.out.print("Fila (A-J): ");
    // TODO:     String filaStr = scanner.nextLine().toUpperCase();
    // TODO:     int fila = filaStr.charAt(0) - 'A';  // A(65) - A(65) = 0, B(66) - A(65) = 1...
    // TODO:     System.out.print("Número (0-7): ");
    // TODO:     try {
    // TODO:         int numero = Integer.parseInt(scanner.nextLine());
    // TODO:         System.out.print("Tu nombre: ");
    // TODO:         String nombre = scanner.nextLine();
    // TODO:         int idEntrada = concierto.comprarEntrada(fila, numero, nombre);
    // TODO:         System.out.println("✅ ¡Entrada comprada! ID: " + idEntrada);
    // TODO:     } catch (NumberFormatException e) {
    // TODO:         System.out.println("❌ Número inválido");
    // TODO:     } catch (IllegalArgumentException e) {
    // TODO:         System.out.println("❌ " + e.getMessage());
    // TODO:     }
    // TODO: }

    // ================================================
    // OPCIÓN 3: Ver entradas vendidas
    // ================================================
    // ¿PARA QUÉ?
    // Listar todas las compras realizadas.
    //
    // ¿CÓMO?
    // Solo delegamos a concierto.verEntradasVendidas().
    //
    // TODO: Implementa verEntradas()
    // TODO: private static void verEntradas(Concierto concierto) {
    // TODO:     concierto.verEntradasVendidas();
    // TODO: }

    // ================================================
    // OPCIÓN 4: Cancelar entrada
    // ================================================
    // ¿PARA QUÉ?
    // Revertir una compra anterior.
    //
    // ¿FLUJO?
    // 1. Pedir ID de la entrada a cancelar
    // 2. Llamar a concierto.cancelarEntrada(id)
    // 3. Concierto maneja:
    //    - Buscar entrada
    //    - Liberar asiento
    //    - Eliminar del registro
    //
    // TODO: Implementa cancelarEntrada()
    // TODO: private static void cancelarEntrada(Concierto concierto, Scanner scanner) {
    // TODO:     System.out.print("ID de entrada a cancelar: ");
    // TODO:     try {
    // TODO:         int id = Integer.parseInt(scanner.nextLine());
    // TODO:         concierto.cancelarEntrada(id);
    // TODO:     } catch (NumberFormatException e) {
    // TODO:         System.out.println("❌ ID debe ser un número");
    // TODO:     }
    // TODO: }

    // ================================================
    // OPCIÓN 5: Ver estadísticas
    // ================================================
    // ¿PARA QUÉ?
    // Mostrar: ¿cuántas entradas vendidas? ¿qué % ocupación? ¿cuánto dinero?
    //
    // ¿CÓMO?
    // 1. Obtener ocupacion (0.0-1.0) de Concierto
    // 2. Obtener recaudacion (total dinero) de Concierto
    // 3. Convertir ocupacion a porcentaje (× 100)
    // 4. Mostrar de forma bonita
    //
    // ¿String.format()?
    // Permite formatear números: %.1f = 1 decimal, %.2f = 2 decimales
    //
    // TODO: Implementa verEstadisticas()
    // TODO: private static void verEstadisticas(Concierto concierto) {
    // TODO:     double ocupacion = concierto.calcularOcupacion();
    // TODO:     double recaudacion = concierto.calcularRecaudacion();
    // TODO:     int vendidas = (int)(ocupacion * 80);
    // TODO:     System.out.println("\n=== ESTADÍSTICAS ===");
    // TODO:     System.out.println("Entradas vendidas: " + vendidas + "/80");
    // TODO:     System.out.println("Ocupación: " + String.format("%.1f", ocupacion * 100) + "%");
    // TODO:     System.out.println("Recaudación: " + String.format("%.2f", recaudacion) + "€");
    // TODO: }

    // ================================================
    // OPCIÓN 6: Generar ticket
    // ================================================
    // ¿PARA QUÉ?
    // Mostrar un "recibo bonito" de una entrada específica.
    //
    // ¿FLUJO?
    // 1. Pedir ID de la entrada
    // 2. Buscar esa entrada en la lista
    // 3. Si la encuentra: mostrar ticket formateado
    // 4. Si no la encuentra: mostrar error
    //
    // ¿POR QUÉ try-catch?
    // Para manejar si el usuario escribe "abc" en lugar de número.
    //
    // TODO: Implementa generarTicket()
    // TODO: private static void generarTicket(Concierto concierto, Scanner scanner) {
    // TODO:     System.out.print("ID de entrada: ");
    // TODO:     try {
    // TODO:         int id = Integer.parseInt(scanner.nextLine());
    // TODO:         for (Entrada e : concierto.getEntradas()) {
    // TODO:             if (e.getId() == id) {
    // TODO:                 System.out.println("\n🎫 === TICKET ===");
    // TODO:                 System.out.println(e.getDetalles());
    // TODO:                 System.out.println("Concierto: Rosalía");
    // TODO:                 System.out.println("Lugar: Palau Sant Jordi");
    // TODO:                 System.out.println("====================\n");
    // TODO:                 return;
    // TODO:             }
    // TODO:         }
    // TODO:         System.out.println("❌ Entrada no encontrada");
    // TODO:     } catch (NumberFormatException e) {
    // TODO:         System.out.println("❌ ID debe ser un número");
    // TODO:     }
    // TODO: }
}
