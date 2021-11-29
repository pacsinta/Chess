package Chess;

import Chess.Exceptions.IncorrectMoveException;
import Chess.Exceptions.NoPiece;

import javax.swing.*;
import java.awt.*;

public class GameController {
    Boolean isMultiplayer;

    PlayerMoveTypes currentMoveType = PlayerMoveTypes.WhiteSelectPiece;
    Player whitePlayer;
    Player blackPlayer;

    JButton selectedButton;
    Field selectedField;

    public GameController(Player whitePlayer, Player blackPlayer, JLabel timeMonitor, Boolean playerCount) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.isMultiplayer = playerCount;

        Thread timerThread = new Thread(new Timer(timeMonitor));
        timerThread.start();
    }

    public void Move(Field clickedField, JButton buttonClicked) {
        switch (currentMoveType) {
            case WhiteSelectPiece -> {
                if (whitePlayer.selectPiece(clickedField)) {
                    SelectField(clickedField, buttonClicked);
                    currentMoveType = PlayerMoveTypes.WhiteMovePiece;
                }
            }
            case WhiteMovePiece -> {
                movePiece(whitePlayer, blackPlayer, clickedField, buttonClicked);
            }
            case BlackSelectPiece -> {
                if (blackPlayer.selectPiece(clickedField)) {
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

    private interface SpecialFor_Lambda {
        int forNewValue(int value);
    }
    private interface SpecialForTest{
        Boolean testFor();
    }


    private Boolean specialFor(int yStart, int yEnd, int xStart, int xEnd, SpecialFor_Lambda newX, SpecialFor_Lambda newY, Player player) {
        for (int x = xStart; x < xEnd; x = newX.forNewValue(x)) {
            for (int y = yStart; y < yEnd; y = newY.forNewValue(y)) {
                if (player.selectPiece(new Field(x, y))){
                    return false;
                }
            }
        }
        return true;
    }

    //Megnézzük, hogy van-e az útban saját bábu
    //Igaz ha nincs, hamis ha van
    private Boolean testOwnPieceCollision(Field startField, Field endField, Player player) {
        if (startField.getY() > endField.getY()) {
            if (startField.getX() > endField.getY()) {
                return specialFor(startField.getY() , endField.getY(), startField.getX(), endField.getX(), x -> x--, y -> y--, player);
            } else {
                return specialFor(startField.getY(), endField.getY(), startField.getX(), endField.getX(), x -> x++, y -> y--, player);
            }
        } else {
            if (startField.getX() > endField.getY()) {
                return specialFor(startField.getY(), endField.getY(), startField.getX(), endField.getX(), x -> x--, y -> y++, player);
            } else {
                return specialFor(startField.getY(), endField.getY(), startField.getX(), endField.getX(), x -> x++, y -> y++, player);
            }
        }
    }

    private void movePiece(Player moving, Player notMoving, Field clickedField, JButton buttonClicked) {
        if (moving.selectPiece(clickedField)) {
            changeBackButtonColor();
            SelectField(clickedField, buttonClicked);
        } else if (!notMoving.selectPiece(clickedField)) {
            if (testOwnPieceCollision(selectedField, clickedField, moving)) {
                try {
                    moving.move(clickedField);

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
                } catch (IncorrectMoveException | NoPiece e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

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

    //Kiválasztjuk, hogy melyik bábuval akarunk lépni
    //click
    private void SelectField(Field clickedField, JButton buttonClicked) {
        buttonClicked.setBackground(Color.LIGHT_GRAY);
        selectedButton = buttonClicked;
        selectedField = clickedField;
    }
}
