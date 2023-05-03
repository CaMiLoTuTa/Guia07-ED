import javax.swing.JOptionPane;

public class Arbol {

    private class Nodo {
        public Nodo padre;
        public Nodo derecha;
        public Nodo izquierda;
        public int llave;

        public Object contenido;

        public Nodo(int indice) {
            llave = indice;
            derecha = null;
            izquierda = null;
            padre = null;
            contenido = null;
        }
    }

    public static void main(String[] args) throws Exception {
        Arbol arb = new Arbol();
        var nNodos = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántos nodos desea ingresar al arbol?"));

        for (int __ = 0; __ < nNodos; __++) {
            var indice = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el índice del nodo"));
            while (indice < 0) {
                JOptionPane.showMessageDialog(null, "El indice no puede ser negativo");
                indice = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el índice del nodo"));
            }
            var valor = JOptionPane.showInputDialog(null, "Ingrese el valor del nodo (Ej: Pera, Uva, etc.)");

            arb.insertar(indice, valor);
        }

        System.out.println("RECORRIDOS\n_________________________________");
        System.out.println("\nRecorrido InOrder:");
        arb.recorrerInOrder(arb.raiz);
        System.out.println("\nRecorrido PreOrder:");
        arb.recorrerPreOrder(arb.raiz);
        System.out.println("\nRecorrido PostOrder:");
        arb.recorrerPostOrder(arb.raiz);
    }

    Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    public void insertar(int i, Object fruta) {
        Nodo n = new Nodo(i);
        n.contenido = fruta;

        if (raiz == null) {
            raiz = n;

        } else {
            Nodo aux = raiz;
            while (aux != null) {
                n.padre = aux;
                if (n.llave >= aux.llave) {
                    aux = aux.derecha;
                } else {
                    aux = aux.izquierda;
                }
            }
            if (n.llave < n.padre.llave) {
                n.padre.izquierda = n;
            } else {
                n.padre.derecha = n;
            }
        }
    }

    public void recorrerInOrder(Nodo nodo) {
        if (nodo != null) {
            recorrerInOrder(nodo.izquierda);
            System.out.println(nodo.llave + " " + nodo.contenido);
            recorrerInOrder(nodo.derecha);
        }
    }

    public void recorrerPreOrder(Nodo nodo) {
        if (nodo != null) {
            System.out.println(nodo.llave + " " + nodo.contenido);
            recorrerPreOrder(nodo.izquierda);
            recorrerPreOrder(nodo.derecha);
        }
    }

    public void recorrerPostOrder(Nodo nodo) {
        if (nodo != null) {
            recorrerPostOrder(nodo.izquierda);
            recorrerPostOrder(nodo.derecha);
            System.out.println(nodo.llave + " " + nodo.contenido);
        }
    }
}
