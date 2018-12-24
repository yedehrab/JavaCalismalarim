/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeodevi;

import java.util.ArrayList;

public abstract class UserData {
    public static String username;
    public static final ArrayList<Integer> selectedBooks = new ArrayList<>();
    
    public static void clear() {
        username = null;
        selectedBooks.clear();
    }
    
    public static String toArrayString() {
        if (selectedBooks.isEmpty()) {
            return "()";
        }
        
        String str = "(";
        for (int i = 0; i < selectedBooks.size(); i++) {
            if (i == selectedBooks.size() - 1) {
                str += "" + selectedBooks.get(i) + ")";
            } else {
                str += "" + selectedBooks.get(i) + ", ";
            }
        }
        
        return str;
    }
}
