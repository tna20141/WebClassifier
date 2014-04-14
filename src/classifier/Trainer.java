package classifier;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created with IntelliJ IDEA.
 * User: tna2
 * Date: 4/12/14
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class Trainer {

    private static Trainer instance;

    private static double INFINITY = 999999999.0;

    private ArrayList<String> categories;

    private HashMap<String, ArrayList<Integer>> index;
    private HashMap<String, ArrayList<Double>> wordProbabilities;

    private ArrayList<Integer> categoryCounts;
    private ArrayList<Double> categoryProbabilities;

    private String configFile;

    private ArrayList<String> trainData;

    private ArrayList<String> testData;

    private HashMap<String, Integer> classifyData;

    private boolean initialized;

    private boolean trained;


    public Trainer() {
        this.initialized = false;
        this.trained = false;
    }

    public static Trainer getInstance() {
        if (Trainer.instance == null)
            Trainer.instance = new Trainer();
        return Trainer.instance;
    }

    public synchronized void init(String configFile) throws Exception {
        this.initialized = false;
        this.trained = false;

        this.categories = new ArrayList<String>();
        this.index = new HashMap<String, ArrayList<Integer>>();
        this.categoryCounts = new ArrayList<Integer>();
        this.trainData = new ArrayList<String>();
        this.testData = new ArrayList<String>();

        this.configFile = configFile;

        parse(this.configFile);

        this.initialized = true;

    }

    public HashMap<String, ArrayList<Integer>> getIndex() {
        return this.index;
    }

    public ArrayList<Integer> getCategoryCounts() {
        return this.categoryCounts;
    }

    public HashMap<String, Integer> getClassifyData() {
        return this.classifyData;
    }

    public boolean initialized() {
        return this.initialized;
    }

    public boolean trained() {
        return this.trained;
    }

    public String getCategoryLabel(int category) {
        return this.categories.get(category);
    }

    public synchronized void train() throws Exception {

        for (String train : this.trainData)
            for (int i = 0; i < this.categories.size(); i++) {
                File dir = new File(train + "/" + this.categories.get(i));
                if (!dir.isDirectory())
                    continue;

                for (File file : dir.listFiles())
                    if (file.isFile())
                        (new TrainTokenizer(new FileReader(file), i)).parse();
            }

        calculateProbabilitiesFromData();

        this.trained = true;

    }

    public synchronized int classify(File file) throws Exception {
        int res = classifyNoSync(file);
        return res;
    }

    private int classifyNoSync(File file) throws Exception {
        this.classifyData = new HashMap<String, Integer>();
        (new ClassifyTokenizer(new FileReader(file))).parse();

        int maxCategory = 1;
        double maxProbability = -INFINITY;

        for (int i = 0; i < this.categories.size(); i++) {
            double probability = bayesP(i);
            if (maxProbability < probability) {
                maxProbability = probability;
                maxCategory = i;
            }
        }
        return maxCategory;
    }

    public synchronized double test() throws Exception {
        int total = 0;
        int accurateClassifications = 0;
        for (String test : this.testData)
            for (int i = 0; i < this.categories.size(); i++) {
                File dir = new File(test + "/" + this.categories.get(i));
                if (!dir.isDirectory())
                    continue;

                for (File file : dir.listFiles())
                    if (file.isFile()) {
                        total++;
                        int res = classifyNoSync(file);
                        if (res == i)
                            accurateClassifications++;
                    }
            }
        double result = (new Integer(accurateClassifications)).doubleValue() /
                (new Integer(total)).doubleValue();

        return result;
    }

    private void calculateProbabilitiesFromData() {
        this.categoryProbabilities = new ArrayList<Double>();
        int total = 0;
        for (int i = 0; i < this.categories.size(); i++)
            total += this.categoryCounts.get(i);
        for (int i = 0; i < this.categories.size(); i++)
            this.categoryProbabilities.add(Math.log(
                    this.categoryCounts.get(i).doubleValue() / (new Integer(total)).doubleValue()));

        int wordCount = this.index.size();

        this.wordProbabilities = new HashMap<String, ArrayList<Double>>();
        for (String word : this.index.keySet()) {
            ArrayList<Integer> counts = this.index.get(word);
            ArrayList<Double> probList = new ArrayList<Double>();
            this.wordProbabilities.put(word, probList);
            for (int i = 0; i < this.categories.size(); i++) {
                double prob = (counts.get(i).doubleValue()+1.0) /
                        (this.categoryCounts.get(i).doubleValue()+(double)wordCount);
                probList.add(Math.log(prob));
            }
        }
    }

    private double bayesP(int category) {
        double result = 0.0;
        for (String word : this.classifyData.keySet()) {
            ArrayList<Double> probList = this.wordProbabilities.get(word);
            if (probList == null)
                continue;

            double wordProb = probList.get(category);
            double addition = wordProb;
//            addition *= Math.log(this.classifyData.get(word));
            result += addition;
        }
        result += this.categoryProbabilities.get(category);

        return result;
    }

    private void parse(String configFile) throws Exception {
        File xmlFile = new File(configFile);
        String dir = xmlFile.getParent();
        int count;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        NodeList categories = doc.getElementsByTagName("categories");

        if (categories.getLength() != 1)
            throw new Exception("File cấu hình sai: số lượng phần tử categories không đúng");

        NodeList categoryNodes = categories.item(0).getChildNodes();

        count = 0;
        for (int i = 0; i < categoryNodes.getLength(); i++) {
            if (categoryNodes.item(i).getNodeType() == Node.TEXT_NODE)
                continue;

            this.categories.add(categoryNodes.item(i).getNodeName());
            this.categoryCounts.add(0);
            count++;
        }

        if (count < 1)
            throw new Exception("File cấu hình sai: không tìm thấy nhãn phân loại");

        NodeList trainingData = doc.getElementsByTagName("train-data");

        if (trainingData.getLength() != 1)
            throw new Exception("File cấu hình sai: số lượng phần tử train-data không đúng");

        NodeList trainingDataNodes = trainingData.item(0).getChildNodes();

        count = 0;
        for (int i = 0; i < trainingDataNodes.getLength(); i++) {
            if (trainingDataNodes.item(i).getNodeType() == Node.TEXT_NODE)
                continue;

            String trainDir = trainingDataNodes.item(i).getNodeName();
            if (!(new File(dir + "/" + trainDir)).isDirectory())
                throw new Exception("Không tìm thấy thư mục luyện " + trainDir);
            this.trainData.add(dir + "/" + trainDir);
            count++;
        }

        if (count < 1)
            throw new Exception("File cấu hình sai: không tìm thấy tập luyện");

        NodeList testData = doc.getElementsByTagName("test-data");

        if (testData.getLength() != 1)
            throw new Exception("File cấu hình sai: số lượng phần tử test-data không đúng");

        NodeList testDataNodes = testData.item(0).getChildNodes();

        for (int i = 0; i < testDataNodes.getLength(); i++) {
            if (testDataNodes.item(i).getNodeType() == Node.TEXT_NODE)
                continue;

            String testDir = testDataNodes.item(i).getNodeName();
            if (!(new File(dir + "/" + testDir)).isDirectory())
                throw new Exception("Không tìm thấy thư mục test " + testDir);
            this.testData.add(dir + "/" + testDir);
        }
    }
}
