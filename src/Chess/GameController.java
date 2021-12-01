package Chess;

import Chess.Enums.PlayerMoveTypes;

import javax.swing.*;
import java.awt.*;

public class GameController {
    PlayerMoveTypes currentMoveType = PlayerMoveTypes.WhiteSelectPiece;
    Player whitePlayer;
    Player blackPlayer;

    JButton selectedButton;
    Field selectedField;

    Timer timer;
    int lepesszam = 0;

    /**
     * A játék mechanikáért felel.
     *
     * @param whitePlayer Fehér játékos referenciája
     * @param blackPlayer Fekete játékos referenciája
     * @param timeMonitor Az idő mutató referenciája
     */
    public GameController(Player whitePlayer, Player blackPlayer, JLabel timeMonitor) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;

        timer = new Timer(timeMonitor);
        Thread timerThread = new Thread(timer);
        timerThread.start();
    }

    /**
     * Ha egy mezőre klikkelt a felhasználó, akkor aktiválódik.
     * Vagy kiválaszt egy bábut, vagy egy már kiválasztott bábut mozgat.
     * @param clickedField A mező értéke amire klikkkelt a felhasználó
     * @param buttonClicked A gomb, ami aktiválódott
     */
    public void Move(Field clickedField, JButton buttonClicked) {
        switch (currentMoveType) {
            case WhiteSelectPiece -> {
                if (whitePlayer.checkPieceIsOnField(clickedField)!=-1) {
                    SelectField(clickedField, buttonClicked);
                    currentMoveType = PlayerMoveTypes.WhiteMovePiece;
                }
            }
            case WhiteMovePiece -> {
                movePiece(whitePlayer, blackPlayer, clickedField, buttonClicked);
            }
            case BlackSelectPiece -> {
                if (blackPlayer.checkPieceIsOnField(clickedField)!=-1) {
                    SelectField(clickedField, buttonClicked);
                    currentMoveType = PlayerMoveTypes.BlackMovePiece;
                }
            }
            default -> { //case BlackMovePiece
                movePiece(blackPlayer, whitePlayer, clickedField, buttonClicked);
            }
        }
        System.out.println(currentMoveType);
    }

    /**
     * Elvégzi a kiválasztott bábu mozgatását, vagy egy új bábut választ ki.
     * Ha egy ellenséges bábu helyére lépünk kiüti azt.
     * Nem lehet úgy lépni, hogy utána a király sakkban legyen.
     * Ellenőrzi a sakk-mattot/pattot.
     *
     * @param moving A lépést végrehajtó játékos referenciája
     * @param notMoving A lépést csak néző játékos referenciája
     * @param clickedField A kiválasztott mező értéke
     * @param buttonClicked A megnyomott gomb referenciája
     */
    private void movePiece(Player moving, Player notMoving, Field clickedField, JButton buttonClicked) {
        if (moving.checkPieceIsOnField(clickedField)!=-1) { //Ha saját bábura klikkelünk, akkor megváltoztatjuk a kiválasztott bábut
            changeBackButtonColor();
            SelectField(clickedField, buttonClicked);
        } else {
            try {
                moving.selectPiece(selectedField);
                moving.move(clickedField, notMoving);

                Boolean kicking = false;
                if (notMoving.checkPieceIsOnField(clickedField)!=-1){
                    notMoving.selectPiece(clickedField);
                    notMoving.kickSelected();
                    kicking = true;
                }

                if(checkCheck(moving, notMoving)){
                    moving.moveBack(); //Ha a lépés után sakkban áll a király, akkor újra kell lépni
                    if(kicking) notMoving.revive();
                    System.out.println("Nem lehet sakk a lepes utan");
                }else{
                    buttonClicked.setIcon(moving.getIconByField(clickedField));
                    selectedButton.setIcon(null);
                    changeBackButtonColor();

                    selectedButton = null;
                    selectedField = null;

                    if (currentMoveType == PlayerMoveTypes.WhiteMovePiece) {
                        currentMoveType = PlayerMoveTypes.BlackSelectPiece;
                    } else {
                        currentMoveType = PlayerMoveTypes.WhiteSelectPiece;
                    }
                    lepesszam++;

                    if(notMoving.checkMatt(moving)){
                        endGame();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                moving.cleanSelect();
            }
        }
    }

    /**
     * Visszaállítja a kiválasztott mező színét az eredeti színre. (Fekete vagy fehér.)
     */
    private void changeBackButtonColor() {
        if (selectedField.getX() % 2 == 0) {
            if (selectedField.getY() % 2 == 1) {
                selectedButton.setBackground(Color.WHITE);
            } else {
                selectedButton.setBackground(Color.DARK_GRAY);
            }
        } else {
            if (selectedField.getY() % 2 == 0) {
                selectedButton.setBackground(Color.WHITE);
            } else {
                selectedButton.setBackground(Color.DARK_GRAY);
            }
        }
    }


    /**
     * Kiválasztjuk, hogy melyik bábuval akarunk lépni.
     * @param clickedField A lenyomott mező értéke.
     * @param buttonClicked A lenyomott mező gomja.
     */
    private void SelectField(Field clickedField, JButton buttonClicked) {
        buttonClicked.setBackground(Color.LIGHT_GRAY);
        selectedButton = buttonClicked;
        selectedField = clickedField;
    }

    /**
     * Ellenőrizzük, hogy a király kapott-e sakkot.
     * @param testPlayer Annak a játékosnak a refereciája, amelyik királyát akarjuk nézni.
     * @param attacker A másik játékos referenciája.
     * @return Ha igaz, akkor a király sakkban áll.
     * @throws Exception
     */
    private Boolean checkCheck(Player testPlayer, Player attacker) throws Exception {
        return attacker.testCheck(testPlayer.getPiece()[4].getLocation(), testPlayer);
    }


    /**
     * Befejezi a játékot, leállítja az órát.
     * @return Visszaadja a játék adatait
     */
    public GameData endGame(){
        timer.isRunning = false;
        if(currentMoveType == PlayerMoveTypes.WhiteMovePiece || currentMoveType == PlayerMoveTypes.WhiteSelectPiece){
            return new GameData("WhitePlayer", "BlackPlayer", timer.getSeconds(), false, lepesszam);
        }else{
            return new GameData("WhitePlayer", "BlackPlayer", timer.getSeconds(), true, lepesszam);
        }
    }
}
