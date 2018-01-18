# boot
Spring boot start application (HS-Stenden)




# Calls (/v1/{call}):		


| Call | Method | Params | Description |
| --- | --- | --- | --- |
| user	| GET | userToken | Get user by the userToken. Returns 401 if the token isn't the latest of that user. |
| user/login	| GET		| mail, password	| Check password and return whether the user is an admin and the new userToken. Returns 401 if the password doesn't match. |
| user/create		| PUT			| name, mail, isAdmin, password, language |	Adds a new user to the database. |
| template | GET | userToken | Gets all templates from a user |
| template/single | GET | userToken, id | Gets a template by id|
| template/create | PUT | userToken, name, gradeDeviation, isCommentNeeded | Creates a new template for the user |
| template/delete | DELETE | userToken, templateId | Deletes a single template |
| group	 | GET | userToken | Returns all groups for a user |
| group/single | GET | userToken, id | Returns a single group |
| group/create | PUT | userToken, templateId, name, deadline, groupGrade | Creates a new group in the selected template|
| group/addmember | PUT	| userToken, groupId, name, email, token | Adds a new member to a group |
| rate/start | GET | token | Returns all rating info |
| rate/finish |	POST	 | To be continued... |
