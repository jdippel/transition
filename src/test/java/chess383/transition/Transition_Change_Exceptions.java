/*
 *  Transition_Change_Exceptions.java
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess383.position.Position;
import chess383.statemachine.exception.InputParameterAssertionViolationException;
import chess383.statemachine.exception.LocationIsOccupiedException;
import chess383.statemachine.exception.LocationIsVacantException;

/**
 * <p>
 * The class Transition_Change_Exceptions implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   November 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested if an exception occurs")
public class Transition_Change_Exceptions {  
  
    @Test
    @DisplayName("null is not a location - should be detected with a InputParameterAssertionViolationException exception")
    public void change_LocationIsNotDefinedForSource() {
        
        // "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2"
        
        Position position = Position.create( "r1b1k1nr/ppp2ppp/8/q1bPp3/2Bn4/2N1B3/PPP1NPPP/R2QK2R b KQkq - 4 16" );
        final String ORIGIN = null;
        assertThatThrownBy( () ->  Transition.create( position ).change( ORIGIN, "c3" ) )
                  .as( "lacation must describe a valid locationn" )
                  .isInstanceOf( InputParameterAssertionViolationException.class );
    }

    @Test
    @DisplayName("null is not a location - should be detected with a InputParameterAssertionViolationException exception")
    public void change_LocationIsNotDefinedForTarget() {

        // "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2"

        Position position = Position.create( "r1b1k1nr/ppp2ppp/8/q1bPp3/2Bn4/2N1B3/PPP1NPPP/R2QK2R b KQkq - 4 16" );
        final String TARGET = null;
        assertThatThrownBy( () ->  Transition.create( position ).change( "a5", TARGET ) )
                .as( "lacation must describe a valid locationn" )
                .isInstanceOf( InputParameterAssertionViolationException.class );
    }
    
    @Test
    @DisplayName("location does not describe a location - should be detected with a InputParameterAssertionViolationException exception")
    public void change_LocationIsEmpty() {
        
        // "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2"
        
        Position position = Position.create( "r1b1k1nr/ppp2ppp/8/q1bPp3/2Bn4/2N1B3/PPP1NPPP/R2QK2R b KQkq - 4 16" );
        final String ORIGIN = "  ";
        assertThatThrownBy( () ->  Transition.create( position ).change( ORIGIN, "c3" ) )
                  .as( "location must describe a valid location" )
                  .isInstanceOf( InputParameterAssertionViolationException.class );
    }
    
    @Test
    @DisplayName("location does not describe a defined location - should be detected with a InputParameterAssertionViolationException exception")
    public void change_LocationIsNotAVlidLocation() {
        
        // "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2"
        
        Position position = Position.create( "r1b1k1nr/ppp2ppp/8/q1bPp3/2Bn4/2N1B3/PPP1NPPP/R2QK2R b KQkq - 4 16" );
        final String ORIGIN = "q2";
        assertThatThrownBy( () ->  Transition.create( position ).change( ORIGIN, "c3" ) )
                  .as( "location may not be validated" )
                  .isInstanceOf( InputParameterAssertionViolationException.class );
    }
    
    @Test
    @DisplayName("source and target location are equal - should be detected with a LocationIsVacantException exception")
    public void change_SourceAndTargetLocationsAreEqualException_ForSource() {
        
        // "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2"
        
        Position position = Position.create( "r1b1k1nr/ppp2ppp/8/q1bPp3/2Bn4/2N1B3/PPP1NPPP/R2QK2R b KQkq - 4 16" );
        final String ORIGIN = "b5";
        assertThatThrownBy( () ->  Transition.create( position ).change( ORIGIN, ORIGIN ) )
                  .as( "both locations may not be the same" )
                  .isInstanceOf( InputParameterAssertionViolationException.class );
    }
    
    @Test
    @DisplayName("a not-existing piece captures the pinned knight - should be detected with a LocationIsVacantException exception")
    public void change_LocationIsVacantException_ForSuurce() {
        
        // "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2"
        
        Position position = Position.create( "r1b1k1nr/ppp2ppp/8/q1bPp3/2Bn4/2N1B3/PPP1NPPP/R2QK2R b KQkq - 4 16" );
        final String ORIGIN = "b5";
        assertThatThrownBy( () ->  Transition.create( position ).change( ORIGIN, "c3" ) )
                  .as( "on the origin location must be a piece" )
                  .isInstanceOf( LocationIsVacantException.class )
                  .hasMessageContainingAll( String.format( "there is no piece located on location %s", ORIGIN ) );
    }
    
    @Test
    @DisplayName("a piece captures a bishop of its own pieces - should be detected with a LocationIsOccupiedException exception")
    public void change_LocationIsOccupiedException_ForSource() {
        
        // "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2"
        
        Position position = Position.create( "r1b1k1nr/ppp2ppp/8/q1bPp3/2Bn4/2N1B3/PPP1NPPP/R2QK2R b KQkq - 4 16" );
        final String TARGET = "c5";
        assertThatThrownBy( () ->  Transition.create( position ).change( "a5", TARGET ) )
                  .as( "on the target location there must not be a piece of the same color (own pieces)" )
                  .isInstanceOf( LocationIsOccupiedException.class )
                  .hasMessageContainingAll( String.format( "location %s is occupied", TARGET ) );
    }
}
 

