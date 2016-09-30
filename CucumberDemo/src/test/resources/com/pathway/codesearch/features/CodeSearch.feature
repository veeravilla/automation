Feature:Test Code Search Tool

@CodeSearchTool
Scenario: Open Code Search Tool
	Given username "veera_reddy@pathway.com.ragile" and password "SFEmp#7141"
	Then select "Code Search" from left navigation
	Then "Code Search" should open in new Window

@DoCodeSearch
Scenario: Do Code Search
	Given search text "DM 2"
	Then  expected ICD codes are "E11" and "E13" in search results.

@CreateCodeSearchObject
Scenario: Search With DataTable

#Add matching Java Object to represent below data.
	Given Code Search input data:
	| inputText | icdList |
	| DM 2 | E11,E13 |
	| Atherosclerosis of Native Arteries  | I70 |
	| Type 2 Diabetes Mellitus  | E11 |
	| Chronic Kidney Disease  | N18,I12,I13,D63,E09,E08,E10,E11,E13 |
	Then verify all combinations of searchInput