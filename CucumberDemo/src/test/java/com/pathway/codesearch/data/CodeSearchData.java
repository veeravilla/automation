package com.pathway.codesearch.data;

import java.util.Arrays;
import java.util.List;

public class CodeSearchData {
	
	private String inputText;
	
	private String icdList;

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public List<String> getIcdList() {
		return Arrays.asList(icdList.split(","));
	}

	public void setIcdList(String icdList) {
		this.icdList = icdList;
	}
	
	public CodeSearchData() {
		//no-op
	}
	
	public CodeSearchData(String inputText, String icdList) {
		this.inputText = inputText;
		this.icdList = icdList;
	}

}
