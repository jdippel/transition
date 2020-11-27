/*
 *  Transition_EnPassant.java
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
import chess383.piece.concretion.king.InitialKing;
import chess383.piece.concretion.pawn.MovedBlackPawn;
import chess383.piece.concretion.pawn.MovedWhitePawn;
import chess383.piece.concretion.rook.Rook;
import chess383.player.Player;
import chess383.position.Castling;
import chess383.position.CastlingElement;
import chess383.position.Correlation;
import chess383.position.Position;

/**
 * <p>
 * The class Transition_EnPassant implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   July 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested for en passent pawn captures")
public class Transition_EnPassant {  
    
    @Test
    @DisplayName("White pawn captures black pawn en passant on kingside.")
    public void change_WhitePawnCapturesEnPassantKingside() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Rook.create( "h1" ),  MovedWhitePawn.create( "e5" ) ));
        Castling whiteCastling = Castling.create( Arrays.asList( CastlingElement.create( "h1", 'K' ) ) );
        Correlation whiteCorrelation = Correlation.create( whitePlayer, whiteCastling ).validate();
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), MovedBlackPawn.create( "f5" ) ));
        Castling blackCastling = Castling.createEmpty();
        Correlation blackCorrelation = Correlation.create( blackPlayer, blackCastling ).validate();
        
        Transition transition = Transition.create( Position.create( whiteCorrelation, blackCorrelation, ColorEnum.WHITE, "f6", 0, 101 ) );
        
        assertThat( "4k3/8/5P2/8/8/8/8/4K2R b K - 0 102" )
                .as( "the string representation of the new position should match" )
                .isEqualTo( transition.change( "e5", "f6" ).toString() );
    }
    
    @Test
    @DisplayName("White pawn captures black pawn en passant on queenside.")
    public void change_WhitePawnCapturesEnPassantQueenside() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Rook.create( "h1" ),  MovedWhitePawn.create( "g5" ) ));
        Castling whiteCastling = Castling.create( Arrays.asList( CastlingElement.create( "h1", 'K' ) ) );
        Correlation whiteCorrelation = Correlation.create( whitePlayer, whiteCastling ).validate();
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), MovedBlackPawn.create( "f5" ) ));
        Castling blackCastling = Castling.createEmpty();
        Correlation blackCorrelation = Correlation.create( blackPlayer, blackCastling ).validate();
        
        Transition transition = Transition.create( Position.create( whiteCorrelation, blackCorrelation, ColorEnum.WHITE, "f6", 0, 101 ) );
        
        assertThat( "4k3/8/5P2/8/8/8/8/4K2R b K - 0 102" )
                .as( "the string representation of the new position should match" )
                .isEqualTo( transition.change( "g5", "f6" ).toString() );
    }
    
    @Test
    @DisplayName("Black pawn captures black pawn en passant on queenside.")
    public void change_BlackPawnCapturesEnPassantQueenside() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Rook.create( "h1" ),  MovedWhitePawn.create( "b4" ) ));
        Castling whiteCastling = Castling.create( Arrays.asList( CastlingElement.create( "h1", 'K' ) ) );
        Correlation whiteCorrelation = Correlation.create( whitePlayer, whiteCastling ).validate();
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Rook.create( "a8" ), MovedBlackPawn.create( "c4" ) ));
        Castling blackCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8", 'q' ) ) );
        Correlation blackCorrelation = Correlation.create( blackPlayer, blackCastling ).validate();
        
        Transition transition = Transition.create( Position.create( whiteCorrelation, blackCorrelation, ColorEnum.BLACK, "b3", 0, 100 ) );
        
        assertThat( "r3k3/8/8/8/8/1p6/8/4K2R w Kq - 0 101" )
                .as( "the string representation of the new position should match" )
                .isEqualTo( transition.change( "c4", "b3" ).toString() );
    }
    
    @Test
    @DisplayName("Black pawn captures black pawn en passant on kingside.")
    public void change_BlackPawnCapturesEnPassantKingside() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Rook.create( "h1" ),  MovedWhitePawn.create( "b4" ) ));
        Castling whiteCastling = Castling.create( Arrays.asList( CastlingElement.create( "h1", 'K' ) ) );
        Correlation whiteCorrelation = Correlation.create( whitePlayer, whiteCastling ).validate();
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Rook.create( "a8" ), MovedBlackPawn.create( "a4" ) ));
        Castling blackCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8", 'q' ) ) );
        Correlation blackCorrelation = Correlation.create( blackPlayer, blackCastling ).validate();
        
        Transition transition = Transition.create( Position.create( whiteCorrelation, blackCorrelation, ColorEnum.BLACK, "b3", 0, 100 ) );
        
        assertThat( "r3k3/8/8/8/8/1p6/8/4K2R w Kq - 0 101" )
                .as( "the string representation of the new position should match" )
                .isEqualTo( transition.change( "a4", "b3" ).toString() );
    }
}


