/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Point;

/**
 *
 * @author alu20919409n
 */
public class Universe {

    private boolean[][] matrix;
    private boolean[][] newMatrix;

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    public Universe() {
        matrix = new boolean[ConfigSingleton.getInstance().getNumRows()][ConfigSingleton.getInstance().getNumCols()];
        newMatrix = new boolean[ConfigSingleton.getInstance().getNumRows()][ConfigSingleton.getInstance().getNumCols()];

        for (int i = 0; i < ConfigSingleton.getInstance().getNumRows(); i++) {
            for (int j = 0; j < ConfigSingleton.getInstance().getNumCols(); j++) {
                matrix[i][j] = false;
                newMatrix[i][j] = false;
            }
        }
    }

    public void tick() {
        int numNeighbours;
        for (int i = 0; i < ConfigSingleton.getInstance().getNumRows(); i++) {
            for (int j = 0; j < ConfigSingleton.getInstance().getNumCols(); j++) {
                newMatrix[i][j] = false;
                numNeighbours = numNeighbours(i, j);
                if (matrix[i][j] && (numNeighbours == 2 || numNeighbours == 3)) {
                    newMatrix[i][j] = true;
                } else {
                    if (!matrix[i][j] && numNeighbours == 3) {
                        newMatrix[i][j] = true;
                    }
                }
            }
        }
        boolean[][] temp = matrix;
        matrix = newMatrix;
        newMatrix = temp;
    }

    public int numNeighbours(int row, int col) {
        int counter = 0;
        if (row - 1 >= 0) {
            if (col - 1 >= 0) {
                if (matrix[row - 1][col - 1]) {
                    counter++;
                }
            }
            if (matrix[row - 1][col]) {
                counter++;
            }
            if (col + 1 < ConfigSingleton.getInstance().getNumCols()) {
                if (matrix[row - 1][col + 1]) {
                    counter++;
                }
            }
        }
        if (col - 1 >= 0) {
            if (matrix[row][col - 1]) {
                counter++;
            }
        }
        if (col + 1 < ConfigSingleton.getInstance().getNumCols()) {
            if (matrix[row][col + 1]) {
                counter++;
            }
        }
        if (row + 1 < ConfigSingleton.getInstance().getNumRows()) {
            if (col - 1 >= 0) {
                if (matrix[row + 1][col - 1]) {
                    counter++;
                }
            }
            if (matrix[row + 1][col]) {
                counter++;
            }
            if (col + 1 < ConfigSingleton.getInstance().getNumCols()) {
                if (matrix[row + 1][col + 1]) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /*public void paintSquare(Point p, int boardWith, int boardHeight){
        
    }*/
    void paintSquare(Point point, int width, int height) {
        int x = point.x;
        int y = point.y;
        matrix[y / (height / ConfigSingleton.getInstance().getNumRows())][x / (width / ConfigSingleton.getInstance().getNumRows())] = true;
    }
}
