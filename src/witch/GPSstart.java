package witch;

import java.awt.*;

public class GPSstart extends Tile {
    public GPSstart(int row, int col ,Color color) {
        this.color = color;
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /**
     *
     * @author Vasil
     * @param "проверка дали хода е допустим или не"
     */
    public boolean isMoveValid(int moveRow, int moveCol) {

        int rowCoeficient = Math.abs(moveRow - this.row);
        int colCoeficient = moveCol - this.col;
        if(rowCoeficient<=1&&colCoeficient<=1&&rowCoeficient>-2&&colCoeficient>-2)
            return  true;
        return false;
    }
    /**
     *
     * @author Vasil
     * @param "рендериране на гарда с неговия outline като един клас"
     */
    public void render(Graphics g) {

        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;
            g.setColor(Color.CYAN);
            g.fillRect(x+10,y+33, 48, 48);
    }
    /**
     *
     * @author Vasil
     * @param "движение на гарда по матрицата"
     */
    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
