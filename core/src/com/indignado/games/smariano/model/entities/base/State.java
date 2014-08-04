package com.indignado.games.smariano.model.entities.base;


public interface State {
    /**
     * Standard {@code Enum} method.
     */
    String name() ;

    /**
     * Standard {@code Enum} method.
     */
    int ordinal() ;

    float getStateTimeMin();

}
