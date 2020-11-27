/*
 *  Transition_Opening_SkandinavianDefense.java
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
 * The class Transition_Opening_SkandinavianDefense implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   July 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested for the Skandinavian Defense opening (B01)")
public class Transition_Opening_SkandinavianDefense {  
    
    @Test
    @DisplayName("The Center Game is checked due to change().")
    public void change_CenterGame() {
        
        Position position;
        
        position = Position.create();
        position =  Transition.create( position ).change( "e2", "e4" );
        position =  Transition.create( position ).change( "d7", "d5" );
        position =  Transition.create( position ).change( "e4", "d5" );
        position =  Transition.create( position ).change( "g8", "f6" );
        position =  Transition.create( position ).change( "d2", "d4" );
        position =  Transition.create( position ).change( "f6", "d5" );
        position =  Transition.create( position ).change( "c2", "c4" );
        position =  Transition.create( position ).change( "d5", "b6" );
        position =  Transition.create( position ).change( "g1", "f3" );
        position =  Transition.create( position ).change( "g7", "g6" );
        position =  Transition.create( position ).change( "h2", "h3" );
        position =  Transition.create( position ).change( "f8", "g7" );
        position =  Transition.create( position ).change( "b1", "c3" );
        position =  Transition.create( position ).change( "e8", "g8" );
        position =  Transition.create( position ).change( "c1", "e3" );
        position =  Transition.create( position ).change( "b8", "c6" );
        position =  Transition.create( position ).change( "d1", "d2" );
        position =  Transition.create( position ).change( "e7", "e5" );
        position =  Transition.create( position ).change( "d4", "d5" );
        
        assertThat( "r1bq1rk1/ppp2pbp/1nn3p1/3Pp3/2P5/2N1BN1P/PP1Q1PP1/R3KB1R b KQ - 0 20" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
    }
}


