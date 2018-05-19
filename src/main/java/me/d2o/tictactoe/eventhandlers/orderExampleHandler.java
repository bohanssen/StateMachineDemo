/**
 *
 */
package me.d2o.tictactoe.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.d2o.tictactoe.config.Events;
import me.d2o.statemachine.core.MachineEvent;
import me.d2o.statemachine.eventhandler.MachineEventHandler;

@Service
@Transactional
public class orderExampleHandler extends MachineEventHandler {

	
	@Override
	public void handleEvent(MachineEvent event) {
		System.out.println("Next Round...");
	}

	@Override
	public String eventType() {
		return Events.PLAY;
	}

	/*
	 * Overide the listner method to add extra logic or anotations
	 * in this case we added the Order anotation giving this eventhandler 
	 * presedence over the others (default presedence is 100)
	 */
	@Order(0)
	@Override
	@EventListener
	protected void listner(MachineEvent event) {
		super.listner(event);
	}
}
