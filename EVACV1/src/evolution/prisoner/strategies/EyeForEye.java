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
public class EyeForEye extends Strategy {
    Result lastMove = null;
    int max = 5;
    Move[] moves = new Move[max];
    int mysc = 0;
    int opsc = 0;
    int it = 0;
    
    
    @Override
    public Move nextMove() {
        if (lastMove == null) {
            return Move.COOPERATE;
        }
        
        // Sanca na ziskanie dovery
        if (mysc >= 0.9 *  opsc) {
            return Move.COOPERATE;
        }
        
        int c = 0;
        for (Move move : moves) {
            if (move == Move.COOPERATE) {
                c++;
                continue;
            }            
        }
        
        // Zrada ked bol posledny tah kooperacny a v poslednych 5 tahoch mali 3 a viac kooperacii
        if (c > 2 && lastMove.getOponentsMove() == Move.COOPERATE ) {
            return Move.DECEIVE;
        }
        return lastMove.getOponentsMove();
    }

    @Override
    public void reward(Result res) {
        lastMove = res;
        opsc += res.getOponentsScore();
        mysc += res.getMyScore();
        if (it >= max) {
            it = 0;
        }
        moves[it] = res.getOponentsMove();
        it++;                
    }

    @Override
    public String getName() {
        return "Better Eye for Eye";
    }

    @Override
    public String authorName() {
        return "Lukas Surin";
    }

    @Override
    public void reset() {
        lastMove = null;
        opsc = 0;
        mysc = 0;
        it = 0;
        
    }
    
}
