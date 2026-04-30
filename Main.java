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
        // PASO 4.1: INICIALIZACIÓN - Preparar todo antes del menú
        // ================================================
        // ¿POR QUÉ?
        // Antes de mostrar el menú, necesitamos preparar:
        // 1. El sistema de gestión (Concierto)
        // 2. Los asientos (llenar la matriz)
        // 3. La forma de leer input (Scanner)
        // 4. La bandera de control (salir)
        //
        // ANALOGÍA: Como ABRIR UNA TIENDA:
        // 1. Tomas control del mostrador (new Concierto)
        // 2. Llenas los estantes (inicializarAsientos)
        // 3. Abres la puerta (Scanner - escuchas clientes)
        // 4. Empiezas con "abierto" (salir=false)
        //
        // ¿QUÉ HACEMOS?
        // 1. Crear el CONCIERTO
        //    new Concierto() → instancia que gestiona TODO
        //    Nombre: "Rosalía - Palau Sant Jordi"
        //    Artista: "Rosalía"
        //
        // 2. Inicializar ASIENTOS
        //    concierto.inicializarAsientos() → llena 80 asientos
        //    ABSTRACCIÓN: No queremos ver CÓMO se llenan
        //    Solo llamamos el método y listo
        //
        // 3. Crear SCANNER
        //    System.in → leer desde teclado
        //    Scanner → transformar input a tipos (String, int, etc)
        //    ENCAPSULACIÓN: Scanner encapsula la complejidad de leer entrada
        //
        // 4. Bandera de control
        //    salir=false → inicialmente NO queremos salir
        //    salir=true → cuando el usuario presiona 7
        //
        Concierto concierto = new Concierto("Rosalía - Palau Sant Jordi", "Rosalía");
        concierto.inicializarAsientos();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // ================================================
        // PASO 4.2: LOOP DEL MENÚ - Ciclo infinito hasta que salga
        // ================================================
        // ¿POR QUÉ while (!salir)?
        // Mientras el usuario NO elija salir (salir=false)
        // Seguimos mostrando el menú y pidiendo opciones
        // Cuando elige opción 7 → salir=true → se rompe el loop
        //
        // ANALOGÍA: Como una TIENDA:
        // while (tienda_abierta) {
        //    atender_cliente();
        //    si (cliente_dice_"cierra") tienda_abierta = false;
        // }
        //
        // ¿QUÉ PASA EN CADA ITERACIÓN?
        // 1. Mostrar menú (6 opciones + salir)
        // 2. Pedir opción al usuario (scanner.nextLine)
        // 3. Procesar opción (switch statement)
        // 4. Volver al inicio (siguiente iteración del while)
        //
        // ¿PASO A PASO?
        // 1. while (!salir) - mientras NO sea true salir
        // 2. Mostrar menú con 7 opciones
        // 3. Pedir input al usuario con System.out.print("Elige: ")
        // 4. Procesar con switch (en siguiente sección)
        //
        while (!salir) {
            System.out.println("\n🎤 === SISTEMA DE ENTRADAS ROSALÍA ===");
            System.out.println("1. Ver mapa de asientos");
            System.out.println("2. Comprar entrada");
            System.out.println("3. Ver mis entradas");
            System.out.println("4. Cancelar entrada");
            System.out.println("5. Ver estadísticas");
            System.out.println("6. Generar ticket");
            System.out.println("7. Salir");
            System.out.print("Elige: ");

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
            // ¿PASO A PASO?
            // 1. try { para capturar NumberFormatException
            // 2. Leer opción: int opcion = Integer.parseInt(scanner.nextLine())
            // 3. switch (opcion) con 7 cases + default
            // 4. Cada case llama a su método correspondiente
            // 5. break; para salir del switch
            // 6. catch para manejar si usuario escribe "abc" en lugar de número
            //
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1: verMapa(concierto); break;
                    case 2: comprarEntrada(concierto, scanner); break;
                    case 3: verEntradas(concierto); break;
                    case 4: cancelarEntrada(concierto, scanner); break;
                    case 5: verEstadisticas(concierto); break;
                    case 6: generarTicket(concierto, scanner); break;
                    case 7: salir = true; System.out.println("👋 ¡Que disfrutes el concierto!"); break;
                    default: System.out.println("❌ Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Debes ingresar un número");
            }
        }
        scanner.close();
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
    // ¿PASO A PASO?
    // 1. Es un método privado static (se llama desde main)
    // 2. Recibe Concierto como parámetro
    // 3. Simplemente: concierto.mostrarMapa()
    // 4. Ese método maneja toda la lógica de mostrar
    //
    private static void verMapa(Concierto concierto) {
        concierto.mostrarMapa();
    }

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
    // ¿PASO A PASO?
    // 1. Mostrar mapa: concierto.mostrarMapa()
    // 2. Pedir fila (A-J): convertir letra a índice con charAt(0) - 'A'
    // 3. Dentro de try: pedir número, nombre, y llamar comprarEntrada()
    // 4. Si éxito: mostrar ID
    // 5. Si error de número: catch NumberFormatException
    // 6. Si error de lógica: catch IllegalArgumentException
    //
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

    // ================================================
    // OPCIÓN 3: Ver entradas vendidas
    // ================================================
    // ¿PARA QUÉ?
    // Listar todas las compras realizadas.
    //
    // ¿CÓMO?
    // Solo delegamos a concierto.verEntradasVendidas().
    //
    // ¿PASO A PASO?
    // 1. Método private static
    // 2. Recibe Concierto
    // 3. Llamar: concierto.verEntradasVendidas()
    // 4. Ese método muestra el listado
    //
    private static void verEntradas(Concierto concierto) {
        concierto.verEntradasVendidas();
    }

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
    // ¿PASO A PASO?
    // 1. Mostrar prompt: "ID de entrada a cancelar: "
    // 2. try { obtener ID con Integer.parseInt()
    // 3. Llamar: concierto.cancelarEntrada(id)
    // 4. catch NumberFormatException si usuario escribe texto
    //
    private static void cancelarEntrada(Concierto concierto, Scanner scanner) {
        System.out.print("ID de entrada a cancelar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            concierto.cancelarEntrada(id);
        } catch (NumberFormatException e) {
            System.out.println("❌ ID debe ser un número");
        }
    }

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
    // ¿PASO A PASO?
    // 1. Obtener ocupación: double ocupacion = concierto.calcularOcupacion()
    // 2. Obtener recaudación: double recaudacion = concierto.calcularRecaudacion()
    // 3. Convertir a cantidad: int vendidas = (int)(ocupacion * 80)
    // 4. Imprimir con formato bonito usando String.format()
    //
    private static void verEstadisticas(Concierto concierto) {
        double ocupacion = concierto.calcularOcupacion();
        double recaudacion = concierto.calcularRecaudacion();
        int vendidas = (int)(ocupacion * 80);
        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println("Entradas vendidas: " + vendidas + "/80");
        System.out.println("Ocupación: " + String.format("%.1f", ocupacion * 100) + "%");
        System.out.println("Recaudación: " + String.format("%.2f", recaudacion) + "€");
    }

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
    // ¿PASO A PASO?
    // 1. Pedir ID con Integer.parseInt()
    // 2. For-each: for (Entrada e : concierto.getEntradas())
    // 3. if (e.getId() == id) - comparar si es la buscada
    // 4. Si encuentra: mostrar ticket bonito y return
    // 5. Si no encuentra después del loop: mostrar "no encontrada"
    // 6. catch NumberFormatException si usuario escribe texto
    //
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
