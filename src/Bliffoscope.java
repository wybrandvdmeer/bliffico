import java.util.List;

public class Bliffoscope {

    private static Searcher searcher = new Searcher();

    public List<Sighting> search(String data, String target, double chance) throws Exception {
        Image dataImage = new Image(data);
        Image targetImage = new Image(target);
        return searcher.search(dataImage, targetImage, chance);
    }
}
