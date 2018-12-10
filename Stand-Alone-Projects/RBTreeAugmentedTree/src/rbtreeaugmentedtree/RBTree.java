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
public class RBTree implements RBTreeInterface {
    // Null value of node
    private final Node nil = new Node(-1, "04/12/2019");
    
    private class Node<T> {
        // Node values
        int age = -1, color = BLACK;
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
                    + "------ RB-Tree ------\n"
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
                    
                    // Define necessary variables and store the tokenized data
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
        int age = getAgeOfNode(key, root);
        
        if (age != nil.age) {
            System.out.println("The result of GETNUMSMALLER for the node with " + key + " is: " + getNumSmaller(root, age));
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
    private int getAgeOfNode(String key, Node node) {
        // Return age if current node provide the rule
        if (node.equals(nil) || node.includes(key)){
           return node.age;
        }
        
        // Check its lefts and rigths
        int left = getAgeOfNode(key, node.left);
        int right = getAgeOfNode(key, node.right);
        
        // Return if one of them (lefts & rights) exist (not nil.age)
        if (left != nil.age) {
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
     * Get number of smaller age node of the node of RBTree
     * @param node the node of RBTree
     * @param age Age
     * @return 
     */
    private int getNumSmaller(Node node, int age) {
        // Dont count nil node
        if (node == nil) {
            return 0;
        }
        
        int num = 0;
        
        // Check lefts
        num += getNumSmaller(node.left, age);
        
        // If age of node is smaller increase the num and check right
        if (age > node.age){
            num++;
            // Check rights
            num += getNumSmaller(node.right, age);
        }
        
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
        System.out.printf("%-15s %-11s %-11s %-25s %-15s \n", node.color == RED ? "Color: Red" : "Color: Black", "Age: " + node.age, "Id: " + node.id, "BirthDate: " + node.birthDate, "Parent Age: " + node.parent.age);
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
                } else if (node.age >= temp.age) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
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
