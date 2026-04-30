# Preguntas de Evaluación - RosaliaShowBlanca

Preguntas para evaluar comprensión de **Encapsulación**, **Abstracción**, **Composición** y **Arreglos** en el proyecto RosaliaShowBlanca.

---

## 📚 NIVEL 1: CONCEPTOS BÁSICOS

### Pregunta 1.1 - Matriz de Asientos
**Pregunta:**
¿Por qué la matriz de asientos se declara así y no como ArrayList?

```java
private Asiento[][] asientos;  // En Concierto
```

¿Por qué no:
```java
private ArrayList<Asiento> asientos;
```

**Respuesta esperada:**
- **Matriz [10][8]:** Porque los asientos tienen POSICIÓN FÍSICA (fila A-J, número 0-7)
- Acceso directo: `asientos[2][3]` = Fila C, asiento 3 (RÁPIDO)
- **ArrayList:** Está desordenado, necesitarías buscar linealmente
- La matriz refleja la ESTRUCTURA REAL del teatro

**Criterio:**
- ✅ Excelente: Explica ubicación física + acceso rápido
- ✅ Bueno: Menciona que es por estructura de filas/columnas
- ❌ Incorrecto: No entiende por qué la matriz es mejor

---

### Pregunta 1.2 - Composición de Objetos
**Pregunta:**
¿Qué significa que `Entrada` tenga un atributo de tipo `Asiento`?

```java
public class Entrada {
    private Asiento asiento;  // ← ¿Qué es esto?
}
```

¿Es lo mismo que:
```java
public class Entrada {
    private int asientoId;
}
```

**Respuesta esperada:**
NO, son muy diferentes:

**Con Asiento (Composición):**
- Tienes TODO: fila, número, precio, disponibilidad
- `entrada.getAsiento().getPrecio()` ← Acceso a toda la información
- Si cambias el precio del asiento, se refleja automáticamente

**Con int asientoId:**
- Solo tienes un número
- Necesitarías buscar en la matriz para obtener información
- Duplicación de datos innecesaria

Conclusión: Composición = reutilizar objetos completos.

---

### Pregunta 1.3 - ArrayList vs Matriz
**Pregunta:**
¿Por qué `entradas` es ArrayList y `asientos` es matriz?

```java
private Asiento[][] asientos;        // Matriz
private ArrayList<Entrada> entradas; // ArrayList
```

**Respuesta esperada:**
- **asientos (matriz):** Posición FIJA, nunca cambia. Siempre 10 filas × 8 columnas
- **entradas (ArrayList):** Cantidad VARIABLE. Comienza con 0, crece con cada compra

Una matriz es para estructura ESTÁTICA. ArrayList es para colecciones DINÁMICAS.

---

## 🔒 NIVEL 2: ENCAPSULACIÓN Y CONTROL

### Pregunta 2.1 - Método comprarEntrada()
**Pregunta:**
¿Por qué `comprarEntrada()` devuelve `int` (el ID) en lugar de devolver la Entrada completa?

```java
public int comprarEntrada(int fila, int numero, String cliente) {
    // ... validaciones ...
    int idEntrada = contador++;
    Entrada e = new Entrada(idEntrada, asientos[fila][numero], cliente);
    entradas.add(e);
    asientos[fila][numero].ocupar();
    return idEntrada;  // ← ¿Por qué solo el ID?
}
```

¿Por qué no:
```java
public Entrada comprarEntrada(...) {
    // ... devolver la Entrada completa
}
```

**Respuesta esperada:**
- **Devolver int:** Main solo necesita el ID para mostrar "Entrada #123"
- **Abstracción:** No exponemos detalles internos (LocalDateTime, precio, etc)
- **Seguridad:** El usuario no puede modificar la Entrada (es private)
- Si devuelves la Entrada, alguien podría hacer `entrada.setCliente("otro")`

Conclusión: Encapsulación = devolver SOLO lo necesario.

---

### Pregunta 2.2 - Validaciones en comprarEntrada()
**Pregunta:**
¿Cuáles son las 2 validaciones MÍNIMAS que debe tener `comprarEntrada()`?

**Respuesta esperada:**
1. **¿El asiento existe y está dentro del rango?**
   - `if (fila < 0 || fila > 9 || numero < 0 || numero > 7)`
   - Previene: `asientos[-5][10]` ← Error de índice

2. **¿El asiento está disponible?**
   - `if (!asientos[fila][numero].isDisponible())`
   - Previene: Vender el mismo asiento 2 veces

**Criterio:**
- ✅ Excelente: Identifica ambas validaciones
- ✅ Bueno: Entiende por qué se validan
- ❌ Incorrecto: Dice que no necesita validaciones

---

