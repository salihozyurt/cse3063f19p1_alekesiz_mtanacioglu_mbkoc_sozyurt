package BL;

/**
 * <h>BDice</h>
 *
 * <p>Dice class in Business Layer. Include method for rolling dice which is used several times in the game.</p>
 *
 * @author Ahmet LEKESİZ
 * @version 1.0
 */
public class BDice {

    public int[] rollDice() {
        int[] diceArray = new int[2];
        double randomDouble = (Math.random() * 6) + 1;
        diceArray[0] = (int) randomDouble;
        randomDouble = (Math.random() * 6) + 1;
        diceArray[1] = (int) randomDouble;
        //Checking if the dices are same or different
        if (checkIfDicesAreSame(diceArray)){
            return rollDice();
        }
        return diceArray;
    }

    public int[] rollDiceWithoutConstraint() {
        int[] diceArray = new int[2];
        int randomInt = (int) (Math.random() * 6) + 1;
        diceArray[0] = randomInt;
        randomInt = (int) (Math.random() * 6) + 1;
        diceArray[1] = randomInt;
        return diceArray;
    }

    public boolean checkIfDicesAreSame(int[] diceArray){
        if(diceArray[0] == diceArray[1]){
            return true;
        }else{
            return false;
        }
    }

}
