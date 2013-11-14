/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution.prisoner.strategies;

import evolution.prisoner.Result;
import evolution.prisoner.Strategy;

/**
 *
 * @author kirrie
 */
public class Other extends Strategy{

    Result lastMove = null;
    Move[] moves = new Move[max];
    static int max = 5;
    int it = 0;
    int mysc = 0;
    int opsc = 0;
    
    Random random = new Random();
    
    @Override
    public Move nextMove() {
        if (lastMove == null) {
            return Move.COOPERATE;
        }
        
        int c = 0;
        int d = 0;
        for (Move move : moves) {
            if (move == Move.COOPERATE) {
                c++;
            }
            if (move == Move.DECEIVE) {
                d++;
            }
        }
        
        if (c==max) {
            if (random.rnd.nextDouble() > 0.8) {
                return Move.DECEIVE;
            }
            return Move.COOPERATE;
        }
        
        if (d > 2) {
            if (random.rnd.nextDouble() > 0.8) {
                return Move.COOPERATE;
            }
            return Move.DECEIVE;
        }
        
        return lastMove.getOponentsMove();
        
    }

    @Override
    public void reward(Result res) {
        lastMove = res;
        mysc = res.getMyScore();
        opsc = res.getOponentsScore();
        if (it >= max) {
            it = 0;
        }
        moves[it] = res.getOponentsMove();
        
    }

    @Override
    public String getName() {
        return "Surko";
    }

    @Override
    public String authorName() {
        return "Surko";
    }

    @Override
    public void reset() {
        lastMove = null;
        moves = new Move[max];
    }
    
}
