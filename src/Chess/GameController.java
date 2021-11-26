package Chess;

import Chess.Exceptions.IncorrectMoveException;
import Chess.Exceptions.NoPiece;

import javax.swing.*;
import java.awt.*;

public class GameController {
    PlayerMoveTypes currentMoveType = PlayerMoveTypes.WhiteSelectPiece;
    Player whitePlayer;
    Player blackPlayer;

    JButton selectedButton;
    Field selectedField;

    public GameController(Player whitePlayer, Player blackPlayer){
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
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
        switch (currentMoveType){
            case WhiteSelectPiece -> currentMoveType = PlayerMoveTypes.WhiteMovePiece;
            case WhiteMovePiece -> currentMoveType = PlayerMoveTypes.BlackSelectPiece;
            case BlackSelectPiece -> currentMoveType = PlayerMoveTypes.BlackMovePiece;
            default -> currentMoveType = PlayerMoveTypes.WhiteSelectPiece;
        }
    }

    private Boolean testOwnPieceCollision(){
        return true;
    }

    private void movePiece(Player moving, Player notMoving, Field clickedField, JButton buttonClicked){
        if(moving.selectPiece(clickedField)){
            changeBackButtonColor();
            SelectField(clickedField, buttonClicked);
        }else if(!notMoving.selectPiece(clickedField)){
            try{
                moving.move(clickedField);

                buttonClicked.setIcon(moving.getIconByField(clickedField));
                selectedButton.setIcon(null);
                changeBackButtonColor();
            } catch (IncorrectMoveException | NoPiece e) {
                e.printStackTrace();
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

    private void SelectField(Field clickedField, JButton buttonClicked){
        buttonClicked.setBackground(Color.LIGHT_GRAY);

        selectedButton = buttonClicked;
        selectedField = clickedField;
    }
}
