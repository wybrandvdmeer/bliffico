import java.util.ArrayList;
import java.util.List;

class Searcher {

    List<Sighting> search(Image data, Image target, double chance) throws Exception {

        /* Data image should be larger then target image.
        */
        if(data.getXSize() < target.getXSize() || data.getYSize() < target.getYSize() ) {
            throw new Exception("Object image should be greater then the data image.");
        }

        /* Resize data image: Extend both size of x axis with half of xsize object image.
        Do the same with the y size of the data image
        */
        data.resize(data.getXSize() + target.getXSize(), data.getYSize() + target.getYSize());

        List<Sighting> sightings = new ArrayList<>();

        for(int y=0; y <= data.getYSize() - target.getYSize(); y++) {
            for(int x=0; x <= data.getXSize() - target.getXSize(); x++) {

                List<Pair> pairs = new ArrayList<>();

                for(int y1=0; y1 < target.getYSize(); y1++) {
                    for(int x1=0; x1 < target.getXSize(); x1++) {
                        pairs.add(new Pair(new Coordinate(x1,y1), new Coordinate(x+x1, y+y1)));
                    }
                }

                /* Match the images.
                */
                int matchedPairs=0;

                for(Pair pair : pairs) {
                    if((data.getByte(pair.getDataCoordinate())^target.getByte(pair.getObjectCoordinate())) == 0) {
                        matchedPairs++;
                    }
                }

                if((double)matchedPairs/pairs.size() >= chance) {
                    sightings.add(new Sighting((double)matchedPairs/pairs.size(), new Coordinate(x,y)));
                }
            }
        }

        return sightings;
    }

    private class Pair {
        private Coordinate objectCoordinate, dataCoordinate;

        Pair(Coordinate objectCoordinate, Coordinate dataCoordinate) {
            this.objectCoordinate = objectCoordinate;
            this.dataCoordinate = dataCoordinate;
        }

        Coordinate getObjectCoordinate() {
            return objectCoordinate;
        }

        Coordinate getDataCoordinate() {
            return dataCoordinate;
        }
    }
}
