/*
 *  Transition_Opening_CentreGame.java
 *
 *  chess383 is a collection of chess related utilities.
 *  Copyright (C) 2020 Jörg Dippel
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package chess383.transition;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess383.position.Position;

/**
 * <p>
 * The class Transition_Opening_CentreGame implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   July 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested for the Center Game opening (C22)")
public class Transition_Opening_CentreGame {  
    
    @Test
    @DisplayName("The Center Game is checked due to change().")
    public void change_CenterGame() {
        
        Position position;
        
        position = Position.create();
        
        assertThat( "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1" )
                .as( "the string representation of the new position should match" )
                .isEqualTo( position.toString() );
        
        position =  Transition.create( position ).change( "e2", "e4" );
        
        assertThat( "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 2" )
                .as( "the string representation of the new position should match" )
                .isEqualTo( position.toString() );
        
        position =  Transition.create( position ).change( "e7", "e5" );
        
        assertThat( "rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 3" )
                .as( "the string representation of the new position should match" )
                .isEqualTo( position.toString() );
        
        position =  Transition.create( position ).change( "d2", "d4" );
        position =  Transition.create( position ).change( "e5", "d4" );
        position =  Transition.create( position ).change( "d1", "d4" );
        position =  Transition.create( position ).change( "b8", "c6" );
        position =  Transition.create( position ).change( "d4", "e3" );
        position =  Transition.create( position ).change( "g8", "f6" );
        position =  Transition.create( position ).change( "b1", "c3" );
        position =  Transition.create( position ).change( "f8", "b4" );
        position =  Transition.create( position ).change( "c1", "d2" );
        position =  Transition.create( position ).change( "e8", "g8" );
        position =  Transition.create( position ).change( "e1", "c1" );
        position =  Transition.create( position ).change( "f8", "e8" );
        position =  Transition.create( position ).change( "f1", "c4" );
        position =  Transition.create( position ).change( "d7", "d6" );
        
        assertThat( "r1bqr1k1/ppp2ppp/2np1n2/8/1bB1P3/2N1Q3/PPPB1PPP/2KR2NR w - - 0 17" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
    }
}


