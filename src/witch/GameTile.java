package witch;

import java.awt.*;

public class GameTile {
    public static final int TILE_SIZE = 50;
    private int row;
    private int col;
    private int tileSize;
    private Color color;
    /**
     *
     * @author Vasil
     * @param "това е конструктора за големината на ходните полета и инициализирането им на бойното поле"
     */
    public GameTile(int row, int col, Color color) {

        this.row = row;
        this.col = col;
        this.tileSize = 50;
        this.color=color;
    }
    public void render(Graphics g) {
        /**
         *
         * @author Vasil
         * @param "това е визуализирането на бойното поле със съответния му цвят и параметри за изчисляването на големината му"
         */
        int tileX = this.col * this.tileSize;
        int tileY = this.row * this.tileSize;

        g.setColor(this.color);
        g.fillRect(tileX+10, tileY+31, TILE_SIZE, TILE_SIZE);
        g.setColor(Color.BLACK);
        for (int i=31; i<=431 ;i+=50){
            for(int j=8;j<=408;j+=50){
                if(i<=381&&j<=358) {
                    g.fillRect(j, i, 2, 50);
                    g.fillRect(j, i, 50, 2);
                }  if(i==431&&j<408){
                    g.fillRect(j, i, 50, 2);
                }
                if(i<431&&j==408){
                    g.fillRect(j, i, 2, 52);
                }

            }
        }


    }


}
