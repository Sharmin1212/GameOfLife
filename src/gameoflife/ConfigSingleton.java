package gameoflife;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20919409n
 */
public class ConfigSingleton {

    private int numRows;
    private int numCols;
    private int deltaTime;

    public int getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(int deltaTime) {
        this.deltaTime = deltaTime;
        save();
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        save();
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
        save();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    private static ConfigSingleton instance = null;

    private ConfigSingleton() {
        deltaTime = 100;
        numRows = 50;
        numCols = 50;
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(
                    new FileInputStream("config.dat"));
            ConfigSingleton readInstance = (ConfigSingleton) input.readObject();
            numRows = readInstance.getNumRows();
            numCols = readInstance.getNumCols();
            deltaTime = readInstance.getDeltaTime();
        } catch (IOException ex) {
            // Do nothing.
        } catch (ClassNotFoundException ex) {
            // Do nothing
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                }

            }
        }
    }

    public static ConfigSingleton getInstance() {
        if (instance == null) {
            instance = new ConfigSingleton();
        }

        return instance;
    }

    public void save() {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(
                    new FileOutputStream("config.dat"));
            output.writeObject(this);
        } catch (IOException ex) {

        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                }

            }
        }

    }

}
