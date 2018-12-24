/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;

import java.util.Scanner;

public interface UyeArayuzleri {

    Scanner tarayici = new Scanner(System.in);

    public abstract void girisYapArayuzu();
    public abstract void kayitOlArayuzu();
}

