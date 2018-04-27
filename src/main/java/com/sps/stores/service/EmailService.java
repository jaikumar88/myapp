/**
 * 
 */
package com.sps.stores.service;

/**
 * @author Jai1.Kumar
 *
 */
public interface EmailService {

	public boolean send(String to,String msg,String sub);
}
