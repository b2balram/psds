package com.guru.psds.wordbreak;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class WordBreak {
    
    public boolean solveBasic(String s, List<String> wordDict) {
        return wordBreak(s, new HashSet<>(wordDict));
    }

    public boolean solveDP(String s, List<String> wordDict) {
        return wordBreak(s, new HashSet<>(wordDict), new HashMap<>());
    }

    private boolean wordBreak(String s, Set<String> wordSet){
        int len = s.length();
        boolean[] dp = new boolean[len];
        for(int i=0; i<len; i++){
            if(wordSet.contains(s.substring(0, i+1))){
                dp[i] = true;
            } else {
                for(int j=i; j>0; j--){
                    String s1 = s.substring(j, i+1);
                    if(j>0 && dp[j-1] && wordSet.contains(s1)){
                        dp[i] = true;
                        break;
                    }
                    if(j==0){
                        dp[i] = wordSet.contains(s1);
                    }
                }
            }
        }
        return dp[len-1]; 
    }

    private boolean wordBreak(String s, Set<String> wordSet, Map<String, Boolean> memo){

        if(memo.containsKey(s)) return memo.get(s);

        if(wordSet.contains(s)){
            memo.put(s, true);
            return true;
        }

        int len = s.length();

        for(int i=1; i<len; i++){
            String s1 = s.substring(0, i);
            String s2 = s.substring(i, len);

            int c=0;
            if(wordBreak(s1, wordSet, memo)){
                memo.put(s1, true);
                c++;
            } else {
                memo.put(s1, false);
            } if(wordBreak(s2, wordSet, memo)){
                memo.put(s2, true);
                c++;
            } else {
                memo.put(s2, false);
            }

            if(c==2){
                return true;
            }
        }
        return false;
    }
}
