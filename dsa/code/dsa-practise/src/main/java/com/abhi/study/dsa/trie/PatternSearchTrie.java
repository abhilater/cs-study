package com.abhi.study.dsa.trie;

import java.util.*;

/**
 * @author abhishek
 * @version 1.0, 8/3/17
 */
public class PatternSearchTrie {

    private TrieNode root = new TrieNode();

    public void insert(String suffix, int startIdx) {
        if (null == suffix || suffix.length() < 1) return;
        TrieNode currNode = root;
        for (int i = 0; i < suffix.length(); i++, startIdx++) {
            TrieNode nextNode = currNode.children.get(suffix.charAt(i));
            if (null == nextNode) {
                nextNode = new TrieNode();
                currNode.children.put(suffix.charAt(i), nextNode);
            }
            nextNode.indexes.add(startIdx);
            currNode = nextNode;
            if (i == suffix.length() - 1) currNode.isLeaf = true;
        }
    }

    public List<Integer> searchOccur(String text) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        TrieNode currNode = root;
        for (int i = 0; i < text.length(); i++) {
            TrieNode nextNode = currNode.children.get(text.charAt(i));
            // text is more than this path hence no match
            if (null == nextNode) {
                result.clear();
                break;
            }

            result.add(nextNode.indexes);
            currNode = nextNode;
        }
        List<Integer> finalRes = new ArrayList<>();
        if (result.size() < 1) return finalRes;
        // ganda list logic, convert last and first list to set
        Set<Integer> setStart = new HashSet<>(result.get(0));
        Set<Integer> setEnd = new HashSet<>(result.get(result.size() - 1));
        for (Integer index : setStart) {
            if (setEnd.contains(index + (text.length() - 1))) {
                finalRes.add(index);
            }
        }
        return finalRes;
    }

    public List<String> search(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode currNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            TrieNode nextNode = currNode.children.get(prefix.charAt(i));
            if (null == nextNode) return result;
            currNode = nextNode;
            if (i == prefix.length() - 1) {
                searchUtil(currNode, prefix, result);
            }
        }
        return result;
    }

    void searchUtil(TrieNode currNode, String prefix, List<String> result) {
        if (currNode.isLeaf) {
            result.add(prefix);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            TrieNode nextNode = currNode.children.get(c);
            if (null != nextNode) {
                searchUtil(nextNode, prefix + c, result);
            }
        }
    }


    static class TrieNode {
        public Map<Character, TrieNode> children = new HashMap<>();
        public ArrayList<Integer> indexes = new ArrayList<>();
        public boolean isLeaf;

        public TrieNode() {
            for (char c = 'a'; c <= 'z'; c++) {
                children.put(c, null);
            }
            children.put(' ', null);
        }
    }

    public static void main(String args[]) {
        PatternSearchTrie trie = new PatternSearchTrie();
        String s = "abhishek";
        trie.insert(s, 0);

        s = "abhishekgupta";
        trie.insert(s, 0);

        s = "abhish";
        trie.insert(s, 0);

        s = "abhi";
        trie.insert(s, 0);

        s = "shailja";
        trie.insert(s, 0);

        s = "shailu";
        trie.insert(s, 0);

        List<String> res = trie.search("abhi");
        System.out.println(res);
        res = trie.search("shail");
        System.out.println(res);

        String textInput = "nana loves banana ";
        int i = 0, j = i;
        while (i < textInput.length()) {
            StringBuilder suffix = new StringBuilder("");
            while (textInput.charAt(j) != ' ') {
                suffix.append(textInput.charAt(j));
                j++;
            }
            suffix.append(textInput.charAt(j));
            trie.insert(suffix.toString(), i);
            i++;
            j = i;
        }
        System.out.println(trie.searchOccur("nana"));
        System.out.println(trie.searchOccur("na"));
        System.out.println(trie.searchOccur("an"));
        System.out.println(trie.searchOccur("nanan"));
        System.out.println(trie.searchOccur("love"));
        System.out.println(trie.searchOccur("lov"));
        System.out.println(trie.searchOccur("loves"));
        System.out.println(trie.searchOccur("loves "));
        System.out.println(trie.searchOccur("loves b"));
    }


}
