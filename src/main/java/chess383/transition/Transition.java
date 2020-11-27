/*
 *  Transition.java
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import chess383.ColorEnum;
import chess383.ICoordinateFactory;
import chess383.attack.Attack;
import chess383.piece.abstraction.Piece;
import chess383.piece.concretion.pawn.Pawn;
import chess383.player.Player;
import chess383.position.Castling;
import chess383.position.Correlation;
import chess383.position.EnPassantLocation;
import chess383.position.Position;
import chess383.statemachine.exception.EnPassantLocationViolationException;
import chess383.statemachine.exception.EnPassantRelatedPawnNotFoundException;
import chess383.statemachine.exception.InputParameterAssertionViolationException;
import chess383.statemachine.exception.KingWouldBeCheckedAfterwardsException;
import chess383.statemachine.exception.KingWouldPassLocationWithCheckException;
import chess383.statemachine.exception.LocationIsOccupiedException;
import chess383.statemachine.exception.LocationIsVacantException;
import chess383.statemachine.exception.NoRelatedRookFoundException;
import chess383.statemachine.exception.PieceTypeToLocationAssertionViolationException;

/**
 * Provides a change between two positions.
 *
 * @author    Jörg Dippel
 * @version   November 2020
 *
 */
public class Transition {
    
    final int CAPTURE_OR_PAWN_MOVE_RESET = 0;
    
    /** ---------  Attributes  -------------------------------- */
    
    private Position position;
    
    /** ---------  Accumulators - temporary attributes  ------- */
    
    ColorEnum color;
    Correlation ownCorrelation;
    Correlation opponentCorrelation;
    Correlation first;
    Correlation second;
    EnPassantLocation enPassantLocation;
    int numberOfPlys;
    Piece originPiece;
    
    /** ---------  Constructors  ------------------------------ */
    
    private Transition( Position position ) {
        setPosition( position );
    }
    
    /** ---------  Getter and Setter  ------------------------- */
    
    private void setPosition( Position value )    { this.position = value; }
    private Position getPosition( )               { return this.position; }
    
    private void setColor()                       { this.color = getPosition().getActivePlayer().getActive(); }
    private ColorEnum getColor()                  { return this.color; }
    private void setOwnCorrelation()              { this.ownCorrelation = getCorrelationFromColor( getColor() ); }
    private Correlation getOwnCorrelation()       { return this.ownCorrelation; }
    private void setOpponentCorrelation()         { this.opponentCorrelation = getCorrelationFromColor( getPosition().getActivePlayer().getInactive() ); }
    private Correlation getOpponentCorrelation()  { return this.opponentCorrelation; }
    private void setFirst( Correlation value )    { this.first = value; }
    private Correlation getFirst()                { return this.first; }
    private void setSecond( Correlation value)    { this.second = value; }
    private Correlation getSecond()               { return this.second; }
    private void setEnPassant( EnPassantLocation value )     { this.enPassantLocation = value; }
    private EnPassantLocation getEnPassant()                 { return this.enPassantLocation; }
    private void setNumberOfPlys( int value )     { this.numberOfPlys = value; }
    private int getNumberOfPlys()                 { return this.numberOfPlys; }
    private void setOriginPiece( String origin )  { this.originPiece = getOwnCorrelation().getPlayer().getPiece( origin ); }
    private Piece getOriginPiece()                { return this.originPiece; }
    
    /** ---------  Factory  ----------------------------------- */
    
    static public Transition create( Position position ) {
        
        return new Transition( position );
    }

    /** ---------  validations   ------------------------------ */
    
