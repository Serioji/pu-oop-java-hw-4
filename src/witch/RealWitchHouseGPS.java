package witch;

import java.awt.*;

public class RealWitchHouseGPS extends Tile {
    private final Color pink1 = new Color(128,0,128);
    public RealWitchHouseGPS(int row, int col , Color color) {
        this.color = color;
        this.row = row;
        this.col = col;
    }
    public void render(Graphics g) {
        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;
        g.setColor(pink1);
        g.fillRect(x+10,y+33, 48, 48);
    }


}