### Pregunta 2.3 - Encapsulación en Asiento
**Pregunta:**
¿Por qué en Asiento cada atributo es `private` EXCEPTO los getters?

```java
private int fila;           // ← PRIVATE
private double precio;      // ← PRIVATE
public int getFila() {      // ← PUBLIC
    return fila;
}
public double getPrecio() { // ← PUBLIC
    return precio;
}
```

¿Por qué no hacer esto?
```java
public int fila;      // ← PELIGROSO
public double precio; // ← PELIGROSO
```

**Respuesta esperada:**
Con `public`:
- `asiento.fila = -999` ← ¡Fila negativa! (imposible)
- `asiento.precio = -50` ← ¡Precio negativo! (fraude)

Con getters:
- Solo LECTURA, sin posibilidad de cambiar
- El compilador BLOQUEA cambios directos
- Los datos están GARANTIZADOS VÁLIDOS

---

## 🎯 NIVEL 3: ABSTRACCIÓN Y COMPLEJIDAD

### Pregunta 3.1 - getResumen() vs getDetalles()
**Pregunta:**
¿Cuál es la diferencia entre estos métodos de abstracción?

```java
// En Asiento:
public String getInfo() {
    return letraFila + numero + " | " + seccion + " | " + precio + "€";
}

// En Entrada:
public String getDetalles() {
    return "Entrada #" + id + " | Cliente: " + cliente +
           " | Asiento " + asiento.getInfo();
}
```

¿Por qué llama `getDetalles()` a `asiento.getInfo()`?

**Respuesta esperada:**
- **Reutilización:** No duplicar código de formato
- **Abstracción en capas:** Entrada NO sabe CÓMO formatear asiento, solo lo pide
- **Mantenimiento:** Si cambias formato de asiento, Entrada se actualiza automáticamente
- **Composición + Abstracción:** Combina objetos usando interfaces simples

Esto es **delegación**: pedir a otro objeto que haga su parte.

---

### Pregunta 3.2 - mostrarMapa()
**Pregunta:**
¿Cuál es el propósito de abstraer `mostrarMapa()` en lugar de hacer TODO en Main?

```java
// En Main:
concierto.mostrarMapa();  // Una línea

// Lo que esconde:
// - Iterar matriz [10][8]
// - Convertir 0-9 a A-J
// - Mostrar ✅ o ❌ para cada asiento
// - Formatear columnas
// - Etc.
```

**Respuesta esperada:**
**Abstracción = Simplicidad**
- Main NO necesita saber CÓMO mostrar, solo pide que lo muestre
- Si cambias el formato, cambias 1 método, no 10 líneas de Main
- Main está LIMPIO y LEGIBLE
- Concierto ENCAPSULA la complejidad

Esto es **encapsulación de lógica**.

---

### Pregunta 3.3 - LocalDateTime en Entrada
**Pregunta:**
¿Por qué `Entrada` usa `LocalDateTime` automático en lugar de pedir la fecha como parámetro?

```java
public Entrada(int id, Asiento asiento, String cliente) {
    this.fecha = LocalDateTime.now();  // ← AUTOMÁTICO, no parámetro
}
```

¿Por qué no:
```java
public Entrada(int id, Asiento asiento, String cliente, LocalDateTime fecha) {
    this.fecha = fecha;  // ← PARÁMETRO
}
```

**Respuesta esperada:**
- **Automático:** Garantiza que la fecha sea EXACTA al momento de compra
- **Sin errores:** Main no puede pasar una fecha incorrecta
- **Intención clara:** La fecha SIEMPRE es "ahora", no variabilidad
- **Auditoría:** Es imposible falsificar la fecha de compra

Conclusión: Si algo DEBE ser automático, hazlo automático, no parámetro.

---

## 🧠 NIVEL 4: CONCEPTOS PROFUNDOS

### Pregunta 4.1 - Contador de IDs
**Pregunta:**
¿Por qué Concierto mantiene un `contador` de IDs en lugar de dejar que Entrada genere IDs aleatorios?

```java
private int contador = 0;  // En Concierto

public int comprarEntrada(...) {
    int idEntrada = contador++;  // ← Secuencial
    Entrada e = new Entrada(idEntrada, ...);
    ...
}
```

¿Problemas con IDs aleatorios?

**Respuesta esperada:**
**IDs Secuenciales (Bien):**
- Garantizado ÚNICO
- Predecible (ID 0, 1, 2, 3...)
- Imposible duplicados
- Fácil depuración

**IDs Aleatorios (Mal):**
- Colisiones posibles (¿dos IDs iguales?)
- Difícil de verificar
- No es responsabilidad de Entrada
- Concierto es quien vende, debe controlar IDs

Conclusión: La responsabilidad de generar IDs ÚNICOS debe estar centralizada.

