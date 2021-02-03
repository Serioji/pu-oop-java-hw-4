package witch;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard extends JFrame implements MouseListener {
    public static final int TILE_SIDE_COUNT = 8;
    protected BlueTile[][] blueTile;
    protected GPSstart[][] gpsStart1;
    protected WitchHouseGPS[][] house;
    private GPSstart gpsStart;
    int randomNumber1;
    int randomNumber2;
    int firstCol,firstRow;
    private JButton Restart;


    public GameBoard() {
    this.gpsStart1 = new GPSstart[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
    randomNumber1 = ThreadLocalRandom.current().nextInt(1, 5);
    if(randomNumber1 == 1)
        this.gpsStart1[0][0] = new GPSstart(0,0,Color.GREEN);
        if(randomNumber1 == 2)
            this.gpsStart1[0][7] = new GPSstart(0,7,Color.GREEN);
        if(randomNumber1 == 3)
            this.gpsStart1[7][0] = new GPSstart(7,0,Color.GREEN);
        if(randomNumber1 == 4)
            this.gpsStart1[7][7] = new GPSstart(7,7,Color.GREEN);

        this.blueTile = new BlueTile[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        for(int blueCount = 5; blueCount>0; blueCount--) {
            randomNumber1 = ThreadLocalRandom.current().nextInt(0, 8);
            randomNumber2 = ThreadLocalRandom.current().nextInt(0, 8);
            if(this.gpsStart1[randomNumber1][randomNumber2] ==null&&this.blueTile[randomNumber1][randomNumber2]==null){
                this.blueTile[randomNumber1][randomNumber2] = new BlueTile(randomNumber1,randomNumber2,Color.blue);
                blueCount--;
            }
            blueCount++;

        }
        this.house= new WitchHouseGPS[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        for(int pinkCount = 9; pinkCount>0;pinkCount--){
            randomNumber1 = ThreadLocalRandom.current().nextInt(0, 8);
            randomNumber2 = ThreadLocalRandom.current().nextInt(0, 8);
            if(this.gpsStart1[randomNumber1][randomNumber2] ==null&&this.blueTile[randomNumber1][randomNumber2]==null&&this.house[randomNumber1][randomNumber2]==null){
                this.house[randomNumber1][randomNumber2] = new WitchHouseGPS(randomNumber1,randomNumber2,Color.pink);
                pinkCount--;
            }
            pinkCount++;
        }

        this.setSize(800, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addMouseListener(this);

    }

    @Override
    public void paint(Graphics g) {
        /**
         *
         * @author Vasil
         * @param "визуализиране на игралните пешки върху бойното поле чрез два for цикъла и повикване на точната им позиция чрез row,col"
         */

        super.paint(g);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                this.renderGameTile(g, row, col);

            }
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                this.renderGamePice(g,row,col);

            }
        }
    }

    private Color getTileColor(int row, int col) {

        return Color.ORANGE;
    }

    private void renderGameTile(Graphics g, int row, int col) {
        Color tileColor = this.getTileColor(row, col);
        GameTile tile = new GameTile(row, col, tileColor);
        tile.render(g);
    }
    private void renderGamePice(Graphics g, int row, int col) {
        if (this.hasBoardGPS(row, col)) {
            GPSstart p = (GPSstart) this.getBoardGPS(row, col);
            p.render(g);

        }
        if (this.hasBoardBlue(row, col)) {
            BlueTile p1 = (BlueTile) this.getBoardBlue(row, col);
            p1.render(g);

        }
        if (this.hasBoardWitch(row,col)){
            WitchHouseGPS p2 = (WitchHouseGPS) this.getBoardWitch(row,col);
            p2.render(g);
        }
    }
    private int getBoardDimensionBasedOnCoordinates(int coordinates) {
        return coordinates / GameTile.TILE_SIZE;
    }
    private GPSstart getBoardGPS(int row, int col) {
        return this.gpsStart1[row][col];
    }
    private boolean hasBoardGPS(int row, int col) {
        return this.getBoardGPS(row, col) != null;
    }
    private BlueTile getBoardBlue(int row, int col) {
        return this.blueTile[row][col];
    }
    private boolean hasBoardBlue(int row, int col) {
        return this.getBoardBlue(row, col) != null;
    }
    private WitchHouseGPS getBoardWitch(int row, int col) {
        return this.house[row][col];
    }
    private boolean hasBoardWitch(int row, int col) {
        return this.getBoardWitch(row, col) != null;
    }


        @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimensionBasedOnCoordinates(e.getX());
        row--;
        if (row < 8 && col < 8) {
            if (this.gpsStart != null) {
                GPSstart p1 = (GPSstart) this.gpsStart;
                if (this.hasBoardGPS(row, col)) {
                    UI.render(this, "Грешка", "Грешен ход");

                } else if (p1.isMoveValid(row, col)) {
                    moveGps(row, col, p1);
                    this.repaint();
                    if (hasBoardWitch(row, col)) {
                        UI.render(this, "Победа", "Победа");
                        System.exit(1);
                    }
                    if(this.hasBoardBlue(row,col))  {
                        UI.render(this, "Грешка", "Невадлен ход");

                    }

                }
            }
            else
                if (this.hasBoardGPS(row, col))
                    this.gpsStart = this.getBoardGPS(row, col);

        }
    }

    private void moveGps(int row, int col, GPSstart p1) {
        int initialRow = p1.getRow();
        int initialCol = p1.getCol();

        p1.move(row, col);

        this.gpsStart1[p1.getRow()][p1.getCol()] = this.gpsStart;
        this.gpsStart1[initialRow][initialCol] = null;

        this.gpsStart = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

