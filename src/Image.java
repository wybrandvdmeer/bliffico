import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Image {

    private List<byte[]> lines = new ArrayList<byte[]>();

    private int xsize;

    public Image(String name) throws Exception {

        FileInputStream fis;
        DataInputStream in;
        BufferedReader br = null;

        int maxLineLength=0;

        try {
            fis = new FileInputStream(name);
            in = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(in));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.length() > maxLineLength) {
                    maxLineLength = line.length();
                }

                byte[] bytes = new byte[line.length()];

                for (int idx = 0; idx < line.toCharArray().length; idx++) {
                    switch (line.toCharArray()[idx]) {
                        case '+':
                            bytes[idx] = 1;
                            break;

                        case ' ':
                            bytes[idx] = 0;
                            break;

                        default:
                            throw new Exception(String.format("Unknown char: %c\n", line.toCharArray()[idx]));
                    }
                }

                lines.add(bytes);
            }
        } finally {
            try {
                br.close();
            } catch (Exception e) {
            }
        }

        for (int idx=0; idx < lines.size(); idx++) {
            if(maxLineLength > lines.get(idx).length) {
                lines.set(idx, Arrays.copyOf(lines.get(idx), maxLineLength));
            }
        }

        xsize = maxLineLength;
    }

    public int getXSize() {
        return xsize;
    }

    public int getYSize() {
        return lines.size();
    }

    public byte getByte(int x, int y) {
        return lines.get(y)[x];
    }

    public byte getByte(Coordinate c) throws Exception {

        if(c.getX() >= getXSize() || c.getY() >= getYSize()) {
            throw new Exception(String.format("Coordinate %s is outside the bounderies of the picture.\n", c));
        }
        return getByte(c.getX(), c.getY());
    }

    public void resize(int newXSize, int newYSize) throws Exception {

        if ((newXSize - xsize) % 2 == 1) {
            newXSize++;
        }

        if ((newYSize - getYSize()) % 2 == 1) {
            newYSize++;
        }

        for (int idx = 0; idx < lines.size(); idx++) {
            byte[] newLine = new byte[newXSize];

            System.arraycopy(lines.get(idx), 0, newLine, (newXSize - getXSize()) / 2, getXSize());

            lines.remove(idx);
            lines.add(idx, newLine);
        }

        int delta = (newYSize - getYSize()) / 2;

        for (int idx = 0; idx < delta; idx++) {
            lines.add(0, new byte[newXSize]);
        }

        for (int idx = 0; idx < delta; idx++) {
            lines.add(new byte[newXSize]);
        }

        xsize = newXSize;
    }
}
