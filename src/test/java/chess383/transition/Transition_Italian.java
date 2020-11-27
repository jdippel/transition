/*
 *  Transition_Italian.java
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
import chess383.piece.concretion.knight.Knight;
import chess383.piece.concretion.pawn.InitialBlackPawn;
import chess383.piece.concretion.pawn.InitialWhitePawn;
import chess383.piece.concretion.pawn.MovedBlackPawn;
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
 * The class Transition_Italian implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   June 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested for the Italian opening")
public class Transition_Italian {  
    
    @Test
    @DisplayName(" 1st white move( \"e2\", \"e4\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_001_e2_e4() {
        
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "g1" ), Bishop.create( "c1" ), Bishop.create( "f1" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), InitialWhitePawn.create( "c2" ), InitialWhitePawn.create( "d2" ),
                InitialWhitePawn.create( "e2" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "b8" ), Knight.create( "g8" ), Bishop.create( "c8" ), Bishop.create( "f8" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), InitialBlackPawn.create( "d7" ),
                InitialBlackPawn.create( "e7" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.WHITE, null, 0, 1 ) );
        
        assertThat( "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 2" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "e2", "e4" ).toString() );
    }
    
    @Test
    @DisplayName(" 1st black move( \"e7\", \"e5\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_002_e7_e5() {
        
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "g1" ), Bishop.create( "c1" ), Bishop.create( "f1" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), InitialWhitePawn.create( "c2" ), InitialWhitePawn.create( "d2" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "b8" ), Knight.create( "g8" ), Bishop.create( "c8" ), Bishop.create( "f8" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), InitialBlackPawn.create( "d7" ),
                InitialBlackPawn.create( "e7" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.BLACK, "e3", 0, 2 ) );
        
        assertThat( "rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 3" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "e7", "e5" ).toString() );
    }
    
    @Test
    @DisplayName(" 2nd white move( \"g1\", \"f3\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_003_g1_f3() {
        
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "g1" ), Bishop.create( "c1" ), Bishop.create( "f1" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), InitialWhitePawn.create( "c2" ), InitialWhitePawn.create( "d2" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "b8" ), Knight.create( "g8" ), Bishop.create( "c8" ), Bishop.create( "f8" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), InitialBlackPawn.create( "d7" ),
                MovedBlackPawn.create( "e5" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.WHITE, "e6", 0, 3 ) );
        
        assertThat( "rnbqkbnr/pppp1ppp/8/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 4" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "g1", "f3" ).toString() );
    }
    
    @Test
    @DisplayName(" 2nd black move( \"b8\", \"c6\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_004_b8_c6() {
        
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "f3" ), Bishop.create( "c1" ), Bishop.create( "f1" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), InitialWhitePawn.create( "c2" ), InitialWhitePawn.create( "d2" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "b8" ), Knight.create( "g8" ), Bishop.create( "c8" ), Bishop.create( "f8" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), InitialBlackPawn.create( "d7" ),
                MovedBlackPawn.create( "e5" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.BLACK, "-", 1, 4 ) );
        
        assertThat( "r1bqkbnr/pppp1ppp/2n5/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 2 5" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "b8", "c6" ).toString() );
    }
    
    @Test
    @DisplayName(" 3rd white move( \"f1\", \"c4\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_005_f1_c4() {
        
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "f3" ), Bishop.create( "c1" ), Bishop.create( "f1" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), InitialWhitePawn.create( "c2" ), InitialWhitePawn.create( "d2" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "c6" ), Knight.create( "g8" ), Bishop.create( "c8" ), Bishop.create( "f8" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), InitialBlackPawn.create( "d7" ),
                MovedBlackPawn.create( "e5" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.WHITE, null, 2, 5 ) );
        
        assertThat( "r1bqkbnr/pppp1ppp/2n5/4p3/2B1P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 3 6" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "f1", "c4" ).toString() );
    }
    
    @Test
    @DisplayName(" 3rd black move( \"f8\", \"c5\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_006_f8_c5() {
        
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "f3" ), Bishop.create( "c1" ), Bishop.create( "c4" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), InitialWhitePawn.create( "c2" ), InitialWhitePawn.create( "d2" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "c6" ), Knight.create( "g8" ), Bishop.create( "c8" ), Bishop.create( "f8" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), InitialBlackPawn.create( "d7" ),
                MovedBlackPawn.create( "e5" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.BLACK, "-", 3, 6 ) );
        
        assertThat( "r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 7" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "f8", "c5" ).toString() );
    }

    @Test
    @DisplayName(" 4th white move( \"c2\", \"c3\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_007_c2_c3() {
          
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "f3" ), Bishop.create( "c1" ), Bishop.create( "c4" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), InitialWhitePawn.create( "c2" ), InitialWhitePawn.create( "d2" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "c6" ), Knight.create( "g8" ), Bishop.create( "c8" ), Bishop.create( "c5" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), InitialBlackPawn.create( "d7" ),
                MovedBlackPawn.create( "e5" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.WHITE, null, 4, 7 ) );
        
        assertThat( "r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/2P2N2/PP1P1PPP/RNBQK2R b KQkq - 0 8" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "c2", "c3" ).toString() );
    }
    
    @Test
    @DisplayName(" 4th black move( \"g8\", \"f6\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_008_g8_f6() {
        
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "f3" ), Bishop.create( "c1" ), Bishop.create( "c4" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), MovedWhitePawn.create( "c3" ), InitialWhitePawn.create( "d2" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "c6" ), Knight.create( "g8" ), Bishop.create( "c8" ), Bishop.create( "c5" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), InitialBlackPawn.create( "d7" ),
                MovedBlackPawn.create( "e5" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.BLACK, "-", 0, 8 ) );
        
        assertThat( "r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/2P2N2/PP1P1PPP/RNBQK2R w KQkq - 1 9" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "g8", "f6" ).toString() );
    }

    @Test
    @DisplayName(" 5th white move( \"d2\", \"d3\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_009_d2_d3() {
          
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "f3" ), Bishop.create( "c1" ), Bishop.create( "c4" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), MovedWhitePawn.create( "c3" ), InitialWhitePawn.create( "d2" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "c6" ), Knight.create( "f6" ), Bishop.create( "c8" ), Bishop.create( "c5" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), InitialBlackPawn.create( "d7" ),
                MovedBlackPawn.create( "e5" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.WHITE, null, 1, 9 ) );
        
        assertThat( "r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/2PP1N2/PP3PPP/RNBQK2R b KQkq - 0 10" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "d2", "d3" ).toString() );
    }
    
    @Test
    @DisplayName(" 5th black move( \"d7\", \"d6\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_010_d7_d6() {
        
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "f3" ), Bishop.create( "c1" ), Bishop.create( "c4" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), MovedWhitePawn.create( "c3" ), MovedWhitePawn.create( "d3" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "c6" ), Knight.create( "f6" ), Bishop.create( "c8" ), Bishop.create( "c5" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), InitialBlackPawn.create( "d7" ),
                MovedBlackPawn.create( "e5" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.BLACK, "-", 0, 10 ) );
        
        assertThat( "r1bqk2r/ppp2ppp/2np1n2/2b1p3/2B1P3/2PP1N2/PP3PPP/RNBQK2R w KQkq - 0 11" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "d7", "d6" ).toString() );
    }
    
    @Test
    @DisplayName(" 6th white move( \"e1\", \"g1\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_011_e1_g1() {
          
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ),
                Knight.create( "b1" ), Knight.create( "f3" ), Bishop.create( "c1" ), Bishop.create( "c4" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), MovedWhitePawn.create( "c3" ), MovedWhitePawn.create( "d3" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.create( Arrays.asList( CastlingElement.create( "a1",  'Q' ), CastlingElement.create( "h1", 'K' ) ) );
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "c6" ), Knight.create( "f6" ), Bishop.create( "c8" ), Bishop.create( "c5" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), MovedBlackPawn.create( "d6" ),
                MovedBlackPawn.create( "e5" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.WHITE, null, 0, 11 ) );
        
        assertThat( "r1bqk2r/ppp2ppp/2np1n2/2b1p3/2B1P3/2PP1N2/PP3PPP/RNBQ1RK1 b kq - 1 12" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "e1", "g1" ).toString() );
    }
    
    @Test
    @DisplayName(" 6th black move( \"e8\", \"g8\" ): for the Italian opening the transition is verified.")
    public void move_ItalianOpening_012_e8_g8() {
        
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "g1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "f1" ),
                Knight.create( "b1" ), Knight.create( "f3" ), Bishop.create( "c1" ), Bishop.create( "c4" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), MovedWhitePawn.create( "c3" ), MovedWhitePawn.create( "d3" ),
                MovedWhitePawn.create( "e4" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" )  ));
        Castling firstCastling = Castling.createEmpty();
        Correlation firstCorrelation = Correlation.create( firstPlayer, firstCastling ).validate();
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "d8" ), Rook.create( "a8" ), Rook.create( "h8" ),
                Knight.create( "c6" ), Knight.create( "f6" ), Bishop.create( "c8" ), Bishop.create( "c5" ),
                InitialBlackPawn.create( "a7" ), InitialBlackPawn.create( "b7" ), InitialBlackPawn.create( "c7" ), MovedBlackPawn.create( "d6" ),
                MovedBlackPawn.create( "e5" ), InitialBlackPawn.create( "f7" ), InitialBlackPawn.create( "g7" ), InitialBlackPawn.create( "h7" ) ));
        Castling secondCastling = Castling.create( Arrays.asList( CastlingElement.create( "a8",  'q' ), CastlingElement.create( "h8", 'k' ) ) );
        Correlation secondCorrelation = Correlation.create( secondPlayer, secondCastling ).validate();
        
        Transition transition = Transition.create( Position.create( firstCorrelation, secondCorrelation, ColorEnum.BLACK, "-", 1, 12 ) );
        
        assertThat( "r1bq1rk1/ppp2ppp/2np1n2/2b1p3/2B1P3/2PP1N2/PP3PPP/RNBQ1RK1 w - - 2 13" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( transition.change( "e8", "g8" ).toString() );
    }    
}


