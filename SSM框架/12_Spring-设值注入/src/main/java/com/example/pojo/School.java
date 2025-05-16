package com.example.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class School {

	private String name;
	private String address;
	private List<String> arrayList;
	private String[] arrays;
	private Map<String, Object> hashMap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getArrayList() {
		return arrayList;
	}

	public void setArrayList(List<String> arrayList) {
		this.arrayList = arrayList;
	}

	public String[] getArrays() {
		return arrays;
	}

	public void setArrays(String[] arrays) {
		this.arrays = arrays;
	}

	public Map<String, Object> getHashMap() {
		return hashMap;
	}

	public void setHashMap(Map<String, Object> hashMap) {
		this.hashMap = hashMap;
	}

	@Override
	public String toString() {
		return "School{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", arrayList=" + arrayList +
				", arrays=" + Arrays.toString(arrays) +
				", hashMap=" + hashMap +
				'}';
	}
}
