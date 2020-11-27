/*
 *  Transition_WeirdMoves.java
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess383.ColorEnum;
import chess383.piece.concretion.bishop.Bishop;
import chess383.piece.concretion.king.InitialKing;
import chess383.piece.concretion.pawn.InitialWhitePawn;
import chess383.piece.concretion.pawn.MovedWhitePawn;
import chess383.piece.concretion.queen.Queen;
import chess383.piece.concretion.rook.Rook;
import chess383.player.Player;
import chess383.position.Castling;
import chess383.position.CastlingElement;
import chess383.position.Correlation;
import chess383.position.Position;
import chess383.statemachine.exception.LocationIsOccupiedException;
import chess383.statemachine.exception.LocationIsVacantException;

/**
 * <p>
 * The class Transition_WeirdMoves implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   July 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested for castlings")
public class Transition_WeirdMoves {  
    
    @Test
    @DisplayName("Trying a change with empty origin.")
    public void change_EmptyOrigin() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Rook.create( "h1" ),  MovedWhitePawn.create( "d3" ), InitialWhitePawn.create( "g2" )  ));
        Castling whiteCastling = Castling.create( Arrays.asList( CastlingElement.create( "h1", 'K' ) ) );
        Correlation whiteCorrelation = Correlation.create( whitePlayer, whiteCastling ).validate();
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "h3" ), Rook.create( "g3" ), Bishop.create( "c4" ) ));
        Castling blackCastling = Castling.createEmpty();
        Correlation blackCorrelation = Correlation.create( blackPlayer, blackCastling ).validate();
        
        Transition transition = Transition.create( Position.create( whiteCorrelation, blackCorrelation, ColorEnum.WHITE, null, 0, 101 ) );
        
        assertThatThrownBy(() -> { transition.change( "f1", "c4" ).toString(); })
                .isExactlyInstanceOf( LocationIsVacantException.class )
                .hasMessageContainingAll( "f1" );
    }
    
    @Test
    @DisplayName("Trying a change with occupied target by himself.")
    public void change_OccupiedTargetByOwn() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Rook.create( "h1" ),  MovedWhitePawn.create( "d3" ), InitialWhitePawn.create( "h2" )  ));
        Castling whiteCastling = Castling.create( Arrays.asList( CastlingElement.create( "h1", 'K' ) ) );
        Correlation whiteCorrelation = Correlation.create( whitePlayer, whiteCastling ).validate();
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "h3" ), Rook.create( "g3" ), Bishop.create( "c4" ) ));
        Castling blackCastling = Castling.createEmpty();
        Correlation blackCorrelation = Correlation.create( blackPlayer, blackCastling ).validate();
        
        Transition transition = Transition.create( Position.create( whiteCorrelation, blackCorrelation, ColorEnum.WHITE, null, 0, 101 ) );
        
        assertThatThrownBy(() -> { transition.change( "h1", "h2" ).toString(); })
                .isExactlyInstanceOf( LocationIsOccupiedException.class )
                .hasMessageContainingAll( "h2" );
    }
}


