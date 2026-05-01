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
2. Ver precios (tabla de precios por sección)
3. Comprar entrada (A-J, 0-7, nombre)
4. Ver mis entradas (lista todas las compras)
5. Cancelar entrada (por ID)
6. Ver estadísticas (% ocupación, recaudación)
7. Generar ticket (mostrar recibo bonito de entrada)
8. Salir
```

## 💰 Tabla de Precios

| Sección | Filas | Precio |
|---------|-------|--------|
| VIP | A-B | 85.00€ |
| Premium | C-E | 65.00€ |
| Normal | F-J | 45.00€ |

## 👨‍💻 Trabajo con Git y GitHub

### ⚠️ Archivos a NO subir
- **`.class`** (compilados) → Crear `.gitignore` con `*.class`
- Solo se suben los `.java` (código fuente)
- Los `.class` se regeneran con `javac`

### Crear `.gitignore`
```bash
echo "*.class" > .gitignore
```

### Opción 1️⃣: Subir tu proyecto nuevo

```bash
git init
git config user.name "Tu Nombre"
git config user.email "tu@email.com"

git add *.java README.md .gitignore
git commit -m "Initial commit: sistema de entradas Rosalía"

git remote add origin https://github.com/usuario/repositorio.git
git branch -M main
git push -u origin main
```

### Opción 2️⃣: Hacer Fork de un proyecto existente

**¿Cuándo hacer fork?**
- Quieres contribuir a un proyecto de otra persona
- Quieres tener tu propia versión del proyecto
- No tienes permisos de escritura en el repo original

**Pasos:**

1. **Haz fork en GitHub** (botón "Fork" en la esquina superior)
   - Esto crea una copia en tu cuenta: `github.com/TU-USUARIO/nombre-repo`

2. **Clona TU fork (no el original)**
   ```bash
   git clone https://github.com/TU-USUARIO/nombre-repo.git
   cd nombre-repo
   ```

3. **Agrega el repo original como "upstream" (opcional pero recomendado)**
   ```bash
   git remote add upstream https://github.com/usuario-original/nombre-repo.git
   ```

4. **Crea una rama para tu feature**
   ```bash
   git switch -b feature/mi-mejora
   ```

5. **Haz cambios y commits**
   ```bash
   git add archivo.java
   git commit -m "feat: agregar nueva funcionalidad"
   ```

6. **Sube a TU fork**
   ```bash
   git push origin feature/mi-mejora
   ```

7. **Crea un Pull Request (PR)** en GitHub
   - GitHub te mostrará un botón para crear PR
   - Describe qué cambios hiciste y por qué

### Flujo de trabajo habitual
```bash
git status              # Ver cambios
git add archivo.java    # Agregar cambios
git commit -m "descripción clara"  # Guardar cambios
git push origin main    # Subir a GitHub
```

### Actualizar tu fork con cambios del original
```bash
git fetch upstream
git merge upstream/main
git push origin main
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
