/*
 *  Transition_GetNumberOfPlys.java
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
 * The class Transition_GetNumberOfPlys implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   September 2020
 *
 */
@DisplayName("the public method Position getNumberOfPlys( ) is tested for the Skandinavian Defense opening (B01)")
public class Transition_GetNumberOfPlys {  
    
    @Test
    @DisplayName("For the Center Game the number of plys are verified().")
    public void change_CenterGame() {
        
        Position position;
        
        position = Position.create();
        position =  Transition.create( position ).change( "e2", "e4" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "d7", "d5" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "e4", "d5" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a capture
        position =  Transition.create( position ).change( "g8", "f6" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 1 );
        position =  Transition.create( position ).change( "d2", "d4" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "f6", "d5" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a capture
        position =  Transition.create( position ).change( "c2", "c4" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "d5", "b6" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 1 );
        position =  Transition.create( position ).change( "g1", "f3" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 2 );
        position =  Transition.create( position ).change( "g7", "g6" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "h2", "h3" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "f8", "g7" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 1 );
        position =  Transition.create( position ).change( "b1", "c3" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 2 );
        position =  Transition.create( position ).change( "e8", "g8" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 3 );
        position =  Transition.create( position ).change( "c1", "e3" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 4 );
        position =  Transition.create( position ).change( "b8", "c6" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 5 );
        position =  Transition.create( position ).change( "d1", "d2" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 6 );
        position =  Transition.create( position ).change( "e7", "e5" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "d4", "d5" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        
        assertThat( "r1bq1rk1/ppp2pbp/1nn3p1/3Pp3/2P5/2N1BN1P/PP1Q1PP1/R3KB1R b KQ - 0 20" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
    }
    
    @Test
    @DisplayName("For the French Defense the number of plys are verified().")
    public void change_FrenchDefense() {
        
        Position position;
        
        position = Position.create();
        position =  Transition.create( position ).change( "e2", "e4" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "e7", "e6" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "d2", "d4" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "d7", "d5" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "b1", "c3" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 1 );
        position =  Transition.create( position ).change( "g8", "f6" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 2 );
        position =  Transition.create( position ).change( "e4", "e5" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "f6", "d7" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 1 );
        position =  Transition.create( position ).change( "g1", "f3" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 2 );
        position =  Transition.create( position ).change( "f8", "b4" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 3 );
        position =  Transition.create( position ).change( "f1", "d3" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 4 );
        position =  Transition.create( position ).change( "c7", "c5" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "e1", "g1" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 1 );   // last ply has been white kingside castling
        position =  Transition.create( position ).change( "e8", "g8" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 2 );   // last ply has been black kingside castling
        position =  Transition.create( position ).change( "d3", "h7" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a capture
        position =  Transition.create( position ).change( "g8", "h7" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a capture
        position =  Transition.create( position ).change( "f3", "g5" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 1 );
        position =  Transition.create( position ).change( "h7", "g6" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 2 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "d1", "d3" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 3 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "f7", "f5" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a movement of a pawn
        position =  Transition.create( position ).change( "e5", "f6" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a capture en passant by a white pawn
        position =  Transition.create( position ).change( "g6", "f6" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 0 );   // last ply has been a capture
        position =  Transition.create( position ).change( "f1", "e1" );
        assertThat( position.getNumberOfPlys() ).as( "the value of the counter for plys must match" ).isEqualTo( 1 );
        
        assertThat( "rnbq1r2/pp1n2p1/4pk2/2pp2N1/1b1P4/2NQ4/PPP2PPP/R1B1R1K1 b - - 1 24" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
    }
}


