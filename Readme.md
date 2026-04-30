# Proyecto RosaliaShow - Encapsulación, Abstracción y Composición

## 📌 Propósito

Sistema de gestión de entradas para un concierto de Rosalía. Demuestra:
- **Encapsulación:** Protección de datos complejos
- **Abstracción:** Ocultamiento de complejidad
- **Composición:** Combinar objetos (Entrada contiene Asiento)
- **Estructuras de datos:** Matrices 2D vs ArrayLists
- **Métodos que validan:** Prevenir estados inconsistentes

## 🎯 Conceptos Clave

### 1. Matriz 2D de Asientos
```java
private Asiento[][] asientos;  // 10 filas × 8 columnas
```
- **Por qué matriz?** Posición FIJA (A0, A1, B0, etc)
- **Acceso rápido:** `asientos[0][2]` → Fila A, asiento 2
- **Estructura física:** Refleja estructura real del teatro

### 2. ArrayList de Entradas
```java
private ArrayList<Entrada> entradas;  // Dinámico
```
- **Por qué ArrayList?** Cantidad VARIABLE
- **Crece automático:** Cada compra agrega una entrada
- **Búsqueda:** Para encontrar entrada por ID

### 3. Composición
- **Entrada contiene Asiento:** `private Asiento asiento;`
- **Concierto contiene Asientos:** `Asiento[][] asientos;`
- **Concierto contiene Entradas:** `ArrayList<Entrada> entradas;`

### 4. Encapsulación de Métodos
- **comprarEntrada():** Valida fila/número y disponibilidad
- **cancelarEntrada():** Busca y libera asiento
- **mostrarMapa():** Abstrae complejidad de iteración

### 5. Abstracción de Información
- **getResumen():** Formatea información bonita
- **getDetalles():** Detalles de entrada con información de asiento
- **getInfo():** Información de asiento con emoji de estado

## 📂 Estructura del Proyecto

```
RosaliaShowBlanca/
├── Asiento.java          # Representa físicamente un asiento
├── Entrada.java          # Representa una compra realizada
├── Concierto.java        # Gestiona todo (matriz de asientos, lista de entradas)
├── Main.java             # Interfaz con usuario (menú interactivo)
└── PREGUNTAS_EVALUACION.md  # Evaluación de conocimientos
```

## 🚀 Ejecutar

```bash
cd /Volumes/Compartido/IOC/SEMESTRE2/PROG/practicando/Proyectos/RosaliaShow/RosaliaShowBlanca
javac *.java
java Main
```

## 🎮 Menú Interactivo

```
1. Ver mapa de asientos (✅ disponible, ❌ vendida)
2. Comprar entrada (A-J, 0-7, nombre)
3. Ver mis entradas (lista todas las compras)
4. Cancelar entrada (por ID)
5. Ver estadísticas (% ocupación, recaudación)
6. Generar ticket (mostrar recibo bonito de entrada)
7. Salir
```

## 📖 Evaluación

Lee **PREGUNTAS_EVALUACION.md** para preguntas progresivas sobre:
- Matriz vs ArrayList
- Composición de objetos
- Encapsulación y validación
- Abstracción en capas
- Responsabilidades de clases

---

**Creado para aprender:** Arquitectura de software con encapsulación, abstracción y composición.
