package classifier;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: tna2
 * Date: 4/12/14
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class TrainTokenizer extends Tokenizer {

    private int category;

    TrainTokenizer(java.io.Reader in, int category) {
        super(in);
        this.category = category;
    }

    TrainTokenizer(java.io.InputStream in, int category) {
        super(in);
        this.category = category;
    }

    @Override
    protected void foundWord() {
        Trainer trainer = Trainer.getInstance();
        HashMap<String, ArrayList<Integer>> index = trainer.getIndex();
        ArrayList<Integer> categoryCounts = trainer.getCategoryCounts();

        String word = Tokenizer.filter(yytext());
        if (word.equals(""))
            return;

        ArrayList<Integer> counts = index.get(word);
        if (counts == null) {
            counts = new ArrayList<Integer>();
            for (int i = 0; i < trainer.getCategoryCounts().size(); i++)
                counts.add(0);
            index.put(word, counts);
        }

        counts.set(category, counts.get(category)+1);
        categoryCounts.set(category, categoryCounts.get(category)+1);
    }
}
