package com.getposted.model;

public class Skill{

	private int id=0;
	private String skill=null;

	public Skill(){}

	public Skill(int id, String skill){
		this.id = id;
		this.skill = skill;
	}

	public int getId(){
		return this.id;
	}
	public String getSkill(){
		return this.skill;
	}
	public void setId(int value){
		this.id = value < 0 ? this.id: value;
	}
	public void setSkill(String value){
		this.skill = value.length() <= 1? this.skill : value;
	}
}
