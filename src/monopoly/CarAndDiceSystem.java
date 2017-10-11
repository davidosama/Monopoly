/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CarAndDiceSystem {

    public void GenerateDiceAndMove() {
        Random rand = new Random();
        
        int d1 = rand.nextInt(6) + 1;

        int d2 = rand.nextInt(6) + 1;

        //check if d1 == d2 to play again
        int res = d1 + d2;

        //Constants.window.getjTextArea1().setText("d1: " + d1 + "\nd2: " + d2);

        //load dice image
        ImageIcon icon = loadImageOfDice(d1);
        Constants.window.get_d1_label().setIcon(icon);

        icon = loadImageOfDice(d2);
        Constants.window.get_d2_label().setIcon(icon);

        //FOR TESTING
        MoveCarNCities(Monopoly.p1, res);
    }
    
     public ImageIcon loadImageOfDice(int dice)
    {
        switch(dice){
            case 1: 
                return new javax.swing.ImageIcon(getClass().getResource("/drawables/1.png"));
            case 2:
                return new javax.swing.ImageIcon(getClass().getResource("/drawables/2.png"));
            case 3:
                return new javax.swing.ImageIcon(getClass().getResource("/drawables/3.png"));
            case 4:
                return new javax.swing.ImageIcon(getClass().getResource("/drawables/4.png"));
            case 5:
                return new javax.swing.ImageIcon(getClass().getResource("/drawables/5.png"));
            case 6:
                return new javax.swing.ImageIcon(getClass().getResource("/drawables/6.png"));
            default :
                System.out.println("SOMETHING IS WRONG IN THE loadImageOfDice");
        }
        return new ImageIcon();
    }

    public void MoveCarNCities(Player player, int movesNum) {

        JLabel playerJlbl = player.label;
        player.currentCity += movesNum;
        player.currentCity = player.currentCity % 40;

        int CityNo = player.currentCity;

        //Setting car icon base on position (Right,Left,Up..)
        if (CityNo == 0 || CityNo == 1 || CityNo == 2 || CityNo == 3 || CityNo == 4 || CityNo == 5
                || CityNo == 6 || CityNo == 7 || CityNo == 8 || CityNo == 9) {
            playerJlbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/CarLeft.png")));
        } else if (CityNo == 10 || CityNo == 11 || CityNo == 12 || CityNo == 13 || CityNo == 14 || CityNo == 15
                || CityNo == 16 || CityNo == 17 || CityNo == 18 || CityNo == 19) {
            playerJlbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/CarUP.png")));
        } else if (CityNo == 20 || CityNo == 21 || CityNo == 22 || CityNo == 23 || CityNo == 24 || CityNo == 25
                || CityNo == 26 || CityNo == 27 || CityNo == 28 || CityNo == 29) {
            playerJlbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/CarRight.png")));
        } else if (CityNo == 30 || CityNo == 31 || CityNo == 32 || CityNo == 33 || CityNo == 34 || CityNo == 35
                || CityNo == 36 || CityNo == 37 || CityNo == 38 || CityNo == 39) {
            playerJlbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/CarDown.png")));
        }

        switch (CityNo) {
            //bottom cities
            case 1:
                playerJlbl.setBounds(570, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 2:
                playerJlbl.setBounds(510, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 3:
                playerJlbl.setBounds(460, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 4:
                playerJlbl.setBounds(400, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 5:
                playerJlbl.setBounds(340, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 6:
                playerJlbl.setBounds(290, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 7:
                playerJlbl.setBounds(230, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 8:
                playerJlbl.setBounds(180, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 9:
                playerJlbl.setBounds(120, 620, Constants.CarDim, Constants.CarDim);
                break;
            //left cities
            case 10:
                playerJlbl.setBounds(60, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 11:
                playerJlbl.setBounds(60, 560, Constants.CarDim, Constants.CarDim);
                break;
            case 12:
                playerJlbl.setBounds(60, 500, Constants.CarDim, Constants.CarDim);
                break;
            case 13:
                playerJlbl.setBounds(60, 450, Constants.CarDim, Constants.CarDim);
                break;
            case 14:
                playerJlbl.setBounds(60, 390, Constants.CarDim, Constants.CarDim);
                break;
            case 15:
                playerJlbl.setBounds(60, 340, Constants.CarDim, Constants.CarDim);
                break;
            case 16:
                playerJlbl.setBounds(60, 280, Constants.CarDim, Constants.CarDim);
                break;
            case 17:
                playerJlbl.setBounds(60, 220, Constants.CarDim, Constants.CarDim);
                break;
            case 18:
                playerJlbl.setBounds(60, 170, Constants.CarDim, Constants.CarDim);
                break;
            case 19:
                playerJlbl.setBounds(60, 110, Constants.CarDim, Constants.CarDim);
                break;
            case 20:
                playerJlbl.setBounds(60, 50, Constants.CarDim, Constants.CarDim);
                break;
            //UP cities
            case 21:
                playerJlbl.setBounds(120, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 22:
                playerJlbl.setBounds(180, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 23:
                playerJlbl.setBounds(230, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 24:
                playerJlbl.setBounds(290, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 25:
                playerJlbl.setBounds(340, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 26:
                playerJlbl.setBounds(400, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 27:
                playerJlbl.setBounds(460, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 28:
                playerJlbl.setBounds(510, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 29:
                playerJlbl.setBounds(570, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 30:
                playerJlbl.setBounds(640, 50, Constants.CarDim, Constants.CarDim);
                break;
            //Right cities
            case 31:
                playerJlbl.setBounds(640, 110, Constants.CarDim, Constants.CarDim);
                break;
            case 32:
                playerJlbl.setBounds(640, 170, Constants.CarDim, Constants.CarDim);
                break;
            case 33:
                playerJlbl.setBounds(640, 220, Constants.CarDim, Constants.CarDim);
                break;
            case 34:
                playerJlbl.setBounds(640, 280, Constants.CarDim, Constants.CarDim);
                break;
            case 35:
                playerJlbl.setBounds(640, 340, Constants.CarDim, Constants.CarDim);
                break;
            case 36:
                playerJlbl.setBounds(640, 390, Constants.CarDim, Constants.CarDim);
                break;
            case 37:
                playerJlbl.setBounds(640, 450, Constants.CarDim, Constants.CarDim);
                break;
            case 38:
                playerJlbl.setBounds(640, 500, Constants.CarDim, Constants.CarDim);
                break;
            case 39:
                playerJlbl.setBounds(640, 560, Constants.CarDim, Constants.CarDim);
                break;
            case 0:
                playerJlbl.setBounds(640, 620, Constants.CarDim, Constants.CarDim);
                break;
            default:
                break;
        }
    }
}
