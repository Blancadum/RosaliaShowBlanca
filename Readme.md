# 📝 SistemaTicketConcierto - Pasos para Resolver

Sigue estos pasos para resolver el proyecto. Cada `// TODO:` es un paso.

## 📦 Importaciones Necesarias

**Asiento.java:**
```java
// No necesita imports especiales
```

**Entrada.java:**
```java
import java.time.LocalDateTime;
```

**Concierto.java:**
```java
import java.util.ArrayList;
```

**Main.java:**
```java
import java.util.Scanner;
```

---

## 🎯 Paso 1: Clase Asiento

**Archivo:** `Asiento.java`

- [ ] Atributos: fila (int), numero (int), seccion (String), precio (double), disponible (boolean)
  - **💡 Pista:** Usa `private` para todos los atributos
  

- [ ] Constructor que reciba fila, numero, seccion, precio
  - disponible por defecto = true
  - **💡 Pista:** El constructor recibe 4 parámetros. El atributo `disponible` se inicializa a `true` automáticamente

- [ ] Getters: getFila(), getNumero(), getSeccion(), getPrecio(), isDisponible()
  - **💡 Pista:** Para boolean usa `isDisponible()` en lugar de `getDisponible()`
  - **💡 Pista:** Todos retornan el valor del atributo correspondiente
  - **💡 Código:**
    ```java
    public int getFila() { return fila; }
    public int getNumero() { return numero; }
    public String getSeccion() { return seccion; }
    public double getPrecio() { return precio; }
    public boolean isDisponible() { return disponible; }
    ```

- [ ] Método: ocupar() - marca como no disponible
  - **💡 Pista:** `disponible = false;`
  - **💡 Pista:** No necesita parámetros, solo modifica el estado del asiento
  - **💡 Código:**
    ```java
    public void ocupar() {
        disponible = false;
    }
    ```

- [ ] Método: liberar() - marca como disponible
  - **💡 Pista:** Opuesto a ocupar(): `disponible = true;`
  - **💡 Código:**
    ```java
    public void liberar() {
        disponible = true;
    }
    ```

- [ ] Método: getInfo() - retorna "A12 | VIP | 85€ | ✅ Disponible"
  - **💡 Pista:** Usa la fila como letra (A=0, B=1, etc): `(char)('A' + fila)`
  - **💡 Pista:** El símbolo depende de disponible: ✅ si está libre, ❌ si está ocupado
  - **💡 Pista:** Forma: `"Letra+Número | Sección | Precio€ | Símbolo Estado"`
  - **💡 Código:**
    ```java
    public String getInfo() {
        char letraFila = (char)('A' + fila);
        String estado = disponible ? "✅ Disponible" : "❌ Ocupado";
        return letraFila + "" + numero + " | " + seccion + " | " + precio + "€ | " + estado;
    }
    ```
  - **💡 Ejemplo:** Si asiento es fila=0, numero=2, seccion="VIP", precio=85.0, disponible=false
    - Salida: `A2 | VIP | 85.0€ | ❌ Ocupado`

**Test:** Crea 3 asientos, ocupa uno, verifica getInfo()
- **💡 Pista:** Crea un archivo `TestAsiento.java` con un main que pruebe todo

---

## 🎯 Paso 2: Clase Entrada

**Archivo:** `Entrada.java`

- [ ] Atributos: id, asiento (Asiento), cliente (String), fecha (LocalDateTime), precio (double)
  - **💡 Pista:** Importa `java.time.LocalDateTime`

- [ ] Constructor que reciba id, asiento, cliente
  - fecha = ahora
    - **💡 Pista:** Usa `LocalDateTime.now()`
  - precio = asiento.getPrecio()
    - **💡 Pista:** Obtén el precio del objeto asiento pasado como parámetro
  - **💡 Código:**
    ```java
    public Entrada(int id, Asiento asiento, String cliente) {
        this.id = id;
        this.asiento = asiento;
        this.cliente = cliente;
        this.fecha = LocalDateTime.now();
        this.precio = asiento.getPrecio();
    }
    ```

- [ ] Getters: getId(), getAsiento(), getCliente(), getFecha(), getPrecio()
  - **💡 Pista:** Cinco métodos simples que retornan cada atributo