    private boolean locationExists( String location ) { return ICoordinateFactory.STANDARD.get( ).getAllLocations( ).contains( location ); }   
    private boolean isImpossibleLocation( String location ) { return location == null || location.trim().length() == 0; }
    private boolean isInvalidLocation( String location ) { return isImpossibleLocation( location ) || ! ( locationExists( location ) ); }
    private boolean isOccupiedByOpponentPiece( String location ) { return getOpponentCorrelation().getPlayer().getPiece( location ) instanceof Piece; }   
    private boolean isOccupiedByOwnPiece( String location ) { return getOwnCorrelation().getPlayer().getPiece( location ) instanceof Piece; }    
    private boolean isOccupied( String location ) { return isOccupiedByOpponentPiece( location ) || isOccupiedByOwnPiece( location ); }
    private boolean isVacantLocationFreeFromOpponentPiece( String location ) {  return ! isOccupiedByOpponentPiece( location ); }    
    private void validateLocation( String location ) throws InputParameterAssertionViolationException {
        if( isInvalidLocation( location ) ) InputParameterAssertionViolationException.throwStateMachineException( location );
    }
    private boolean isMatchOfLocations( String origin, String target ) { return origin.compareTo( target ) == 0; }    
    private void validateDisparity( String origin, String target ) {       
        if( isMatchOfLocations( origin, target ) ) { 
            InputParameterAssertionViolationException.throwStateMachineException( String.format( "parameter combination for locations (%s, %s)", origin, target ) );
        }
    } 
    private boolean isPiece( Piece piece ) { return piece instanceof Piece; }   
    private void validateOccupiedLocationByPiece( Piece piece, String location ) { if( ! isPiece( piece ) ) LocationIsVacantException.throwStateMachineException( location ); }  
    private void validateVacantLocationByPiece( Piece piece ) { if( isPiece( piece ) ) LocationIsOccupiedException.throwStateMachineException( piece.getLocation() ); }
    
    /** ------------------------------------------------------- */
    
    public Position change( String origin, String target ) {
        
        validateLocation( origin );
        validateLocation( target );
        validateDisparity( origin, target );
        
        setColor();
        setOwnCorrelation();
        setOpponentCorrelation();
        setOriginPiece( origin );
        
        validateOccupiedLocationByPiece( getOriginPiece(), origin );
        Piece piece = getOwnCorrelation().getPlayer().getPiece( target );
        validateVacantLocationByPiece( piece );
        
        if( getOriginPiece().isPawn() && isEnpassant( target ) ) return changeForEnpassant( origin, target );
        if( getOriginPiece().isKing() ) return changeForKing( origin, target );
        
        return changeForDefault( origin, target );
    }
    
    private Correlation getCorrelationFromColor( ColorEnum color ) {
        
        return ( ColorEnum.WHITE == color ) ? getPosition().getFirst() : getPosition().getSecond();
    }

    private boolean isEnpassant( String target ) {
        
        if( isImpossibleLocation( target ) ) return false;
        String enPassantLocation = getPosition().getEnPassantLocation().getLocation();
        if( isImpossibleLocation( enPassantLocation ) ) return false;
        
        if( isMatchOfLocations( target, enPassantLocation ) ) {

            Set<List<String>> lines = getOriginPiece().getCapturingLines( ); 
            for( List<String> line : lines ) {
                if( line.contains( target ) ) {
                    return isVacantLocationFreeFromOpponentPiece( target );
                }
            }
        }
        return false;
    }
    
    private Position changeForEnpassant( String origin, String target ) {
        
        Player opponentplayer = getOpponentCorrelation().getPlayer();
        String opponentsPawnLocation = findCapturedEnPassantPawnCandidate( Pawn.create( getOpponentCorrelation().getColor(), target ), opponentplayer ).getLocation() ;
        Player ownPlayer = getOwnCorrelation().getPlayer().replace( origin, target );
        opponentplayer = opponentplayer.remove( opponentsPawnLocation );
        setEnPassant( EnPassantLocation.createEmptyPlaceholder() );
        setNumberOfPlys( CAPTURE_OR_PAWN_MOVE_RESET );
        
        if( getColor() == ColorEnum.WHITE ) {
            setFirst(  Correlation.create( ownPlayer, getOwnCorrelation().getCastling() ));
            setSecond( Correlation.create( opponentplayer, getOpponentCorrelation().getCastling() ));
        }
        else {
            setFirst(  Correlation.create( opponentplayer, getOpponentCorrelation().getCastling() ));
            setSecond( Correlation.create( ownPlayer, getOwnCorrelation().getCastling() ));
        }
        
        return verifyPosition( createPosition(), findKingLocation( ownPlayer ));
    }
 
    private Pawn findCapturedEnPassantPawnCandidate( Pawn pawn, Player player ) {
        
        String location;
        Set<List<String>> lines = pawn.getMovingLines( ); 
        for( List<String> line : lines ) {
            Iterator<String> iterator = line.iterator();
            if( iterator.hasNext() ) location = iterator.next();   // this is enPassantLocation
            while( iterator.hasNext() ) {
                location = iterator.next();
                Piece piece = player.getPiece( location );
                if( ! isPiece( piece ) ) {
                    continue;
                }
                else if( piece.isPawn() ) {
                    return (Pawn) piece;
                }
                else {
                    EnPassantLocationViolationException.throwStateMachineException( location );
                }
            }
        }
        
        EnPassantRelatedPawnNotFoundException.throwStateMachineException( pawn.getLocation() );
        return null;
    }
    
