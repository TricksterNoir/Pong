import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectDifficulty extends JFrame implements  Runnable{
    public Graphics2D g2;
    public KL keyListener;
    public ML mouseListener = new ML();
    public Text easyText, mediumText, hardText, difficultText;
    public boolean isRunning = true;

    public SelectDifficulty(){
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.keyListener = new KL();
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.difficultText = new Text("SELECT DIFFICULTY",new Font("Silkscreen",Font.PLAIN,Constants.TITLE_TEXT_SIZE), Constants.SCREEN_WIDTH / 2.0 - 387.0,Constants.SCREEN_HEIGHT / 2.0 - 150.0, Color.WHITE);
        this.easyText = new Text("EASY",new Font("Silkscreen",Font.PLAIN,Constants.TITLE_TEXT_SIZE), Constants.SCREEN_WIDTH / 2.0 - 120.0,Constants.SCREEN_HEIGHT / 2.0 - 25.0, Color.WHITE);
        this.mediumText = new Text("MEDIUM",new Font("Silkscreen",Font.PLAIN,Constants.TITLE_TEXT_SIZE), Constants.SCREEN_WIDTH / 2.0 - 170.0,Constants.SCREEN_HEIGHT / 2.0 + 40.0, Color.WHITE);
        this.hardText = new Text("HARD",new Font("Silkscreen",Font.PLAIN,Constants.TITLE_TEXT_SIZE), Constants.SCREEN_WIDTH / 2.0 - 120.0,Constants.SCREEN_HEIGHT / 2.0 + 105.0, Color.WHITE);

        g2 = (Graphics2D)getGraphics();
    }

    public void update(double dt) {
        BufferedImage dbImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2 = (Graphics2D) this.getGraphics();
        g2.drawImage(dbImage, 0, 0, this);

        if(mouseListener.getMouseX() > easyText.x && mouseListener.getMouseX() < easyText.x + easyText.width &&
                mouseListener.getMouseY() > easyText.y - easyText.height / 2.0 && mouseListener.getMouseY() < easyText.y + easyText.height / 2.0){
            easyText.color = new Color(158,158,158);

            if(mouseListener.isMousePressed()){
                Constants.AI_PADDLE_SPEED = Constants.EASY_SPEED;
                Main.changeState(1, "");
            }
        }else{
            easyText.color = Color.WHITE;
        }

        if(mouseListener.getMouseX() > mediumText.x && mouseListener.getMouseX() < mediumText.x + mediumText.width &&
                mouseListener.getMouseY() > mediumText.y - mediumText.height / 2.0 && mouseListener.getMouseY() < mediumText.y + mediumText.height / 2.0){
            mediumText.color = new Color(158,158,158);
            if(mouseListener.isMousePressed()){
                Constants.AI_PADDLE_SPEED = Constants.MEDIUM_SPEED;
                Main.changeState(1, "");

            }
        }else{
            mediumText.color = Color.WHITE;
        }

        if(mouseListener.getMouseX() > hardText.x && mouseListener.getMouseX() < hardText.x + hardText.width &&
                mouseListener.getMouseY() > hardText.y - hardText.height / 2.0 && mouseListener.getMouseY() < hardText.y + hardText.height / 2.0){
            hardText.color = new Color(158,158,158);
            if(mouseListener.isMousePressed()){
                Constants.AI_PADDLE_SPEED = Constants.HARD_SPEED;
                Main.changeState(1, "");
            }
        }else{
            hardText.color = Color.WHITE;
        }

    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        difficultText.draw(g2);
        easyText.draw(g2);
        mediumText.draw(g2);
        hardText.draw(g2);

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
        return;
    }
}
