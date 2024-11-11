import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListener = new KL();
    public Rect playerOne, ai, ballRect;
    public Ball ball;
    public PlayerController playerController;
    public AiController aiController;
    public Text leftScoreText, rightScoreText, pauseGameText;
    public boolean isRunning = true;
    private static boolean gamePaused;
    private boolean escPressed = false;

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        Constants.TOOLBAR_HEIGHT_TOP = this.getInsets().top;
        Constants.TOOLBAR_HEIGHT_BOTTOM = this.getInsets().bottom;

        leftScoreText = new Text(0, new Font("Silkscreen", Font.PLAIN, Constants.TEXT_SIZE), Constants.TEXT_X_POS, Constants.TEXT_Y_POS);
        rightScoreText = new Text(0, new Font("Silkscreen", Font.PLAIN, Constants.TEXT_SIZE), Constants.SCREEN_WIDTH - Constants.TEXT_X_POS - Constants.TEXT_SIZE + 10, Constants.TEXT_Y_POS);
        pauseGameText = new Text("PAUSE", new Font("Silkscreen",Font.PLAIN,Constants.TITLE_TEXT_SIZE),Constants.SCREEN_WIDTH / 2.0 - 150.0, Constants.SCREEN_HEIGHT / 2.0, Color.WHITE);

        playerOne = new Rect(Constants.H2_PADDING, Constants.SCREEN_HEIGHT / 2.0, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        playerController = new PlayerController(playerOne, keyListener);

        ai = new Rect(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.H2_PADDING, Constants.H2_PADDING, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        ballRect = new Rect(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, Constants.BALL_WIDTH, Constants.BALL_WIDTH, Constants.PADDLE_COLOR);
        ball = new Ball(ballRect, playerOne, ai, leftScoreText, rightScoreText);
        aiController = new AiController(new PlayerController(ai), ballRect, ai);
    }

    public void update(double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            if (!escPressed) {
                Window.setGamePaused(!Window.isGamePaused());
                escPressed = true;
            }
        } else {
            escPressed = false;
        }

        if(!gamePaused) {
            Image dbImage = createImage(getWidth(), getHeight());
            Graphics dbg = dbImage.getGraphics();
            this.draw(dbg);
            g2 = (Graphics2D) this.getGraphics();
            g2.drawImage(dbImage, 0, 0, this);

            playerController.update(dt);
            aiController.update(dt);
            ball.update(dt);
        }else{
            pauseGameText.draw((g2));
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        leftScoreText.draw(g2);
        rightScoreText.draw(g2);
        playerOne.draw(g2);
        ai.draw(g2);
        ballRect.draw(g2);
    }

    public void stop(){
        isRunning = false;
    }

    public static void setGamePaused(boolean gamePaused){
        Window.gamePaused = gamePaused;
    }

    public static boolean isGamePaused(){
        return gamePaused;
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