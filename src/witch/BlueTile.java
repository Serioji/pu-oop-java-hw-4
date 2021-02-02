package witch;

import java.awt.*;

public class BlueTile extends Tile{
    public BlueTile(int row, int col , Color color) {
        this.color = color;
        this.row = row;
        this.col = col;
    }
    public void render(Graphics g) {
        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;
        g.setColor(Color.BLUE);
        g.fillRect(x+10,y+33, 48, 48);
    }

}
