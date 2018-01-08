/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bf.gov.util;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author trasmiro
 */
public class GvExceptionHandlerFactory extends ExceptionHandlerFactory{

    private final ExceptionHandlerFactory parent;

    public GvExceptionHandlerFactory(ExceptionHandlerFactory parent) {
    	this.parent = parent;
    }

    // Inject our own ExceptionHandler
    @Override
    public ExceptionHandler getExceptionHandler() {
    	return new GvExceptionHandler( parent.getExceptionHandler());
    }

}
