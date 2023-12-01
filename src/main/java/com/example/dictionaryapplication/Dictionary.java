import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    private HashMap<String, Word> wordList;
    private Trie searchTree;

    Utility constants = new Utility();

    public void displayWord(Word word) { //hàm để show từ
        System.out.format("%-5s | %-15s | %-15s\n",
                1,
                word.getWord_target(),
                word.getWord_explain());
    }

    public Word lookup(String keyword) { //hàm tìm kiếm từ chính xác, giả dụ tìm từ air thì ra mỗi air thôi. O(1).
        if (wordList.containsKey(keyword)) {
            return wordList.get(keyword);
        }
        return constants.error1;
    }

    public ArrayList<String> search(String keyword) { //hàm này để kiếm từ, độ phức tạp cây tìm kiếm của Trie là O(m) với m là độ dài từ cần tìm
        ArrayList<String> result;
        result = searchTree.search(keyword);
        return result;
    }
    public Dictionary() { //hàm khởi tạo
        this.wordList = new HashMap<String, Word>();
        searchTree = new Trie();
    }

    public void addWord(Word word) { //hàm để thêm 1 từ vào wordlist, cần sửa 1 chút vì bây giờ đang dùng để đọc file, cần 1 hàm từ console.
//        System.out.println(word.getWord_target());
        String key = word.getWord_target().substring(0, word.getWord_target().indexOf(" /")); //1 cái word target sẽ bao gồm cả cách phát âm, nên phải cắt phần từ ra để làm key
        wordList.put(key, word);
    }

    public void clear() {
        wordList.clear();
    } // hàm xóa hết phần tử trong wordlist

    public int getSize() {
        return wordList.size();
    } //hàm trả về size của wordlist

    public void searchTreeTraversal() {
        searchTree.trieTraversal();
    } //hàm để duyệt cây Trie, dùng để debug
    public void readFromFile(String link) throws IOException { //hàm để đọc file
        BufferedReader bufferedReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(link);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String Line = bufferedReader.readLine();
            String wordTarget = "";
            String wordExplain = "";
            while (Line != null) {
                if (Line.length() == 0) {
                    final Word word = new Word(wordTarget, wordExplain);
                    this.addWord(word);
                    searchTree.insertTrieNode(wordTarget);
                    wordTarget = "";
                    wordExplain = "";
                }
                if (Line.startsWith("@")) {
                    wordTarget = Line.substring(1, Line.length());
                }
                if (Line.startsWith("*")) {
                    wordExplain += "\n" + "\t * " + Line.substring(3, Line.length());
                }
                if (Line.startsWith("-")) {
                    wordExplain += "\n\t" +  "\t + " + Line.substring(2, Line.length());
                }
                if (Line.startsWith("!")) {
                    wordExplain += "\n\t" + "\t(Idiom: )" + Line.substring(1, Line.length());
                }
                if (Line.startsWith("=")) {
                    Line = Line.replace("+", ":");
                    wordExplain += "\n\t" + "\t\t- " + Line.substring(1, Line.length());
                }
                Line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Something went wrong...");
        } catch (IOException ex) {
            throw new IOException("Some thing went wrong...");
        } catch (StringIndexOutOfBoundsException ex){
            System.out.println(ex);
        } finally {
            try {
                bufferedReader.close();
                fileInputStream.close();
                System.out.println("Done!, added " + wordList.size() + " words into hashmap");
                System.out.println("Added " + searchTree.getTrieSize() + " words into trie");

            } catch (IOException ex) {
                throw new IOException("Something went wrong...");
            }
        }
    }
    public Word getWord(String s) {
        return wordList.get(s);
    } // hàm để tra từ trong hashmap wordlist


}
