Spring Secirity Application

User(name,password) is the model/entity class.
Userdetails is credentials are saved in a MySQL database after encoding is done.
BCryptPasswordEncoder(12) is used for encoding password and then saved inside database.
Inorder to decode & varify user credentials we use DaoAuthenticationProvider class & then gave them access to any endpoint.
Only when user is registering for the first time then security authentication is removed. we used "/add-user" endpoint for this.
