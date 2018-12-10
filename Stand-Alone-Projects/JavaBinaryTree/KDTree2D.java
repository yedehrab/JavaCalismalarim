import java.awt.geom.Point2D;

class Node {
    Point2D point;
    Node left, right;

    public Node(Point2D point) {
        this.point = point;
        left = right = null;
    }
}

class KDTree2D {
    
    Node root;

    /**
     * Main method
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        KDTree2D binTree = 
            new KDTree2D(new Point2D.Double(30., 40.));

        binTree.insert(new Point2D.Double(5., 25.));
        binTree.insert(new Point2D.Double(18., 10.));
        binTree.insert(new Point2D.Double(80., 90.));
        binTree.insert(new Point2D.Double(55., 30.));
        binTree.insert(new Point2D.Double(35., 45.));

        // Test 1
        System.out.println("Bintree: " + binTree.toString());

        // Test 2
        System.out.println("Searched node: " + binTree.search(new Point2D.Double(18., 10.)));

        // Test 3
        System.out.println("Max X: " + binTree.findMax(0));
        System.out.println("Max Y: " + binTree.findMax(1));
        System.out.println("Min X: " + binTree.findMin(0));
        System.out.println("Min Y: " + binTree.findMin(1));
    }

    /**
     * Constructer
     */
    KDTree2D(Point2D point) {
        // Setting location
        this.root = new Node(point);
    }

    KDTree2D() {
        root = null;
    }

    /**
     * Insert the given point into the tree
     * @param point
     */
    public void insert(Point2D point) {      
        root = insertRec(root, point, true);
    }

    private Node insertRec(Node root, Point2D point, boolean first) {
        // If tree is empty, return the new root
        if (root == null) { 
            root = new Node(point); 
            return root; 
        }

        // Check assignment
        boolean check;
        if (first) {
            check = point.getX() < root.point.getX();
        } else {
            check = point.getY() < root.point.getY();
        }
  
        // Recur down the tree
        if (check) 
            root.left = insertRec(root.left, point, false); 
        else
            root.right = insertRec(root.right, point, false); 
  
        // return the (unchanged) node
        return root; 
    }


    /**
     * Searches for the given point in the tree
     * @param point
     * @return The point if found, otherwise null
     */
    public Point2D search(Point2D point) {
        return searchRec(root, point, true);
    }

    // A utility function to search a given key in BST 
    private Point2D searchRec(Node root, Point2D point, boolean first) 
    { 
        // Base Cases: root is null or key is present at root 
        if (root == null) 
            return null; 

        if (root.point.equals(point)) {
            return point;
        }

        boolean check;
        if (first) {
            check = point.getX() < root.point.getX();
        } else {
            check = point.getY() < root.point.getY();
        }

        // Recur down the tree
        if (check) 
            return searchRec(root.left, point, false); 
        else
            return searchRec(root.right, point, false);
    } 

    /**
     * @return a string representation of the tree by doing a preorder
     * traversal of the tree. Each node's data should appear on a separate line and
     * be indented according to its level
     */
    public String toString() {
        return toStringRec(root);
    }

    private String toStringRec(Node root) {
        if (root == null) {
            return "\n";
        }

        String str = "";
        str += toStringRec(root.left);
        str += root.point.toString();
        str += toStringRec(root.right);

        return str;
    }

    /**
     * Finds the point with smallest value in the d-th dimension.
     * Let d=0 represent the x dimension, d=1 represent the y dimension
     * @param d
     * @return
     */
    Point2D findMin(int d) {
        if (d == 0 && root.left != null) {
            return findMinRec(root.left, 0);
        } else {
            return findMinRec(root, d);
        }
    }

    private Point2D findMinRec(Node node, int d) { 
        if (node == null) 
            return new Point2D.Double(Double.MAX_VALUE, Double.MAX_VALUE); 
  
        Point2D val = node.point;
        Point2D lval = findMinRec(node.left, d); 
        Point2D rval = findMinRec(node.right, d); 
      
        if (d == 0) {
            if (lval.getX() < val.getX()) 
                val = lval; 
            if (rval.getX() < val.getX()) 
                val = rval;
        } else {
            if (lval.getY() < val.getY()) 
                val = lval; 
            if (rval.getY() < val.getY()) 
                val = rval;
        }
        
        return val; 
    }

    /**
     * Finds the point with the largest value in the d-th dimension
     */
    Point2D findMax(int d) {
        if (d == 0) {
            return findMaxRec(root.right, d);
        } else {
            return findMaxRec(root, d);
        }
    }

    private Point2D findMaxRec(Node node, int d) { 
        if (node == null) 
            return new Point2D.Double(Double.MIN_VALUE, Double.MIN_VALUE); 
  
        Point2D val = node.point;
        Point2D lval = findMaxRec(node.left, d); 
        Point2D rval = findMaxRec(node.right, d); 
      
        if (d == 0) {
            if (lval.getX() > val.getX()) 
                val = lval; 
            if (rval.getX() > val.getX()) 
                val = rval;
        } else {
            if (lval.getY() > val.getY()) 
                val = lval; 
            if (rval.getY() > val.getY()) 
                val = rval;
        }
        
        return val; 
    } 
}
