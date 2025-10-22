public class ransomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] freqMap1 = getFreqMap(ransomNote);
        int[] freqMap2 = getFreqMap(magazine);

        for(int i=0; i<26; i++) {
            if(freqMap2[i] < freqMap1[i]) return false;
        }

        return true;
    }

    private int[] getFreqMap(String s) {
        int[] freq = new int[26];

        for(char c: s.toCharArray()) {
            freq[c-'a']++;
        }

        return freq;
    }
}
