/*
 *  Transition_Change_BugFixes.java
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import chess383.position.Position;
import chess383.statemachine.exception.KingWouldBeCheckedAfterwardsException;
import chess383.statemachine.exception.LocationIsOccupiedException;

/**
 * <p>
 * The class Transition_Change_BugFixes implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   July 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested for found bugs)")
public class Transition_Change_BugFixes {  
    
    @Test
    @DisplayName("for a pawn capture the numer of plys should be resetted")
    public void change_CenterGame() {
        
        Position position;
        
        position = Position.create( "8/3bk3/5p2/1p2p2p/p2n1Pp1/P5P1/1PP2N1P/2K1N3 w - - 0 77" );
        position =  Transition.create( position ).change( "f4", "e5" );
        
        assertThat( 0 )
                  .as( "the counter for non-captures and non-pawn-moves has to be resetted for pawn captures" )
                  .isEqualTo( position.getNumberOfPlys() );
    }
    
    @Test
    @DisplayName("the white king knight should move")
    public void change_InitialKnightMove() {
        
        Position position;
        
        position = Position.create( "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1" );
        position =  Transition.create( position ).change( "g1", "f3" );
        
        assertThat( "rnbqkbnr/pppppppp/8/8/8/5N2/PPPPPPPP/RNBQKB1R b KQkq - 1 2" )
                  .as( "inititial position and the white king knight should move" )
                  .isEqualTo( position.toString() );
    }
    
    @Test
    @DisplayName("the initial white pawn should move")
    public void change_InitialWhitePawnMove() {
        
        Position position;
        
        position = Position.create( "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1" );
        position =  Transition.create( position ).change( "d2", "d4" );
        
        assertThat( "rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq d3 0 2" )
                  .as( "inititial position and the white king knight should move" )
                  .isEqualTo( position.toString() );
    }
    
    @Test
    @DisplayName("two knights can move to e2 but one of them is pinned - not pinned knight")
    public void change_AmbigousMovePossibilityForKnightsButOneKnightIsNotpinned() {
        
        // "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2"
        Position position;
        
        position = Position.create( "r1b1k1nr/ppp2ppp/8/q1bPp3/2Bn4/2N1B3/PPP2PPP/R2QK1NR w KQkq - 3 15" );
        position =  Transition.create( position ).change( "g1", "e2" );
        
        assertThat( "r1b1k1nr/ppp2ppp/8/q1bPp3/2Bn4/2N1B3/PPP1NPPP/R2QK2R b KQkq - 4 16" )
                  .as( "not pinned knight should move" )
                  .isEqualTo( position.toString() );
    }
    
    @Test
    @DisplayName("two knights can move to e2 but one of them is pinned - pinned knight")
    public void change_AmbigousMovePossibilityForKnightsButOneKnightIsPinned() {
        
        // "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2"
        
        Position position = Position.create( "r1b1k1nr/ppp2ppp/8/q1bPp3/2Bn4/2N1B3/PPP2PPP/R2QK1NR w KQkq - 3 15" );
        
        assertThatThrownBy( () ->  Transition.create( position ).change( "c3", "e2" ) )
                  .as( "pinned knight should not move" )
                  .isInstanceOf( KingWouldBeCheckedAfterwardsException.class );
    }
    
    @Test
    @DisplayName("black pawn captures en passant")
    public void change_PawnCaptureEnpassant() {
        
        // "e4", "c5", "Nf3", "d6", "d4", "cxd4", "Nxd4", "Nf6", "Nc3", "g6", "Be3", "Bg7", "f3", "O-O", "Qd2", "Nc6", "O-O-O", "Nxd4", "Bxd4", "Be6", 
        // "h4", "Qa5", "Kb1", "Rfc8", "a3", "Rab8", "Bxf6", "Bxf6", "Nd5", "Qxd2", "Nxf6+", "Kg7", "Nh5+", "Kh6", "Rxd2", "Kxh5", "g4+", "Kh6", "g5+", "Kg7",
        // "h5", "Rc5", "f4", "Bg4", "hxg6", "hxg6", "Rdh2", "Rg8", "Bd3", "d5", "b4", "Rc7", "exd5", "Bf3", "Rf1", "Bxd5", "f5", "gxf5", "Rxf5", "Bc4", 
        // "Be4", "Rh8", "Rxh8", "Kxh8", "Rf4", "Be6", "Bd3", "Kg7", "Rh4", "f5", "gxf6+"
        Position position;
        
        position = Position.create( "6r1/ppr1p1k1/6p1/3b1pP1/1P3P2/P2B4/2P4R/1K3R2 w - f6 0 71" );
        position =  Transition.create( position ).change( "g5", "f6" );
        
        assertThat( "6r1/ppr1p1k1/5Pp1/3b4/1P3P2/P2B4/2P4R/1K3R2 b - - 0 72" )
                  .as( "black pawn captures white pawn en passant" )
                  .isEqualTo( position.toString() );
    }
    
    @Test
    @DisplayName("one move after white pawn captures en passant")
    public void change_MoveAfterPawnCaptureEnpassant() {
        
        // "e4", "c5", "Nf3", "d6", "d4", "cxd4", "Nxd4", "Nf6", "Nc3", "g6", "Be3", "Bg7", "f3", "O-O", "Qd2", "Nc6", "O-O-O", "Nxd4", "Bxd4", "Be6", 
        // "h4", "Qa5", "Kb1", "Rfc8", "a3", "Rab8", "Bxf6", "Bxf6", "Nd5", "Qxd2", "Nxf6+", "Kg7", "Nh5+", "Kh6", "Rxd2", "Kxh5", "g4+", "Kh6", "g5+", "Kg7",
        // "h5", "Rc5", "f4", "Bg4", "hxg6", "hxg6", "Rdh2", "Rg8", "Bd3", "d5", "b4", "Rc7", "exd5", "Bf3", "Rf1", "Bxd5", "f5", "gxf5", "Rxf5", "Bc4", 
        // "Be4", "Rh8", "Rxh8", "Kxh8", "Rf4", "Be6", "Bd3", "Kg7", "Rh4", "f5", "gxf6+", "Kxf6"
        Position position;
        
        position = Position.create( "6r1/ppr1p1k1/5Pp1/3b4/1P3P2/P2B4/2P4R/1K3R2 b - - 0 72" );
        position =  Transition.create( position ).change( "g7", "f6" );
        
        assertThat( "6r1/ppr1p3/5kp1/3b4/1P3P2/P2B4/2P4R/1K3R2 w - - 0 73" )
                  .as( "white king captures black pawn but black pawn has captured en passant before" )
                  .isEqualTo( position.toString() );
    }
    
    @Test
    @DisplayName("black castling but a rook has been already moved")
    public void change_BlackCastlingButARookHasAlreadyBeenMoved() {
        
        // e4 d6 d4 g6 Nc3 Bg7 Be3 Nf6 Qd2 Nbd7 O-O-O c6 f3 Qc7 g4 e5 h4 Nb6 h5 Be6
        // hxg6 hxg6 Rxh8+ Bxh8 dxe5 dxe5 Qh2 Bg7 Qh4 Nbd7 Nh3 Qa5 a3 O-O-O Qf2 Bf8 Nb1 Bc5 Ng5 Qb6
        Position position;
        
        position = Position.create( "r3k3/pp1n1pb1/2p1bnp1/q3p3/4P1PQ/P1N1BP1N/1PP5/2KR1B2 b kq - 0 34" );
        position =  Transition.create( position ).change( "e8", "c8" );
        
        assertThat( "2kr4/pp1n1pb1/2p1bnp1/q3p3/4P1PQ/P1N1BP1N/1PP5/2KR1B2 w - - 1 35" )
                  .as( "possible rooks can be already moved or captured" )
                  .isEqualTo( position.toString() );
    }
    
    @Test
    @DisplayName("black castling flags should be removed if king moves - castling is no longer possible for black")
    public void change_BlackCastlingShouldAlsoBeRemovedIfKingMoves() {
        
        // 1.e4 c5 2.Nf3 d6 3.d4 cxd4 4.Nxd4 Nf6 5.Nc3 g6 6.f4 Bg7 7.e5 dxe5 8.fxe5 Ng4 9.Bb5+ Kf8
        Position position;
        
        position = Position.create( "rnbqk2r/pp2ppbp/6p1/1B2P3/3N2n1/2N5/PPP3PP/R1BQK2R b KQkq - 2 18" );
        position =  Transition.create( position ).change( "e8", "f8" );
        
        assertThat( "rnbq1k1r/pp2ppbp/6p1/1B2P3/3N2n1/2N5/PPP3PP/R1BQK2R w KQ - 3 19" )
                  .as( "black king moves and the related castling flags should be removed" )
                  .isEqualTo( position.toString() );
    }
    
    @Test
    @DisplayName("when black rook is captured then the castling flags should be aligned")
    public void change_WhenBlackRookIsCapturedTheCastlingFalsShouldBeAligned() {
        
        // 1.e4 c6 2.d4 d5 3.exd5 cxd5 4.Bd3 Nc6 5.c3 g6 6.Ne2 Nh6 7.h4 Bf5 8.h5 Bxd3 9.Qxd3 Qd7 10.Bf4 Bg7
        // 11.Nd2 Nf5 12.O-O-O Nd6 13.hxg6 hxg6 14.Rxh8+ Bxh8  15.Rh1 O-O-O 16.Rh7 f5 17.Qg3 Qe6 18.Be3 Qg8 19.Rh6 e5 20.Rxg6 Qf7

        Position position;
        
        position = Position.create( "r3k2r/pp1qppb1/2nn2p1/3p4/3P1B2/2PQ4/PP1NNPP1/2KR3R w kq - 0 27" );
        position =  Transition.create( position ).change( "h1", "h8" );
        
        assertThat( "r3k2R/pp1qppb1/2nn2p1/3p4/3P1B2/2PQ4/PP1NNPP1/2KR4 b q - 0 28" )
                  .as( "black king moves and the related castling flags should be removed" )
                  .isEqualTo( position.toString() );
    }
    
    @Test
    @DisplayName("when white rooks can capture into the same direction to the same location then only one change is legal (legal move)")
    public void change_WhenBlackRooksCanCaptureIntoTheSameDirectionToTheSameLocationThenOnlyOneChangeIsLegal_LegalMove() {
        
        // Spassky - Fischer, WM 1972 (20)
        // 1.e4 c5 2.Nf3 Nc6 3.d4 cd4 4.Nd4 Nf6 5.Nc3 d6 6.Bg5 e6 7.Qd2 a6 8.O-O-O Bd7 9.f4 Be7 10.Be2 O-O
        // 11.Bf3 h6 12.Bh4 Ne4 13.Be7 Nd2 14.Bd8 Nf3 15.Nf3 Rfd8 16.Rd6 Kf8 17.Rhd1 Ke7 18.Na4 Be8 19.Rd8

        Position position;
        
        position = Position.create( "r2rb3/1p2kpp1/p1nRp2p/8/N4P1P/5N2/PPP3P1/2KR4 w - - 5 37" );
        position =  Transition.create( position ).change( "d6", "d8" );
        
        assertThat( "r2Rb3/1p2kpp1/p1n1p2p/8/N4P1P/5N2/PPP3P1/2KR4 b - - 0 38" )
                  .as( "white rook captures the related black rook" )
                  .isEqualTo( position.toString() );
    }
    
    @Test
    @DisplayName("when white rooks can capture into the same direction to the same location then only one change is legal (thrown exception)")
    public void change_WhenBlackRooksCanCaptureIntoTheSameDirectionToTheSameLocationThenOnlyOneChangeIsLegal_Exception() {
        
        // Spassky - Fischer, WM 1972 (20)
        // 1.e4 c5 2.Nf3 Nc6 3.d4 cd4 4.Nd4 Nf6 5.Nc3 d6 6.Bg5 e6 7.Qd2 a6 8.O-O-O Bd7 9.f4 Be7 10.Be2 O-O
        // 11.Bf3 h6 12.Bh4 Ne4 13.Be7 Nd2 14.Bd8 Nf3 15.Nf3 Rfd8 16.Rd6 Kf8 17.Rhd1 Ke7 18.Na4 Be8 19.Rd8

        Position position;
        
        position = Position.create( "r2rb3/1p2kpp1/p1nRp2p/8/N4P1P/5N2/PPP3P1/2KR4 w - - 5 37" );
        
        assertThatThrownBy( () ->  Transition.create( position ).change( "d1", "d8" ) )
                  .as( "a capturing white rook should only pass empty locations - if not an exception is thrown" )
                  .isInstanceOf( LocationIsOccupiedException.class );
    }
}
 

