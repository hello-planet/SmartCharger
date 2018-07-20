package com.func.domain;

import java.util.ArrayList;
import java.util.HashMap;

import com.func.service.DataEntity;
import com.func.util.Encoder;

/**
 * Trans entity<br>
 * 
 * @param trans_id
 *            SHA1(charger_id | usr_id | date_time)<br>
 * @param date_time
 *            format : YYYY:MM:DD HH:MM:SS<br>
 * 
 * @author helloplanet
 * @create 2018.4.8
 *
 */
public class TransEntity extends DataEntity{

	private String transID;
	private String powerVol;
	private String cashVol;
	private String chargerID;
	private String usrID;
	private String dateTime;

	public TransEntity() {

	}

	public TransEntity(HashMap<String, String> trans) {
		this.initialTrans(trans);
	}

	public TransEntity(ArrayList<String> trans) {
		this.initialTrans(trans);
	}

	/**
	 * To set the trans entity with the Hashmap input<br>
	 */
	public void initialTrans(HashMap<String, String> trans) {
		this.powerVol = trans.get("power_vol");
		this.cashVol = trans.get("cash_vol");
		this.chargerID = trans.get("charger_id");
		this.usrID = trans.get("usr_id");
		this.dateTime = trans.get("date_time");
		if (trans.containsKey("trans_id")) {
			this.transID = trans.get("trans_id");
		} else {
			this.transID = Encoder.sha1Text(this.chargerID + this.usrID + this.dateTime);
		}
	}

	/**
	 * To set the trans entity with the ArrayList input<br>
	 * trans format
	 * [trans_id,power_vol,cash_vol,charger_id,usr_id,date_time]<br>
	 * or<br>
	 * trans format [power_vol,cash_vol,charger_id,usr_id,date_time]<br>
	 */
	public void initialTrans(ArrayList<String> trans) {
		if (trans.size() == 5) {
			this.powerVol = trans.get(0);
			this.cashVol = trans.get(1);
			this.chargerID = trans.get(2);
			this.usrID = trans.get(3);
			this.dateTime = trans.get(4);
			this.transID = Encoder.sha1Text(this.chargerID + this.usrID + this.dateTime);
		} else {
			this.transID = trans.get(0);
			this.powerVol = trans.get(1);
			this.cashVol = trans.get(2);
			this.chargerID = trans.get(3);
			this.usrID = trans.get(4);
			this.dateTime = trans.get(5);
		}
	}

	/**
	 * Getters and Setters
	 */

	public String getTransID() {
		return transID;
	}

	public void setTransID() {
		this.transID = Encoder.sha1Text(this.chargerID + this.usrID + this.dateTime);
	}

	public String getPowerVol() {
		return powerVol;
	}

	public void setPowerVol(String powerVol) {
		this.powerVol = powerVol;
	}

	public String getCashVol() {
		return cashVol;
	}

	public void setCashVol(String cashVol) {
		this.cashVol = cashVol;
	}

	public String getChargerID() {
		return chargerID;
	}

	public void setChargerID(String chargerID) {
		this.chargerID = chargerID;
	}

	public String getUsrID() {
		return usrID;
	}

	public void setUsrID(String usrID) {
		this.usrID = usrID;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Methods for getting the object transentity in different forms
	 */
	
	/**
	 * To return trans in String with full details including ID<br>
	 * Trans format<br>
	 * "trans_id.power_vol.cash_vol.charger_id.usr_id.date_time"<br>
	 * Especially for the usage of being recorded in blockchain.
	 */
	@Override
	public String toStringForm() {
		String trans = new String(this.transID + "." + this.powerVol + "." + this.cashVol + "." + this.chargerID + "."
				+ this.usrID + "." + this.dateTime);
		return trans;
	}

	/**
	 * To return trans in HashMap with full details including ID
	 */
	@Override
	public HashMap<String, String> toMapForm() {
		HashMap<String, String> trans = new HashMap<String, String>(6, 1);
		trans.put("trans_id", this.getTransID());
		trans.put("power_vol", this.getPowerVol());
		trans.put("cash_vol", this.getCashVol());
		trans.put("charger_id", this.getChargerID());
		trans.put("usr_id", this.getUsrID());
		trans.put("date_time", this.getDateTime());
		return trans;
	}

	/**
	 * To return trans in ArrayList with full details including ID trans format
	 * [trans_id,power_vol,cash_vol,charger_id,usr_id,date_time]<br>
	 */
	@Override
	public ArrayList<String> toListForm() {
		ArrayList<String> trans = new ArrayList<String>(6);
		trans.add(this.transID);
		trans.add(this.powerVol);
		trans.add(this.cashVol);
		trans.add(this.chargerID);
		trans.add(this.usrID);
		trans.add(this.dateTime);
		return trans;
	}
}