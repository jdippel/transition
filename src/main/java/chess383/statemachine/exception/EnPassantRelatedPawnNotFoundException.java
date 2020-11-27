/*
 *  EnPassantLocationViolationException.java.java
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

package chess383.statemachine.exception;

/**
 * This class defines a derived exception.
 *
 * @author    Jörg Dippel
 * @version   July 2020
 * 
 */
public class EnPassantRelatedPawnNotFoundException extends StateMachineException {

    /**
     * exception is derived from Exception which implements interface java.io.Serializable
     */
    private static final long serialVersionUID = -2598333260560099851L;

    /**
     * Constructor creates a special exception
     */    
    public EnPassantRelatedPawnNotFoundException( ) { }
    
    /**
     * Constructor creates a special exception
     * 
     * @param message is a description of the exception
     */    
    public EnPassantRelatedPawnNotFoundException( String message ) {
        
        super( message );
    }
    
    public static void throwStateMachineException( String location ) throws StateMachineException {
        throw new EnPassantRelatedPawnNotFoundException( String.format( "for en passant location %s there is no related pawn", location ) );
    }
}
