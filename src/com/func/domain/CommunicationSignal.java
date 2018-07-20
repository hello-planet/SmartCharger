package com.func.domain;

import java.util.ArrayList;

/**
 * <pre>
 *  
 * Signals for communication among app/client, server and pool.
 * 
 * <b>Payment Process</b>
 * <b>TRANS_WAITING</b>   =1 After trans verified by the pool/chain, waiting for activation of charger. 
 * <b>TRANS_RUNNING</b>   =2 After trans successfully-started, waiting for query from /ExecTrans. 
 * <b>TRANS_FINISHED</b>  =3 After trans successfully-finished, waiting for query from app/client.
 * <b>TRANS_EXCEPTION</b> =0 Exception occurs.
 * </pre>
 * 
 * @author helloplanet
 * @create 2018.3.15
 */
public class CommunicationSignal {
	/**
	 * Signals for payment process
	 */
	public static final int TRANS_NULL = 100;
	public static final int TRANS_WAITING = 101;
	public static final int TRANS_RUNNING = 102;
	public static final int TRANS_FINISHED = 103;
	public static final int TRANS_EXCEPTION = 104;

	/**
	 * To get the list of signal and its description.
	 */
	public ArrayList<String> getSignalList() {
		return null;
	}
}