- [ ] Método: getDetalles() - retorna "Entrada #123 | Cliente: Juan | Asiento A12 | 85€ | 2026-04-29"
  - **💡 Pista:** Usa `asiento.getInfo()` para obtener la info del asiento
  - **💡 Pista:** Formatea la fecha con `.toString()` o `.toLocalDate()` para mostrar solo YYYY-MM-DD
  - **💡 Código:**
    ```java
    public String getDetalles() {
        return "Entrada #" + id + " | Cliente: " + cliente + " | Asiento " +
               asiento.getInfo() + " | " + precio + "€ | " + fecha.toLocalDate();
    }
    ```
  - **💡 Ejemplo de salida:** `Entrada #0 | Cliente: Juan | Asiento A2 | VIP | 85.0€ | ✅ Ocupado | 85.0€ | 2026-04-29`

**Test:** Crea una entrada y muestra detalles
- **💡 Pista:** Primero necesitas un Asiento, luego creas una Entrada con ese asiento

---

## 🎯 Paso 3: Clase Concierto

**Archivo:** `Concierto.java`

- [ ] Atributos:
  - nombre (String)
  - artista (String)
  - asientos (Asiento[][]) - matriz 10 filas × 8 columnas
  - entradas (ArrayList<Entrada>)
  - contadorEntradas (int)
  - **💡 Pista:** Importa `java.util.ArrayList`

- [ ] Constructor: recibe nombre y artista
  - Inicializa matriz vacía
    - **💡 Pista:** `asientos = new Asiento[10][8];` (no llena las posiciones aún)
  - Inicializa ArrayList
    - **💡 Pista:** `entradas = new ArrayList<>();`
  - Inicializa contador
    - **💡 Pista:** `contadorEntradas = 0;`
  - **💡 Pista:** Llama a `inicializarAsientos()` al final del constructor

- [ ] Método: inicializarAsientos()
  - Crea matriz con asientos
    - **💡 Pista:** Usa dos bucles for anidados (fila y columna)
  - Asigna precios según sección:
    - Filas 0-1: "VIP" - 85€
    - Filas 2-4: "Premium" - 65€
    - Filas 5-9: "Normal" - 45€
  - **💡 Pista:** Usa if-else para determinar la sección según la fila
  - **💡 Código:**
    ```java
    public void inicializarAsientos() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                String seccion;
                double precio;

                if (i >= 0 && i <= 1) {
                    seccion = "VIP";
                    precio = 85.0;
                } else if (i >= 2 && i <= 4) {
                    seccion = "Premium";
                    precio = 65.0;
                } else {
                    seccion = "Normal";
                    precio = 45.0;
                }

                asientos[i][j] = new Asiento(i, j, seccion, precio);
            }
        }
    }
    ```

- [ ] Método: mostrarMapa()
  - Imprime matriz en texto
  - ✅ = disponible
  - ❌ = ocupado
  - **💡 Pista:** Imprime el nombre de la sección al inicio de cada rango de filas (VIP, Premium, Normal)
  - **💡 Pista:** Usa dos bucles for anidados, imprime símbolos con espacios, `println()` al final de cada fila
  - **💡 Código:**
    ```java
    public void mostrarMapa() {
        System.out.println("\n=== MAPA DE ASIENTOS ===");
        System.out.println("VIP (Filas 0-1):");
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(asientos[i][j].isDisponible() ? "✅ " : "❌ ");
            }
            System.out.println();
        }

        System.out.println("\nPremium (Filas 2-4):");
        for (int i = 2; i <= 4; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(asientos[i][j].isDisponible() ? "✅ " : "❌ ");
            }
            System.out.println();
        }

        System.out.println("\nNormal (Filas 5-9):");
        for (int i = 5; i <= 9; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(asientos[i][j].isDisponible() ? "✅ " : "❌ ");
            }
            System.out.println();
        }
    }
    ```

- [ ] Método: buscarAsiento(fila, numero) - retorna Asiento o null
  - **💡 Pista:** Valida que fila y numero estén en rango (0-9 y 0-7)
  - **💡 Pista:** Retorna `asientos[fila][numero]` si son válidos, `null` si no
  - **💡 Código:**
    ```java
    public Asiento buscarAsiento(int fila, int numero) {
        if (fila < 0 || fila >= 10 || numero < 0 || numero >= 8) {
            return null;
        }
        return asientos[fila][numero];
    }
    ```

