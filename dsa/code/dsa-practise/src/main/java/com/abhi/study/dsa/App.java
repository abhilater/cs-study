package com.abhi.study.dsa;

import java.util.Stack;

/**
 * Hello world!
 */
public class App {

    static TreeNode buildTree(String input) {
        Stack<Character> s = new Stack<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode root = null;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ')') {
                s.push(input.charAt(i));
            } else {
                char c = s.pop();
                if (c == '(') {
                    st.push(null);
                } else {
                    s.pop();
                    TreeNode r = st.pop();
                    TreeNode l = st.pop();
                    TreeNode node = new TreeNode(Integer.parseInt(c + ""));
                    node.left = l;
                    node.right = r;
                    st.push(node);
                }
            }
        }
        return st.pop();
    }

    static class TreeNode {
        public int val;
        public TreeNode left = null, right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }



    static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static void pairSum(Node root, int K){
        Stack<Node> stackLeft = new Stack<>();
        Stack<Node> stackRight = new Stack<>();
        Node rootLeft = root, rootRight = root;
        addNodesLeft(rootLeft, stackLeft);
        addNodesRight(rootRight, stackRight);
        boolean traverseLeft = false;
        boolean traverseRight = false;
        rootLeft = stackLeft.pop();
        rootRight = stackRight.pop();
        while(!stackLeft.isEmpty() && !stackRight.isEmpty()){
            if(traverseLeft){
                rootLeft = stackLeft.pop();
            }
            if(traverseRight){
                rootRight = stackRight.pop();
            }
            if(rootLeft.value + rootRight.value == K){
                System.out.println(rootLeft.value +", "+ rootRight.value);
                return;
            }
            else if(rootLeft.value + rootRight.value < K){
                rootLeft = rootLeft.right;
                addNodesLeft(rootLeft, stackLeft);
                traverseLeft = true;
                traverseRight = false;
            }else {
                rootRight = rootRight.left;
                addNodesLeft(rootRight, stackRight);
                traverseRight = true;
                traverseLeft = true;
            }
        }

    }
    static void  addNodesLeft(Node root, Stack<Node> stackLeft){
        while(root != null){
            stackLeft.push(root);
            root = root.left;
        }
    }

    static void addNodesRight(Node root, Stack<Node> stackRight){
        while(root != null){
            stackRight.push(root);
            root = root.right;
        }
    }

   static class A {
        public static void sm(){
            System.out.println("A");
        }
    }

    static class B extends A {

        public static void sm(){
            System.out.println("B");
        }
    }



    public static void main(String[] args) {
        /*String s = "(0(5(5()())(4()(10()())))(7(6()())(3()())))";
        TreeNode t = buildTree(s);*/
       /* Node n = new Node(15);
        n.left = new Node(10);
        n.left.left = new Node(8);
        n.left.right = new Node(12);
        n.right = new Node(20);
        n.right.left = new Node(16);
        n.right.right = new Node(25);*/

        /*pairSum(n, 33);*/

      A a = new A();
        B b = new B();
        A bb = b;
        B bbb = (B) b;

    }
}
