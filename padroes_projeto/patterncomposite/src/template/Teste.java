package template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Teste {

    public static void main(String[] args){
        List<String> listaNomes = new ArrayList<>(Arrays.asList("Maria", "Joana", "Ana", "Kamila"));
        listaNomes.stream()
                .map((word)-> word.toUpperCase())
                .map((word) -> word + " " + word.charAt(0) + ". ")
                .map((word)-> {
                    int posicao = 0;
                    while (posicao < word.length()){
                        if (word.charAt(posicao) == ' '){
                            break;
                        }
                        posicao++;
                    }
                    String wordToReverse = word.substring(0, posicao);
                    StringBuilder wordReverse = new StringBuilder(wordToReverse);
                    wordReverse.reverse();
                    return(word + wordReverse.toString());
                })
                .filter((word) -> !(word.split(" ")[0].equals(word.split(" ")[2])))
                .forEach(System.out::println);
    }
}
