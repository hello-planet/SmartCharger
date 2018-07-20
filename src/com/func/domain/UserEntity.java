package com.func.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.func.service.DataEntity;
import com.func.util.Encoder;

/**
 * User Entity<br>
 * 
 * @params usr_pw sha1( plaintext of usr password )<br>
 * 
 * @author helloplanet
 * @create 2018.4.25
 *
 */
public class UserEntity extends DataEntity {

	private String usr_id;
	private String usr_pw;
	private Boolean owner_flg = false;

	public UserEntity() {

	}

	public UserEntity(HashMap<String, Object> usr_data) {
		this.initialUsrData(usr_data);
	}

	public UserEntity(List<Object> usr_data) {
		this.initialUsrData(usr_data);
	}

	//TODO the owner_flg need to be initialized or not ???
	public void initialUsrData(HashMap<String, Object> usr_data) {
		this.usr_id = (String) usr_data.get("usr_id");
		this.usr_pw = Encoder.sha1Text((String) usr_data.get("usr_pw"));
		if (usr_data.containsKey("owner_flg")) {
			this.owner_flg = (Boolean) usr_data.get("owner_flg");
		}
	}

	/**
	 * list format ( usr_id, usr_pw(plaintext), owner_flg )<br>
	 * 
	 * @param usr_data
	 */
	public void initialUsrData(List<Object> usr_data) {
		this.usr_id = (String) usr_data.get(0);
		this.usr_pw = Encoder.sha1Text((String) usr_data.get(1));
		if (usr_data.size() == 3) {
			this.owner_flg = (Boolean) usr_data.get(2);
		}
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public String getUsr_pw() {
		return usr_pw;
	}

	public void setUsr_pw(String usr_pw) {
		this.usr_pw = usr_pw;
	}

	public boolean isOwner_flg() {
		return owner_flg;
	}

	public void setOwner_flg(boolean owner_flg) {
		this.owner_flg = owner_flg;
	}

	@Override
	public List<?> toListForm() {
		List<String> usr = new ArrayList<String>();
		usr.add(this.usr_id);
		usr.add(this.usr_pw);
		usr.add(this.owner_flg.toString());
		return usr;
	}

	@Override
	public Map<?, ?> toMapForm() {
		Map<String, Object> usr = new HashMap<String, Object>(3,1);
		usr.put("usr_id", this.usr_id);
		usr.put("usr_pw", this.usr_pw);
		usr.put("owner_flg", this.owner_flg);
		return null;
	}

	@Override
	public String toStringForm() {
		return this.usr_id + "." + this.usr_pw + "." + this.owner_flg.toString();
	}
}
