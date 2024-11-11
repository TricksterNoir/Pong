public class AiController {
    public PlayerController playerController;
    public Rect ball;
    public Rect rect;

    public AiController(PlayerController plrController, Rect ball, Rect rect) {
        this.playerController = plrController;
        this.ball = ball;
        this.rect = rect;
    }

    public void update(double dt) {
        playerController.update(dt);

        if (ball.y < playerController.rect.y) {
            if (rect.y - Constants.AI_PADDLE_SPEED * dt >= Constants.TOOLBAR_HEIGHT_TOP)
                this.rect.y -= Constants.AI_PADDLE_SPEED * dt;
        } else if (ball.y + ball.height > playerController.rect.y + playerController.rect.height) {
            if ((rect.y + Constants.AI_PADDLE_SPEED * dt) + rect.height <= Constants.SCREEN_HEIGHT - Constants.TOOLBAR_HEIGHT_BOTTOM)
                this.rect.y += Constants.AI_PADDLE_SPEED * dt;
        }
    }
}