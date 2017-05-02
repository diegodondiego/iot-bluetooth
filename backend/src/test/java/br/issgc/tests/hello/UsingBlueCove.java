/**
 * 
 */
package br.issgc.tests.hello;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

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

		assertTrue(device != null);

	}

	/**
	 * Using the bluecove to find a device
	 * 
	 * @throws BluetoothStateException
	 * @throws InterruptedException 
	 */
	@Test
	public void learningHowToSearch() throws BluetoothStateException, InterruptedException {
		
		final Object waitingObject = new Object();
		
		LocalDevice device = LocalDevice.getLocalDevice();

		device.getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, new DiscoveryListener() {

			public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
				System.out.println("service discovered: " + transID);

			}

			public void serviceSearchCompleted(int transID, int respCode) {
				System.out.println("service search completed: " + transID);
				System.out.println("service search completed (response code): " + respCode);
			}

			public void inquiryCompleted(int discType) {
				System.out.println("inquiry completed: " + discType);
				synchronized (waitingObject) {
					waitingObject.notifyAll();
				}
			}

			public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
				try {
					System.out.println("device discovered: " + btDevice.getFriendlyName(true));
				} catch (IOException e) {
					System.out.println("couldn't find name for address: " + btDevice.getBluetoothAddress());
				}

			}
		});
		
		
		synchronized (waitingObject) {
			System.out.println("starting the search...");
			waitingObject.wait();
			System.out.println("search completed");
		}
	}
}