    private Position changeForKing( String origin, String target ) {

        Set<List<String>> lines = getOriginPiece().getMovingLines( ); 
        for( List<String> line : lines ) {
            if( line.contains( target ) ) {
                if( lineMatchesCastlingLocation( line, target ) ) {
                    return changeForCastling( line, origin, target );
                }
                return changeForDefault( origin, target );
            }
        }
        return Position.createEmptyPlaceholder();
    }
    
    private boolean lineMatchesCastlingLocation( List<String> line, String castlingLocation ) {
        
        final int LOCATION_AFTER_NEXT_DISTANCE_FOR_CASTLING = 2;
        
        return line.size() > LOCATION_AFTER_NEXT_DISTANCE_FOR_CASTLING && isMatchOfLocations( castlingLocation, line.get( LOCATION_AFTER_NEXT_DISTANCE_FOR_CASTLING ) );
    }

    
    private Position changeForCastling( List<String> kingLine, String origin, String target ) {
        
        String location;
        String kingsOriginLocation = origin;
        String kingsTargetLocation = target;
        String rookOriginLocation = "";
        String rookTargetLocation = "";
        
        List<String> rookLine = getRookLine( origin, target );
        Iterator<String> iterator = rookLine.iterator();
        if( iterator.hasNext() ) {
            rookOriginLocation = iterator.next();
        }
        else {
            NoRelatedRookFoundException.throwStateMachineException( kingsOriginLocation );
        }
        while( iterator.hasNext() ) {
            location = iterator.next();
            if( isMatchOfLocations( location, origin ) ) break;
            if( isPiece( getOwnCorrelation().getPlayer().getPiece( location ) ) )
                LocationIsOccupiedException.throwStateMachineException( location );
            if( isPiece( getOpponentCorrelation().getPlayer().getPiece( location ) ) )
                LocationIsOccupiedException.throwStateMachineException( location );
        }
        
        Attack attackingPosition = Attack.create( getOwnCorrelation().getPlayer(), getOpponentCorrelation().getPlayer() );
        iterator = kingLine.iterator();
        while( iterator.hasNext() ) {
            location = iterator.next();
            if( attackingPosition.isAttacked( location )) {
                KingWouldPassLocationWithCheckException.throwStateMachineException( location );
            }
            if( isMatchOfLocations( location, kingsTargetLocation ) ) break;
            rookTargetLocation = location;
        }

        Player player = getOwnCorrelation().getPlayer().replace( kingsOriginLocation, kingsTargetLocation ).replace( rookOriginLocation, rookTargetLocation );
        Castling castling = Castling.createEmpty();
        setEnPassant( EnPassantLocation.createEmptyPlaceholder() );
        setNumberOfPlys( getPosition().getNumberOfPlys() + 1 );
        
        if( getColor() == ColorEnum.WHITE ) {
            setFirst( Correlation.create( player, castling ));
            setSecond( getPosition().getSecond() );
        }
        else {
            setFirst( getPosition().getFirst() );
            setSecond( Correlation.create( player, castling ));
        }
        
        return verifyPosition( createPosition(), kingsTargetLocation );
    }
    
    private List<String> getRookLine( String origin, String target ) {
        
        Piece piece;
        String location;
        Set<String> rooks = getOwnCorrelation().getCastling().getLocations();
        for( String rookOrigin : rooks ) {
            piece = getOwnCorrelation().getPlayer().getPiece( rookOrigin );
            if( ! isPiece( piece ) ) continue;    // rook may have been moved already;
            if( ! ( piece.isRook() )) {
                PieceTypeToLocationAssertionViolationException.throwStateMachineException( rookOrigin );
            }
            for( List<String> line : piece.getMovingLines() ) {
                if( line.contains( origin )) {
                    Iterator<String> iterator = line.iterator();
                    while( iterator.hasNext() ) {
                        location = iterator.next();
                        if( isMatchOfLocations( location, target ) ) return line;
                        if( isMatchOfLocations( location, origin ) ) break;
                    }
                }
            }
        }
        return new ArrayList<String>();
    }
    