- [ ] Método: comprarEntrada(fila, numero, cliente)
  - **💡 Código:**
    ```java
    public int comprarEntrada(int fila, int numero, String cliente) {
        // Busca el asiento
        Asiento asiento = buscarAsiento(fila, numero);

        // Valida que exista
        if (asiento == null) {
            throw new IllegalArgumentException("Asiento inválido: Fila " + fila +
                                             ", Número " + numero);
        }

        // Valida que esté disponible
        if (!asiento.isDisponible()) {
            throw new IllegalArgumentException("Asiento no disponible");
        }

        // Crea nueva entrada
        Entrada nuevaEntrada = new Entrada(contadorEntradas, asiento, cliente);

        // Ocupa el asiento
        asiento.ocupar();

        // Agrega a la lista
        entradas.add(nuevaEntrada);

        // Guarda el ID antes de incrementar
        int idGenerado = contadorEntradas;

        // Incrementa contador para próxima entrada
        contadorEntradas++;

        // Retorna el ID generado
        return idGenerado;
    }
    ```
  - **💡 Ejemplo de uso:** `int idEntrada = concierto.comprarEntrada(0, 2, "Juan");`

- [ ] Método: cancelarEntrada(idEntrada)
  - **💡 Código:**
    ```java
    public void cancelarEntrada(int idEntrada) {
        // Busca la entrada en el ArrayList
        for (int i = 0; i < entradas.size(); i++) {
            Entrada entrada = entradas.get(i);
            if (entrada.getId() == idEntrada) {
                // Libera el asiento
                entrada.getAsiento().liberar();

                // Elimina la entrada
                entradas.remove(i);

                System.out.println("Entrada #" + idEntrada + " cancelada");
                return;
            }
        }

        // Si no encontró nada
        System.out.println("No se encontró entrada con ID: " + idEntrada);
    }
    ```
  - **💡 Pista importante:** Usa índice tradicional, NO foreach (evita ConcurrentModificationException)

- [ ] Método: calcularOcupacion()
  - Retorna porcentaje de asientos vendidos (0.0 a 1.0)
  - **💡 Código:**
    ```java
    public double calcularOcupacion() {
        return (double) entradas.size() / (10 * 8);
    }
    ```
  - **💡 Ejemplo:** Si hay 40 entradas vendidas: 40 / 80 = 0.5 (50%)

- [ ] Método: calcularRecaudacion()
  - Retorna suma de todos los precios vendidos
  - **💡 Código:**
    ```java
    public double calcularRecaudacion() {
        double total = 0;
        for (Entrada e : entradas) {
            total += e.getPrecio();
        }
        return total;
    }
    ```
  - **💡 Ejemplo:** 10 VIP (850€) + 10 Premium (650€) + 20 Normal (900€) = 2400€

- [ ] Método: verEntradasVendidas()
  - Imprime todas las entradas
  - **💡 Código:**
    ```java
    public void verEntradasVendidas() {
        if (entradas.isEmpty()) {
            System.out.println("No hay entradas vendidas aún");
            return;
        }
        System.out.println("\n=== ENTRADAS VENDIDAS ===");
        for (Entrada e : entradas) {
            System.out.println(e.getDetalles());
        }
    }
    ```

**Test:** Inicializa asientos, compra 3, muestra mapa, calcula ocupación
- **💡 Pista:** Crea un `TestConcierto.java` que lo pruebe todo

---

## 🎯 Paso 4: Clase Main

**Archivo:** `Main.java`

