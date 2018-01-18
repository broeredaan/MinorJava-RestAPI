# boot
Spring boot start application (HS-Stenden)




# Calls (/v1/{call}):		Method:	Params:

user						GET			userToken				Get user by the userToken. Returns 401 if the token isn't the latest of that user.
user/login					GET			mail, password			Check password and return whether the user is an admin and the new userToken. 
																Returns 401 if the password doesn't match.
user/create					PUT			name, mail, isAdmin,	Adds a new user to the database.
										password, language
template					GET			userToken				Gets all templates from a user
template/single				GET			userToken, id			Gets a template by id
template/create				PUT			userToken, name,		Creates a new template for the user
										gradeDeviation, 
										isCommentNeeded
template/delete				DELETE		userToken, templateId	Deletes a single template
group						GET			userToken				Returns all groups for a user
group/single				GET			userToken, id			Returns a single group
group/create				PUT			userToken, templateId,	Creates a new group in the selected template
										name, deadline, 
										groupGrade
group/addmember				PUT			userToken, groupId,		Adds a new member to a group
										name, email, token
rate/start					GET			token					Returns all rating info
rate/finish					POST		To be continued...