/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtreeaugmentedtree;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 */
public class AugmentedRBTree implements RBTreeInterface {
    // Null value of node
    private final Node nil = new Node();
    
    private class Node<T> {
        
        // New Variable
        float numSmall;
        
        // Node values
        int age, color = BLACK;    
        T id;     
        String birthDate;
        
        // Set the successors nil which means null
        Node left = nil, right = nil, parent = nil;
        
        /** 
         * Create RBTree Node
         * @param key1 ID of person
         * @param key2 Birth date of person
         */
        Node(T id, String birthDate) {
            // Set the id
            this.id = id;
            
            // Convert date string
            this.birthDate = birthDate;
            
            // Calculate and store age
            this.age = calculateAge();
        }
        
        /**
         * Default node is nil (null)
         */
        Node() {
            this.id = null;
            this.birthDate = "4/12/2018";
            this.numSmall = -1;
        }
        
        private LocalDate parseLocalDate(String str) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y");
            return LocalDate.parse(str, formatter);
        }
        
        /**
         * Calculate age of node
         * @return Age of the node (person)
         */
        private int calculateAge() {
            return Period.between(parseLocalDate(birthDate), LocalDate.now()).getYears();                                              
        }
        
        /**
         * Check if node has the given key
         * @param key Id or birth date
         * @return True if has otherwise false
         */
        public final boolean includes(T key) {
           return key.equals(id) || key.equals(birthDate);
        }
}
    
    // The root of RBTree
    private Node root = nil;
    
    public void consoleUI() {
        Scanner scan = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.print("\n"
                    + "------ Augmented RB-Tree ------\n"
                    + "1.- Insert all elements in file\n"
                    + "2.- GetNumSmaller\n"
                    + "3.- GetMax\n"
                    + "4.- GetMin\n"
                    + "5.- GetNum\n"
                    + "6.- Print Tree\n"
                    + "0.- Back\n");
            
            // Rewant input if the given input mismatch
            try {
                choice = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch !");
                consoleUI();
                break;
            }
            
            
            // Capture '\n'
            scan.nextLine();
            
            System.out.println();
            switch (choice) {
                case 1:
                    insertFile();
                    System.out.println("All items were inserted.");
                    break;
                case 2:
                    System.out.println("Type id or birthdate");
                    String key = scan.nextLine();
                    printNumSmaller(key);
                    break;
                case 3:
                    getMax(root);
                    break;
                case 4:
                    getMin(root);
                    break;
                case 5:
                    System.out.println("The number of all people is " + getNum(root));
                    break;
                case 6:
                    printTree(root);
                    break;
            }
        }
    }
    
    private <T> void insertFile() {
        // Try to read file line by line
        try {
            File file = new File("people.txt");
            try (Scanner scanner = new Scanner(file)) {
                // Reset the current tree
                root = nil;
                
                while (scanner.hasNext()) {
                    // Get the line
                    String line = scanner.nextLine();
                    
                    // Tokenize the line by comma ','
                    String delims = ",";
                    StringTokenizer st = new StringTokenizer(line, delims);
                    
                    // Define necessary variables and store the tokenized data (generic olmalı)
                    T id = (T) st.nextElement();
                    String birthdate = "" + st.nextElement();
                    
                    // Insert the new node to tree
                    insert(new Node(id, birthdate));
                }
            }
       } catch (FileNotFoundException e) {
            System.out.println(e);
       } 
    }
    
    /**
     * Search the node with given key and print it numSmall variable
     * @param key 
     */
    private void printNumSmaller(String key) {
        int numSmall = getNumSmallOfNode(key, root);
        
        if (numSmall != nil.numSmall) {
            System.out.println("The result of GETNUMSMALLER for the node with " + key + " is: " + numSmall);
        } else {
            System.out.println("Given key is wrong.");
        }
    }
    
    /**
     * Find and return node for the given key
     * @param key Searching key
     * @param node Root of node
     * @return If find node return the age of node otherwise age of nil (-1)
     */
    private int getNumSmallOfNode(String key, Node node) {
        // Return age if current node provide the rule
        if (node.equals(nil) || node.includes(key)){
           return (int) node.numSmall;
        }
        
        // Check its lefts and rigths
        int left = getNumSmallOfNode(key, node.left);
        int right =  getNumSmallOfNode(key, node.right);
        
        // Return if one of them (lefts & rights) exist (not nil.age)
        if (left != nil.numSmall) {
            return left;
        } else {
            return right;
        }
    }
    
   
    /**
     * Print the oldest person info
     * @param node Root of RBTree
     */
    private void getMax(Node node) {
        if (!node.equals(nil)) {
            // Find the rightest node
            while (!node.right.equals(nil)) {
                node = node.right;
            }
            // The maximum age of all people is 98, id : 4434, Birthdate : 1920
            System.out.println("The maximum age of all people is " + node.age + ", Id: " + node.id + ", BirthDate: " + node.birthDate);
        } else {
            System.out.println("No node exist");
        }
    }
    
    /**
     * Print the youngest person info
     * @param node Root of RBTree
     */
    private void getMin(Node node) {
        if (!node.equals(nil)) {
            // Find the rightest node
            while (!node.left.equals(nil)) {
                node = node.left;
            }
            // The minimum age of all people is 13, id : 6060, Birthdate : 2003
            System.out.println("The minimum age of all people is " + node.age + ", Id: " + node.id + ", BirthDate: " + node.birthDate);
        } else {
            System.out.println("No node exist");
        }
    }
    
    /**
     * Get number of node of the node of RBTree
     * @param node Root of the RBTree
     * @return Number of node
     */
    private int getNum(Node node) {
        // Dont count nil node
        if (node == nil) {
            return 0;
        }
        
        int num = 0;
        num += getNum(node.left);
        num++;
        num += getNum(node.right);
        
        return num;
    }
    
    /**
     * Prints the given node
     * @param node Root of RBTRee
     */
    private void printTree(Node node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        // Color: Black age: 13id: 1329birthDate: 2005-07-17 Parent: 15
        System.out.printf("%-15s %-11s %-11s %-25s %-15s %-15s \n", node.color == RED ? "Color: Red" : "Color: Black", "Age: " + node.age, "Id: " + node.id, "BirthDate: " + node.birthDate, "NumSmall: " + node.numSmall, "Parent Age: " + node.parent.age);
        printTree(node.right);
    }

    private void insert(Node node) {
        Node temp = root;
        if (root == nil) {
            root = node;
            node.color = BLACK;
            node.parent = nil;
        } else {
            node.color = RED;
            while (true) {
                if (node.age < temp.age) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    // New line come from augmented
                    // If the given node turn right, it higher than the node before or equal
                    // If it equal the age of node, it will equal its numsmall too
                    node.numSmall = (int) temp.numSmall + (temp.numSmall * 10) % 10;
                    
                    if (node.age == temp.age) {
                        node.numSmall += 0.1;
                    }
                    
                    // If the givene node higher the node, increase the given node numsmall variable
                    if (node.age > temp.age) {
                        node.numSmall++;
                    }
                        
                    if (temp.right == nil) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            // Fix tree
            fixTree(node);
            
            // New line cone from augmented 
            // Update num smalls var
            updateNumSmalls(root, node.age);
        }
    }

    //Takes as argument the newly inserted node
    private void fixTree(Node node) {
        while (node.parent.color == RED) {
            Node uncle = nil;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } 
                if (node == node.parent.right) {
                    //Double rotation needed
                    node = node.parent;
                    rotateLeft(node);
                } 
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation 
                rotateRight(node.parent.parent);
            } else {
                uncle = node.parent.parent.left;
                 if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {
                    //Double rotation needed
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }
    
    /**
     * Update numSmall variable of required nodes with the given age in AugmentedRBTree
     * @param node Root or part of AugmentedRBTree
     * @param age the age what use to check
     */
    private void updateNumSmalls(Node node, int age) {
        // Check if node is nil
        if (node != nil) {
            // If node of age is greater than age increase numSmall value
            if (node.age > age) {
                node.numSmall++;
                
                // Update the lefts
                updateNumSmalls(node.left, age);
            }
            
            // Update the rights
            updateNumSmalls(node.right, age);
        }
        
        
    }

    void rotateLeft(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {//Need to rotate root
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    void rotateRight(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {//Need to rotate root
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }
}