    private Position changeForDefault( String origin, String target ) {
    
        Piece targetPiece = getOpponentCorrelation().getPlayer().getPiece( target );
        if( ! isPiece( targetPiece ) ) {  // normal move
            
            String location;
            Set<List<String>> lines = originPiece.getMovingLines( ); 
            for( List<String> line : lines ) {
                if( line.contains( target )) {
                    Iterator<String> iterator = line.iterator();
                    if( iterator.hasNext() ) location = iterator.next();
                    while( iterator.hasNext() ) {
                        location = iterator.next();
                        if( isMatchOfLocations( location, target ) ) {
                            
                            Piece piece = ownCorrelation.getPlayer().getPiece( origin );
                            Player player = ownCorrelation.getPlayer().replace( origin, target );
                            Castling castling = ownCorrelation.getCastling();
                            setEnPassant( EnPassantLocation.createEmptyPlaceholder() );
                            setNumberOfPlys( getPosition().getNumberOfPlys() + 1 );
                            
                            if( piece.isRook() ) {
                                // possible correction of castling flags
                                castling = castling.remove( origin ); 
                            }
                            else if( piece.isPawn() ) {
                                overruleEnPassantLocation( line, target );
                                setNumberOfPlys( CAPTURE_OR_PAWN_MOVE_RESET );
                            }
                            else if( piece.isKing() ) {
                                castling = Castling.createEmpty();
                            }
                            if( getColor() == ColorEnum.WHITE ) {
                                setFirst( Correlation.create( player, castling ));
                                setSecond( getPosition().getSecond() );
                            }
                            else {
                                setFirst( getPosition().getFirst() );
                                setSecond( Correlation.create( player, castling ));
                            }
                            
                            return verifyPosition( createPosition(), findKingLocation( player ));
                        }
                        else if( isOccupied( location ) ) {
                            LocationIsOccupiedException.throwStateMachineException( location );
                        }
                    }
                }
            }
            
        }
        else { // capture
            Player opponentPlayer = getOpponentCorrelation().getPlayer();
            Piece opponentPiece = opponentPlayer.getPiece( target );
            Castling opponentCastling = getOpponentCorrelation().getCastling();
            Player ownPlayer = getOwnCorrelation().getPlayer().replace( origin, target );
            Castling ownCastling = getOwnCorrelation().getCastling();
            if( getOriginPiece().isKing() ) {
                ownCastling = Castling.createEmpty();
            }
            else if( getOriginPiece().isRook() ) {
                ownCastling = ownCastling.remove( origin );
            }
            if( opponentPiece.isRook() ) {
                opponentCastling = opponentCastling.remove( target );
            }
            
            opponentPlayer = opponentPlayer.remove( target );
            setEnPassant( EnPassantLocation.createEmptyPlaceholder() );
            setNumberOfPlys( 0 );
            
            if( getColor() == ColorEnum.WHITE ) {
                setFirst(  Correlation.create( ownPlayer, ownCastling ));
                setSecond( Correlation.create( opponentPlayer, opponentCastling ));
            }
            else {
                setFirst(  Correlation.create( opponentPlayer, opponentCastling ));
                setSecond( Correlation.create( ownPlayer, ownCastling ));
            }
            
            return verifyPosition( createPosition(), findKingLocation( ownPlayer ));
        }
        
        return Position.createEmptyPlaceholder();
    }
    
    private String findKingLocation( Player player ) {
        
        return( player.getKingLocation() );
    }
    
    private Position verifyPosition( Position position, String kingLocation ) {
    
        Attack attackingPosition;
        if( position.getActivePlayer().getActive() == ColorEnum.BLACK ) {
            attackingPosition = Attack.create( position.getFirst().getPlayer(), position.getSecond().getPlayer() );
        }
        else {
            attackingPosition = Attack.create( position.getSecond().getPlayer(), position.getFirst().getPlayer() );
        }
        
        if( attackingPosition.isAttacked( kingLocation )) {
            KingWouldBeCheckedAfterwardsException.throwStateMachineException( kingLocation );
            return getPosition();
        }
        return position;
    }
    
    private void overruleEnPassantLocation( List<String> line, String target ) {
        
        String location = "";
        Iterator<String> iterator = line.iterator();
        if( iterator.hasNext() ) location = iterator.next();          // this is the original starting location
        if( iterator.hasNext() ) location = iterator.next();          // this is the follow up
        if( ! isMatchOfLocations( target, location ) ) {              // this means follow-up and target are differing
            setEnPassant( EnPassantLocation.create( location ) );     //  overruling
        }   
    }
    
    private Position createPosition() {
        
        ColorEnum nextColor = getPosition().getActivePlayer().getInactive();
        Position position = Position.create( getFirst(), getSecond(), nextColor, getEnPassant().getLocation(), getNumberOfPlys(), getPosition().getNumberOfMoves() + 1 );
        return position;
    }
}

