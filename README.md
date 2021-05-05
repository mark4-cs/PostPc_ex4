============ What would you change in the code in order to let the service run for =================
============ maximum 200ms in tests environments, but continue to run for 20sec max ================
============ in the real app (production environment)? =============================================

A simple solution is to create a method in "MainActivity.java" to change the running mode.
That is, we will have default variable for time limit set to 20sec, and if we call the 
method "to_test_environment_mode()", the variable will change to 200ms. Also, we will have a method
called "to_production_to_test_environment_mode_mode()" that sets the time limit to 20sec.
We can pass the time limit in the Intent from the mainActivity to the service. 
For each test, after creating MainActivity object we will call the method "to_test_environment_mode()".


I pledge the highest level of ethical principles in support of academic excellence.  
I ensure that all of my work reflects my own abilities and not those of someone else.