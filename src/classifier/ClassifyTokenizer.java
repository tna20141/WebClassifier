package classifier;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: tna2
 * Date: 4/12/14
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassifyTokenizer extends Tokenizer {

    ClassifyTokenizer(java.io.Reader in) {
        super(in);
    }

    ClassifyTokenizer(java.io.InputStream in) {
        super(in);
    }

    @Override
    protected void foundWord() {
        Trainer trainer = Trainer.getInstance();
        HashMap<String, Integer> classifyData = trainer.getClassifyData();

        String word = Tokenizer.filter(yytext());
        if (word.equals(""))
            return;

        Integer count = classifyData.get(word);
        if (count == null) {
            classifyData.put(word, 1);
        } else
            classifyData.put(word, count+1);
    }
}
