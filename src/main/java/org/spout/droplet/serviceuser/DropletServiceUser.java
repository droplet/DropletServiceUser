/*
 * This file is part of DropletServiceUser.
 *
 * Copyright (c) 2013 Spout LLC <http://www.spout.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.spout.droplet.serviceuser;

import java.util.logging.Level;

import org.spout.api.UnsafeMethod;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.server.service.ServiceRegisterEvent;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.plugin.ServiceProvider;
import org.spout.api.plugin.services.EconomyService;
import org.spout.api.plugin.services.ProtectionService;

public class DropletServiceUser extends CommonPlugin implements Listener {

	// Services, which we want to use in our plugin
	private EconomyService economyService;
	private ProtectionService protectionService;

	@Override
	@UnsafeMethod
	public void onDisable() {
		getLogger().log(Level.INFO, "Disabled.");
	}

	@Override
	@UnsafeMethod
	public void onEnable() {

		// Register the listener, in this case this class
		getEngine().getEventManager().registerEvents(this, this);

		// Done
		getLogger().log(Level.INFO, "Enabled.");
	}

	@EventHandler(order = Order.LATEST)
	public void serviceEvent(ServiceRegisterEvent event) {

		/*
		 * 1. We get the provider of the service, which is being registered 
		 * 2. We check if the service which is associated with the provider is what we want 
		 * 3. We initialize our variables for further use
		 */

		// Get the service provider
		ServiceProvider<?> serviceProvider = event.getProvider();

		// Check if the service is of type EconomyService
		if (serviceProvider.getService().equals(EconomyService.class)) {

			// Initialize our economyService with the provider of the EconomyService
			economyService = (EconomyService) serviceProvider.getProvider();

			// Note: Here is a good place to instantiate your classes which need the economyService in order to work.

		} else if (serviceProvider.getService().equals(ProtectionService.class)) {

			// Initialize our protectionService with the provider of the ProtectionService
			protectionService = (ProtectionService) serviceProvider.getProvider();

			// Note: Here is a good place to instantiate your classes which need the protectionService in order to work.

		}
	}

}