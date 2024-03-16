package com.ali;

/**
 * Author ali Abidi
 * Date: Mar 16, 2024.
 */
import com.ali.ExecutionCount;
import com.ali.ExecutionTime;

public class Main {
    @ExecutionCount(id = "method1")
    public void method1() {
        // Method implementation
    }

    @ExecutionTime(id = "method2", maxAllowedTime = 5000)
    public void method2() {
        // Method implementation
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.method1();
        main.method2();
    }
}