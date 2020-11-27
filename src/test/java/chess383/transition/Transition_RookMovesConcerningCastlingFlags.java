/*
 *  Transition_RookMovesConcerningCastlingFlags.java
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

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess383.ColorEnum;
import chess383.piece.concretion.bishop.Bishop;
import chess383.piece.concretion.king.InitialKing;
import chess383.piece.concretion.pawn.InitialBlackPawn;
import chess383.piece.concretion.pawn.InitialWhitePawn;
import chess383.piece.concretion.pawn.MovedWhitePawn;
import chess383.piece.concretion.queen.Queen;
import chess383.piece.concretion.rook.Rook;
import chess383.player.Player;
import chess383.position.Castling;
import chess383.position.CastlingElement;
import chess383.position.Correlation;
import chess383.position.Position;

/**
 * <p>
 * The class Transition_RookMovesConcerningCastlingFlags implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   July 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested for rooks in combination with castling flags")
public class Transition_RookMovesConcerningCastlingFlags {  
    
    @Test
    @DisplayName("white rook moves if kingside castling is possible.")
    public void change_WhiteRookMovesIfKingsideCastlingIsPossible() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Rook.create( "h1" ),  MovedWhitePawn.create( "d3" ), InitialWhitePawn.create( "g2" )  ));
        Castling whiteCastling = Castling.create( Arrays.asList( CastlingElement.create( "h1", 'K' ) ) );
        Correlation whiteCorrelation = Correlation.create( whitePlayer, whiteCastling ).validate();
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "h3" ), Rook.create( "g3" ), Bishop.create( "c4" ) ));
        Castling blackCastling = Castling.createEmpty();
        Correlation blackCorrelation = Correlation.create( blackPlayer, blackCastling ).validate();
        
        Transition transition = Transition.create( Position.create( whiteCorrelation, blackCorrelation, ColorEnum.WHITE, null, 0, 101 ) );
        
        assertThat( "4k3/8/8/8/2b5/3P2rq/6P1/4K1R1 b - - 1 102" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "h1", "g1" ).toString() );
    }
    
    @Test
    @DisplayName("white rook moves if queenside castling is possible")
    public void change_WhiteRookMovesIfQueensideCastlingIsPossible() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Rook.create( "a1" ) ));
        Castling whiteCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1", 'Q' ) ) );
        Correlation whiteCorrelation = Correlation.create( whitePlayer, whiteCastling ).validate();
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ) ));
        Castling blackCastling = Castling.createEmpty();
        Correlation blackCorrelation = Correlation.create( blackPlayer, blackCastling ).validate();
        
        Transition transition = Transition.create( Position.create( whiteCorrelation, blackCorrelation, ColorEnum.WHITE, null, 0, 101 ) );
        
        assertThat( "4k3/8/8/8/8/8/8/2R1K3 b - - 1 102" )
                .as( "the string representation of the new position should match" )
                .isEqualTo( transition.change( "a1", "c1" ).toString() );
    }
    
    @Test
    @DisplayName("black rook moves if queenside castling is possible.")
    public void change_BlackRookMovesIfgQueensideCastlingIsPossible() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Bishop.create( "f4" ) ));
        Castling whiteCastling = Castling.createEmpty();
        Correlation whiteCorrelation = Correlation.create( whitePlayer, whiteCastling ).validate();
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Rook.create( "a8" ), InitialBlackPawn.create( "e7" ) ));
        Castling blackCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8", 'q' ) ));
        Correlation blackCorrelation = Correlation.create( blackPlayer, blackCastling ).validate();
        
        Transition transition = Transition.create( Position.create( whiteCorrelation, blackCorrelation, ColorEnum.BLACK, null, 0, 100 ) );
        
        assertThat( "2r1k3/4p3/8/8/5B2/8/8/4K3 w - - 1 101" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "a8", "c8" ).toString() );
    }
    
    @Test
    @DisplayName("black rook moves if kingside castling is possible.")
    public void change_BlackRookMovesIfKingsideCastlingIsPossible() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Bishop.create( "c4" ) ));
        Castling whiteCastling = Castling.createEmpty();
        Correlation whiteCorrelation = Correlation.create( whitePlayer, whiteCastling ).validate();
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Rook.create( "h8" ), InitialBlackPawn.create( "e7" ) ));
        Castling blackCastling = Castling.create( Arrays.asList( CastlingElement.create( "h8", 'k' ) ));
        Correlation blackCorrelation = Correlation.create( blackPlayer, blackCastling ).validate();
        
        Transition transition = Transition.create( Position.create( whiteCorrelation, blackCorrelation, ColorEnum.BLACK, null, 0, 100 ) );
        
        assertThat( "4k1r1/4p3/8/8/2B5/8/8/4K3 w - - 1 101" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "h8", "g8" ).toString() );
    }
}


