package org.zootella.cheat.encrypt.secret;

import javax.crypto.Cipher;

import org.zootella.cheat.data.Bin;
import org.zootella.cheat.exception.ProgramException;
import org.zootella.cheat.size.move.Move;
import org.zootella.cheat.state.Close;
import org.zootella.cheat.state.Task;
import org.zootella.cheat.state.TaskBody;
import org.zootella.cheat.state.Update;


public class SecretTask extends Close {

	public SecretTask(Update up, Cipher cipher, int mode, Bin source, Bin destination) {
		this.up = up; // We'll tell update when we're done
		this.cipher = cipher;
		this.mode = mode;
		this.source = source;
		this.destination = destination;
		task = new Task(new MyTask()); // Make a separate thread call thread() below now
	}
	
	private final Update up;
	private final Cipher cipher;
	private final int mode;
	private final Bin source;
	private final Bin destination;
	private final Task task;

	@Override public void close() {
		if (already()) return;
		close(task);
		up.send();
	}

	public Move result() { check(exception, data); return data; }
	private ProgramException exception;
	private Move data;
	
	/** Our Task with a thread that runs our code that blocks. */
	private class MyTask implements TaskBody {
		private Move taskMove; // References thread() can safely set

		// A separate thread will call this method
		public void thread() throws Exception {
			
			taskMove = Secret.process(cipher, mode, source, destination);
		}

		// Once thread() above returns, the normal event thread calls this done() method
		public void done(ProgramException e) {
			if (closed()) return; // Don't let anything change if we're already closed
			exception = e;        // Get the exception our code above threw
			data = taskMove;
			close(me());          // We're done
		}
	}
	private SecretTask me() { return this; } // Give inner code a link to the outer object
}
