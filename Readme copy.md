# 📝 SistemaTicketConcierto - Pasos para Resolver

Sigue estos pasos para resolver el proyecto. Cada `// TODO:` es un paso.

---

## 🎯 Paso 1: Clase Asiento

**Archivo:** `Asiento.java`

- [ ] Atributos: fila (int), numero (int), seccion (String), precio (double), disponible (boolean)
- [ ] Constructor que reciba fila, numero, seccion, precio
  - disponible por defecto = true
- [ ] Getters: getFila(), getNumero(), getSeccion(), getPrecio(), isDisponible()
- [ ] Método: ocupar() - marca como no disponible
- [ ] Método: liberar() - marca como disponible
- [ ] Método: getInfo() - retorna "A12 | VIP | 85€ | ✅ Disponible"

**Test:** Crea 3 asientos, ocupa uno, verifica getInfo()

---

## 🎯 Paso 2: Clase Entrada

**Archivo:** `Entrada.java`

- [ ] Atributos: id, asiento (Asiento), cliente (String), fecha (LocalDateTime), precio (double)
- [ ] Constructor que reciba id, asiento, cliente
  - fecha = ahora
  - precio = asiento.getPrecio()
- [ ] Getters: getId(), getAsiento(), getCliente(), getFecha(), getPrecio()
- [ ] Método: getDetalles() - retorna "Entrada #123 | Cliente: Juan | Asiento A12 | 85€ | 2026-04-29"

**Test:** Crea una entrada y muestra detalles

---

## 🎯 Paso 3: Clase Concierto

**Archivo:** `Concierto.java`

- [ ] Atributos:
  - nombre (String)
  - artista (String)
  - asientos (Asiento[][]) - matriz 10 filas × 8 columnas
  - entradas (ArrayList<Entrada>)
  - contadorEntradas (int)

- [ ] Constructor: recibe nombre y artista
  - Inicializa matriz vacía
  - Inicializa ArrayList
  - Inicializa contador

- [ ] Método: inicializarAsientos()
  - Crea matriz con asientos
  - Asigna precios según sección:
    - Filas 0-1: "VIP" - 85€
    - Filas 2-4: "Premium" - 65€
    - Filas 5-9: "Normal" - 45€

- [ ] Método: mostrarMapa()
  - Imprime matriz en texto
  - ✅ = disponible
  - ❌ = ocupado
  - Ejemplo:
    ```
    VIP:     ✅ ✅ ❌ ✅ ...
    Premium: ✅ ❌ ✅ ✅ ...
    ```

- [ ] Método: buscarAsiento(fila, numero) - retorna Asiento o null

- [ ] Método: comprarEntrada(fila, numero, cliente)
  - Valida que fila/numero sean válidos
  - Valida que asiento esté disponible
  - Crea nueva Entrada
  - Ocupa el asiento
  - Agrega a ArrayList
  - Incrementa contador
  - Retorna ID de entrada

- [ ] Método: cancelarEntrada(idEntrada)
  - Busca la entrada por ID
  - Libera el asiento
  - Elimina del ArrayList

- [ ] Método: calcularOcupacion()
  - Retorna porcentaje de asientos vendidos
  - Ejemplo: 0.45 (45%)

- [ ] Método: calcularRecaudacion()
  - Retorna suma de todos los precios vendidos

- [ ] Método: verEntradasVendidas()
  - Imprime todas las entradas

**Test:** Inicializa asientos, compra 3, muestra mapa, calcula ocupación

---

## 🎯 Paso 4: Clase Main

**Archivo:** `Main.java`

- [ ] Crea Concierto("Rosalía - Palau Sant Jordi", "Rosalía")
- [ ] Inicializa asientos
- [ ] Menú principal con opciones:
  - 1. Ver mapa de asientos
  - 2. Comprar entrada
  - 3. Ver mis entradas
  - 4. Cancelar entrada
  - 5. Ver estadísticas
  - 6. Generar ticket
  - 7. Salir

- [ ] While loop hasta opción 7
- [ ] Switch para cada opción
- [ ] Try-catch para validación
- [ ] Scanner para entrada

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

## 💡 Tips

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

---

**¡Cuando termines, habrás creado un sistema real de ticketing!** 🎫
