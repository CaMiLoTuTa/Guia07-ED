import javax.swing.JOptionPane;

public class ArbolBusqueda {

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

        public String toString() {
            return llave + " " + contenido;
        }

    }

    public static void main(String[] args) throws Exception {
        ArbolBusqueda arb = new ArbolBusqueda();

        arb.insertar(10, "Plátano");
        arb.insertar(15, "Fresa");
        arb.insertar(5, "Cereza");

        int opc = 1;
        String text = "=== Menú Árbol Binario ===\n"
                + "1. Agregar nodo\n"
                + "2. Eliminar nodo\n"
                + "3. Buscar nodo\n"
                + "4. Mostrar recorrido InOrder\n"
                + "5. Mostrar recorrido PreOrder\n"
                + "6. Mostrar recorrido PostOrder\n"
                + "0. Salir\n";

        while (true) {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, text));
            int indice;
            String valor;
            if (opc <= 6 && opc > 0) {
                switch (opc) {
                    case 1:
                        indice = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el índice del nodo"));
                        while (indice < 0) {
                            JOptionPane.showMessageDialog(null, "El indice no puede ser negativo");
                            indice = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el índice del nodo"));
                        }
                        valor = JOptionPane.showInputDialog(null,
                                "Ingrese el valor del nodo (Ej: Pera, Uva, etc.)");

                        arb.insertar(indice, valor);

                        JOptionPane.showMessageDialog(null,
                                "El nodo " + arb.buscarNodo(indice) + " fue agregado al árbol");
                        break;
                    case 2:
                        indice = Integer
                                .parseInt(JOptionPane.showInputDialog(null, "Ingrese el índice del nodo a eliminar"));

                        JOptionPane.showMessageDialog(null, "El nodo " + arb.buscarNodo(indice) + " fue eliminado");
                        arb.eliminar(indice);
                        break;
                    case 3:
                        indice = Integer
                                .parseInt(JOptionPane.showInputDialog(null, "Ingrese el índice del nodo a buscar"));

                        if (arb.buscarNodo(indice) != null) {
                            JOptionPane.showMessageDialog(null, arb.buscarNodo(indice));

                        } else {
                            JOptionPane.showMessageDialog(null, "El nodo no se encuentra en el árbol");
                        }
                        break;
                    case 4:
                        System.out.println("\nRecorrido InOrder:");
                        arb.recorrerInOrder(arb.raiz);
                        break;
                    case 5:
                        System.out.println("\nRecorrido PreOrder:");
                        arb.recorrerPreOrder(arb.raiz);
                        break;
                    case 6:
                        System.out.println("\nRecorrido PostOrder:");
                        arb.recorrerPostOrder(arb.raiz);
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "GRACIAS...");
                break;
            }
        }

    }

    Nodo raiz;

    public ArbolBusqueda() {
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

    public String buscarNodo(int i) {
        Nodo aux = raiz;
        while (aux.llave != i) {
            if (i < aux.llave) {
                aux = aux.izquierda;
            } else {
                aux = aux.derecha;
            }
            if (aux == null) {
                return null;
            }
        }
        return aux.toString();
    }

    public boolean eliminar(int i) {
        Nodo aux = raiz;
        Nodo padre = raiz;

        boolean esHijoIzquierdo = true;
        while (aux.llave != i) {
            padre = aux;
            if (i < aux.llave) {
                esHijoIzquierdo = true;
                aux = aux.izquierda;
            } else {
                esHijoIzquierdo = false;
                aux = aux.derecha;
            }
            if (aux == null) {
                return false;
            }
        }
        if (aux.izquierda == null && aux.derecha == null) {
            if (aux == raiz) {
                raiz = null;
            } else if (esHijoIzquierdo) {
                padre.izquierda = null;
            } else {
                padre.derecha = null;
            }

        } else if (aux.derecha == null) {
            if (aux == raiz) {
                raiz = aux.izquierda;
            } else if (esHijoIzquierdo) {
                padre.izquierda = aux.izquierda;
            } else {
                padre.derecha = aux.izquierda;
            }
        } else if (aux.izquierda == null) {
            if (aux == raiz) {
                raiz = aux.derecha;
            } else if (esHijoIzquierdo) {
                padre.izquierda = aux.derecha;
            } else {
                padre.derecha = aux.izquierda;
            }
        } else {
            Nodo reemplazo = obtenerNodoReemplazo(aux);
            if (aux == raiz) {
                raiz = reemplazo;
            } else if (esHijoIzquierdo) {
                padre.izquierda = reemplazo;
            } else {
                padre.derecha = reemplazo;
            }
            reemplazo.izquierda = aux.izquierda;
        }
        return true;

    }

    private ArbolBusqueda.Nodo obtenerNodoReemplazo(ArbolBusqueda.Nodo nodoReemplazo) {
        Nodo reemplazarPadre = nodoReemplazo;
        Nodo reemplazo = nodoReemplazo;
        Nodo aux = nodoReemplazo.derecha;
        while (aux != null) {
            reemplazarPadre = reemplazo;
            reemplazo = aux;
            aux = aux.izquierda;
        }
        if (reemplazo != nodoReemplazo.derecha) {
            reemplazarPadre.izquierda = reemplazo.derecha;
            reemplazo.derecha = nodoReemplazo.derecha;
        }
        System.out.println("El nodo reemplazo es " + reemplazo);
        return reemplazo;
    }

    public void recorrerInOrder(Nodo nodo) {
        if (nodo != null) {
            recorrerInOrder(nodo.izquierda);
            System.out.print(nodo.llave + " " + nodo.contenido + ", ");
            recorrerInOrder(nodo.derecha);
        }
    }

    public void recorrerPreOrder(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.llave + " " + nodo.contenido + ", ");
            recorrerPreOrder(nodo.izquierda);
            recorrerPreOrder(nodo.derecha);
        }
    }

    public void recorrerPostOrder(Nodo nodo) {
        if (nodo != null) {
            recorrerPostOrder(nodo.izquierda);
            recorrerPostOrder(nodo.derecha);
            System.out.print(nodo.llave + " " + nodo.contenido + ", ");
        }
    }
}
