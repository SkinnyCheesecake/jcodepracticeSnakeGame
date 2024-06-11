import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList.*;
import javax.swing.*;

//Create a game loop to contantly redraw the frames

public class SnakeGame extends JPanel implements ActionListener, KeyListener{
    private class Tail{
        int x;
        int y;

        Tail(int x, int y){
            this.x = y;
            this.y = x;
        }
    }
    int boardWidth;
    int boardHeigth;
    int tailSize = 25;

    //Snake
    Tail snakeHead;

    //Food
    Tail snakeFood;

    //Random
    Random random;

    //Game Logic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    //ArrayList
    ArrayList<Tail> snakeBody;

    SnakeGame(int boardHeigth, int boardWidth){
        this.boardHeigth = boardWidth;
        this.boardWidth = boardHeigth;

        setPreferredSize(new Dimension(boardHeigth, boardWidth));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tail(5, 5);

        snakeFood = new Tail(10, 10);

        snakeBody = new ArrayList<Tail>();

        random = new Random();

        gameLoop = new Timer(100, this);
        gameLoop.start();
        placeFood();

        velocityX = 0;
        velocityY = 1;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //GRID

        // for(int i = 0; i < boardHeigth/tailSize; i++){
        //     g.drawLine(i*tailSize, 0, i*tailSize, boardHeigth);
        //     g.drawLine(0, i*tailSize, boardWidth, i*tailSize);
        // }


        //FOOD COLOR
        g.setColor(Color.RED);
        g.fillRect(snakeFood.x * tailSize, snakeFood.y * tailSize, tailSize, tailSize);
        // g.fill3DRect(snakeFood.x * tailSize, snakeFood.y * tailSize, tailSize, tailSize, true);

        //SNAKE'S HEAD
        g.setColor(Color.BLUE);
        // g.fillRect(snakeHead.x * tailSize, snakeHead.y * tailSize, tailSize, tailSize);
        g.fill3DRect(snakeHead.x * tailSize, snakeHead.y * tailSize, tailSize, tailSize, true);

        //SNAKE'S BODY
            for(int i = 0; i < snakeBody.size(); i++){
                Tail snakePart = snakeBody.get(i);
                // g.fillRect(snakePart.x * tailSize, snakePart.y * tailSize, tailSize, tailSize);
                g.fill3DRect(snakePart.x * tailSize, snakePart.y * tailSize, tailSize, tailSize, true);
            }

        //Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if(gameOver){
            g.setColor(Color.RED);
            g.drawString("Gave Over: " + String.valueOf(snakeBody.size()), tailSize -16, tailSize);
        }
        else{
            g.drawString("Score: " + String.valueOf(snakeBody.size()), tailSize - 16, tailSize);
        }
    }

    public void placeFood(){
        snakeFood.x = random.nextInt(boardWidth/tailSize); //It'll take the value of the Width and divide it by the tile size, so the food will appear in a randon position from 1 to 24, because 600/25 = 24

        snakeFood.y = random.nextInt(boardHeigth/tailSize); //Same here
    }


    public void move(){
        //Eating the food
        if(colision(snakeHead, snakeFood)){
            snakeBody.add(new Tail(snakeFood.x, snakeFood.y));
            placeFood();
        }

        //Snake's Body
        for (int i = snakeBody.size()-1; i >= 0; i--) {
            Tail snakePart = snakeBody.get(i);
            if (i == 0) { //right before the head
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tail prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

                //SnakeHead
                snakeHead.x += velocityX;
                snakeHead.y += velocityY;

        for(int i = 0; i < snakeBody.size(); i++){
            Tail snakePart = snakeBody.get(i);

            if(colision(snakeHead, snakePart)){
                gameOver = true;
            }
        }

        if(snakeHead.x * tailSize < 0 || snakeHead.x * tailSize > boardWidth || snakeHead.y * tailSize < 0 || snakeHead.y * tailSize > boardHeigth){
            gameOver = true;
        }

    }

    public boolean colision(Tail tail1, Tail tail2){
        return tail1.x == tail2.x && tail1.y == tail2.y;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        move(); //First we move then we repaint
        repaint(); //Bascially calls 'Draw' over and over again
        if(gameOver){
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1){
            velocityX = 0;
            velocityY = -1;
        }

        else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1){
            velocityX = 0;
            velocityY = 1;
        }

        else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1){
            velocityX = -1;
            velocityY = 0;
        }

        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }
    }



    //We do not need any of these
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