---

### Pregunta 4.2 - Diferencia entre Asiento y Entrada
**Pregunta:**
¿Cuál es la diferencia conceptual entre Asiento y Entrada?

```java
// Asiento: Existe ANTES de cualquier compra
asientos[0][0]  // Asiento A0, siempre existe

// Entrada: Se crea CUANDO alguien compra
entradas.add(new Entrada(...))  // Nace en el momento de compra
```

¿Por qué tener dos clases?

**Respuesta esperada:**
- **Asiento:** ESTRUCTURA FÍSICA del teatro (10×8, nunca cambia)
- **Entrada:** PRUEBA DE COMPRA (quién, cuándo, cuánto pagó)

**Analogía:**
- Asiento = silla en el cine (existe siempre)
- Entrada = recibo de compra (existe solo si compras)

Si solo tuviéramos Asiento:
- No sabrías quién compró
- No sabrías cuándo
- No podrías hacer búsquedas por cliente

Dos clases = dos responsabilidades distintas.

---

### Pregunta 4.3 - Precio Dinámico por Sección
**Pregunta:**
¿Por qué cada Asiento tiene su PROPIO precio basado en sección?

```java
// Concierto.inicializarAsientos():
if (fila < 2) {
    new Asiento(..., "VIP", 85.0);    // Frente: caro
} else if (fila < 6) {
    new Asiento(..., "Premium", 65.0); // Medio: normal
} else {
    new Asiento(..., "Normal", 45.0);  // Atrás: barato
}
```

¿Por qué no tener un método que calcule precio según fila?

**Respuesta esperada:**
- **Independencia:** Cada asiento SABE su precio sin cálculos
- **Flexibilidad:** Puedes cambiar precio de VIP sin cambiar lógica
- **Encapsulación:** No mezclas "dónde está" con "cuánto cuesta"
- **Rendimiento:** No necesitas recalcular cada vez

Si lo hicieras con método:
```java
public double calcularPrecio(int fila) {
    if (fila < 2) return 85.0;
    ...
}
```

Problemas:
- Cada vez que pides precio, se recalcula
- Difícil de cambiar (precios dinámicos)
- Asunto responsable por su precio

**Conclusión:** Los datos que no cambian deben almacenarse, no calcularse.

---

### Pregunta 4.4 - Scanner en Main
**Pregunta:**
¿Por qué Main necesita Scanner en lugar de que Concierto lo maneje?

```java
// Main:
Scanner scanner = new Scanner(System.in);
int opcion = Integer.parseInt(scanner.nextLine());
concierto.comprarEntrada(fila, numero, nombre);

// ¿Por qué NO?
public void comprarEntradaDesdeConsola() {  // En Concierto
    Scanner scanner = new Scanner(System.in);
    int fila = Integer.parseInt(scanner.nextLine());
    ...
}
```

**Respuesta esperada:**
- **Separación de responsabilidades:**
  - Concierto = LÓGICA (qué hacer con los datos)
  - Main = INTERFAZ (cómo leer datos del usuario)

- **Reutilización:** Si quieres una interfaz web o gráfica, Concierto sigue funcionando
- **Testeo:** Puedes testing Concierto sin interacción con usuario
- **Modularidad:** Cambiar interfaz NO afecta lógica

Con Concierto leyendo Scanner:
- El código es INÚTIL para aplicación web
- No puedes automatizar tests
- Concierto hace 2 cosas (lógica + interfaz)

---

## 📋 RUBRICA DE EVALUACIÓN

| Nivel | Preguntas | Criterio de Éxito |
|-------|-----------|------------------|
| **Básico** | 1.1-1.3 | Entiende matriz vs ArrayList, composición (60%+) |
| **Intermedio** | 2.1-2.3 | Encapsulación, validación, private/getters (70%+) |
| **Avanzado** | 3.1-3.3 | Abstracción, LocalDateTime, delegación (75%+) |
| **Experto** | 4.1-4.4 | IDs únicos, Asiento vs Entrada, separación de responsabilidades (80%+) |

---

## 🎓 BONUS: Preguntas de Reflexión

### Bonus 1: ArrayList de Entradas
¿Por qué `entradas.add()` es seguro pero `entradas[100]` sería peligroso?

### Bonus 2: Compatibilidad de Matrices
¿Qué ocurriría si intentas acceder a `asientos[10][8]` (índices fuera de rango)?

### Bonus 3: Encapsulación de Asiento
¿Por qué no devolver directamente el `Asiento` ocupado en lugar de solo el ID?

```java
// Actual:
public int comprarEntrada(...) { return idEntrada; }

// ¿Por qué no?
public Asiento comprarEntrada(...) { return asientos[fila][numero]; }
```
