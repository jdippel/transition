/*
 *  Transition_Opening_BugFixes.java
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
 * The class Transition_Opening_BugFixes implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   August 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested for an opening to get a test case")
public class Transition_Opening_BugFixes {  
    
    @Test
    @DisplayName("An opening is checked via method change().")
    public void change() {
        
        Position position;
        
        // #1603
        // [Event ""] [Site ""] [Date "2007.??.??"] [Round ""] [White "Scherer, Helmut"] [Black "Schneider, Bernd2"] [Result "0-1"] [WhiteElo "2314"] [BlackElo "2288"] [ECO "B13"] 1.e4 c6 2.d4 d5 3.exd5 cxd5 4.Bd3 Nc6 5.c3 g6 6.Ne2 Nh6 7.h4 Bf5 8.h5 Bxd3 9.Qxd3 Qd7 10.Bf4 Bg7 11.Nd2 Nf5 12.O-O-O Nd6 13.hxg6 hxg6 14.Rxh8+ Bxh8  15.Rh1 O-O-O 16.Rh7 f5 17.Qg3 Qe6 18.Be3 Qg8 19.Rh6 e5 20.Rxg6 Qf7 21.dxe5 Nxe5 22.Rh6 Ne4 23.Qh4 Bg7 24.Rh5 Qd7 25.Qh3 Rf8 26.Bd4 Qb5 27.Bxe5 Qxe2  28.Bf4 Nxf2 29.Qf3 Nd3+ 30.Kb1 Qxf3 31.Nxf3 Nxf4 0-1 
        // 1.e4 c6 2.d4 d5 3.exd5 cxd5 4.Bd3 Nc6 5.c3 g6 6.Ne2 Nh6 7.h4 Bf5 8.h5 Bxd3 9.Qxd3 Qd7 10.Bf4 Bg7 11.Nd2 Nf5 12.O-O-O Nd6 13.hxg6 hxg6 14.Rxh8+ Bxh8  15.Rh1 O-O-O 16.Rh7 f5 17.Qg3 Qe6 18.Be3 Qg8 19.Rh6 e5 20.Rxg6 Qf7 21.dxe5 Nxe5 22.Rh6 Ne4 23.Qh4 Bg7 24.Rh5 Qd7 25.Qh3 Rf8 26.Bd4 Qb5 27.Bxe5 Qxe2  28.Bf4 Nxf2 29.Qf3 Nd3+ 30.Kb1 Qxf3 31.Nxf3 Nxf4 0-1 
        // e4 c6 d4 d5 exd5 cxd5 Bd3 Nc6 c3 g6 Ne2 Nh6 h4 Bf5 h5 Bxd3 Qxd3 Qd7 Bf4 Bg7 Nd2 Nf5 O-O-O Nd6 hxg6 hxg6 Rxh8+ Bxh8 Rh1 O-O-O Rh7 f5 Qg3 Qe6 Be3 Qg8 Rh6 e5 Rxg6 Qf7 dxe5 Nxe5 Rh6 Ne4 Qh4 Bg7 Rh5 Qd7 Qh3 Rf8 Bd4 Qb5 Bxe5 Qxe2 Bf4 Nxf2 Qf3 Nd3+ Kb1 Qxf3 Nxf3 Nxf4
        // Exception in thread "main" chess383.statemachine.exception.PieceTypeToLocationAssertionViolationException: piece type on location h8 does not match the expected type
        
        position = Position.create();
        position =  Transition.create( position ).change( "e2", "e4" );
        position =  Transition.create( position ).change( "c7", "c6" );
        position =  Transition.create( position ).change( "d2", "d4" );
        position =  Transition.create( position ).change( "d7", "d5" );
        position =  Transition.create( position ).change( "e4", "d5" );
        position =  Transition.create( position ).change( "c6", "d5" );
        position =  Transition.create( position ).change( "f1", "d3" );
        position =  Transition.create( position ).change( "b8", "c6" );
        position =  Transition.create( position ).change( "c2", "c3" );
        position =  Transition.create( position ).change( "g7", "g6" );
        position =  Transition.create( position ).change( "g1", "e2" );
        position =  Transition.create( position ).change( "g8", "h6" );
        position =  Transition.create( position ).change( "h2", "h4" );
        position =  Transition.create( position ).change( "c8", "f5" );
        position =  Transition.create( position ).change( "h4", "h5" );
        position =  Transition.create( position ).change( "f5", "d3" );
        position =  Transition.create( position ).change( "d1", "d3" );
        position =  Transition.create( position ).change( "d8", "d7" );
        position =  Transition.create( position ).change( "c1", "f4" );
        position =  Transition.create( position ).change( "f8", "g7" );
        
        assertThat( "r3k2r/pp1qppbp/2n3pn/3p3P/3P1B2/2PQ4/PP2NPP1/RN2K2R w KQkq - 3 21" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
        
        // 11.Nd2 Nf5 12.O-O-O Nd6 13.hxg6 hxg6 14.Rxh8+ Bxh8 15.Rh1 O-O-O 16.Rh7 f5 17.Qg3 Qe6 18.Be3 Qg8 19.Rh6 e5 20.Rxg6 Qf7
        
        position =  Transition.create( position ).change( "b1", "d2" );
        position =  Transition.create( position ).change( "h6", "f5" );
        position =  Transition.create( position ).change( "e1", "c1" );
        position =  Transition.create( position ).change( "f5", "d6" );
        position =  Transition.create( position ).change( "h5", "g6" );
        position =  Transition.create( position ).change( "h7", "g6" );
        
        assertThat( "r3k2r/pp1qppb1/2nn2p1/3p4/3P1B2/2PQ4/PP1NNPP1/2KR3R w kq - 0 27" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
        
        position =  Transition.create( position ).change( "h1", "h8" );
        
        assertThat( "r3k2R/pp1qppb1/2nn2p1/3p4/3P1B2/2PQ4/PP1NNPP1/2KR4 b q - 0 28" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
        
        position =  Transition.create( position ).change( "g7", "h8" );
        position =  Transition.create( position ).change( "d1", "h1" );
        position =  Transition.create( position ).change( "e8", "c8" );
        position =  Transition.create( position ).change( "h1", "h7" );
        position =  Transition.create( position ).change( "f7", "f5" );
        position =  Transition.create( position ).change( "d3", "g3" );
        position =  Transition.create( position ).change( "d7", "e6" );
        position =  Transition.create( position ).change( "f4", "e3" );
        position =  Transition.create( position ).change( "e6", "g8" );
        position =  Transition.create( position ).change( "h7", "h6" );
        position =  Transition.create( position ).change( "e7", "e5" );
        position =  Transition.create( position ).change( "h6", "g6" );
        position =  Transition.create( position ).change( "g8", "f7" );
        
        assertThat( "2kr3b/pp3q2/2nn2R1/3ppp2/3P4/2P1B1Q1/PP1NNPP1/2K5 w - - 1 41" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
        
        // 21.dxe5 Nxe5 22.Rh6 Ne4 23.Qh4 Bg7 24.Rh5 Qd7 25.Qh3 Rf8 26.Bd4 Qb5 27.Bxe5 Qxe2 28.Bf4 Nxf2 29.Qf3 Nd3+ 30.Kb1 Qxf3 31.Nxf3 Nxf4 0-1 
        
        position =  Transition.create( position ).change( "d4", "e5" );
        position =  Transition.create( position ).change( "c6", "e5" );
        position =  Transition.create( position ).change( "g6", "h6" );
        position =  Transition.create( position ).change( "d6", "e4" );
        position =  Transition.create( position ).change( "g3", "h4" );
        position =  Transition.create( position ).change( "h8", "g7" );
        position =  Transition.create( position ).change( "h6", "h5" );
        position =  Transition.create( position ).change( "f7", "d7" );
        position =  Transition.create( position ).change( "h4", "h3" );
        position =  Transition.create( position ).change( "d8", "f8" );
        position =  Transition.create( position ).change( "e3", "d4" );
        position =  Transition.create( position ).change( "d7", "b5" );
        position =  Transition.create( position ).change( "d4", "e5" );
        position =  Transition.create( position ).change( "b5", "e2" );
        position =  Transition.create( position ).change( "e5", "f4" );
        position =  Transition.create( position ).change( "e4", "f2" );
        position =  Transition.create( position ).change( "h3", "f3" );
        position =  Transition.create( position ).change( "f2", "d3" );
        position =  Transition.create( position ).change( "c1", "b1" );
        position =  Transition.create( position ).change( "e2", "f3" );
        position =  Transition.create( position ).change( "d2", "f3" );
        position =  Transition.create( position ).change( "d3", "f4" );

        assertThat( "2k2r2/pp4b1/8/3p1p1R/5n2/2P2N2/PP4P1/1K6 w - - 0 63" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
    }
}


