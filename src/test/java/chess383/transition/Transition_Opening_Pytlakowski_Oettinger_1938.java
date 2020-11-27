/*
 *  Transition_Opening_Pytlakowski_Oettinger_1938.java
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
 * The class Transition_Opening_Pytlakowski_Oettinger_1938 implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   August 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested for an opening to get a test case")
public class Transition_Opening_Pytlakowski_Oettinger_1938 {  
    
    @Test
    @DisplayName("An opening is checked via method change().")
    public void change() {
        
        Position position;
        
//        #7797
//        [Event ""]
//        [Site ""]
//        [Date "1938.??.??"]
//        [Round ""]
//        [White "Pytlakowski, Andrzej"]
//        [Black "Oettinger Hagen"]
//        [ECO "B20"]
//        [Result "1-0"]
//        1.e4 c5 2.b4 cxb4 3.a3 d5 4.exd5 Qxd5 5.Nf3 e5 6.axb4 Bxb4 7.Ba3 Bxa3 8. Rxa3 Nc6 9.Nc3 Qd6 10.Nb5 Qe7 11.Re3 Bg4 12.d4 O-O-O 13.d5 Nh6 14.d6 Bxf3  15.gxf3 Qe6 16.Qa1 Rxd6 17.Bc4 Qe7 18.Nxd6+ Qxd6 19.Rd3 Qc7 20.O-O Rd8 
//        21. Rfd1 Rxd3 22.Rxd3 Nf5 23.Qa3 Qe7 24.Qxe7 Nfxe7 25.Bxf7 Kc7 26.Kf1 a5 27. Ke1 b5 28.c3 e4 29.fxe4 Ne5 30.Rg3 Nxf7 31.Rxg7 a4 32.Rxf7 Kd6 33.e5+ 1-0 
//
//        1.e4 c5 2.b4 cxb4 3.a3 d5 4.exd5 Qxd5 5.Nf3 e5 6.axb4 Bxb4 7.Ba3 Bxa3 8. Rxa3 Nc6 9.Nc3 Qd6 10.Nb5 Qe7 11.Re3 Bg4 12.d4 O-O-O 13.d5 Nh6 14.d6 Bxf3  15.gxf3 Qe6 16.Qa1 Rxd6 17.Bc4 Qe7 18.Nxd6+ Qxd6 19.Rd3 Qc7 20.O-O Rd8 21. Rfd1 Rxd3 22.Rxd3 Nf5 23.Qa3 Qe7 24.Qxe7 Nfxe7 25.Bxf7 Kc7 26.Kf1 a5 27. Ke1 b5 28.c3 e4 29.fxe4 Ne5 30.Rg3 Nxf7 31.Rxg7 a4 32.Rxf7 Kd6 33.e5+ 1-0 
//        e4 c5 b4 cxb4 a3 d5 exd5 Qxd5 Nf3 e5 axb4 Bxb4 Ba3 Bxa3 Rxa3 Nc6 Nc3 Qd6 Nb5 Qe7 Re3 Bg4 d4 O-O-O d5 Nh6 d6 Bxf3 gxf3 Qe6 Qa1 Rxd6 Bc4 Qe7 Nxd6+ Qxd6 Rd3 Qc7 O-O Rd8 Rfd1 Rxd3 Rxd3 Nf5 Qa3 Qe7 Qxe7 Nfxe7 Bxf7 Kc7 Kf1 a5 Ke1 b5 c3 e4 fxe4 Ne5 Rg3 Nxf7 Rxg7 a4 Rxf7 Kd6 e5+
        
        
        // 1.e4 c5 2.b4 cxb4 3.a3 d5 4.exd5 Qxd5 5.Nf3 e5 6.axb4 Bxb4 7.Ba3 Bxa3 8. Rxa3 Nc6 9.Nc3 Qd6 10.Nb5 Qe7
        position = Position.create();
        position =  Transition.create( position ).change( "e2", "e4" );
        position =  Transition.create( position ).change( "c7", "c5" );
        position =  Transition.create( position ).change( "b2", "b4" );
        position =  Transition.create( position ).change( "c5", "b4" );
        position =  Transition.create( position ).change( "a2", "a3" );
        position =  Transition.create( position ).change( "d7", "d5" );
        position =  Transition.create( position ).change( "e4", "d5" );
        position =  Transition.create( position ).change( "d8", "d5" );
        position =  Transition.create( position ).change( "g1", "f3" );
        position =  Transition.create( position ).change( "e7", "e5" );
        position =  Transition.create( position ).change( "a3", "b4" );
        position =  Transition.create( position ).change( "f8", "b4" );
        position =  Transition.create( position ).change( "c1", "a3" );
        position =  Transition.create( position ).change( "b4", "a3" );
        
        assertThat( "rnb1k1nr/pp3ppp/8/3qp3/8/b4N2/2PP1PPP/RN1QKB1R w KQkq - 0 15" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
        
        position =  Transition.create( position ).change( "a1", "a3" );
        
        assertThat( "rnb1k1nr/pp3ppp/8/3qp3/8/R4N2/2PP1PPP/1N1QKB1R b Kkq - 0 16" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
        
        position =  Transition.create( position ).change( "b8", "c6" );
        position =  Transition.create( position ).change( "b1", "c3" );
        position =  Transition.create( position ).change( "d5", "d6" );
        position =  Transition.create( position ).change( "c3", "b5" );
        position =  Transition.create( position ).change( "d6", "e7" );
        
        assertThat( "r1b1k1nr/pp2qppp/2n5/1N2p3/8/R4N2/2PP1PPP/3QKB1R w Kkq - 5 21" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
        
        // 11.Re3 Bg4 12.d4 O-O-O 13.d5 Nh6 14.d6 Bxf3  15.gxf3 Qe6 16.Qa1 Rxd6 17.Bc4 Qe7 18.Nxd6+ Qxd6 19.Rd3 Qc7 20.O-O Rd8 
        position =  Transition.create( position ).change( "a3", "e3" );
        position =  Transition.create( position ).change( "c8", "g4" );
        position =  Transition.create( position ).change( "d2", "d4" );
        position =  Transition.create( position ).change( "e8", "c8" );
        position =  Transition.create( position ).change( "d4", "d5" );
        position =  Transition.create( position ).change( "g8", "h6" );
        position =  Transition.create( position ).change( "d5", "d6" );
        position =  Transition.create( position ).change( "g4", "f3" );
        position =  Transition.create( position ).change( "g2", "f3" );
        position =  Transition.create( position ).change( "e7", "e6" );
        position =  Transition.create( position ).change( "d1", "a1" );
        position =  Transition.create( position ).change( "d8", "d6" );
        position =  Transition.create( position ).change( "f1", "c4" );
        position =  Transition.create( position ).change( "e6", "e7" );
        position =  Transition.create( position ).change( "b5", "d6" );
        position =  Transition.create( position ).change( "e7", "d6" );
        position =  Transition.create( position ).change( "e3", "d3" );
        position =  Transition.create( position ).change( "d6", "c7" );
        position =  Transition.create( position ).change( "e1", "g1" );
        position =  Transition.create( position ).change( "h8", "d8" );
        
        assertThat( "2kr4/ppq2ppp/2n4n/4p3/2B5/3R1P2/2P2P1P/Q4RK1 w - - 4 41" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
        
        // 21. Rfd1 Rxd3 22.Rxd3 Nf5 23.Qa3 Qe7 24.Qxe7 Nfxe7 25.Bxf7 Kc7 26.Kf1 a5 27. Ke1 b5 28.c3 e4 29.fxe4 Ne5 30.Rg3 Nxf7
        position =  Transition.create( position ).change( "f1", "d1" );
        position =  Transition.create( position ).change( "d8", "d3" );
        position =  Transition.create( position ).change( "d1", "d3" );
        position =  Transition.create( position ).change( "h6", "f5" );
        position =  Transition.create( position ).change( "a1", "a3" );
        position =  Transition.create( position ).change( "c7", "e7" );
        position =  Transition.create( position ).change( "a3", "e7" );
        position =  Transition.create( position ).change( "f5", "e7" );
        position =  Transition.create( position ).change( "c4", "f7" );
        position =  Transition.create( position ).change( "c8", "c7" );
        position =  Transition.create( position ).change( "g1", "f1" );
        position =  Transition.create( position ).change( "a7", "a5" );
        position =  Transition.create( position ).change( "f1", "e1" );
        position =  Transition.create( position ).change( "b7", "b5" );
        position =  Transition.create( position ).change( "c2", "c3" );
        position =  Transition.create( position ).change( "e5", "e4" );
        position =  Transition.create( position ).change( "f3", "e4" );
        position =  Transition.create( position ).change( "c6", "e5" );
        position =  Transition.create( position ).change( "d3", "g3" );
        position =  Transition.create( position ).change( "e5", "f7" );
        
        assertThat( "8/2k1nnpp/8/pp6/4P3/2P3R1/5P1P/4K3 w - - 0 61" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
        
        // 31.Rxg7 a4 32.Rxf7 Kd6 33.e5+
        position =  Transition.create( position ).change( "g3", "g7" );
        position =  Transition.create( position ).change( "a5", "a4" );
        position =  Transition.create( position ).change( "g7", "f7" );
        position =  Transition.create( position ).change( "c7", "d6" );
        position =  Transition.create( position ).change( "e4", "e5" );
        
        assertThat( "8/4nR1p/3k4/1p2P3/p7/2P5/5P1P/4K3 b - - 0 66" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
        
    }
}


