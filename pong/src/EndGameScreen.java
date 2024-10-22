import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class EndGameScreen extends JFrame implements  Runnable{
    public Graphics2D g2;
    public KL keyListener;
    public Text endGameScreen, pressContinue;
    public boolean isRunning = true;
    public String message;

    public EndGameScreen(String message){
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.keyListener = new KL();
        this.addKeyListener(keyListener);
        this.message = message;
        this.endGameScreen = new Text(this.message, new Font("Silkscreen",Font.PLAIN,Constants.TITLE_TEXT_SIZE),Constants.SCREEN_WIDTH / 2.0 - 290.0, Constants.SCREEN_HEIGHT / 2.0, Color.WHITE);
        this.pressContinue = new Text("PRESS SPACE", new Font("Silkscreen",Font.PLAIN,25), Constants.SCREEN_WIDTH / 2.0 - 110.0, Constants.SCREEN_HEIGHT / 1.7, Color.WHITE);

        g2 = (Graphics2D)getGraphics();
    }

    public void update(double dt){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2 = (Graphics2D) this.getGraphics();
        g2.drawImage(dbImage, 0, 0, this);

        if(keyListener != null){
            if (keyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
                changeScreen();
            }
        }
    }

    public void changeScreen(){
        Main.changeState(0, "");
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        endGameScreen.draw(g2);
        pressContinue.draw(g2);
    }

    public void stop(){
        isRunning = false;
    }

    public void run() {
        double lastFrameTime = 0.0;
        double FPS = 60.0;
        double frameTime = 1.0 / FPS;

        while (isRunning) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;

            if (deltaTime >= frameTime) {
                lastFrameTime = time;
                update(deltaTime);
            }

        }
        this.dispose();
    }
}
