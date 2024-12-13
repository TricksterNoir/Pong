public class Main {

    public static int state = 0;
    public static Thread mainThread, gameController;
    public static MainMenu menu;
    public static Window window;
    public static SelectDifficulty difficulty;
    public static EndGameScreen endGameScreen;

    public static void main(String[] args) {
        menu = new MainMenu();

        mainThread = new Thread(menu);
        mainThread.start();
    }

    public static void changeState(int newState, String message){
        if(newState == 1 && state == 4){
            difficulty.stop();
            Sound.playBackgroundMusic("Backgroundmusic.wav");
            window = new Window();
            mainThread = new Thread(window);
            mainThread.start();
        } else if(newState == 0 && state == 1 || newState == 0 && state == 3) {
            window.stop();
            endGameScreen.stop();
            menu = new MainMenu();
            mainThread = new Thread(menu);
            mainThread.start();
        } else if(newState == 2){
            if(window != null)
                window.stop();
            if(menu != null)
                menu.stop();
        } else if(newState == 3){
            if(window != null){
                window.stop();
                Sound.stopSound();
                if(message == "YOU'VE WON!"){
                    Sound.generalSounds("VictorySound.wav");
                }else{
                    Sound.generalSounds("LosingSong.wav");
                }
                endGameScreen = new EndGameScreen(message);
                mainThread = new Thread(endGameScreen);
                mainThread.start();
            }
        }else if(newState == 4){
            menu.stop();
            difficulty = new SelectDifficulty();
            mainThread = new Thread(difficulty);
            mainThread.start();
        }
        state = newState;
    }

}