**💡 Código completo de Main.java:**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear concierto
        Concierto concierto = new Concierto("Rosalía - Palau Sant Jordi", "Rosalía");
        concierto.inicializarAsientos();

        // Crear scanner
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Loop del menú
        while (opcion != 7) {
            mostrarMenu();
            System.out.print("Elige opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia el \n

            switch (opcion) {
                case 1:
                    concierto.mostrarMapa();
                    break;

                case 2:
                    try {
                        System.out.print("Fila (0-9): ");
                        int fila = scanner.nextInt();

                        System.out.print("Número de asiento (0-7): ");
                        int numero = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Tu nombre: ");
                        String cliente = scanner.nextLine();

                        int idEntrada = concierto.comprarEntrada(fila, numero, cliente);
                        System.out.println("✅ Entrada comprada. ID: " + idEntrada);
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    concierto.verEntradasVendidas();
                    break;

                case 4:
                    System.out.print("ID de entrada a cancelar: ");
                    int idCancelar = scanner.nextInt();
                    concierto.cancelarEntrada(idCancelar);
                    break;

                case 5:
                    double ocupacion = concierto.calcularOcupacion();
                    double recaudacion = concierto.calcularRecaudacion();
                    System.out.printf("Ocupación: %.1f%%%n", ocupacion * 100);
                    System.out.printf("Recaudación: %.2f€%n", recaudacion);
                    break;

                case 6:
                    System.out.print("ID de entrada para el ticket: ");
                    int idTicket = scanner.nextInt();
                    generarTicket(concierto, idTicket);
                    break;

                case 7:
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }

        scanner.close();
    }

    static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE TICKETS CONCIERTO ===");
        System.out.println("1. Ver mapa de asientos");
        System.out.println("2. Comprar entrada");
        System.out.println("3. Ver entradas vendidas");
        System.out.println("4. Cancelar entrada");
        System.out.println("5. Ver estadísticas");
        System.out.println("6. Generar ticket");
        System.out.println("7. Salir");
    }

    static void generarTicket(Concierto concierto, int idEntrada) {
        System.out.println("\n🎫 TICKET GENERADO 🎫");
        System.out.println("====================");
        System.out.println("ID: #" + idEntrada);
        System.out.println("====================");
    }
}
```

- [ ] Crea Concierto("Rosalía - Palau Sant Jordi", "Rosalía")
- [ ] Inicializa asientos con `concierto.inicializarAsientos()`
- [ ] Scanner para entrada del usuario
- [ ] While loop hasta opción 7
- [ ] Switch para cada opción con try-catch
- [ ] Método `mostrarMenu()` separado (mejor organización)

---

## ✅ Checklist Final

- [ ] Asiento creada y funcional
- [ ] Entrada creada y funcional
- [ ] Concierto creada y funcional
- [ ] Matriz 2D inicializada correctamente
- [ ] Compra de entradas funcional
- [ ] Mapa visual claro
- [ ] Main con menú completo
- [ ] Cálculo de ocupación y recaudación
- [ ] Validación de errores
- [ ] Programa sin crashes

---

## 💡 Tips Avanzados

1. **Matriz 2D:**
   ```java
   asientos = new Asiento[10][8];
   asientos[0][0] = new Asiento(0, 0, "VIP", 85.00);
   ```

2. **Mostrar matriz:**
   ```java
   for (int i = 0; i < asientos.length; i++) {
       for (int j = 0; j < asientos[i].length; j++) {
           if (asientos[i][j].isDisponible()) {
               System.out.print("✅ ");
           } else {
               System.out.print("❌ ");
           }
       }
       System.out.println();
   }
   ```

3. **Validar índices:**
   ```java
   if (fila < 0 || fila >= asientos.length || numero < 0 || numero >= asientos[0].length) {
       throw new IllegalArgumentException("Asiento inválido");
   }
   ```

4. **Convertir número de fila a letra:**
   ```java
   char letraFila = (char)('A' + fila);
   System.out.println("Asiento " + letraFila + numero);  // A0, A1, B0, B1...
   ```

5. **Buscar elemento en ArrayList:**
   ```java
   for (Entrada e : entradas) {
       if (e.getId() == idBuscado) {
           return e;
       }
   }
   return null;  // No encontrado
   ```

6. **Iterar y eliminar (cuidado):**
   ```java
   // MAL: ConcurrentModificationException
   for (Entrada e : entradas) {
       if (e.getId() == id) {
           entradas.remove(e);  // ❌ Error
       }
   }

   // BIEN:
   for (int i = 0; i < entradas.size(); i++) {
       if (entradas.get(i).getId() == id) {
           entradas.remove(i);  // ✅ Correcto
           break;
       }
   }
   ```

7. **Formato de fecha:**
   ```java
   LocalDateTime ahora = LocalDateTime.now();
   System.out.println(ahora.toLocalDate());  // 2026-04-29
   ```

8. **Menu interactivo:**
   ```java
   int opcion = 0;
   while (opcion != 7) {
       System.out.println("\n=== MENÚ ===");
       System.out.println("1. Ver mapa");
       System.out.println("2. Comprar");
       // ...
       System.out.print("Elige opción: ");
       opcion = scanner.nextInt();
       scanner.nextLine();  // Limpia el \n

       switch (opcion) {
           case 1: concierto.mostrarMapa(); break;
           case 2: // comprar
           // ...
           default: System.out.println("Opción inválida");
       }
   }
   ```

---

## ⚠️ Errores Comunes a Evitar

1. **Olvidar inicializar la matriz:**
   - ❌ No hacer `inicializarAsientos()` en el constructor
   - ✅ Hacerlo al final del constructor o llamarlo explícitamente

2. **Confundir null con no inicializado:**
   - ❌ Acceder a `asientos[i][j]` sin verificar si es null
   - ✅ Asegúrate de llenar TODAS las posiciones de la matriz

3. **Usar `==` para comparar Strings:**
   - ❌ `if (cliente == "Juan")`
   - ✅ `if (cliente.equals("Juan"))`

4. **Olvidar `nextLine()` después de `nextInt()`:**
   - ❌ Causa que Scanner salte líneas
   - ✅ Siempre haz `scanner.nextLine();` después de `nextInt()`

5. **ArrayList y modificaciones en bucles:**
   - ❌ No puedes hacer `.remove()` dentro de un foreach
   - ✅ Usa un for tradicional con índice

6. **Matriz vacía vs matriz null:**
   - Cuando haces `new Asiento[10][8]`, la matriz EXISTS pero está vacía (todos null)
   - Necesitas llenarla con `new Asiento(...)` en cada posición

7. **ID duplicados:**
   - ❌ Reutilizar IDs después de cancelar una entrada
   - ✅ El contador SIEMPRE sube, nunca baja

---

## 🔍 Checklist de Depuración

Si algo no funciona, verifica:

- [ ] ¿La matriz se inicializa con los Asientos correctos?
  - Imprime `mostrarMapa()` al inicio
- [ ] ¿Los precios se asignan según la sección?
  - Verifica filas 0-1 (VIP), 2-4 (Premium), 5-9 (Normal)
- [ ] ¿`buscarAsiento()` retorna null cuando es inválido?
  - Prueba indices fuera de rango
- [ ] ¿Los IDs de las entradas son únicos?
  - Compra 3 entradas y verifica que tengan ID 0, 1, 2
- [ ] ¿Las excepciones se capturan correctamente?
  - Intenta comprar un asiento dos veces
- [ ] ¿El Scanner no salta líneas?
  - Después de cada `nextInt()`, haz `nextLine()`

---

## 📊 Ejemplo de Ejecución Esperada

```
=== SISTEMA DE TICKETS CONCIERTO ===
1. Ver mapa de asientos
2. Comprar entrada
3. Ver entradas vendidas
4. Cancelar entrada
5. Ver estadísticas
6. Generar ticket
7. Salir
Elige opción: 1

