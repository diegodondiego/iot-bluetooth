/**
 * 
 */
package br.issgc.tests.hello;

import static org.junit.Assert.assertTrue;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;

import org.junit.Test;

/**
 * Just a brainstorm to work with bluetooth and Java8 using bluecove
 * 
 * one of the problems is that problably this is not 'low energy':
 * https://software.intel.com/en-us/java-for-bluetooth-le-apps
 * 
 * some links:
 * https://learn.adafruit.com/introduction-to-bluetooth-low-energy?view=all
 * https://dbus.freedesktop.org/doc/dbus-specification.html
 * 
 *
 * @author dinhego
 *
 */
public class UsingBlueCove {

	/**
	 * Using the java api to do that.
	 * 
	 * @throws BluetoothStateException
	 */
	@Test
	public void connectToBluetooth() throws BluetoothStateException {
		
		LocalDevice device = LocalDevice.getLocalDevice();

		assertTrue(device != null && LocalDevice.isPowerOn());

	}

}
