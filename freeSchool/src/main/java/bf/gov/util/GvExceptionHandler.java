/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.util;

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 *
 * @author trasmiro
 */
public class GvExceptionHandler extends ExceptionHandlerWrapper {

    private final ExceptionHandler wrapped;

    public GvExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        // Iterate over all unhandeled exceptions
        Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            // obtain throwable object
            Throwable t = context.getException();

            try {
                // Que faire de l'exeception?
                try {
                    MessagesJsf.addException(t);
                } catch (Exception e) {
                }

                t.printStackTrace();
            } finally {
                i.remove();
            }
            // Let parent wrapper handle it
            this.getWrapped().handle();
        }

    }
}