=== MAPA DE ASIENTOS ===
VIP (Filas 0-1):
✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅
✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅

Premium (Filas 2-4):
✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅
... (más filas)

=== SISTEMA DE TICKETS CONCIERTO ===
Elige opción: 2
Fila (0-9): 0
Número de asiento (0-7): 2
Tu nombre: Juan
✅ Entrada comprada. ID: 0

=== SISTEMA DE TICKETS CONCIERTO ===
Elige opción: 5
Ocupación: 1.2%
Recaudación: 85.00€

=== SISTEMA DE TICKETS CONCIERTO ===
Elige opción: 7
¡Hasta luego!
```

---

## 🎯 Resumen Rápido por Clase

| Clase | Responsabilidad | Métodos Principales |
|-------|-----------------|-------------------|
| **Asiento** | Representar un asiento del concierto | ocupar(), liberar(), getInfo() |
| **Entrada** | Representar una entrada comprada | Constructor con id/asiento/cliente, getDetalles() |
| **Concierto** | Gestionar toda la lógica del concierto | comprarEntrada(), cancelarEntrada(), mostrarMapa() |
| **Main** | Interfaz con el usuario | Menú interactivo, Scanner, Switch/case |

---

**¡Cuando termines, habrás creado un sistema real de ticketing!** 🎫
