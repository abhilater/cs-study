package com.abhi.study.dsa.trie;

import java.util.*;

/**
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public class Trie {

    private TrieNode root = new TrieNode();

    public void insert(String record) {
        if (null == record || record.length() < 1) return;
        TrieNode itr = root;
        for (int i = 0; i < record.length(); i++) {
            TrieNode nextNode = itr.children.get(record.charAt(i));
            if (nextNode == null) {
                nextNode = new TrieNode();
                itr.children.put(record.charAt(i), nextNode);
            }
            itr = nextNode;

            if (i == record.length() - 1) itr.isLeaf = true;
        }
    }

    public List<String> search(String str) {
        List<String> res = new ArrayList<>();
        TrieNode prevNode = root;

        StringBuilder prefix = new StringBuilder("");
        int len = str.length();

        for (int i = 0; i < len; i++) {
            prefix.append(str.charAt(i));
            TrieNode curNode = prevNode.children.get(prefix.charAt(i));
            if (curNode == null) {
                return res;
            }
            if (i == len - 1) {
                searchUtil(curNode, prefix.toString(), res);
            }
            prevNode = curNode;
        }
        return res;
    }

    public void searchUtil(TrieNode currNode, String prefix, List<String> result) {
        if (currNode.isLeaf) result.add(prefix);

        for (char currChar = 'a'; currChar <= 'z'; currChar++) {
            TrieNode nextNode = currNode.children.get(currChar);
            if (nextNode != null) {
                searchUtil(nextNode, prefix + currChar, result);
            }
        }
    }

    static class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean isLeaf;

        public TrieNode() {
            children = new HashMap<>();
            for (char i = 'a'; i <= 'z'; i++) children.put(i, null);
            isLeaf = false;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String s = "abhishek";
        trie.insert(s);

        s = "abhishekgupta";
        trie.insert(s);

        s = "abhish";
        trie.insert(s);

        s = "abhi";
        trie.insert(s);

        s = "shailja";
        trie.insert(s);

        s = "shailu";
        trie.insert(s);

        List<String> res = trie.search("abhi");
        System.out.println(res);
        res = trie.search("shail");
        System.out.println(res);
    }

}
