import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {

    public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
        Stack<HtmlTag> stack = new Stack<>(); // Crear una pila para rastrear las etiquetas de apertura

        // Iterar a través de las etiquetas HTML en la cola
        for (HtmlTag tag : tags) {
            if (tag.isSelfClosing()) {
                // Etiqueta de autocierre, se ignora
                continue;
            } else if (tag.isOpenTag()) {
                // Etiqueta de apertura, agregarla a la pila
                stack.push(tag);
            } else if (!tag.isOpenTag()) {
                // Etiqueta de cierre
                if (!stack.isEmpty()) {
                    // Si la pila no está vacía, buscar la etiqueta de apertura correspondiente
                    if (stack.peek().matches(tag)) {
                        // Coincide con la etiqueta de apertura, eliminarla de la pila
                        stack.pop();
                    } else {
                        // No coincide con la etiqueta de apertura, devolver la pila actual
                        return stack;
                    }
                } else {
                    // La pila está vacía, lo que indica un error (etiqueta de cierre sin apertura correspondiente)
                    return null;
                }
            }
        }

        // Verificación final
        // Si la pila está vacía, todas las etiquetas se cerraron correctamente, HTML válido
        // Si la pila no está vacía, algunas etiquetas de apertura no se cerraron, HTML no válido
        return stack;
    }
}







