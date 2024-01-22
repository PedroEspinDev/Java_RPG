# RPG Simple en Java

## Descripción del Proyecto
Este proyecto es un juego RPG simple desarrollado en Java como parte de la práctica de programación orientada a objetos. El juego permite a los jugadores interactuar con una tienda, comprar objetos y mejorar las estadísticas de su personaje.

## Instrucciones de Uso

1. **Ejecución del Juego:**
    - Asegúrate de tener Java instalado en tu máquina.
    - Descarga el código fuente o el archivo JAR ejecutable.
    - Ejecuta el juego desde la línea de comandos o haciendo doble clic en el archivo JAR.

2. **Interacción con el Juego:**
    - Usa la interfaz gráfica para navegar por la tienda.
    - Compra objetos para mejorar las estadísticas de tu personaje.
    - Disfruta de tu aventura RPG simple.

## Dependencias

No se requieren dependencias externas. El juego utiliza las bibliotecas estándar de Java.

## Estructura del Proyecto

- `src/`: Código fuente Java.
- `customPanelFrame/`: Código fuente Java customizado para la personalización de las ventanas y paneles.
- `images/`: Directorio que contiene las imágenes utilizadas en el juego.

## Tecnologías Utilizadas

- Java
- Adaptación de clases en Java (para la interfaz gráfica)

## Ejemplos de Código

A continuación se muestra un ejemplo de cómo se implementa la lógica de compra de objetos:

```java
// Código Java
private void buyObject(StoreItem item) {
    if (player.getGold() >= item.getPrice()) {
        player.setAttack(player.getAttack() + item.getAttackIncrease());
        player.setDefense(player.getDefense() + item.getDefenseIncrease());
        pw.getLabAttributes().setText(" Dmg: " + player.getAttack() + "  |  Def: " + player.getDefense() + "   Health: ");
        player.setGold(player.getGold() - item.getPrice());
        pw.getLabGold().setText("  Gold: " + player.getGold());
        item.setAlreadyBought(true);
        updateButton(item);
    }
}
