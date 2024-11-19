import java.util.ArrayList;
import java.util.List;

public class DisposicionEscritoriosSillas {
    private static final int N = 4;

    public static void main(String[] args) {
        int[][] tablero = new int[N][N]; 
        List<int[][]> soluciones = new ArrayList<>();
        colocarEscritoriosSillas(0, tablero, soluciones);
        imprimirSoluciones(soluciones);
    }

    // Función principal para colocar escritorios y sillas en el tablero
    private static void colocarEscritoriosSillas(int fila, int[][] tablero, List<int[][]> soluciones) {
        if (fila == N) { 
            soluciones.add(copiarTablero(tablero));
            return;
        }

        // Probar todas las columnas en la fila actual
        for (int columna = 0; columna < N; columna++) {
            if (esSeguro(tablero, fila, columna)) {
                tablero[fila][columna] = 1; 
                colocarEscritoriosSillas(fila + 1, tablero, soluciones);
                tablero[fila][columna] = 0; 
            }
        }
    }

    // Verifica si es seguro colocar un escritorio en la posición (fila, columna)
    private static boolean esSeguro(int[][] tablero, int fila, int columna) {
        // Verificar la columna
        for (int i = 0; i < fila; i++) {
            if (tablero[i][columna] == 1) {
                return false;
            }
        }
        return true;
    }

    // Crear una copia del tablero actual
    private static int[][] copiarTablero(int[][] tablero) {
        int[][] copia = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(tablero[i], 0, copia[i], 0, N);
        }
        return copia;
    }

    // Imprimir todas las soluciones encontradas
    private static void imprimirSoluciones(List<int[][]> soluciones) {
        System.out.println("Número total de soluciones: " + soluciones.size());
        for (int[][] solucion : soluciones) {
            imprimirTablero(solucion);
            System.out.println();
        }
    }

    // Imprimir un tablero
    private static void imprimirTablero(int[][] tablero) {
        for (int[] fila : tablero) {
            for (int celda : fila) {
                System.out.print((celda == 1 ? "E " : ". ") + " ");
            }
            System.out.println();
        }
    }
}
