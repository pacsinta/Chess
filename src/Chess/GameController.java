package Chess;

import Chess.Exceptions.IncorrectMoveException;
import Chess.Exceptions.NoPiece;

import javax.swing.*;
import java.awt.*;

public class GameController {
    Boolean isSinglePlayer;

    PlayerMoveTypes currentMoveType = PlayerMoveTypes.WhiteSelectPiece;
    Player whitePlayer;
    Player blackPlayer;

    JButton selectedButton;
    Field selectedField;

    public GameController(Player whitePlayer, Player blackPlayer){
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;

        Thread timerThread = new Thread(new Timer());
        timerThread.start();
    }

    public void Move(Field clickedField, JButton buttonClicked){
        switch (currentMoveType){
            case WhiteSelectPiece -> {
                if(whitePlayer.selectPiece(clickedField)){
                    SelectField(clickedField, buttonClicked);
                }
            }
            case WhiteMovePiece -> {
                movePiece(whitePlayer, blackPlayer, clickedField, buttonClicked);
            }
            case BlackSelectPiece -> {
                if(blackPlayer.selectPiece(clickedField)){
                    SelectField(clickedField, buttonClicked);
                }
            }
            default -> { //case BlackMovePiece
                movePiece(blackPlayer, whitePlayer, clickedField, buttonClicked);
            }
        }
        System.out.println(currentMoveType);
    }

    //Megnézzük, hogy van-e az útban saját bábu
    //Igaz ha nincs, hamis ha van
    private Boolean testOwnPieceCollision(Field startField, Field endField, Player player){
        for(int x = startField.getX(); x< endField.getX()+1;x++){
            for(int y = startField.getY(); y< endField.getY()+1; y++){
                for(int i = 0; i<16; i++){
                    if(player.getPiece()[i].getLocation().isEqual(new Field(x, y))){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void movePiece(Player moving, Player notMoving, Field clickedField, JButton buttonClicked){
        if(moving.selectPiece(clickedField)){
            changeBackButtonColor();
            SelectField(clickedField, buttonClicked);
        }else if(!notMoving.selectPiece(clickedField)){
            if(testOwnPieceCollision(selectedField, clickedField, moving)){
                try{
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

    private void changeBackButtonColor(){
        if(selectedField.getX()%2==0){
            if(selectedField.getY()%2==1){
                selectedButton.setBackground(Color.WHITE);
            }else{
                selectedButton.setBackground(Color.DARK_GRAY);
            }
        }else{
            if(selectedField.getY()%2==0){
                selectedButton.setBackground(Color.WHITE);
            }else{
                selectedButton.setBackground(Color.DARK_GRAY);
            }
        }
    }

    //Kiválasztjuk, hogy melyik bábuval akarunk lépni
    //click
    private void SelectField(Field clickedField, JButton buttonClicked){
        buttonClicked.setBackground(Color.LIGHT_GRAY);
        selectedButton = buttonClicked;
        selectedField = clickedField;

        if (currentMoveType == PlayerMoveTypes.WhiteMovePiece) {
            currentMoveType = PlayerMoveTypes.WhiteSelectPiece;
        } else {
            currentMoveType = PlayerMoveTypes.BlackSelectPiece;
        }
    }
}
