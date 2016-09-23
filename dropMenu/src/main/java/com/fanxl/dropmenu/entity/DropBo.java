package com.fanxl.dropmenu.entity;

/**
 * 菜单的实体类
 */
public class DropBo {

	private int type;
	private int id;
	private String value;
	private String text;
	private String name;

	public DropBo() {
	}

	public DropBo(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
