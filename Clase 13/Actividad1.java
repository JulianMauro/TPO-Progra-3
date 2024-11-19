import java.util.*;

class Nodo {
    String accion; 
    int valor;     
    List<Nodo> hijos; 

    public Nodo(String accion, int valor) {
        this.accion = accion;
        this.valor = valor;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(Nodo hijo) {
        this.hijos.add(hijo);
    }
}

class PodaAlfaBeta {
    // Algoritmo Alfa-Beta
    public int podaAlfaBeta(Nodo nodo, int profundidad, int alfa, int beta, boolean esMaximizador) {
        if (nodo.hijos.isEmpty() || profundidad == 0) {
            return nodo.valor;
        }

        if (esMaximizador) {
            int maxEval = Integer.MIN_VALUE;
            for (Nodo hijo : nodo.hijos) {
                int evaluacion = podaAlfaBeta(hijo, profundidad - 1, alfa, beta, false);
                maxEval = Math.max(maxEval, evaluacion);
                alfa = Math.max(alfa, evaluacion);

                // Poda
                if (beta <= alfa) {
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Nodo hijo : nodo.hijos) {
                int evaluacion = podaAlfaBeta(hijo, profundidad - 1, alfa, beta, true);
                minEval = Math.min(minEval, evaluacion);
                beta = Math.min(beta, evaluacion);

                // Poda
                if (beta <= alfa) {
                    break;
                }
            }
            return minEval;
        }
    }
}

public class SistemaDeteccion {
    public static void main(String[] args) {
        // Crear árbol de decisiones
        Nodo raiz = new Nodo("Inicio", 0);

        Nodo ataque1 = new Nodo("Ataque: Fuerza Bruta", 0);
        Nodo ataque2 = new Nodo("Ataque: Phishing", 0);

        Nodo defensa1 = new Nodo("Defensa: Bloquear IP", 10);
        Nodo defensa2 = new Nodo("Defensa: Monitorear", 5);
        Nodo defensa3 = new Nodo("Defensa: No actuar", -10);

        Nodo defensa4 = new Nodo("Defensa: Analizar Email", 7);
        Nodo defensa5 = new Nodo("Defensa: No actuar", -5);

        // Conectar nodos
        raiz.agregarHijo(ataque1);
        raiz.agregarHijo(ataque2);

        ataque1.agregarHijo(defensa1);
        ataque1.agregarHijo(defensa2);
        ataque1.agregarHijo(defensa3);

        ataque2.agregarHijo(defensa4);
        ataque2.agregarHijo(defensa5);

        // Ejecutar algoritmo Alfa-Beta
        PodaAlfaBeta algoritmo = new PodaAlfaBeta();
        int resultado = algoritmo.podaAlfaBeta(raiz, 3, Integer.MIN_VALUE, Integer.MAX_VALUE, true);

        // Mostrar resultado
        System.out.println("Mejor evaluación del sistema: " + resultado);
    }
}
